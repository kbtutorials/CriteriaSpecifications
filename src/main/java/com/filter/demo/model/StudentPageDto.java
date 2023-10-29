package com.filter.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentPageDto {
    private Integer pageNumber=0;
    private Integer pageSize=5;

    public Pageable getPageable(StudentPageDto studentPageDto){
        Integer pageNum = Objects.nonNull(studentPageDto.getPageNumber())?studentPageDto.getPageNumber():this.pageNumber;
        Integer size = Objects.nonNull(studentPageDto.getPageSize())? studentPageDto.getPageSize():this.pageSize;
        return PageRequest.of(pageNum,size);
    }

}
