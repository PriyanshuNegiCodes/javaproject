package org.JukeBox;

import java.util.Scanner;

public class Controller {
    public  void controller(){
        InsertSongs insertSongs=new InsertSongs();
        PlayerInput playerInput=new PlayerInput();
        Scanner sc=new Scanner(System.in);

        Connector connector=new Connector();
        connector.getConnection();
        CreateCateogry createCateogry=new CreateCateogry();
        System.out.println("1. Insert the songs in the catalog\n2. Create Catalog\n3. Create Playlist\n4. Insert Song in Playlist" +
                "\n5. Play songs from catalog or playlist\n6. Drop Playlist\n7. See all songs in the playlist or catalog");
        int input =sc.nextInt();
        switch (input){
            case 1: //Add song to a list
                System.out.println(insertSongs.insertSongs(insertSongs.insertSong()));//Add song to the database of catalog
                break;
            case 2: createCateogry.category();
                break;
            case 3: {
//                songsOperations.showCatalog();
                        createCateogry.createPlaylist();
                    }
                break;
            case 4:insertSongs.InsertSongInPlaylist();
                break;
            case 5:playerInput.songsSequence(playerInput.playerInput());
                break;
            case 6:
                break;
                default:{
                System.out.println("entered input is wrong");
            }
        }
    }
    public static void main(String[] args) {
        Controller obj= new Controller();
        obj.controller();
    }
}
