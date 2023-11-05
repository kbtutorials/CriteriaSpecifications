package com.filter.demo.service;

import com.filter.demo.model.Employee;
import com.filter.demo.model.SearchSpecification;
import com.filter.demo.model.SpecificationInput;
import com.filter.demo.repo.EmployeeRepo;
import jakarta.persistence.criteria.Predicate;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(input.getColumnName()),input.getValue());

    }
    public long getGreaterThan(SpecificationInput input){
        Specification<Employee> specificationByGreaterThan = getSpecificationByGreaterThan(input);
        Long empCount =  employeeRepo.count(specificationByGreaterThan);
        System.out.println("Emp count is :"+empCount);
        boolean emExists = employeeRepo.exists(specificationByGreaterThan);
        System.out.println("Emp Exists :"+emExists);
        long deleteStatus = 0;
        if(emExists){
           deleteStatus= employeeRepo.delete(specificationByGreaterThan);
        }
       return  deleteStatus;

    }
    private Specification<Employee> getSpecificationByIn(SpecificationInput input){
        String[] values = input.getValue().split(",");
        List<String> empList = Arrays.asList(values);
        return (root, query, criteriaBuilder) -> {
            return root.get(input.getColumnName()).in(List.of(values));

        };

    }
    public List<Employee> getEmployeeByIn(SpecificationInput specificationInput){
        Specification<Employee> employeeSpecification = getSpecificationByIn(specificationInput);
        return employeeRepo.findAll(employeeSpecification);
    }


    private Specification<Employee> getSpecificationForList
            (List<SearchSpecification> searchSpecificationList,String overalOp){

        List<Predicate> predicateList = new ArrayList<>();
        return (root, query, criteriaBuilder) -> {
            for (SearchSpecification sf : searchSpecificationList) {
                String operation = sf.getOperation();
                switch (operation) {
                    case "EQUAL" -> {
                        Predicate equal = criteriaBuilder.equal(root.get(sf.getColumnName()), sf.getValue());
                        predicateList.add(equal);
                    }
                    case "GREATER_THAN" -> {
                        Predicate greaterThan = criteriaBuilder.greaterThan(root.get(sf.getColumnName()), sf.getValue());
                        predicateList.add(greaterThan);
                    }
                    case "GREATER_THAN_EQUAL" -> {
                        Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(root.get(sf.getColumnName()), sf.getValue());
                        predicateList.add(greaterThanOrEqualTo);
                    }
                    case "LESS_THAN" -> {
                        Predicate lessThan = criteriaBuilder.lessThan(root.get(sf.getColumnName()), sf.getValue());
                        predicateList.add(lessThan);
                    }
                    case "LESS_THAN_EQUAL" -> {
                        Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(root.get(sf.getColumnName()), sf.getValue());
                        predicateList.add(lessThanOrEqualTo);
                    }
                    case "LIKE" -> {
                        Predicate like = criteriaBuilder.like(root.get(sf.getColumnName()), "%" + sf.getValue() + "%");
                        predicateList.add(like);
                    }
                    case "IN" ->{
                       String[] sp = sf.getValue().split(",");
                       Predicate in = root.get(sf.getColumnName()).in(List.of(sp));
                       predicateList.add(in);
                    }
                    case "JOIN" ->{
                        Predicate join  = criteriaBuilder.equal(root.join(sf.getJoinTable()).get(sf.getColumnName()), sf.getValue());
                        predicateList.add(join);
                    }
                }

            }
            if ("AND".equalsIgnoreCase(overalOp)) {
                return criteriaBuilder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])));
            } else {
                return criteriaBuilder.or(predicateList.toArray(predicateList.toArray(new Predicate[0])));
            }
        };

    }
    public List<Employee> getDetailsFromList(List<SearchSpecification> searchSpecificationList,String op){
        Specification<Employee> specification= getSpecificationForList(searchSpecificationList,op);
        return employeeRepo.findAll(specification);


    }


}
