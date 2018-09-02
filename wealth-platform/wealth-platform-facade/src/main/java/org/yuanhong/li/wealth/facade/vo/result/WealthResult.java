package org.yuanhong.li.wealth.facade.vo.result;

import org.yuanhong.li.wealth.api.consts.ResultCode;

public class WealthResult<T> {

	private int code;
	
	private String msg;
	
	private T data;
	
	public WealthResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public WealthResult(ResultCode code, T data) {
		this.code = code.getCode();
		this.msg = code.getDesc();
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isSuccess() {
		return this.code == ResultCode.SUCCESS.getCode();
	}
}
