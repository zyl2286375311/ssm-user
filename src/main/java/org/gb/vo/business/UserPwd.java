package org.gb.vo.business;

import java.io.Serializable;
import java.util.Date;


import org.gb.util.MD5Util;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Administrator
 *
 */
public class UserPwd  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5418464405845252012L;
	
	
	private String userId;
	
	private String newPwd;
	
	private String oldPwd;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatePwdTime; //当前系统时间

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//密码加密
	public String getNewPwd() {
		String jiami = "";
		if (newPwd != null && !"".equals(newPwd)) {
			jiami = MD5Util.md5(newPwd);
		}
		return jiami;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getUpdatePwdTime() {
		return new Date();
	}

	public void setUpdatePwdTime(Date updatePwdTime) {
		this.updatePwdTime = updatePwdTime;
	}
	
	
	
	
	

}
