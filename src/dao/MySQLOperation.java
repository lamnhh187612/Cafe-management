package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLOperation {
    public static void dataToTable(String query) throws SQLException, ClassNotFoundException {
        try{
            Connection connection= ConnectionProvider.getConnection();
            Statement statement=connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet dataFromTable(String query){
        ResultSet resultSet=null;
        try{
            Connection connection= ConnectionProvider.getConnection();
            Statement statement=connection.createStatement();
            resultSet=statement.executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
