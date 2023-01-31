package JukeBox;
import java.util.Scanner;

public class Controller {
    public  void controller(){
        InsertSongs insertSongs=new InsertSongs();
        PlayerInput playerInput=new PlayerInput();
        TablesImp tables=new TablesImp();
        Scanner sc=new Scanner(System.in);

        Connector connector=new Connector();
        connector.getConnection();
        CreateCateogry createCateogry=new CreateCateogry();
        System.out.println("1. Insert the songs in the catalog\n2. Create Catalog\n3. Create Playlist\n4. Insert Song in Playlist" +
                "\n5. Play songs from catalog or playlist\n6. Drop Playlist\n7. See all songs in the playlist or catalog\n8 Insert data into album, genre, or artist table");
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
            case 4:
//                insertSongs.InsertSongInPlaylist();
                break;
//            case 5:playerInput.songsSequence(playerInput.playerInput());
//                break;
            case 6:
                break;
            case 7:{
                System.out.println("1. Insert into album\n2. Insert into artist\n3. Insert into genre");
                int value= sc.nextInt();
                String Name="";
                switch (value){
                    case 1: Name=Name.concat("album");
                        break;
                    case 2: Name=Name.concat("artist");
                        break;
                    case 3: Name=Name.concat("genre");
                        break;
                    default:
                        System.out.println("invalid input for the table selection");
                }
                System.out.println("The data is inserted in the "+Name+" table: "+tables.Table(Name));;
            }
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
