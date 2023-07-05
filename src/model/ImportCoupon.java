package model;

public class ImportCoupon{
    private String name;
    private double cost;

    public ImportCoupon(String name,double cost){
        this.name=name;
        this.cost=cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
