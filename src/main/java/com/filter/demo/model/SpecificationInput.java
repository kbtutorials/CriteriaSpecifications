package com.filter.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationInput {
    private String columnName;
    private String value;
    private String sortColumn;
    private String sortOrder;
    private Integer pageNum;
    private Integer pageSize;
}
