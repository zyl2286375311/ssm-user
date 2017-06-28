package org.gb.vo;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;



public class SysRole implements java.io.Serializable,Comparable<SysRole> {
	
	@Override
	public int compareTo(SysRole o) {
		return this.seq - o.seq;
		//return 0;默认情况，不排序
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7377438885694136912L;
	private String id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdatetime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedatetime;
	private String name;
	private String description;
	private String iconCls;
	private Integer seq;

	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
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

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}



}
