package com.filter.demo.controller;

import com.filter.demo.model.InputDto;
import com.filter.demo.model.PaginationInputDto;
import com.filter.demo.model.Student;
import com.filter.demo.model.StudentPageDto;
import com.filter.demo.repo.StudentRepo;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class StudentController {
    private final StudentRepo studentRepo;

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        studentRepo.save(student);
        log.info("Student data added");
        return ResponseEntity.ok(student);

    }


    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(@RequestParam(name = "columnName") String columnName,
                                        @RequestParam(name="order") String order){

        boolean orderFlag = false;
       if( StringUtils.isNotBlank(order) && "ASC".equalsIgnoreCase(order) ){
           orderFlag = true;
       }
        Sort s = Sort.by(orderFlag?Sort.Direction.ASC:Sort.Direction.DESC,columnName);

        return studentRepo.findAll(s);
    }

    @GetMapping("/getStudent")
    public Page<Student> getStudents(@RequestBody StudentPageDto dt){
        Pageable pg = dt.getPageable(dt);
        return studentRepo.findAll(pg);


    }

   @GetMapping("/getStudentByPages")
    public Page<Student> getStudentByPages(@RequestBody InputDto dto) {
       Pageable pageable = dto.getPageable(dto);
       return studentRepo.findAll(pageable);
   }


    @GetMapping("/getStudentByPagesSort")
    public Page<Student> getStudentByPagesSort(@RequestBody PaginationInputDto dto) {
      Pageable pg = dto.getPage(dto);
      return studentRepo.findAll(pg);
    }



}
