package JukeBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertSongs extends CreateCateogry {
    Scanner sc = new Scanner(System.in);
    TablesImp tablesImp=new TablesImp();

    public String insertSongs(List<Songs> songsList) {

        try {
            st = getConnection().createStatement();
            for (Songs data : songsList) {
                System.out.println("Reached here song list"+data);
            st.executeUpdate(" INSERT INTO song (LanguageId, AlbumId, ArtistId, GenreId, SongName, Path, Duration)\n" +
                    "VALUES ("+data.getLanguageId()+", "+data.getAlbumId()+", "+data.getArtistid()+", "+data.getGenreId()+", '"+data.getSongName()+"', '"+data.getPath()+"', '"+data.getDuration()+"')");
            }
            System.out.println("Song inserted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Song Inserted in the Catalog";
    }
    public List<Songs> insertSong(){
        List<Songs> list=new ArrayList<>();
        boolean flag=true;
        TimeOperations timeOperations=new TimeOperations();
        int input;
        do{;
            System.out.println("Enter the languageID");
            int languageId=sc.nextInt();
            while(!tablesImp.checkData(languageId, "language")){
                 System.out.println("Id already exists, enter the id again");
                languageId=sc.nextInt();
            }
            System.out.println("Enter the id of the artist");
            int artistId=sc.nextInt();
            while(!tablesImp.checkData(artistId, "artist")){
                System.out.println("Id already exists, enter the id again");
                artistId=sc.nextInt();
            }
            System.out.println("Enter the id of the Genre");
            int genreId=sc.nextInt();
            while(!tablesImp.checkData(genreId, "genre")){
                System.out.println("Id already exists, enter the id again");
                genreId=sc.nextInt();
            }
            System.out.println("Enter the id of the album");
            int albumId=sc.nextInt();
            while(!tablesImp.checkData(albumId, "album")){
                System.out.println("Id already exists, enter the id again");
                albumId=sc.nextInt();
            }
            System.out.println("Enter the name of the song");
            String SongName=sc.next();;
            String commonLocation="C:\\Users\\admin\\Desktop\\coding\\Java\\JavaProject\\"+SongName+".wav";
            long duration=timeOperations.calculateSongLength(commonLocation);
            commonLocation=commonLocation.replace("\\","/");
            Songs songs=new Songs(languageId, albumId, artistId, genreId, SongName, commonLocation, timeOperations.timer1(duration));

            list.add(songs);
            System.out.println("Press 1 if you want to insert more Songs else 0");
            input= sc.nextInt();
        }while (input==1);
        return list;
    }
}

//    public void InsertSongInPlaylist(){
//        PlayerInput obj =new PlayerInput();
//        TimeOperations timeOperations=new TimeOperations();
//
//        try {
//            System.out.println("Enter the name of the Playlist:");
//            String table= sc.next();
//            System.out.println("Enter the Sno of the song you want to add to the playlist");
//            st=getConnection().createStatement();
//            for(Songs in: obj.playerInput()){
//                File file=new File(in.getPath());
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
//                Clip clip=AudioSystem.getClip();
//                clip.open(audioInputStream);
//                long frameSize=clip.getFrameLength();
//                long frameSizeInMilliseconds= (long)((frameSize*1000)/clip.getFormat().getSampleRate());
//                System.out.println("Frame length "+clip.getFrameLength());
//                System.out.println("This is sample rate"+clip.getFormat().getSampleRate());
//                st.executeUpdate("insert into "+table+" values('"+in.getIndex()+"','"+in.getName()+"' ,'"+timeOperations.timer1(frameSizeInMilliseconds)+"', '"+in.getPath()+"');");
//
//            }
//            System.out.println("Song inserted");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }