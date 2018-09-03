package org.yuanhong.li.wealth.api.consts;

public enum UserRoleStatus {

	NORMAL(0L,"正常"),
	TIME_OUT(-1L,"超时");
	
	private Long code;
	
	private String desc;
	
	private UserRoleStatus(Long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
