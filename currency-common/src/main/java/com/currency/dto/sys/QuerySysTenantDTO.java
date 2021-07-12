package com.currency.dto.sys;

import com.currency.dto.PageDTO;
import lombok.Data;

@Data
public class QuerySysTenantDTO extends PageDTO {
    private String tenantName;
    private String tenantCode;

}
