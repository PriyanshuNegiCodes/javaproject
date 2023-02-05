package JukeBox.Database;

import JukeBox.connector.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//This table is for inserting data in the table of langauge,album, artist, genre and verifiying from it as well
public class TablesImp extends Connector {
    static Scanner sc=new Scanner(System.in);
    Statement st;
    //display the table
    public void showDetailsTable(String table){
          try {
              st=super.getConnection().createStatement();
              ResultSet resultSet=st.executeQuery("select * from "+table+";");
              System.out.println("+-------+----------------+");
              System.out.printf("| %5s | %10s |\n", table + "id", table + "Name");
              System.out.println("+-------+----------------+");
              while(resultSet.next()){
                  System.out.printf("| %5d | %10s |\n", resultSet.getInt(1), resultSet.getString(2));
                  System.out.println("+-------+----------------+");              }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("____________________________");
    }
    //insert data in the table
    public String Table(String tableName){
        System.out.println("Enter the id:");
        int id=sc.nextInt();
        if(!checkData(id, tableName)){
            return "Id already exists.. Kindly provide the valid id";
        }
        System.out.println("Enter the name:");
        String name=sc.next();
        try {
            st = getConnection().createStatement();
            st.executeUpdate("INSERT INTO "+tableName+"("+tableName+"Name) VALUES( '"+name+"');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Data verified from the table "+tableName+"";
    }
//check if  id and name is exists in the category table before inserting them in the catalog
    public boolean checkData(int id, String tableName) {
        try {
            st=super.getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("select* from "+tableName+";");
            while(resultSet.next()){
                if(id==resultSet.getInt(1)){
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
