package org.gb.vo;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;


public class SysUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8102542876644062334L;


	/* long
	 *  String 
	 * UUID随机字符串
	 *  ID生成器 <工具类>---固定规则+随机数
	 *      */
	private String id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdatetime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedatetime;
	private String loginname;
	private String pwd;
	private String name;
	private Integer sex;
	private Integer age;
	private String photo;
	
	
	// 此属性是业务字段不存数据库，虚拟属性
	private String ip;
	
	//拥有的角色id字符串
	private String roleIds;
	
	//验证码
	private String imgcode;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getImgcode() {
		return imgcode;
	}

	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}

	public String getId() {
		/*if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();*/
		
		return id;
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

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}
