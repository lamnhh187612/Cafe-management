package model;

import java.util.Scanner;

public abstract class Employee extends Account{
    protected String name,ID;
    protected int age;

    Scanner input =new Scanner(System.in);
    public Employee(String name,int age,String ID){
        this.name=name;
        this.age=age;
        this.ID=ID;
    }



    abstract public void raiseSalary(double amount);

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }
}