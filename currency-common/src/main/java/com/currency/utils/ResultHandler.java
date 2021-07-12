package com.currency.utils;

import com.currency.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultHandler<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String rs;
    private String msg;
    private T data;
    //业务异常
    public static final String RS_RS_ERROR = "201";
    //系统异常
    public static final String RS_RS_SYS_ERROR = "9999";

    public static final String RS_RS_SUC = "200";

    private ResultHandler() {

    }

    private ResultHandler(String rs, String msg) {
        this.rs = rs;
        this.msg = msg;
    }

    public static ResultHandler newInstance() {
        return new ResultHandler(RS_RS_SUC, "操作成功");
    }


    public static ResultHandler suc() {
        return suc(null);
    }

    public static ResultHandler suc(Object data) {
        ResultHandler result = ResultHandler.newInstance();
        result.setRs(RS_RS_SUC);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static ResultHandler error() {
        return error("操作失败");
    }

    public static ResultHandler error(String msg) {
        ResultHandler result = ResultHandler.newInstance();
        result.setRs(RS_RS_ERROR);
        result.setMsg(msg);
        return result;
    }

    public static ResultHandler error(Throwable e) {
        ResultHandler result = ResultHandler.newInstance();
        String rs = RS_RS_SYS_ERROR;
        if (e != null) {
            if (e instanceof BusinessException) {
                rs = RS_RS_ERROR;
            }
        }
        result.setRs(rs);
        result.setMsg(e.getMessage());
        return result;
    }
}
