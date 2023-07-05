package cafe.management.system;

import model.*;
import dao.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DrinkCategory{
    ArrayList<Drink> drinkList= new ArrayList<>();
    ArrayList<Sale> saleList= new ArrayList<>();



    public void addDrink(Drink drink) throws SQLException, ClassNotFoundException {
        for (Drink value : drinkList) {
            if (Objects.equals(drink.getCode(), value.getCode())) {
                System.out.println("Drink has already existed.");
                return;
            }
        }
        drinkList.add(drink);
        String query="insert into Drink values ('"+drink.getName()+"','"+drink.getCode()+"',"+drink.getCost()+")";
        MySQLOperation.dataToTable(query);
        System.out.println("Added succeed !");
    }

    public void deleteDrink(int index) throws SQLException, ClassNotFoundException {
        drinkList.remove(index);
        String query="delete from Drink where code=\""+drinkList.get(index).getCode()+"\";";
        MySQLOperation.dataToTable(query);
        System.out.println("Deleted succeed !");
    }

    public double createBill(HashMap<String,Integer> order, ArrayList<Customer> customerList, int telephone) throws SQLException, ClassNotFoundException {
        LocalDateTime time =LocalDateTime.now();
        Month now=new Month(time.getMonthValue(), time.getYear());
        double bill=0;
        for (Map.Entry<String, Integer> set : order.entrySet()) {
            for(Drink drink:drinkList){
                if(Objects.equals(drink.getName(), set.getKey())){
                    for(Customer customer:customerList){
                        if(telephone==customer.getTelephone()){
                            double primaryCost=set.getValue()*drink.getCost();
                            bill+=primaryCost-(primaryCost*customer.getDiscountRate());
                            customer.setPurchased(bill);
                            String query="update Customer set Purchased="+customer.getPurchased()+"where Telephone="+customer.getTelephone()+";";
                            MySQLOperation.dataToTable(query);
                        }
                    }
                }
            }
        }
        boolean found=false;
        for(Sale sale:saleList){
            if(sale.getTime().getMonth() == now.getMonth() && sale.getTime().getYear() == now.getYear()){
                sale.setSales(bill);
                found=true;
                String query="update Sale set Sale="+sale.getSales()+"where Month="+sale.getTime().getMonth()+" and Year="+sale.getTime().getYear()+";";
                MySQLOperation.dataToTable(query);

            }
        }
        if(!found){
            saleList.add(new Sale(now,bill));
            String query="insert into Sale values ("+now.getMonth()+","+now.getYear()+","+bill+");";
            MySQLOperation.dataToTable(query);
        }
        return bill;
    }
    public double createBill(HashMap<String,Integer> order) throws SQLException, ClassNotFoundException {
        LocalDateTime time =LocalDateTime.now();
        Month now=new Month(time.getMonthValue(), time.getYear());
        double bill=0;
        for (Map.Entry<String, Integer> set : order.entrySet()) {
            for(Drink drink:drinkList){
                if(Objects.equals(drink.getName(), set.getKey())){
                    bill+=set.getValue()*drink.getCost();
                }
            }
        }
        boolean found=false;
        for(Sale sale:saleList){
            if(sale.getTime().getMonth() == now.getMonth() && sale.getTime().getYear() == now.getYear()){
                sale.setSales(bill);
                found=true;
                String query="update Sale set Sale="+sale.getSales()+"where Month="+sale.getTime().getMonth()+" and Year="+sale.getTime().getYear()+";";
                MySQLOperation.dataToTable(query);
            }
        }
        if(!found){
            saleList.add(new Sale(now,bill));
            String query="insert into Sale values ("+now.getMonth()+","+now.getYear()+","+bill+");";
            MySQLOperation.dataToTable(query);
        }
        return bill;
    }

    public void showDrinks(){
        System.out.printf("%15s", "No.");
        System.out.printf("%40s", "Name");
        System.out.printf("%15s", "Code");
        System.out.printf("%15s%n", "Cost");
        for (int i=0;i<drinkList.size();i++) {
            System.out.println("\n");
            System.out.printf("%15d", i);
            System.out.printf("%40s", drinkList.get(i).getName());
            System.out.printf("%15s", drinkList.get(i).getCode());
            System.out.printf("%15.2f%n", drinkList.get(i).getCost());
        }
    }

    public void showSale(){
        System.out.printf("%20s", "Time");
        System.out.printf("%20s%n", "Sale");
        for (Sale sale : saleList) {
            System.out.println("\n");
            System.out.printf("%20s", sale.toString());
            System.out.printf("%20.2f%n", sale.getSales());
        }
    }

}
