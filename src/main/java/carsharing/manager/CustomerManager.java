package carsharing.manager;

import carsharing.model.Customer;
import carsharing.DAOImpl.CustomerDAOImpl;
import java.util.List;
import java.util.Scanner;


public class CustomerManager extends CustomerDAOImpl {

    private Customer selectedCustomer;
    private List<Customer> listOfCustomers;

    public CustomerManager() {
        dropTable();
        createTable();
    }

    public void printList() {
        this.listOfCustomers = getAll();

        if (this.listOfCustomers.isEmpty()) {
            System.out.printf("%nThe customer list is empty!%n");
        } else {
            System.out.printf("%nChoose the customer:%n");
            int n = 1;
            for (Customer customer : listOfCustomers) {
                System.out.printf("%d. %s%n", n, customer.getName());
                n++;
            }
            System.out.println("0. Back");
        }
    }

    public void create(Scanner scn) {
        String name;

        System.out.printf("%nEnter the customer name:%n");
        name = scn.nextLine();

        insert(new Customer(0,name, 0));

        System.out.printf("The customer was created!%n");
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }
}
