package JukeBox;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//For doing various time calculations
public class TimeOperations {
    Scanner sc=new Scanner(System.in);

    public String timer(long time){
        long newTime = time/ 1000;
        int minutes = (int) (newTime / 60000);
        int seconds = (int) ((newTime % 60000) / 1000);
        return minutes+":"+String.format("%02d", seconds);
    }
    public String timer1(long time){
        long newTime = time/ 1000;
        int minutes = (int) (newTime / 60);
        int seconds = (int) (newTime % 60);
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
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        throw new RuntimeException(e);
    }
          return frameSizeInMilliseconds;
    }
}
