package cafe.management.system;

import dao.*;
import model.*;

import java.io.IOException;
import java.sql.*;


public class CafeManagementSystem {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Department department=new Department(10);

        CustomerManagement customerManagement=new CustomerManagement();

        DrinkCategory drinkCategory=new DrinkCategory();

        ImportManagement importManagement=new ImportManagement();

        String query="select * from Drink";
        ResultSet resultSet=MySQLOperation.dataFromTable(query);

        if(resultSet.next()){
            do{
                drinkCategory.drinkList.add(new Drink(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3)));
            }while(resultSet.next());
        }

        query="select * from Full_time";
        resultSet=MySQLOperation.dataFromTable(query);

        if(resultSet.next()){
            do{
                department.fullTimes.add(new FullTime(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7)));
            }while(resultSet.next());
        }

        query="select * from Part_time";
        resultSet=MySQLOperation.dataFromTable(query);

        if(resultSet.next()){
            do{
                department.partTimes.add(new PartTime(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getInt(6),resultSet.getInt(7)));
            }while(resultSet.next());
        }

        query="select * from Coupon";
        resultSet=MySQLOperation.dataFromTable(query);

        if(resultSet.next()){
            do{
                importManagement.addCoupon(resultSet.getString(1),resultSet.getDouble(2));
            }while(resultSet.next());
        }

        query="select * from Customer";
        resultSet=MySQLOperation.dataFromTable(query);

        if(resultSet.next()){
            do{
                customerManagement.customerList.add(new Customer(resultSet.getString(1),resultSet.getInt(2),resultSet.getDouble(3)));
            }while(resultSet.next());
        }

        //Problem in database here : (too many sale)
        query="select * from Sale";
        resultSet=MySQLOperation.dataFromTable(query);

        if(resultSet.next()){
            do{
                drinkCategory.saleList.add(new Sale(new Month(resultSet.getInt(1),resultSet.getInt(2)),resultSet.getDouble(3)));
            }while(resultSet.next());
        }

        OptionMenu menu=new OptionMenu();

        menu.getLogin(department,customerManagement,drinkCategory,importManagement);

    }
}
