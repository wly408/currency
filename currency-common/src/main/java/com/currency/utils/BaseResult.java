package com.currency.utils;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private String rs;
	private String msg;
	private T data;
	//业务异常
	public static final String RS_RS_ERROR = "201";
	//系统异常
	public static final String RS_RS_SYS_ERROR = "9999";

	public static final String RS_RS_SUC = "200";

	private BaseResult(){

	}

	private BaseResult(String rs, String msg) {
		this.rs = rs;
		this.msg = msg;
	}

	public static  BaseResult newInstance(){
		return new BaseResult(RS_RS_SUC,"操作成功");
	}
	
}
