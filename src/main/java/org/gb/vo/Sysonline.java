package org.gb.vo;

import java.util.Date;
import java.util.UUID;


public class Sysonline implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -197003612835768568L;
	
	private String id;
	private String loginname;
	private String ip;
	private Date createdatetime;
	private String type;// 1.登录0.注销

	public Sysonline() {
	}

	public Sysonline(String id) {
		this.id = id;
	}

	public Sysonline(String id, String loginname, String ip, Date createdatetime, String type) {
		this.id = id;
		this.loginname = loginname;
		this.ip = ip;
		this.createdatetime = createdatetime;
		this.type = type;
	}

	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
