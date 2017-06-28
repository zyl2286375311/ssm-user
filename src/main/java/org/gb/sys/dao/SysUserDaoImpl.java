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

	@Override
	public PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage) {
		int totalCount = (int) this.getSqlMapClientTemplate().queryForObject("user.selectUserCount",userPage);

		//查询当前页list集合
		List<SysUser> userList = this.getSqlMapClientTemplate().queryForList("user.selectUserList", userPage);

		userPage.setTotalCount(totalCount);
		userPage.setList(userList);
		return userPage;
	}
}
