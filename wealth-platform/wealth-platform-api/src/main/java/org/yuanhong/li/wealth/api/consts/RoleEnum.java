package org.yuanhong.li.wealth.api.consts;

public enum RoleEnum {

	VISTOR(100L,"vistor"),
	LOGIN(200L, "login user"),
	VIP(300L,"pay login user"),
	SHARE(400L,"share login user");
	
	private long code;
	private String desc;
	private RoleEnum(long code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
