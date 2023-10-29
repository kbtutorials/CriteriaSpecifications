package com.filter.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaginationInputDto {

    private Integer pageNum=0;

    private Integer size=5;

    private String columnName;

    private String sortOrder;

    public Pageable getPage(PaginationInputDto dto){
        int pageNumber = Objects.nonNull(dto.getPageNum())?dto.getPageNum():this.pageNum;
        int pageSize = Objects.nonNull(dto.getSize())?dto.getSize():this.size;
        String[] columNames = columnName.split(",");
        boolean sortFlag ="ASC".equalsIgnoreCase(dto.getSortOrder());
        Sort s = Sort.by(sortFlag? Sort.Direction.ASC: Sort.Direction.DESC,columNames[0])
                .and(Sort.by(sortFlag? Sort.Direction.ASC: Sort.Direction.DESC,columNames[1]));

        return PageRequest.of(pageNumber,pageSize,s);

    }

}
