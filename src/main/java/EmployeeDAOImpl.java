import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    final String user = "postgres";
    final String password = "Strahov1488";
    final String url = "jdbc:postgresql://localhost:5432/db_skypro";

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);

             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {

                int idOfPerson = resultSet.getInt("id");
                System.out.println("ID работника: " + idOfPerson);

                String namePerson = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city_id");

                employees.add(new Employee(idOfPerson, namePerson, lastName, gender, age, city));

            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return null;
    }

    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=" + id;
        try (final Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement =
                connection.prepareStatement(sql)) {
        int resultSet = statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }
}
