package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/cs360";
            String user="root";
            String password="a1230123";
            connection= DriverManager.getConnection(url,user,password);

        }catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException occur");
        }
        catch(SQLException e){
            System.out.println("SQLException occur");
        }


        return connection;
    }
}
