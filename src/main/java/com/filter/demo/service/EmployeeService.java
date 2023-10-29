package com.filter.demo.service;

import com.filter.demo.model.Employee;
import com.filter.demo.model.SpecificationInput;
import com.filter.demo.repo.EmployeeRepo;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.hibernate.persister.collection.mutation.RowMutationOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;


    private Specification<Employee> getSpecification(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"),"ramu");
    }

    public List<Employee> getEmployeeByName(){
        Specification<Employee> specification = getSpecification();
        return employeeRepo.findAll(specification);
    }

    private Specification<Employee> getSpecification(SpecificationInput specificationInput){
         return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(specificationInput.getColumnName()),
                    specificationInput.getValue());

    }
    public List<Employee> getEmployeeData(SpecificationInput specificationInput) {
        Specification<Employee> specification = getSpecification(specificationInput);

        return  employeeRepo.findAll(specification);
    }

   private Specification<Employee> getEmployeeSpecificationBetweenDates(SpecificationInput input) throws ParseException {
        String value = input.getValue();
        String[] values = value.split(",");
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
       Date startDt = simpleDateFormat.parse(values[0]);
       Date endDt = simpleDateFormat.parse(values[1]);
       return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(input.getColumnName()),startDt,endDt);

   }
   public List<Employee> getEmployeesBetweenDates(SpecificationInput input) throws ParseException {
       Specification<Employee> empSpec = getEmployeeSpecificationBetweenDates(input);
       String sortColumn = input.getSortColumn();
       String sortOrder = input.getSortOrder();
       Integer pageNumber = 0;
       Integer pageSize = 2;
       pageNumber = input.getPageNum()>=0?input.getPageNum():pageNumber;
       pageSize = input.getPageSize()>=0?input.getPageSize():pageSize;

       boolean sortFlag = sortOrder.equalsIgnoreCase("ASC")?true:false;

       Sort sort = Sort.by(sortFlag?Sort.Direction.ASC: Sort.Direction.DESC,sortColumn);
       Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

       Page<Employee> pageEmployee =employeeRepo.findAll(empSpec,pageable);
       return pageEmployee.getContent();

    }

    private Specification<Employee> getEmployeeSpecificationByLike(SpecificationInput input){
       return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(input.getColumnName()),
               "%"+input.getValue()+"%");

    }
    public List<Employee> getEmployeeByLike(SpecificationInput specificationInput){
        Specification<Employee> employeeSpecification = getEmployeeSpecificationByLike(specificationInput);
        return employeeRepo.findAll(employeeSpecification);
    }

    private Specification<Employee> getSpecificationByGreaterThan(SpecificationInput input){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(input.getColumnName()),input.getValue());

    }
    public List<Employee> getGreaterThan(SpecificationInput input){
        Specification<Employee> specificationByGreaterThan = getSpecificationByGreaterThan(input);
         return employeeRepo.findAll(specificationByGreaterThan);

    }

}
