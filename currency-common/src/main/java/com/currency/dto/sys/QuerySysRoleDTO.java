package com.currency.dto.sys;

import com.currency.dto.PageDTO;
import lombok.Data;

@Data
public class QuerySysRoleDTO extends PageDTO {
    private String roleName;
}
