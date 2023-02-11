package carsharing.DAO;

import carsharing.model.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {
    void update(Customer customer, int carId);
}