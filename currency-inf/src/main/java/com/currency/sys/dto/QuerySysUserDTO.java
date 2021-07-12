package com.currency.sys.dto;

import com.currency.sys.common.dto.PageDTO;
import lombok.Data;

@Data
public class QuerySysUserDTO extends PageDTO {
    private String userName;
}
