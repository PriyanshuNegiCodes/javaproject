package JukeBox.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    public Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("driver loaded");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox", "root", "Negi@123");
//            System.out.println("connection established");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
