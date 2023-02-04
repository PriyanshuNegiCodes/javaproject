package JukeBox;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.List;


public class Player extends TimeOperations {
    static PlayerInput playerInput= new PlayerInput();
    int index = 0;
    static Player player=new Player();
    boolean flag=true;
    String input="";
    String Song;
    File file;
    public void songDetails(List<Music> list, Clip clip){
        System.out.println( list.get(index).getAlbumName()+"     |   "+list.get(index).getArtistName()+"     | "
                +list.get(index).getGenreName()+"      |   "+list.get(index).getSongName()+"   |   "
                +list.get(index).getDuration()+"   |    "+timer(clip.getMicrosecondPosition()));
    }
    public void playSong1(List<Music> list){
        input = "";
        Song = list.get(index).getPath();
        file = new File(Song);
        boolean flag=true;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            while (flag){
                System.out.println("\033[31m" + "--------------------------------------------------------------------------------------------" + "\033[0m");
                System.out.println("| P - Play Song S - Stop R - Reset J  X - Exit Player |");
                System.out.println("\033[33m"+ "Album Name  | ArtistName | GenreName | SongName |  Duration|  Current Time"+ "\033[0m");
                player.songDetails(list, clip);
                System.out.println("\033[31m" + "--------------------------------------------------------------------------------------------" + "\033[0m");
                input="";
                input=sc.next();
                switch(input){
                    case "P": clip.start();

                        break;
                    case "S": clip.stop();
                        timer(clip.getMicrosecondPosition());
                        break;
                    case "R": clip.setMicrosecondPosition(0);
                        timer(clip.getMicrosecondPosition());
                        break;
                    case "J": clip.stop();
                        clip.setMicrosecondPosition(super.jump());
                        clip.start();
                        timer(clip.getMicrosecondPosition());
                        break;
                    case "X":clip.stop();
                        clip.close();
                            flag=false;
                            playerInput.songsSequence(list);
                        break;
                    default:
                        System.out.println("Wrong input try again");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void playSong(List<Music> list) {

        while (index < list.size()&&flag) {

            input = "";
            Song = list.get(index).getPath();

            file = new File(Song);
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);


                while (!input.equals("F")&&(!input.equals("X"))) {
                    if(list.size()>1){
                        if(index==0){
                            System.out.println("|Next Song    :  "+list.get(index+1).getSongName());
                        }else {
                            System.out.println("|Previous Song:  "+list.get(index-1).getSongName());
                            if (index== list.size()-1){
                                System.out.println("|Next Song    :  "+list.get(0).getSongName());
                            }else {
                                System.out.println("|Next Song    :  "+list.get(index+1).getSongName());
                            }
                        }
                    }

                    System.out.println("\033[31m" + "--------------------------------------------------------------------------------------------" + "\033[0m");
                    System.out.println("| P - Play Song S - Stop R - Reset J - Jump F - Forward U - Previous Song X - Exit Player |");                    System.out.println("\033[33m"+ "Album Name  | ArtistName | GenreName | SongName |  Duration|  Current Time"+ "\033[0m");
                    player.songDetails(list, clip);
                    System.out.println("\033[31m" + "--------------------------------------------------------------------------------------------" + "\033[0m");
                    input="";
                    input = sc.next();
                    switch (input) {
                        case "P":
                            clip.stop();
                            clip.start();
                            timer(clip.getMicrosecondPosition());
                            break;
                        case "S":
                            clip.stop();
                            timer(clip.getMicrosecondPosition());
                            break;
                        case "R": {
                            clip.setMicrosecondPosition(0);
                        }
                        break;
                        case "U":
                            if(index==0) {
                                index=(list.size()-1);
                            } else if (index > 0) {
                                index--;
                            }
                            clip.stop();
                            clip.close();
                            playSong(list);

                            break;
                        case "J":
                            clip.stop();
                            clip.setMicrosecondPosition(super.jump());
                            clip.start();
                            timer(clip.getMicrosecondPosition());
                            break;
                        case "X": {
                            clip.stop();
                            clip.close();
                            playerInput.songsSequence(list);

                        }
                        default: {
                            if (input.equals("F")) {
                                break;
                            } else {
                                System.out.println("Invalid Response");
                            }
                        }
                    }
                }
                clip.stop();
                clip.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            index++;

            if(index==(list.size())){
                index=0;
                playSong(list);
            }
        }
    }
}
