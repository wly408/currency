package com.currency.dto;

import lombok.Data;

@Data
public class PageDTO extends BaseDTO {

    private Integer pageSize = 10;
    private Integer current = 1;

    public Integer getPageSize() {
        return pageSize <0?10: pageSize;
    }

    public Integer getCurrent() {
        return current<0?1:current;
    }
}
