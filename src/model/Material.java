package model;

import dao.MySQLOperation;
import java.sql.SQLException;

public class Material{
    private Month time;
    private double materials;

    public Material(Month time,double materials) throws SQLException, ClassNotFoundException {
        this.time=time;
        this.materials=materials;
        String query="insert into Material values ("+time.getMonth()+","+time.getYear()+","+materials+");";
        MySQLOperation.dataToTable(query);
    }

    public void setTime(Month time) {
        this.time = time;
    }

    public void setMaterials(double sales) {
        this.materials += sales;
    }

    public double getMaterials() {
        return materials;
    }

    public Month getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time.getMonth()+"/"+time.getYear();
    }
}
