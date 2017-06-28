package org.gb.vo;

import java.io.Serializable;

public class SysUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6518349295729377963L;

	
	private String sysUserId;
	
	private String sysRoleId;

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
