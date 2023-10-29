package com.filter.demo.controller;

import com.filter.demo.model.Employee;
import com.filter.demo.model.SpecificationInput;
import com.filter.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/ByName")
    List<Employee> getByName(){
        return employeeService.getEmployeeByName();
    }

    @GetMapping("/ByEquals")
    List<Employee> ByEqual(@RequestBody SpecificationInput specificationInput) throws ParseException {
        return employeeService.getEmployeeData(specificationInput);
    }

    @GetMapping("/ByBetweenDates")
    List<Employee> ByBetweenDates(@RequestBody SpecificationInput input) throws ParseException {
        return employeeService.getEmployeesBetweenDates(input);
    }

}
