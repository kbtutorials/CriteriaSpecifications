package com.filter.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InputDto {
    private Integer pageNumber=0;
    private Integer size=5;

   public Pageable getPageable(InputDto dto){
        Integer pageNum = Objects.nonNull(dto.getPageNumber())?dto.getPageNumber():this.pageNumber;
        Integer pageSize = Objects.nonNull(dto.getSize())?dto.getSize():this.size;

        return PageRequest.of(pageNum,pageSize);
    }
}
