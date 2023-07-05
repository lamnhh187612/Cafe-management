package model;

public class Customer {
    private String name;
    private int telephone;
    private double purchased;
    private double discountRate;

    public Customer(String name,int telephone){
        this.name=name;
        this.telephone=telephone;
        this.purchased=0;
        this.discountRate=0;
    }

    public Customer(String name,int telephone, double purchased){
        this.name=name;
        this.telephone=telephone;
        this.purchased=purchased;
        if(this.purchased>=500 && this.purchased<1000){
            this.discountRate=0.05;
        }
        else if(this.purchased>=1000 && this.purchased<1500){
            this.discountRate=0.06;
        }
        else if(this.purchased>=1500 && this.purchased<2000){
            this.discountRate=0.07;
        }
        else if(this.purchased>=2000){
            this.discountRate=0.1;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setPurchased(double purchased) {
        this.purchased += purchased;
    }

    public double getPurchased() {
        return purchased;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}
