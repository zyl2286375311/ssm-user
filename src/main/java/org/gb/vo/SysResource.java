package org.gb.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;



public class SysResource implements java.io.Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 8783421438612966130L;

	

	private String id;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdatetime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedatetime;
	private String name;
	private String url;
	private String description;
	private String iconCls;
	private Integer seq;
	private String resourcetypeId;
	private String pid;// 虚拟属性，用于获得当前资源的父资源ID
	
	//是否是叶子节点
	private int leafNode=0;
	
	public int getLeafNode() {
		return leafNode;
	}

	public void setLeafNode(int leafNode) {
		this.leafNode = leafNode;
		if (this.leafNode == 1) {
			this.state="open";
		}
	}

	//业务字段---节点状态 closed open
	private String state="closed";
	
	
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getResourcetypeId() {
		return resourcetypeId;
	}

	public void setResourcetypeId(String resourcetypeId) {
		this.resourcetypeId = resourcetypeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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


	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "SysResource [id=" + id + ", name=" + name + ", description=" + description + ", resourcetypeId="
				+ resourcetypeId + ", pid=" + pid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdatetime == null) ? 0 : createdatetime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((iconCls == null) ? 0 : iconCls.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((resourcetypeId == null) ? 0 : resourcetypeId.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result + ((updatedatetime == null) ? 0 : updatedatetime.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysResource other = (SysResource) obj;
		if (createdatetime == null) {
			if (other.createdatetime != null)
				return false;
		} else if (!createdatetime.equals(other.createdatetime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (iconCls == null) {
			if (other.iconCls != null)
				return false;
		} else if (!iconCls.equals(other.iconCls))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (resourcetypeId == null) {
			if (other.resourcetypeId != null)
				return false;
		} else if (!resourcetypeId.equals(other.resourcetypeId))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (updatedatetime == null) {
			if (other.updatedatetime != null)
				return false;
		} else if (!updatedatetime.equals(other.updatedatetime))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	
	
	
}
