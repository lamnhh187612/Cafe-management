package model;

public class Drink{
    private String name;
    private String code;
    private double cost;


    public Drink (String name,String code,double cost) {
        this.name=name;
        this.code=code;
        this.cost=cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
