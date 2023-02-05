package JukeBox.Credentials;
import JukeBox.connector.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImp extends Connector{
    static List<User> credential=new ArrayList<>();
    static Statement st;
    //Create the account of new user;
    public List<User> createAccount(){
        Scanner sc= new Scanner(System.in);
        System.out.println("{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}");
        System.out.println("\t\t\t\t=-+=-+=-+=-+=-+=-+=-+=-+=-+=WELCOME-+=-+=-+=-+=-+=-+=-+");
        System.out.println("{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}{{][}}");
        System.out.println("Enter your name");
        String UserName= sc.next();
        System.out.println("Enter your password");
        String password= sc.next();
        User data= new User(UserName, password);
        credential.add(data);
        return credential;
    }
    //Check if the user is there in the database or not;
    public int checkUser( String userName, String Password) {
         try {
            st=getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("select * from users;");
            while(resultSet.next()){
                if((resultSet.getString(2).equals(userName))
                        &&(resultSet.getString(3).equals(Password))){
                    return resultSet.getInt(1);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    //Insert the data of the new user in the playlist
    public String insertUser(List<User> insertData) {
        try {
            for(User in: insertData){
                st = getConnection().createStatement();
                st.executeUpdate(" INSERT INTO users (UserName, password)\n" +
                        "VALUES ('"+ in.getName()+"','"+in.getPassword()+"')");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Account Created";
    }

}
