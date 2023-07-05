package cafe.management.system;

import model.*;
import dao.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ImportManagement{
    ArrayList<ImportCoupon> coupons=new ArrayList<>();
    ArrayList<Material> materialList=new ArrayList<>();


    public void addCoupon(String name,double cost) throws SQLException, ClassNotFoundException {
        LocalDateTime time =LocalDateTime.now();
        Month now=new Month(time.getMonthValue(), time.getYear());
        coupons.add(new ImportCoupon(name,cost));
        boolean found=false;
        for(Material material:materialList){
            if(material.getTime().getMonth() == now.getMonth() && material.getTime().getYear() == now.getYear()){
                material.setMaterials(cost);
                found=true;
                String query="update Material set Material="+material.getMaterials()+"where Month="+material.getTime().getMonth()+" and Year="+material.getTime().getYear()+";";
                MySQLOperation.dataToTable(query);
            }
        }
        if(!found){
            materialList.add(new Material(now,cost));
        }
    }

    public void showCoupon(int num){
        System.out.printf("%15s", "Name");
        System.out.printf("%15s%n", "Cost");
        for(int i=coupons.size()-1;i>coupons.size()-1-num;i--){
            System.out.println("\n");
            System.out.printf("%15s", coupons.get(i).getName());
            System.out.printf("%15.2f%n", coupons.get(i).getCost());
        }
    }

}
