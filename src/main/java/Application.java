

public class Application {
    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        //Employee employee1 = new Employee( "Вася", "Иванов", "М", 25, 3);
        employeeDAO.getAllEmployee();
        //employeeDAO.updateEmployee(employeeDAO.getEmployeeByID(68));
    }
}

