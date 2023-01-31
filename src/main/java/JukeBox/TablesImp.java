package JukeBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TablesImp extends CreateCateogry {
    static Scanner sc=new Scanner(System.in);
    public String Table(String tableName){
        System.out.println("Enter the id:");
        int id=sc.nextInt();
        if(checkData(id, tableName)){
            return "Id already exists.. Kindly provide the valid id";
        }
        System.out.println("Enter the name:");
        String name=sc.next();
        try {
            st = getConnection().createStatement();
            st.executeUpdate("select * from "+tableName+";");
            st.executeUpdate("INSERT INTO "+tableName+"("+tableName+"Name) VALUES( '"+name+"');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Data Inserted in the "+tableName+"";
    }
    public boolean checkData(int id, String tableName) {
        try {
            st=super.getConnection().createStatement();
            System.out.println(tableName+"id\t"+tableName+"Name");
            ResultSet resultSet=st.executeQuery("select* from "+tableName+";");
            while(resultSet.next()){
//                System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2));
                if(id>resultSet.getInt(1)){
                    System.out.println("Kindly provide id that is more than"+resultSet.getInt(1));
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
