package org.gb.sys.dao;

import java.util.List;

import javax.annotation.Resource;

import org.gb.util.PageUtil;
import org.gb.vo.SysUser;
import org.gb.vo.SysUser;
import org.gb.vo.SysUserRole;
import org.gb.vo.business.UserPwd;
import org.gb.util.PageUtil;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class SysUserDaoImpl extends SqlMapClientDaoSupport implements SysUserDao {
	
	@Resource(name="sqlMapClient")
	public void setSqlMapClientFactory(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}

	/**
	 * 校验用户---账户名称是否已存在
	 * @param user
	 * @return
	 */
	@Override
	public SysUser checkSysUser(SysUser user) {
		return (SysUser) this.getSqlMapClientTemplate().queryForObject("user.selectSysUserByloginName", user);
	}

	/**
	 * 注册用户
	 * @param sysUser
	 */
	@Override
	public void saveSysUser(SysUser sysUser) {
		Object insert = this.getSqlMapClientTemplate().insert("user.insertSysUser", sysUser);
		System.out.println(insert);
	}

	/**
	 * 查询用户
	 * @param userPage
	 * @return
	 */
	@Override
	public PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage) {
		int totalCount = (int) this.getSqlMapClientTemplate().queryForObject("user.selectUserCount",userPage);

		//查询当前页list集合
		List<SysUser> userList = this.getSqlMapClientTemplate().queryForList("user.selectUserList", userPage);

		userPage.setTotalCount(totalCount);
		userPage.setList(userList);
		return userPage;


	}

	@Override
	public List<SysUserRole> selectUserRoleList(SysUser user) {
		return this.getSqlMapClientTemplate().queryForList("userRole.getUserRoleList",user);
	}
}
