package JukeBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlayerInput extends CreateCateogry{
    List<Songs> list=new ArrayList<>();

//    public List<Songs> playerInput() {
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Enter the name of the Catalog or Playlist:");
//        String table= sc.next();
//        try {
//            st=super.getConnection().createStatement();
//            ResultSet resultSet=st.executeQuery("select * from "+table+";");
//            while(resultSet.next()){
//                if(table.equals("song")){
//                    Songs songs=new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
//                    list.add(songs);
//                }else {
//                    Songs songs1=new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4));
//                    list.add(songs1);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
    public void songsSequence(List<Songs>list){
        Player player=new Player();
        Scanner sc=new Scanner(System.in);
        System.out.println("1. Play in sequence\n2. Play in shuffle");
        int input=sc.nextInt();
        if(input==1){
            player.playSong(list);
        }else if(input==2){
            Collections.shuffle(list);
            player.playSong(list);
        }else {
            System.out.println("Invalid Input");
        }
    }
}
