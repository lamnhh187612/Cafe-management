package cafe.management.system;

import model.*;
import dao.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Department{
    private int nEmployee;
    public static final int MAX_EMP_NUM =100;
    protected ArrayList<FullTime> fullTimes = new ArrayList<FullTime>();
    protected ArrayList<PartTime> partTimes = new ArrayList<PartTime>();



    public ArrayList<FullTime> getFullTimes() {
        return fullTimes;
    }

    public ArrayList<PartTime> getPartTimes() {
        return partTimes;
    }

    public Department(int nEmployee){
        this.nEmployee=nEmployee;
    }



    public boolean addEmployee(FullTime f){
        if(nEmployee<MAX_EMP_NUM){
            f.setType(2);
            fullTimes.add(f);
            nEmployee++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addEmployee(PartTime p){
        if(nEmployee<MAX_EMP_NUM){
            p.setType(3);
            partTimes.add(p);
            nEmployee++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeEmployee(FullTime f) throws SQLException, ClassNotFoundException {
        boolean done=false;
        if(nEmployee>0){
            for(int i=0;i<fullTimes.size();i++){
                if(Objects.equals(f.getID(), fullTimes.get(i).getID())){
                    fullTimes.remove(i);
                    done = true;
                    String query="delete from Full_time where ID=\""+ f.getID() +"\";";
                    MySQLOperation.dataToTable(query);
                    break;
                }
            }
        }
        return done;
    }

    public boolean removeEmployee(PartTime p) throws SQLException, ClassNotFoundException {
        boolean done=false;
        if(nEmployee>0){
            for(int i=0;i<partTimes.size();i++){
                if(Objects.equals(p.getID(), partTimes.get(i).getID())){
                    partTimes.remove(i);
                    done = true;
                    String query="delete from Part_time where ID=\""+ p.getID() +"\";";
                    MySQLOperation.dataToTable(query);
                    break;
                }
            }
        }

        return done;
    }


    public void checkAttendance(FullTime f) throws SQLException, ClassNotFoundException {
        LocalDateTime time =LocalDateTime.now();
        Date temp=new Date(time.getDayOfMonth(),time.getMonthValue(),time.getYear());
        for (Date i : f.getMap().keySet()) {
            if(i.getDay()==time.getDayOfMonth() && i.getMonth()==time.getMonthValue() && i.getYear()==time.getYear()){
                break;
            }
            else{
                if(!f.getMap().get(i)){
                    f.setDayOff(f.getDayOff()+1);
                    String query="update Full_time set Day_off="+f.getDayOff()+" where ID=\""+ f.getID() +"\";";
                    MySQLOperation.dataToTable(query);
                }
                i=null;
                assert false;
                i.setDay(time.getDayOfMonth());
                i.setMonth(time.getMonthValue());
                i.setYear(time.getYear());
                f.getMap().put(i,false);
            }
        }
    }

    public void payEmployee(PartTime p) throws SQLException, ClassNotFoundException {
        for (PartTime partTime : partTimes) {
            if (Objects.equals(p.getID(), partTime.getID())) {
                partTime.deleteTime();
                String query="update Part_time set Time=0 where ID=\""+ p.getID() +"\";";
                MySQLOperation.dataToTable(query);
            }
        }
    }

    public void payEmployee(FullTime f) throws SQLException, ClassNotFoundException {
        for (FullTime fullTime : fullTimes) {
            if (Objects.equals(f.getID(), fullTime.getID())) {
                fullTime.setDayOff(0);
                String query="update Full_time set Day_off=0 where ID=\""+ f.getID() +"\";";
                MySQLOperation.dataToTable(query);
            }
        }
    }

    public void showFullTimeEmployee(){
        System.out.printf("%15s", "Name");
        System.out.printf("%15s", "Age");
        System.out.printf("%15s", "ID");
        System.out.printf("%15s", "Base salary");
        System.out.printf("%15s", "Day off");
        System.out.printf("%15s", "User name");
        System.out.printf("%15s%n", "Password");
        for (FullTime employee : fullTimes) {
            System.out.println("\n");
            System.out.printf("%15s", employee.getName());
            System.out.printf("%15d", employee.getAge());
            System.out.printf("%15s", employee.getID());
            System.out.printf("%15.2f", employee.getBaseSalary());
            System.out.printf("%15d", employee.getDayOff());
            System.out.printf("%15d", employee.getUserName());
            System.out.printf("%15d%n", employee.getUserPassword());
        }
    }

    public void showPartTimeEmployee(){
        System.out.printf("%15s", "Name");
        System.out.printf("%15s", "Age");
        System.out.printf("%15s", "ID");
        System.out.printf("%15s", "Pay per shift");
        System.out.printf("%15s", "Time");
        System.out.printf("%15s", "User name");
        System.out.printf("%15s%n", "Password");
        for (PartTime employee : partTimes) {
            System.out.println("\n");
            System.out.printf("%15s", employee.getName());
            System.out.printf("%15d", employee.getAge());
            System.out.printf("%15s", employee.getID());
            System.out.printf("%15.2f", employee.getPayPerShift());
            System.out.printf("%15.2f", employee.getTime());
            System.out.printf("%15d", employee.getUserName());
            System.out.printf("%15d%n", employee.getUserPassword());
        }
    }
}
