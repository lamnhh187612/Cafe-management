package model;



public class Sale{
    private Month time;
    private double sales;

    public Sale(Month time,double sales){
        this.time=time;
        this.sales=sales;
    }

    public void setTime(Month time) {
        this.time = time;
    }

    public void setSales(double sales) {
        this.sales += sales;
    }

    public double getSales() {
        return sales;
    }

    public Month getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time.getMonth()+"/"+time.getYear();
    }
}
