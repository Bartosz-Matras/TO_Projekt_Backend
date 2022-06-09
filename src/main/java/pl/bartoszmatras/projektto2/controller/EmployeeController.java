package pl.bartoszmatras.projektto2.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmatras.projektto2.dto.EmployeeDto;
import pl.bartoszmatras.projektto2.entity.Employee;
import pl.bartoszmatras.projektto2.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public EmployeeDto saveOrUpdateEmployee(@RequestBody EmployeeDto dto){
        if (dto.getIdEmployee() == null){
            return EmployeeDto.of(employeeRepository.save(Employee.of(dto)));
        }else {
            Optional<Employee> optionalEmployee = employeeRepository.findById(dto.getIdEmployee());
            if (optionalEmployee.isPresent()){
                Employee employee = optionalEmployee.get();
                employee.updateEmployee(dto);
                return EmployeeDto.of(employeeRepository.save(employee));
            }else{
                throw new RuntimeException("Can't find user with given id: " + dto.getIdEmployee());
            }
        }
    }

    @GetMapping("/employees")
    public List<EmployeeDto> listEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{idEmployee}")
    public EmployeeDto getEmployee(@PathVariable Long idEmployee) throws InterruptedException {
        Thread.sleep(500);
        Optional<Employee> optionalEmployee = employeeRepository.findById(idEmployee);
        return EmployeeDto.of(optionalEmployee.get());
    }

    @DeleteMapping("/employees/{idEmployee}")
    public ResponseEntity deleteEmployee(@PathVariable Long idEmployee){
        employeeRepository.deleteById(idEmployee);
        return ResponseEntity.ok().build();
    }
}
