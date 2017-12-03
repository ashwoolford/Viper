
package db;

import java.sql.*;


public class Sqlite {
    
    
    
    StringBuffer str;
    Connection c = null;
    Statement statement = null;

    public Sqlite(){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ScoreDB.sqlite");
            System.out.println("DB connected!!");

        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    public StringBuffer getList() throws SQLException {
        this.statement = c.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM scores");
        str = new StringBuffer();
       
        while (rs.next()){
            int id = rs.getInt("id");
            int score = rs.getInt("scores");
            str.append(id);
            str.append(".  ");
            str.append(score);
            str.append('\n');

           
        }
        
        return str;
        
    }

    public void closeDatabase() throws SQLException {
        c.close();
    }

    public void insetIntoDB(String query){
        try{
            this.statement = c.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    


    
}
