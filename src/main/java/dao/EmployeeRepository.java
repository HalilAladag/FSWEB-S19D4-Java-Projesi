package dao;

import entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
    Optional<Employee> findById(int id);
    List<Employee> findByEmail(String email);
    List<Employee> finsBySalary(double salary);
    List<Employee> findByOrder();
    Employee saveEmployee(Employee employee);

}
