package org.JukeBox;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertSongs extends CreateCateogry {
    Scanner sc=new Scanner(System.in);

    public String insertSongs(List<Songs> songsList)  {

        try {
            System.out.println("Enter the name of the Catalog:");
            st = getConnection().createStatement();
            for (Songs data : songsList) {
                st.executeUpdate("INSERT INTO Catalog (SiNo, Album, Artist, Genre, Name, Path) VALUES (" + data.getIndex() + ", '" + data.getAlbum() + "','" + data.getArtist() + "', '" + data.getGenre() + "', '" + data.getName() + "', '" + data.getPath() + "');");
            }
            System.out.println("Song inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Song Inserted in the Catalog";
    }
    public List<Songs> insertSong(){
        List<Songs> list=new ArrayList<>();
        int input;
        do{
            System.out.println("Enter the index of the song");
            int index=sc.nextInt();
            System.out.println("Enter the Album of the song");
            String Album= sc.next();
            System.out.println("Enter the Genre of the Song");
            String Genre= sc.next();
            System.out.println("Enter the Artist of the Song");
            String Artist= sc.next();
            System.out.println("Enter the Name of the Song");
            String Name= sc.next();
            String commonLocation="C:\\Users\\admin\\Desktop\\coding\\Java\\JavaProject\\"+Name+".wav";
            commonLocation=commonLocation.replace("\\","/");

            Songs songs=new Songs(index, Album ,Genre, Artist, Name, commonLocation);

            list.add(songs);
            System.out.println("Press 1 if you want to insert more Songs else 0");
            input= sc.nextInt();
        }while (input==1);
        list.forEach(s-> System.out.println(s.getName()));
        return list;
    }
    public void InsertSongInPlaylist(){
        PlayerInput obj =new PlayerInput();
        TimeOperations timeOperations=new TimeOperations();
        String duration;
        try {
            System.out.println("Enter the name of the Playlist:");
            String table= sc.next();
            System.out.println("Enter the Name of the song you want to add to the playlist");
            String Name = sc.next();
            st=getConnection().createStatement();
            for(Songs in: obj.playerInput()){
                if(Name.equals(in.getName())){

                    File file=new File(in.getPath());
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                    Clip clip=AudioSystem.getClip();
                    clip.open(audioInputStream);
                    long frameSize=clip.getFrameLength();
                    long frameSizeInMilliseconds= (long)((frameSize*1000)/clip.getFormat().getSampleRate());
                    System.out.println("Frame length "+clip.getFrameLength());
                    System.out.println("This is sample rate"+clip.getFormat().getSampleRate());
                    st.executeUpdate("insert into "+table+" values('"+in.getIndex()+"','"+in.getName()+"' ,'"+timeOperations.timer1(frameSizeInMilliseconds)+"', '"+in.getPath()+"');");
                }
            }
            System.out.println("Song inserted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
