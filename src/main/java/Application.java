import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        final String user = "postgres";
        final String password = "Strahov1488";
        final String url = "jdbc:postgresql://localhost:5432/db_skypro";

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
                String city = resultSet.getString("city_id");

                System.out.println(namePerson + " " + lastName + " " + gender + " Город: " +
                        city);

            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> employees = employeeDAO.getAllEmployee();
        for (Employee employee : employees){
            System.out.println(employee.getFirst_name());
        }

        employeeDAO.deleteEmployee(1);
        for (Employee employee : employees){
            System.out.println(employee.getFirst_name());
        }
        employeeDAO.getEmployeeByID(14);
        Employee employee1 = new Employee(3, "Вася", "Иванов", "М", 25, 3);
        employeeDAO.createEmployee(employee1);
        employeeDAO.getEmployeeByID(21);
    }
}

