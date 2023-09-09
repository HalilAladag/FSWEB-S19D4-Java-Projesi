package dao;

import entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Ali");
        employee1.setLastName("Veli");
        employee1.setEmail("ali_veli@test.com");
        employee1.setSalary(30000);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ali");
        employee2.setLastName("Ahmet");
        employee2.setEmail("ali_ahmet@test.com");
        employee2.setSalary(40000);

        Employee employee3 = new Employee();
        employee3.setFirstName("Ali");
        employee3.setLastName("Cengiz");
        employee3.setEmail("ali_cengiz@test.com");
        employee3.setSalary(50000);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeRepository.saveAll(employeeList);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        String notExistingEmail = "admin@test.com";
        Optional<Employee> employee = employeeRepository.findByEmail(notExistingEmail);
        assertEquals(Optional.empty(), employee);

        String existingEmail = "ali_ahmet@test.com";
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(existingEmail);
        assertNotNull(existingEmployee.get());
        assertEquals("Ahmet", existingEmployee.get().getLastName());
        assertEquals(40000, existingEmployee.get().getSalary());
    }

    @Test
    void findBySalary() {
        List<Employee> employeeList = employeeRepository.findBySalary(35000);
        assertEquals(2, employeeList.size());
        assertEquals("Cengiz", employeeList.get(0).getLastName());
        assertEquals("Ahmet", employeeList.get(1).getLastName());
    }

    @Test
    void findByOrder() {
        List<Employee> employeeList = employeeRepository.findByOrder();
        assertEquals(3, employeeList.size());
        assertEquals("ali_ahmet@test.com", employeeList.get(0).getEmail());
        assertEquals("ali_cengiz@test.com", employeeList.get(1).getEmail());
        assertEquals("ali_veli@test.com", employeeList.get(2).getEmail());

    }
}