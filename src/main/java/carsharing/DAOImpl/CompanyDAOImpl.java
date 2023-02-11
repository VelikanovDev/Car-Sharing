package carsharing.DAOImpl;

import carsharing.DAO.CompanyDAO;
import carsharing.Database;
import carsharing.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {
    @Override
    public Company get(int id) {
        Company company = null;
        String sql = "SELECT id, name FROM carsharing.company WHERE id = ?";

        try (Connection conn = Database.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int comId = rs.getInt("id");
                String name = rs.getString("name");
                company = new Company(comId, name);
            }

            Database.closePreparedStatement(ps);
            Database.closeConnection(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return company;
    }

    @Override
    public List<Company> getAll() {

        String sql = "SELECT id, name FROM carsharing.company";
        List<Company> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             Statement state = conn.createStatement();
             ResultSet rs = state.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Company(id, name));
            }

            Database.closeStatement(state);
            Database.closeResultSet(rs);
            Database.closeConnection(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS company (
                    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    name VARCHAR(255) UNIQUE NOT NULL
                );""";

        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();

            statement.executeUpdate(sql);

            Database.closeStatement(statement);
            Database.closeConnection(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS company, car, customer";
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();

            statement.executeUpdate(sql);
            Database.closeStatement(statement);
            Database.closeConnection(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(Company company) {
        String sql = "INSERT INTO company (name) VALUES(?)";

        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, company.getName());
            ps.executeUpdate();

            Database.closePreparedStatement(ps);
            Database.closeConnection(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}