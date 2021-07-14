package com.currency.dto.sys;

import com.currency.dto.PageDTO;
import lombok.Data;

@Data
public class QuerySysMenuDTO extends PageDTO {
    private String menuName;
    private String menuCode;
}
