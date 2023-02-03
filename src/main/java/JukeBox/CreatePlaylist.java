package JukeBox;
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
    ResultSet resultSet;
    // Check if the playlist is there or not  with the help of play list id
    public String createPlaylist(int id) {
//        playlistUserLogImp.displayLog(id);
        System.out.println("Enter the name of the Playlist:");
        String playList = sc.next();
        while (!playlistUserLogImp.checkLogData(playList)) {
            playList="";
            System.out.println("The playlist with the name "+playList+" already exists");
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

    //   insert data into the play list
    public void InsertSongInPlaylist(int playlistId, int userId) {
        int songID=0;
        boolean flag;
        int input;
        Display display=new Display();
        display.showCatalog();
        do {
            flag=true;
            while (flag){
                System.out.println("Enter the song ID:");
                songID= sc.nextInt();
                if(!playlistUserLogImp.checkSongID(songID)){
                    flag=false;
                }else {
                    System.out.println("Id do not exists");
                }
            }
            try {
                st = getConnection().createStatement();
                resultSet = st.executeQuery("SELECT LanguageId, AlbumId, " +
                        "ArtistId, GenreId FROM song WHERE Sno = " + songID + ";");
                while (resultSet.next()) {
                    insertIntoPlaylistTable(playlistId, userId, songID );
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter 1 if you want to insert other song else 0");
            input = sc.nextInt();
        } while (input == 1);
    }
    public void insertIntoPlaylistTable( int playlistId, int userId, int songID){

        try {
            st = getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("SELECT LanguageId, AlbumId, " +
                    "ArtistId, GenreId FROM song WHERE Sno = " + songID + ";");
            if (resultSet.next()) {
                st.executeUpdate("INSERT INTO Playlist (PlaylistId, PlaylistName, UserId, LanguageId, AlbumId, ArtistId, GenreId, SongId) \n" +
                        "VALUES (" + playlistId + ", '" + playlistUserLogImp.getPlaylistName(playlistId) + "', " + userId + ", " + resultSet.getInt(1) + ", " +
                        "" + resultSet.getInt(2) + ", " + resultSet.getInt(3) + ", " + resultSet.getInt(4) + ", " + songID + ");");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}