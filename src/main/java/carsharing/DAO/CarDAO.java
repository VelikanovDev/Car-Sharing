package carsharing.DAO;

import carsharing.model.Car;

import java.util.List;

public interface CarDAO extends GenericDAO<Car> {
    List<Car> getAllById(int id, boolean printAlsoRented);
    void update(Car car, boolean isRented);
}