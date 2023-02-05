package JukeBox.Credentials;
import JukeBox.connector.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistUserLogImp extends  Connector {
    static Statement st;
    static ResultSet resultSet;
    public List<Integer> showLog(int userId){
        List<Integer> playlistIdData=new ArrayList<>();
        //Method to get the Name of the playlist
        try {
            st=super.getConnection().createStatement();
            resultSet= st.executeQuery("select *from Log where userid="+userId+";");

            while (resultSet.next()){
                if(resultSet.getInt(3)==userId){
                    playlistIdData.add(resultSet.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistIdData;
    }

    //Method to display the playlist id and Names;
    public void displayLog(int userId){
        try {
            st=super.getConnection().createStatement();
            resultSet= st.executeQuery("select *from Log where userid="+userId+";");
            System.out.println("+----------------------------------------------+");
            System.out.println("| PlaylistId | PlaylistName     | Userid     |");
            System.out.println("+----------------------------------------------+");

            while (resultSet.next()){
                if(resultSet.getInt(3)==userId){
                    System.out.printf("| %-12d | %-12s | %-12d |\n", resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                }
            }
            System.out.println("+----------------------------------------------+");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Method to check if the table already exist in the database or not
    public boolean checkLogData( String tableName, int id) {
        try {
            st=super.getConnection().createStatement();
            resultSet=st.executeQuery("select* from Log where userid="+id+";");
            while(resultSet.next()){
                if(resultSet.getString(2).equalsIgnoreCase(tableName)&&resultSet.getInt(3)==id){
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

//    public String getPlaylistName(int id) {
//
//        String tableName="";
//        try {
//            st=super.getConnection().createStatement();
//            resultSet=st.executeQuery("select* from Log;");
//            while(resultSet.next()){
//                if(resultSet.getInt(1)==id){
//                    return resultSet.getString(2);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return tableName;
//    }
    //TESTING PURPOSE CODE TO ITERATE THE TABLE OF SONG
    public boolean checkSongID(int Id) {
        try {
            st=super.getConnection().createStatement();
            resultSet=st.executeQuery("select * from song;");
            while(resultSet.next()){
                if (resultSet.getInt(1)==Id){
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

