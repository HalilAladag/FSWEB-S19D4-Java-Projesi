package services;

import static org.junit.jupiter.api.Assertions.*;

import dao.EmployeeRepository;
import entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void canNotSave() {
        String email = "deneme@test.com";
        Employee employee = new Employee();
        employee.setEmail(email);

        given(employeeRepository.findByEmail(email)).willReturn(Optional.of(employee));
        assertNull(employeeService.save(employee));
        verify(employeeRepository, never()).save(employee);
    }

    @Test
    void canSave(){
        String email = "deneme@test.com";
        Employee employee = new Employee();
        employee.setEmail(email);

        given(employeeRepository.findByEmail(email)).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);
        Employee savedEmployee = employeeService.save(employee);
        assertNotNull(savedEmployee);
        verify(employeeRepository).save(employee);
    }

    @Test
    void delete() {
    }

    @Test
    void findByEmail() {
        String email = "admin";
        given(employeeRepository.findByEmail(email)).willReturn(Optional.empty());
        assertNull(employeeService.findByEmail(email));

        Employee employee = new Employee();
        employee.setFirstName("Dogancan");
        String email2 = "test";

        given(employeeRepository.findByEmail(email2)).willReturn(Optional.of(employee));
        Employee foundEmployee = employeeService.findByEmail(email2);
        assertEquals("Dogancan", foundEmployee.getFirstName());
    }

    @Test
    void findBySalary() {
    }

    @Test
    void findByOrder() {
        employeeService.findByOrder();
        verify(employeeRepository).findByOrder();
    }
}