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
//    static Connector connector=new Connector();
    static Statement st;
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
    public boolean checkUser(int id, String userName, String Password) {
         try {
            st=getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("select * from users;");
            while(resultSet.next()){
                if((resultSet.getInt(1)==id)&&(resultSet.getString(2).equals(userName))
                        &&(resultSet.getString(3).equals(Password))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public String insertUser(List<User> insertData) {
        UserImp userImp=new UserImp();
        try {
            for(User in: insertData){
                st = getConnection().createStatement();
                st.executeUpdate(" INSERT INTO users (UserName, password)\n" +
                        "VALUES ('"+ in.getName()+"','"+in.getPassword()+"')");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("+--------------------------------------------+");
        System.out.println("+-------Wecome Your UserID is "+(userImp.findID())+"---------------+");
        System.out.println("+--------------------------------------------+");
        return "Account Created";
    }
    public int findID() {
        int allocatedId=0;
        try {
            st=getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("select count(*) from users;");
            while(resultSet.next()){
                allocatedId=resultSet.getInt(1);
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allocatedId;
    }
}
