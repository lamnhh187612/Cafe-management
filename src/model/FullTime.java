package model;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class FullTime extends Employee{
    private double baseSalary;
    private int dayOff;
    HashMap<Date,Boolean> map = new HashMap<Date,Boolean>();

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

    public int getDayOff() {
        return dayOff;
    }

    public HashMap<Date, Boolean> getMap() {
        return map;
    }

    public FullTime(String ID, String name, int age, double baseSalary,int userName,int passWord) throws SQLException, ClassNotFoundException {
        super(name,age,ID);
        this.baseSalary=baseSalary;
        LocalDateTime time =LocalDateTime.now();
        Date temp=new Date(time.getDayOfMonth(),time.getMonthValue(),time.getYear());
        map.put(temp,false);
        dayOff=0;
        setUserName(userName);
        setUserPassword(passWord);
    }

    public FullTime(String ID,String name, int age,double baseSalary, int dayOff,int userName,int passWord){
        super(name,age,ID);
        this.baseSalary=baseSalary;
        LocalDateTime time =LocalDateTime.now();
        Date temp=new Date(time.getDayOfMonth(),time.getMonthValue(),time.getYear());
        map.put(temp,false);
        this.dayOff=dayOff;
        setUserName(userName);
        setUserPassword(passWord);
    }

    @Override
    public void raiseSalary(double amount) {
        baseSalary+=amount;
    }

    public Date getDate(){
        LocalDateTime time =LocalDateTime.now();
        return new Date(time.getDayOfMonth(),time.getMonthValue(),time.getYear());
    }

    public void attendance(){
        map.replace(getDate(),true);
    }

    public void showInfo(){
        System.out.println("ID          : "+ID);
        System.out.println("Name        : "+name);
        System.out.println("Age         : "+age);
        System.out.println("Base salary : "+baseSalary);
        System.out.println("Day off     : "+dayOff);
    }
}
