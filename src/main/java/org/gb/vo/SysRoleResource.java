package org.gb.vo;

import java.io.Serializable;

public class SysRoleResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369521962364999932L;
	
	private String sysRoleId;
	
	private String sysResourceId;

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysResourceId() {
		return sysResourceId;
	}

	public void setSysResourceId(String sysResourceId) {
		this.sysResourceId = sysResourceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
