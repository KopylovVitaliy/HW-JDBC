import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAOImpl implements EmployeeDAO {

    Scanner scanner = new Scanner(System.in);

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection()){

             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee");

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {

                int idOfPerson = resultSet.getInt("id");
                System.out.println("ID работника: " + idOfPerson);

                String namePerson = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                Integer city = resultSet.getInt("city_id");

                employees.add(new Employee(idOfPerson, namePerson, lastName, gender, age, city));

            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void getEmployeeByID(int id) {
        String sql = "SELECT employee.id, employee.first_name, employee.gender, employee.age, employee.last_name, city.city_name " +
                "FROM employee JOIN city ON employee.city_id = city.city_id WHERE id=" + id;

        try (Connection connection = getConnection()){
             PreparedStatement statement =
                     connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int idOfPerson = resultSet.getInt("id");
                System.out.println("ID работника: " + idOfPerson);

                String namePerson = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String city_name = resultSet.getString("city_name");
                System.out.println(namePerson + " " + lastName + " " + gender + " возраст: " + age + " город: " + city_name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }


    @Override
    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO employee(first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection()){
             PreparedStatement statement =
                     connection.prepareStatement(sql);
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity_id());
            int resultSet = statement.executeUpdate();
            System.out.println("Сотрудник добавлен!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateEmployee(int id) {
        System.out.println("Введите новое имя");
        String fn = scanner.nextLine();
        System.out.println("Введите новую Фамилию");
        String ln = scanner.nextLine();
        System.out.println("Введите пол");
        String gd = scanner.nextLine();
        System.out.println("Введите возраст");
        int age = scanner.nextInt();
        System.out.println("Введите id города");
        Integer city_id = scanner.nextInt();
        String sql = "UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) WHERE id =" + id;

        try (Connection connection = getConnection()){
             PreparedStatement statement =
                     connection.prepareStatement(sql);

            statement.setString(1, fn);
            statement.setString(2, ln);
            statement.setString(3, gd);
            statement.setInt(4, age);
            statement.setInt(5, city_id);

            int resultSet = statement.executeUpdate();
            System.out.println("Сотрудник обновлён!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=" + id;
        try (Connection connection = getConnection()){
             PreparedStatement statement =
                     connection.prepareStatement(sql);
            int resultSet = statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }
    private static Connection getConnection() throws SQLException{
        String user = "postgres";
        String password = "Strahov1488";
        String url = "jdbc:postgresql://localhost:5432/db_skypro";
        return DriverManager.getConnection(url, user, password);
    }
}

