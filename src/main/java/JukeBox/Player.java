package JukeBox;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.List;


public class Player extends TimeOperations {
    int index = 0;

    public String playSong(List<Songs> list) {

        String input = "";
        String Song = "";
        while (index < list.size()) {
            input = "";
            Song = list.get(index).getPath();
            File file = new File(Song);
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                while (!input.equals("F")) {
                    System.out.println("P-Play Song\nS-Stop\nR-Reset\nJ-Jump\nF-Forward\nU Previous Song\n X Exit Player");
                    System.out.println("Enter your choice:");
                    input = sc.next();
                    switch (input) {
                        case "P":
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
                            clip.stop();
                            clip.close();
                            if (index > 0) {
                                index--;
                                playSong(list);
                            }
                            break;
                        case "J":
                            clip.stop();
                            clip.setMicrosecondPosition(super.jump());
                            clip.start();
                            timer(clip.getMicrosecondPosition());
                            break;
                        case "X": {
                            return "Player Closed Thanks for Playing";
                        }
                        default: {
                            if (input.equals("F")) {
                                System.out.println("Song Forwarded");
                            } else {
                                System.out.println("Invalid Response");
                            }
                        }
                    }
                    System.out.println(timer(clip.getMicrosecondPosition()));
                }
                clip.stop();
                clip.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            index++;
        }
        return "Playing Song";
    }
}