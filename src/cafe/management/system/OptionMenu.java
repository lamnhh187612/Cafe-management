package cafe.management.system;

import model.*;
import dao.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class OptionMenu extends Account{
    Scanner menuInput=new Scanner(System.in);
    Scanner input=new Scanner(System.in);
    HashMap<Integer,Integer> data=new HashMap<Integer,Integer>();
    public void getLogin(Department department,CustomerManagement customerManagement,DrinkCategory drinkCategory,ImportManagement importManagement) throws IOException, SQLException, ClassNotFoundException {
        int x=1;
        System.out.println("Welcome to the Cafe Management Project !");
        do{
            try{
                data.put(1234,123456);
                String query="select * from Part_time";
                ResultSet resultSet=MySQLOperation.dataFromTable(query);
                if(resultSet.next()){
                    do{
                        data.put(resultSet.getInt(6),resultSet.getInt(7));
                    }while(resultSet.next());
                }
                query="select * from Full_time";
                resultSet=MySQLOperation.dataFromTable(query);
                if(resultSet.next()){
                    do{
                        data.put(resultSet.getInt(6),resultSet.getInt(7));
                    }while(resultSet.next());
                }
                System.out.println("User name : ");
                setUserName(input.nextInt());
                System.out.println("Password : ");
                setUserPassword(input.nextInt());
            }catch(Exception e){
                System.out.println("\nInvalid Character(s). Only Numbers.\n");
                x=2;
            }

            int un=getUserName();
            int p=getUserPassword();

            if(data.containsKey(un) && data.get(un)==p){
                //create a loop to find user name in hashmap

                for(int i=0;i<department.getFullTimes().size();i++){
                    if(un==department.getFullTimes().get(i).getUserName()){
                        employeeChoices(department.getFullTimes().get(i),customerManagement,drinkCategory);
                        break;
                    }
                }
                for(int i=0;i<department.getPartTimes().size();i++){
                    if(un==department.getPartTimes().get(i).getUserName()){
                        employeeChoices(department.getPartTimes().get(i),customerManagement,drinkCategory);
                        break;
                    }
                }


                if(un==1234){
                    managerChoices(department,customerManagement,drinkCategory,importManagement);
                }
            }
            else{
                System.out.println("\nWrong user name or password");
            }
        }while(x==1);
    }


    public void managerChoices(Department department,CustomerManagement customerManagement,DrinkCategory drinkCategory,ImportManagement importManagement) throws SQLException, ClassNotFoundException, InputMismatchException {
        System.out.println("-----------------------------");
        System.out.println("1. Employee Management");
        System.out.println("2. Sell product");
        System.out.println("3. Customer");
        System.out.println("4. Products");
        System.out.println("5. Import goods");
        System.out.println("6. Sales Statistics");
        System.out.println("7. Inventory Statistics");
        System.out.println("8. Exit");
        int choice=input.nextInt();
        switch(choice){
            case 1:
                managerChoice1(department);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 2:
                managerChoice2(customerManagement,drinkCategory);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 3:
                managerChoice3(customerManagement);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 4:
                managerChoice4(drinkCategory);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 5:
                managerChoice5(importManagement);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 6:
                managerChoice6(drinkCategory);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 7:
                managerChoice7(importManagement);
                System.out.println("Enter anything to go to main menu ");
                menuInput.nextLine();
                managerChoices(department,customerManagement,drinkCategory,importManagement);
                break;
            case 8:
                break;
        }
    }

    public void managerChoice1(Department department) throws SQLException, ClassNotFoundException {
        boolean done;
        System.out.println("-->Employee : ");
        System.out.println("1. Check attendance");
        System.out.println("2. Pay Employee");
        System.out.println("3. Info");
        System.out.println("4. Add Employee");
        System.out.println("5. Remove Employee");
        System.out.println("6. Exit");
        System.out.println("Your choice : ");
        int choose=input.nextInt();
        switch (choose){
            case 1:
                for(int i=0;i<department.getFullTimes().size();i++){
                    department.checkAttendance(department.getFullTimes().get(i));
                }
                System.out.println("Check success !");
                managerChoice1(department);
                break;
            case 2:
                System.out.println("Enter employee ID :");
                String temp=menuInput.nextLine();
                for(int i=0;i<department.getFullTimes().size();i++){
                    if(Objects.equals(department.getFullTimes().get(i).getID(), temp)){
                        department.payEmployee(department.getFullTimes().get(i));
                    }
                }
                for(int i=0;i<department.getPartTimes().size();i++){
                    if(Objects.equals(department.getPartTimes().get(i).getID(), temp)){
                        department.payEmployee(department.getPartTimes().get(i));
                    }
                }
                managerChoice1(department);
                break;
            case 3:
                System.out.println("\n");
                department.showFullTimeEmployee();
                System.out.println("\n");
                department.showPartTimeEmployee();
                System.out.println("Enter anything to exit ");
                menuInput.nextLine();
                managerChoice1(department);
                break;
            case 4:
                System.out.println("Enter 2 for Full-time and 3 for Part-time : ");
                int type=input.nextInt();

                if(type==2){
                    System.out.println("Name : ");
                    String name=menuInput.nextLine();
                    System.out.println("Age : ");
                    int age=input.nextInt();
                    System.out.println("ID : ");
                    String ID=menuInput.nextLine();
                    System.out.println("Base salary : ");
                    double pay=input.nextDouble();
                    System.out.println("Create user name : ");
                    int userName=input.nextInt();
                    System.out.println("Create user password : ");
                    int userPassword=input.nextInt();
                    FullTime staff=new FullTime(ID,name,age,pay,userName,userPassword);
                    String query="insert into Part_time values (\""+ID+"\",\""+name+"\","+age+","+pay+","+0+","+userName+","+userPassword+");";
                    MySQLOperation.dataToTable(query);
                    done=department.addEmployee(staff);
                    if(done){
                        System.out.println("Added full-time employee!");
                    }
                    else{
                        System.out.println("Fail!");
                    }
                }
                else if(type==3){
                    System.out.println("Name : ");
                    String name=menuInput.nextLine();
                    System.out.println("Age : ");
                    int age=input.nextInt();
                    System.out.println("ID : ");
                    String ID=menuInput.nextLine();
                    System.out.println("Pay per shift : ");
                    double pay=input.nextDouble();
                    System.out.println("Create user name : ");
                    int userName=input.nextInt();
                    System.out.println("Create user password : ");
                    int userPassword=input.nextInt();
                    String query="insert into Part_time values (\""+ID+"\",\""+name+"\","+age+","+pay+","+0+","+getUserName()+","+getUserPassword()+");";
                    MySQLOperation.dataToTable(query);
                    PartTime staff=new PartTime(name,age,ID,pay,userName,userPassword);
                    done=department.addEmployee(staff);
                    if(done){
                        System.out.println("Added part-time employee!");
                    }
                    else{
                        System.out.println("Fail!");
                    }
                }
                managerChoice1(department);
                break;
            case 5:
                System.out.println("\n");
                department.showFullTimeEmployee();
                System.out.println("\n");
                department.showPartTimeEmployee();
                System.out.println("Enter ID : ");
                String id=menuInput.nextLine();
                for(int i=0;i<department.getPartTimes().size();i++){
                    if(Objects.equals(id, department.getPartTimes().get(i).getID())){
                        done=department.removeEmployee(department.getPartTimes().get(i));
                        if(done){
                            System.out.println("Removed part-time employee!");
                        }
                        else{
                            System.out.println("Fail!");
                        }
                    }
                }
                for(int i=0;i<department.getFullTimes().size();i++){
                    if(Objects.equals(id, department.getFullTimes().get(i).getID())){
                        done=department.removeEmployee(department.getFullTimes().get(i));
                        if(done){
                            System.out.println("Removed part-time employee!");
                        }
                        else{
                            System.out.println("Fail!");
                        }
                    }
                }
                managerChoice1(department);
                break;
            case 6:
                break;
        }
    }

    public void managerChoice2(CustomerManagement customerManagement,DrinkCategory drinkCategory) throws SQLException, ClassNotFoundException {
        System.out.println("\n");
        drinkCategory.showDrinks();
        sellProduct(customerManagement,drinkCategory);
    }

    public void managerChoice3(CustomerManagement customerManagement) throws SQLException, ClassNotFoundException {
        System.out.println("-->Customer : ");
        System.out.println("1. Add customer");
        System.out.println("2. Info");
        System.out.println("3. Exit");
        System.out.println("Your choice : ");
        switch (input.nextInt()){
            case 1:
                System.out.println("Enter customer's name : ");
                String name=menuInput.nextLine();
                System.out.println("Enter customer's telephone : ");
                int telephone=input.nextInt();
                customerManagement.addCustomer(new Customer(name,telephone));
                System.out.println("Enter anything to exit ");
                menuInput.nextLine();
                managerChoice3(customerManagement);
                break;
            case 2:
                System.out.println("\n");
                customerManagement.showCustomer();
                System.out.println("Enter anything to exit ");
                menuInput.nextLine();
                managerChoice3(customerManagement);
            case 3:
                break;
        }

    }

    public void managerChoice4(DrinkCategory drinkCategory) throws SQLException, ClassNotFoundException {
        System.out.println("1. Add drink");
        System.out.println("2. Remove drink");
        System.out.println("3. Show drinks");
        System.out.println("4. Exit");
        int choice=input.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter name of drink : ");
                String name= menuInput.nextLine();
                System.out.println("Enter code of drink : ");
                String code=menuInput.nextLine();
                System.out.println("Enter cost of drink : ");
                double cost=input.nextInt();
                drinkCategory.addDrink(new Drink(name,code,cost));
                System.out.println("Added succeed!");
                managerChoice4(drinkCategory);
                break;
            case 2:
                System.out.println("\n");
                drinkCategory.showDrinks();
                System.out.println("Enter number to delete : ");
                drinkCategory.deleteDrink(input.nextInt());
                System.out.println("Deleted succeed!");
                managerChoice4(drinkCategory);
                break;
            case 3:
                System.out.println("\n");
                drinkCategory.showDrinks();
                System.out.println("Enter anything to exit ");
                menuInput.nextLine();
                managerChoice4(drinkCategory);
            case 4:
                break;
        }
    }

    public void managerChoice5(ImportManagement importManagement) throws SQLException, ClassNotFoundException {
        System.out.println("Enter name of good : ");
        String name=menuInput.nextLine();
        System.out.println("Enter cost of good : ");
        double cost=input.nextDouble();
        importManagement.addCoupon(name,cost);
        System.out.println("Imported succeed!");
    }

    public void managerChoice6(DrinkCategory drinkCategory){
        System.out.println("\n");
        drinkCategory.showSale();
    }

    public void managerChoice7(ImportManagement importManagement){
        System.out.println("Enter number of recently coupon to display : ");
        importManagement.showCoupon(input.nextInt());
    }

    public void employeeChoices(FullTime f,CustomerManagement customerManagement,DrinkCategory drinkCategory) throws SQLException, ClassNotFoundException {
        System.out.println("-----------------------------");
        System.out.println("1. Employee");
        System.out.println("2. Sell product");
        System.out.println("3. Exit");
        System.out.println("Your choice : ");
        int choice=input.nextInt();
        switch(choice){
            case 1:
                employeeChoice1(f);
                System.out.println("Enter anything to go main menu ");
                menuInput.nextLine();
                employeeChoices(f, customerManagement, drinkCategory);
                break;
            case 2:
                employeeChoice2(customerManagement, drinkCategory);
                System.out.println("Enter anything to go main menu ");
                menuInput.nextLine();
                employeeChoices(f, customerManagement, drinkCategory);
                break;
            case 3:
                break;
        }
    }

    public void employeeChoice1(FullTime f){
        System.out.println("-->Employee : ");
        System.out.println("1. Attendance");
        System.out.println("2. Info");
        System.out.println("3. Exit");
        System.out.println("Your choice : ");
        int choose=input.nextInt();
        switch (choose){
            case 1:
                f.attendance();
                System.out.println("Enter anything exit ");
                menuInput.nextLine();
                employeeChoice1(f);
                break;
            case 2:
                f.showInfo();
                System.out.println("Enter anything exit ");
                menuInput.nextLine();
                employeeChoice1(f);
                break;
            case 3:
                break;
        }
    }

    public void employeeChoices(PartTime p,CustomerManagement customerManagement,DrinkCategory drinkCategory) throws SQLException, ClassNotFoundException {
        System.out.println("-----------------------------");
        System.out.println("1. Employee");
        System.out.println("2. Sell product");
        System.out.println("3. Exit");
        System.out.println("Your choice : ");
        int choice=input.nextInt();
        switch(choice){
            case 1:
                employeeChoice1(p);
                System.out.println("Enter anything to go main menu ");
                menuInput.nextLine();
                employeeChoices(p, customerManagement, drinkCategory);
                break;
            case 2:
                employeeChoice2(customerManagement, drinkCategory);
                System.out.println("Enter anything to go main menu ");
                menuInput.nextLine();
                employeeChoices(p, customerManagement, drinkCategory);
                break;
            case 3:
                break;
        }
    }

    public void employeeChoice1(PartTime p) throws SQLException, ClassNotFoundException {
        System.out.println("-->Employee : ");
        System.out.println("1. Attendance");
        System.out.println("2. Info");
        System.out.println("3. Exit");
        System.out.println("Your choice : ");
        int choose=input.nextInt();
        switch (choose){
            case 1:
                attendanceChoice(p);
                employeeChoice1(p);
                break;
            case 2:
                p.showInfo();
                System.out.println("Enter anything exit ");
                menuInput.nextLine();
                employeeChoice1(p);
                break;
            case 3:
                break;
        }
    }

    public void employeeChoice2(CustomerManagement customerManagement,DrinkCategory drinkCategory) throws SQLException, ClassNotFoundException {
        sellProduct(customerManagement,drinkCategory);
    }

    public void sellProduct(CustomerManagement customerManagement,DrinkCategory drinkCategory) throws SQLException, ClassNotFoundException {
        HashMap<String,Integer> order=new HashMap<>();
        System.out.println("-->Sell product : ");
        System.out.println("Enter number of orders : ");
        int num=input.nextInt();
        String name;
        int amount;
        int telephone;
        String choice;
        double bill;
        for(int i=0;i<num;i++){
            System.out.println("Enter name of drink "+(i+1)+" : ");
            name=menuInput.nextLine();
            System.out.println("Enter number of drink"+(i+1)+" : ");
            amount=input.nextInt();
            order.put(name,amount);
        }
        System.out.println("Is prefer customer (y/n) : ");
        choice=menuInput.nextLine();
        if(Objects.equals(choice, "y")){
            System.out.println("Customer telephone : ");
            telephone=input.nextInt();
            bill=drinkCategory.createBill(order,customerManagement.getCustomerList(),telephone);
        }
        else{
            bill=drinkCategory.createBill(order);
        }
        System.out.println("Bill is created !");
        System.out.println("Total cost : "+bill);
    }

    public void attendanceChoice(PartTime p) throws SQLException, ClassNotFoundException {
        System.out.println("1. Begin Shift");
        System.out.println("2. End Shift");
        System.out.println("3. Exit");
        System.out.println("Your choice : ");
        int choose=input.nextInt();
        switch (choose){
            case 1:
                p.beginShift();
                attendanceChoice(p);
                break;
            case 2:
                p.endShift();
                p.attendance();
                attendanceChoice(p);
                break;
            case 3:
                employeeChoice1(p);
                break;
        }
    }
}

