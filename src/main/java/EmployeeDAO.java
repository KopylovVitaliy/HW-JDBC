import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployee();
    void getEmployeeByID(int id);
    void createEmployee(Employee employee);
    void updateEmployee(int id);
    void deleteEmployee(int id);
}
