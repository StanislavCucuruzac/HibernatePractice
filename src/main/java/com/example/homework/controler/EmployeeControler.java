package com.example.homework.controler;

import com.example.homework.Model.Car;
import com.example.homework.Model.Employee;
import com.example.homework.repository.CarRepository;
import com.example.homework.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeControler {
    EmployeeRepository employeeRepository;
    CarRepository carRepository;
    @GetMapping
    public List<Employee> getAll(){
        return employeeRepository.getAll();
    }
    @PostMapping
    public Employee createEmploee(@RequestBody Employee employee){
        return employeeRepository.createEmployee(employee);
    }
    @GetMapping("/id/{id}")
    public Employee getById(@PathVariable int id){
        return employeeRepository.getById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){
       employeeRepository.deleteEmployeeById(id);
       return "deleted with id" +id;
    }
    @GetMapping("/addCar")
    public Employee addCar(@RequestParam int carid, @RequestParam int employeeid){
        Car car=carRepository.getCarById(carid);
        Employee employee=employeeRepository.getById(employeeid);
        employee.setCar(car);
        return employeeRepository.createEmployee(employee);
    }

}
