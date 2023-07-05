package dao;

import javax.swing.JOptionPane;

public class Tables {
    public static void main(String[] args) throws Exception{
        try{
            MySQLOperation.dataToTable("create database cs360");
            MySQLOperation.dataToTable("use cs360");
            MySQLOperation.dataToTable("create table Part_time(ID varchar(20),Name varchar(20),Age int, Pay_per_shift double, Time double)");
            MySQLOperation.dataToTable("insert into Part_time values\n" +
                    "(\"P101231\",\"Jimmy\",35,15,100,8123,012345),\n" +
                    "(\"P101232\",\"Shane\",35,15,200,5678,567890),\n" +
                    "(\"P101233\",\"Marry\",35,16,150,3323,360365),\n" +
                    "(\"P101234\",\"Sarah\",35,17,100,2241,250255)");
            MySQLOperation.dataToTable("create table Full_time(ID varchar(20),Name varchar(20),Age int, Base_salary double, Day_off int)");
            MySQLOperation.dataToTable("insert into Full_time values\n" +
                    "(\"P101235\",\"James\",36,3000,1,3456,345678),\n" +
                    "(\"P101236\",\"Jennifer\",25,2500,2,7658,847563),\n" +
                    "(\"P101237\",\"Robert\",18,2500,3,7483,458429),\n" +
                    "(\"P101238\",\"john\",23,2500,1,3423,324234)");
            MySQLOperation.dataToTable("create table Customer(Name varchar(20),Telephone int,Purchased double);");
            MySQLOperation.dataToTable("insert into Customer values \n" +
                    "(\"Micheal\",01292688283,1600.8),\n" +
                    "(\"Johnny Depp\",0982342334,534),\n" +
                    "(\"Amber Heard\",0971231232,234)");
            MySQLOperation.dataToTable("create table Coupon(Name varchar(40),Cost double)");
            MySQLOperation.dataToTable("insert into Coupon values\n" +
                    "(\"Coffee\",4000),\n" +
                    "(\"Coconut juice\",3750),\n" +
                    "(\"Lemon\",500),\n" +
                    "(\"Milk\",2600),\n" +
                    "(\"Sugar\",1000)");
            MySQLOperation.dataToTable("create table Drink(Name varchar(40),Code varchar(20),cost double)");
            MySQLOperation.dataToTable("insert into Drink values\n" +
                    "(\"Black coffee\",\"c101\",23),\n" +
                    "(\"Black coffee with sweet milk\",\"c102\",25),\n" +
                    "(\"White coffee\",\"c103\",28),\n" +
                    "(\"Special coffee with coconut\",\"c104\",35),\n" +
                    "(\"Coconut\",\"f101\",35),\n" +
                    "(\"Lemon juice\",\"f102\",28)");
            MySQLOperation.dataToTable("create table Sale(Month int,Year int,Sale double)");
            MySQLOperation.dataToTable("insert into Sale values\n" +
                    "(1,2022,9653.5),\n" +
                    "(2,2022,8345.4),\n" +
                    "(5,2022,3423),\n" +
                    "(6,2022,5645)");
            MySQLOperation.dataToTable("create table Material(Month int,Year int,Material double);");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
