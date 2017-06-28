package org.gb.util;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao  extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	public void setSqlMapClientFactory(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	

}
