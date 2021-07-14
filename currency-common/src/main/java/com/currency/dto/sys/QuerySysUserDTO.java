package com.currency.dto.sys;

import com.currency.dto.PageDTO;
import lombok.Data;

@Data
public class QuerySysUserDTO extends PageDTO {
    private String userName;
    private String userCode;
    private String realname;

}
