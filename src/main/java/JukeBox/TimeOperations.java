package JukeBox;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class TimeOperations {
    Scanner sc=new Scanner(System.in);

    public String timer(long time){
        long elapsedTime = time/ 1000;
        int minutes = (int) (elapsedTime / 60000);
        int seconds = (int) ((elapsedTime % 60000) / 1000);
        return minutes+":"+String.format("%02d", seconds);
    }
    public String timer1(long time){
        long elapsedTime = time/ 1000;
        int minutes = (int) (elapsedTime / 60);
        int seconds = (int) (elapsedTime % 60);
        return minutes+":"+String.format("%02d", seconds);
    }
    public long jump(){
        System.out.println("Enter the minutes you want to jump to:");
        long min=sc.nextLong();
        System.out.println("Enter the seconds you want to jump to:");
        long seconds=sc.nextLong();
        return (min*60+seconds)*1000000;
    }
    public long calculateSongLength(String path){
        long frameSizeInMilliseconds;
          try {
              File file=new File(path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip=AudioSystem.getClip();
        clip.open(audioInputStream);
        long frameSize=clip.getFrameLength();
        frameSizeInMilliseconds= (long)((frameSize*1000)/clip.getFormat().getSampleRate());
        System.out.println("Frame length "+clip.getFrameLength());
        System.out.println("This is sample rate"+clip.getFormat().getSampleRate());
        System.out.println("Song inserted");
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        throw new RuntimeException(e);
    }
          return frameSizeInMilliseconds;
    }
}
