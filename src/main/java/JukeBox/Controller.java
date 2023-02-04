package JukeBox;
import JukeBox.Credentials.PlaylistUserLogImp;
import JukeBox.Credentials.UserImp;
import JukeBox.Database.TablesImp;
import JukeBox.Playlist.CreatePlaylist;
import JukeBox.Playlist.Display;
import JukeBox.Playlist.InsertSongsInCatalog;
import JukeBox.Playlist.PlayerInput;
import JukeBox.admin.CredentialsValidatorImpl;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    static Controller obj=new Controller();
    static PlayerInput playerInput=new PlayerInput();
    static TablesImp tables=new TablesImp();
    static Scanner sc=new Scanner(System.in);
    static UserImp userImp=new UserImp();
    static Display display=new Display();
    static PlaylistUserLogImp playlistUserLogImp=new PlaylistUserLogImp();
    static CreatePlaylist createPlaylist = new CreatePlaylist();
    boolean login=true;
    int index=0;
    String temp="";
    static int value;
    static int existingUserID;
    static String adminid;
    static String key;
    public void createAccount()throws InputMismatchException{
        System.out.println(userImp.insertUser(userImp.createAccount())+ " Kindly Login with credentials");
        controller();
    }
    public void mainReturnMethod()throws InputMismatchException{
        if(existingUserID==0){
            obj.guestUser();
        }else {
            obj.existingUser();
        }
    }
    public void guestUser()throws InputMismatchException{
        System.out.println("1. Play Song\n2. Create Account\nAny Key to Exit");
        int guest = sc.nextInt();
        switch (guest) {
            case 1:
                System.out.println("1. Play all song from catalog\n2. Filter songs\n3. To return to main menu\nAny key to exit");
                value=sc.nextInt();
                switch (value){
                    case 1:playerInput.songsSequence(display.showCatalog());
                        break;
                    case 2:playerInput.FilterSongMethod();
                    break;
                    case 3: obj.guestUser();
                    break;
                    default:{
                        System.out.println("Thanks for visiting");
                        }
                    }
                    break;

            case 2: obj.createAccount();
                break;
            default:
                System.exit(0);
        }
    }
    public void existingUser( ) throws InputMismatchException{
        while (true){
            System.out.println("-------------------------------------------------");
            System.out.println("| Option | Description                        |");
            System.out.println("-------------------------------------------------");
            System.out.println("| 1      | Create Playlist                    |");
            System.out.println("| 2      | Play from your playLists           |");
            System.out.println("| 3      | Insert Song in Playlist            |");
            System.out.println("| 4      | Play songs from catalog            |");
            System.out.println("| 5      | Search Song in Catalog             |");
            System.out.println("| 6      | Exit the app                       |");
            System.out.println("-------------------------------------------------");
            int input = sc.nextInt();
            temp="";
            switch (input) {
                case 1:
                    temp=temp.concat(createPlaylist.createPlaylist(existingUserID));
                    System.out.println("Playlist Created as:"+temp);
                    break;
                case 2:
                    if(!playlistUserLogImp.showLog(existingUserID).isEmpty()){
                        playlistUserLogImp.displayLog(existingUserID);
                        login=true;
                        while (true){
                            System.out.println("(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)");
                            System.out.println("Enter the playlist Id you want to hear songs from\n"+
                                    "Press 0 to return to main menu");
                            System.out.println("(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)");
                            int playListId= sc.nextInt();
                            if(playlistUserLogImp.showLog(existingUserID).contains(playListId)){
                                playerInput.songsSequence(playerInput.userPlaylistSongs(playListId));
                            }else if(playListId==0){
                                obj.existingUser();
                            } else {
                                System.out.println("You entered the wrong input: Try again");
                            }
                        }
                    }else {
                        System.out.println("You Do not have playlist registered Please Create playlist from the main menu");
                    }
                    break;
                case 3:
                    display.showCatalog();
                    if(!playlistUserLogImp.showLog(existingUserID).isEmpty()){
                        login=true;
                        int playListID;
                        while (login){
                            playlistUserLogImp.displayLog(existingUserID);
                            System.out.println("=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_");
                            System.out.println("Enter the play list Id in which " +
                                    "you want to insert the songs\nFor Exit press 0");
                            System.out.println("=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_");

                            playListID=sc.nextInt();
                            if(playlistUserLogImp.showLog(existingUserID).contains(playListID)){
                                createPlaylist.InsertSongInPlaylist(playListID);
                                login=false;
                            }else if(playListID==0){
                                login=false;
                            }
                        }
                    }else {
                        System.out.println(")()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()(" +
                                "\nYou Do not have playlist registered Please Create playlist from the main menu\n" +
                                ")()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()(");
                    }
                    break;
                case 4:
                    playerInput.songsSequence(display.showCatalog());
                    break;
                case 5:playerInput.FilterSongMethod();
                    break;
                case 6:
                    System.out.println("Thanks for visiting");
                    System.exit(0);
                    break;
                default: {
                    System.out.println("entered input is wrong");
                }
            }
        }
    }
    static int adminInput;
    public void admin() throws FileNotFoundException {
        switch (adminInput){
            case 1:
                tables.showDetailsTable("artist");
                tables.showDetailsTable("album");
                tables.showDetailsTable("genre");
                tables.showDetailsTable("language");
                System.out.println("KINDLY ENTER THE ID'S FROM EXISTING DATABASE OF " +
                        "ALBUM, ARTIST, LANGUAGE, GENRE");
                InsertSongsInCatalog insertSongs=new InsertSongsInCatalog();
                System.out.println(insertSongs.insertSongs(insertSongs.insertSong()));//Add song to the database of catalog;

                break;
            case 2: {

                while (true){
                    System.out.println("1. Insert into album\n2. Insert into artist\n3. Insert into genre\n4. Insert into language" +
                            "\n5. Go to main Menu\n6. To exit ");
                    temp="";
                    adminInput = sc.nextInt();
                    switch (adminInput) {
                        case 1:
                            temp = temp.concat("album");
                            break;
                        case 2:
                            temp = temp.concat("artist");
                            break;
                        case 3:
                            temp = temp.concat("genre");
                            break;
                        case 4:
                            temp = temp.concat("language");
                            break;
                        case 5:obj.admin();
                        break;
                        case 6: System.exit(0);
                        default:
                            System.out.println("invalid input for the table selection");
                    }
                    tables.showDetailsTable(temp);
                    System.out.println( tables.Table(temp));
                    tables.showDetailsTable(temp);
                }
            }
        }
    }
        public  void controller()throws InputMismatchException{
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
            System.out.println("1. Guest User\n2. Existing User\n3. Admin Login\n Any key to exit");
            System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
            int level=sc.nextInt();
                switch (level) {
                    case 1: obj.guestUser();
                    break;
                    case 2: {
                            while(login){

                                System.out.println("Enter the User Name");
                                String userName=sc.next();
                                System.out.println("Enter the password");
                                String password=sc.next();
                                existingUserID=userImp.checkUser( userName, password);
                                if(existingUserID>0){
                                    obj.existingUser();
                                }else {
                                    index++;
                                    System.out.println("Login Details are Wrong. You have "+ (3-index)+" attempts left");
                                    if(index==3){
                                        System.out.println("Kindly try login in after sometime");
                                        login=false;
                                    }
                                }
                            }
                        }
                case 3:{
                    System.out.println("-------------------------------------------------");
                    System.out.println("| Option | Description                        |");
                    System.out.println("| 1      | Insert song in the catalog         |");
                    System.out.println("| 2      | Insert new data in Album, Artist   |");
                    System.out.println("|          Genre, Language                    |");
                    System.out.println("| Press any number for Main Menu              |");
                    System.out.println("-------------------------------------------------");
                    adminInput = sc.nextInt();
                    CredentialsValidatorImpl credentialsValidator=new CredentialsValidatorImpl();
                    System.out.println("Enter the adminID");
                        adminid=sc.next();
                        System.out.println("Enter the password");
                        key= sc.next();
                        if(credentialsValidator.validateUser(adminid, key)){
                            try{
                                obj.admin();
                            } catch (FileNotFoundException x) {
                                System.out.println("File was not found");
                            } catch (RuntimeException x) {
                                if (x.getCause() instanceof FileNotFoundException) {
                                    System.out.println("File was not found");
                                } else {
                                    throw x;
                                }
                            }
                        } else {
                            System.out.println("Unauthorized admin login:");
                        }
                    }
                break;
                case 4: System.exit(1);
                break;
                default:{
                    System.out.println("Thanks for visiting Happy Listening");
                }
        }
    }
    public static void main(String[] args)  {
        System.out.println("+---------------------------------------+");
        System.out.println("|                                       |");
        System.out.println("|           NIIT JUKE BOX               |");
        System.out.println("|                                       |");
        System.out.println("+---------------------------------------+");
        Controller obj= new Controller();
        try{
            obj.controller();
        }catch ( InputMismatchException e) {
            System.out.println("Error Invalid Input Exiting the app");
        }
    }
}
