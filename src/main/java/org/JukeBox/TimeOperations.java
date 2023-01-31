package org.JukeBox;

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
}
