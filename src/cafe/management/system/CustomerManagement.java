package cafe.management.system;

import model.*;
import dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CustomerManagement{
    ArrayList<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        boolean found =false;
        for(Customer value : customerList) {
            if (Objects.equals(customer.getTelephone(), value.getTelephone())) {
                System.out.println("Phone has already existed.");
                found=true;
                break;
            }
        }
        if(!found){
            customerList.add(customer);
            String query="insert into Customer values (\""+customer.getName()+"\","+customer.getTelephone()+","+0+");";
            MySQLOperation.dataToTable(query);
            System.out.println("Added succeed !");
        }

    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void showCustomer(){
        System.out.printf("%15s", "Name");
        System.out.printf("%15s", "Telephone");
        System.out.printf("%15s", "Purchased");
        System.out.printf("%25s%n", "Discount rate");
        for (Customer customer : customerList) {
            System.out.println("\n");
            System.out.printf("%15s", customer.getName());
            System.out.printf("%15d", customer.getTelephone());
            System.out.printf("%15.2f", customer.getPurchased());
            System.out.printf("%25.2f%n", customer.getDiscountRate());
        }
    }
}
