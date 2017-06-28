package org.gb.vo;

import java.util.Date;
import java.util.UUID;

public class SysResourceType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3414912501336621431L;
	
	private String id;
	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String description;

	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
