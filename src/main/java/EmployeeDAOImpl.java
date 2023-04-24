import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAOImpl implements EmployeeDAO {

    Scanner scanner = new Scanner(System.in);

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees;
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        String JPQL = "SELECT s FROM Employee s";
        TypedQuery<Employee> query = manager.createQuery(JPQL, Employee.class);
        employees = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        employees.forEach(System.out::println);
        return employees;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        Employee employee = manager.find(Employee.class, id);
        manager.getTransaction().commit();
        System.out.println(employee);
        manager.close();
        return employee;
    }

    @Override
    public void createEmployee(Employee employee) {
        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        manager.persist(employee);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void updateEmployee(Employee employee) {
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
        employee.setFirst_name(fn);
        employee.setLast_name(ln);
        employee.setGender(gd);
        employee.setAge(age);
        employee.setCity_id(city_id);

        EntityManager manager = PersistenceUtil.getEm();
        manager.getTransaction().begin();
        manager.merge(employee);
        manager.getTransaction().commit();
        manager.close();
        System.out.println("Сотрудник" + employee.getId() + " обновлён!");
    }

    @Override
    public void deleteEmployee(Employee employee) {
    EntityManager manager = PersistenceUtil.getEm();
    manager.getTransaction().begin();
    Employee employee1 = manager.find(Employee.class, employee.getId());
    manager.remove(employee1);
    manager.getTransaction().commit();
    manager.close();
    System.out.println("Сотрудник " + employee + " удалён.");
    }
    private static Connection getConnection() throws SQLException{
        String user = "postgres";
        String password = "Strahov1488";
        String url = "jdbc:postgresql://localhost:5432/db_skypro";
        return DriverManager.getConnection(url, user, password);
    }
}

