package JukeBox;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateCateogry extends Connector {
        Statement st;
        public void category() {
            System.out.println("Enter the name of the Catalog:");
            try {
                st=getConnection().createStatement();
                st.execute("create table Catalog(SiNo int primary key, Album varchar(100), Artist varchar(100), Genre varchar(100), Name varchar(100), Path varchar(1000));");
                System.out.println("Catalog Created");
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPlaylist() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the name of the Playlist:");
        String table= sc.next();
        try {
            st=getConnection().createStatement();
            st.execute("create table "+table+"(SiNo int, Name varchar(100), Duration varchar(100), Path varchar(1000), PRIMARY KEY (Name), FOREIGN KEY (SiNo) REFERENCES Catalog(SiNo));");
            System.out.println("Playlist Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
