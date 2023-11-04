package com.filter.demo.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.filter.demo.model.Employee;
import com.filter.demo.model.SpecificationInput;
import com.filter.demo.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceDiffblueTest {
    @MockBean
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeService#getEmployeeData(SpecificationInput)}
     */
    @Test
    void testGetEmployeeData() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepo.findAll(Mockito.<Specification<Employee>>any())).thenReturn(employeeList);
        List<Employee> actualEmployeeData = employeeService.getEmployeeData(new SpecificationInput());
        assertSame(employeeList, actualEmployeeData);
        assertTrue(actualEmployeeData.isEmpty());
        verify(employeeRepo).findAll(Mockito.<Specification<Employee>>any());
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeeData(SpecificationInput)}
     */
    @Test
    void testGetEmployeeData2() {
        when(employeeRepo.findAll(Mockito.<Specification<Employee>>any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> employeeService.getEmployeeData(new SpecificationInput()));
        verify(employeeRepo).findAll(Mockito.<Specification<Employee>>any());
    }
}

