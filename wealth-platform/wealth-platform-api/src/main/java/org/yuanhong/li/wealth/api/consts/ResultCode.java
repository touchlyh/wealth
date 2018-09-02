package org.yuanhong.li.wealth.api.consts;

public enum ResultCode {

	SUCCESS(2000,"成功"),
	FAIL(3000,"失败"),
	PARAM_ERROR(400,"参数错误"),
	RES_EXSISTED(4001,"资源已存在"),
	OP_FORBIDON(4003,"无权限"),
	RES_NOT_EXSISTED(4004,"数据不存在"),
	SYSTEM_EXCEPTION(5000,"系统异常");
	
	private int code;
	
	private String desc;
	
	private ResultCode(int code, String desc) {
		this.code = code;
		
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
