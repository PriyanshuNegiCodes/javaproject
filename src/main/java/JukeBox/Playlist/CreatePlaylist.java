package JukeBox.Playlist;
import JukeBox.Credentials.PlaylistUserLogImp;
import JukeBox.connector.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreatePlaylist extends Connector {
    static PlaylistUserLogImp playlistUserLogImp = new PlaylistUserLogImp();
    static Statement st;
    static Scanner sc = new Scanner(System.in);
    static CreatePlaylist createPlaylist=new CreatePlaylist();
    //create playlist of the user
    public String createPlaylist(int id) {
        System.out.println("Enter the name of the Playlist:");
        String playList = sc.next();
        // check if the playlist with the same name is already there for the given user
        while (!playlistUserLogImp.checkLogData(playList, id)) {
            System.out.println("The playlist with the name "+playList+" already exists. Enter the name again");
            playList="";
            playList = sc.next();
        }
        try {
            st = getConnection().createStatement();
            st.execute("insert into Log(UserId, PlaylistName) values(" + id + ", '" + playList + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playList;
    }

    //   insert data into the play list ie, songId and the playlistId
    public void InsertSongInPlaylist(int playlistId) {
        int songID=0;
        boolean flag;
        int input;
        Display display=new Display();
        display.showCatalog();
        do {
            flag=true;
            //Check if the song Id is already there or Not in the playlist
            while (flag){
                System.out.println("Enter the song ID:");
                songID= sc.nextInt();
                if(!playlistUserLogImp.checkSongID(songID)){
                    if(createPlaylist.verifySongInPlaylist(playlistId,songID)){
                        flag=false;
                    }
                }else {
                    System.out.println("Id do not exists or already there in the playlist ");
                }
            }
            try {
                st = getConnection().createStatement();
                st.executeUpdate("INSERT INTO PLAYLIST(PlaylistId, SongID) " +
                        "VALUES("+playlistId+", "+songID+");");
                } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter 1 if you want to insert other song else 0");
            input = sc.nextInt();
        } while (input == 1);
    }
    //Check if the song is there already in the catalog or not:
    public boolean verifySongInPlaylist(int playlistId, int songId){
        try {
            st = getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("SELECT *FROM PLAYLIST WHERE PLAYLISTID ="+playlistId+" AND SONGID ="+songId+";");
            while (resultSet.next()){
                if((resultSet.getInt(2)==playlistId)&&(resultSet.getInt(3)==songId)){
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
