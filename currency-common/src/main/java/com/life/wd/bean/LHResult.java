package com.life.wd.bean;

import com.life.wd.enums.LHResultEnum;
import lombok.Data;

@Data
public class LHResult {
    private LHResultEnum result;
    private PaiItem lo;
    private PaiItem hu;
    private int count;
}
