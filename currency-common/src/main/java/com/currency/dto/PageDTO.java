package com.currency.dto;

import lombok.Data;

@Data
public class PageDTO extends BaseDTO {

    private Integer pagesize = 10;
    private Integer current = 1;
}
