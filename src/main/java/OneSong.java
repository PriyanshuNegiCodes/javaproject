import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.sql.*;
import java.util.Scanner;
public class OneSong {
    static OneSong obj=new OneSong();
    Connection connection=null;
    Scanner sc=new Scanner(System.in);
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OneSong", "root", "Negi@123");
            System.out.println("connection established");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void playSong(){
    String Song="C:\\Users\\admin\\Desktop\\coding\\Java\\Java project\\Kedarnath.wav";
    File file=new File(Song);
        try {
            AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(file);
            Clip clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            String input="";
            while(!input.equals("Q")){
                System.out.println("P-Play Song, S-Stop, R-Reset, Q-Quit, J-Jump to a specific time");
                System.out.println("Enter your choice:");
                input=sc.next();
                switch (input){
                    case "P": clip.start();
                    obj.timer(clip.getMicrosecondPosition());
                    break;
                    case "S": clip.stop();
                    obj.timer(clip.getMicrosecondPosition());
                    break;
                    case "R":{ clip.setMicrosecondPosition(0);}
                    break;
                    case "Q":clip.close();
                    break;
                    case "J":clip.stop();
                        clip.setMicrosecondPosition(obj.jump());
                        clip.start();
                        obj.timer(clip.getMicrosecondPosition());
                    break;
                    default: System.out.println("Invalid Response");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void timer(long time){
        long elapsedTime = time/ 1000;
        int minutes = (int) (elapsedTime / 60000);
        int seconds = (int) ((elapsedTime % 60000) / 1000);
        System.out.println("Time: "+minutes+":"+seconds);
    }
    public long jump(){
        System.out.println("Enter the minutes you want to jump to:");
        long min=sc.nextLong();
        System.out.println("Enter the seconds you want to jump to:");
        long seconds=sc.nextLong();
        return (min*60+seconds)*1000000;

    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String Name= sc.next();
        String commonLocation="C:\\Users\\admin\\Desktop\\coding\\Java\\JavaProject\\"+Name+".wav";
        commonLocation=commonLocation.replace("\\","/");
        System.out.println(commonLocation);

    }
}
