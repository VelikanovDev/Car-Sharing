package carsharing.DAO;
import java.util.List;

public interface GenericDAO<T> {

    T get(int id);

    List<T> getAll();

    void createTable();

    void dropTable();

    void insert(T t);

}