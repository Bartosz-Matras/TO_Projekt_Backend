package pl.bartoszmatras.projektto2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszmatras.projektto2.entity.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
