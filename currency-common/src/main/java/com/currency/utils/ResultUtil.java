package com.currency.utils;

import com.currency.exception.BusinessException;

/**
 * @author 吴良勇
 * @date 2019/8/30 11:33
 */
public final class ResultUtil {
    //业务异常
    public static final String RS_RS_ERROR = "201";
    //系统异常
    public static final String RS_RS_SYS_ERROR = "9999";

    public static final String RS_RS_SUC = "200";

    public static BaseResult suc() {
        return suc(null);
    }
    public static BaseResult suc(Object data) {
        BaseResult result = BaseResult.newInstance();
        result.setRs(RS_RS_SUC);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }
    public static BaseResult error() {
        return error("操作失败");
    }
    public static BaseResult error(String msg) {
        BaseResult result = BaseResult.newInstance();
        result.setRs(RS_RS_ERROR);
        result.setMsg(msg);
        return result;
    }
    public static BaseResult error(Throwable e) {
        BaseResult result = BaseResult.newInstance();
        String rs = RS_RS_SYS_ERROR;
        if(e!=null){
            if(e instanceof BusinessException){
                rs = RS_RS_ERROR;
            }
        }
        result.setRs(rs);
        result.setMsg(e.getMessage());
        return result;
    }

}
