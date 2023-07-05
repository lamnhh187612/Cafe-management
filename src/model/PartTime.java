package model;

import dao.MySQLOperation;
import java.sql.SQLException;
import java.time.LocalTime;

public class PartTime extends Employee{
    private double time,payPerShift;

    public void setPayPerShift(double payPerShift) {
        this.payPerShift = payPerShift;
    }

    public double getPayPerShift() {
        return payPerShift;
    }

    public void setTime(double time) {
        this.time += time;
    }

    public void deleteTime(){
        this.time=0;
    }

    public double getTime() {
        return time;
    }

    public PartTime(String name, int age,String ID, double payPerShift,int userName,int passWord) throws SQLException, ClassNotFoundException {
        super(name,age,ID);
        time=0;
        this.payPerShift=payPerShift;
        setUserName(userName);
        setUserPassword(passWord);
    }

    public PartTime(String ID,String name, int age, double payPerShift,double time,int userName,int passWord){
        super(name,age,ID);
        this.time=time;
        this.payPerShift=payPerShift;
        setUserName(userName);
        setUserPassword(passWord);
    }

    @Override
    public void raiseSalary(double amount) {
        payPerShift+=amount;
    }

    public void attendance() throws SQLException, ClassNotFoundException {
        setTime(endShift()-beginShift());
        String query="update Part_time set Time="+getTime()+" where ID=\""+ID+"\";";
        MySQLOperation.dataToTable(query);
    }

    public double beginShift(){
        double begin;
        LocalTime time = LocalTime.now();
        begin=time.getHour()+((time.getMinute()/60)*100);
        return begin;
    }

    public double endShift(){
        double end;
        LocalTime time =LocalTime.now();
        end=time.getHour()+((time.getMinute()/60)*100);
        return end;
    }

    public void showInfo(){
        System.out.println("ID            : "+ID);
        System.out.println("Name          : "+name);
        System.out.println("Age           : "+age);
        System.out.println("Pay per shift : "+payPerShift);
        System.out.println("Work time     : "+time);
    }
}
