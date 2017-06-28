package org.gb.util;

import java.util.HashMap;
import java.util.List;

/** 
 * <pre>项目名称：ssh-02    
 * 类名称：Page    
 * 类描述：    分页工具类
 * 创建人：  朱冀京
 * 创建时间：2017年2月16日 下午1:43:49    
 * 修改人： 朱冀京
 * 修改时间：2017年2月16日 下午1:43:49    
 * 修改备注：       
 * @version </pre>    
 */
public class PageUtil<T> {
	
	//当前页
	private Integer cpage;
	
	//每页条数
	private Integer pageSize;
	
	//总条数---数据库查询
	private Integer totalCount;
	
	//总页数---总条数%每页条数 == 0  ? 总条数/每页条数 : 总条数/每页条数+1
	private Integer totalPage;
	
	//每页开始位置 ----  (当前页-1)*每页条数
	private Integer start;
	
	// 分页list
	private List<T> list;
	
	//查询条件map
	private HashMap<String,Object> whereMap;
	

	public HashMap<String, Object> getWhereMap() {
		return whereMap;
	}

	public void setWhereMap(HashMap<String, Object> whereMap) {
		this.whereMap = whereMap;
	}

	public Integer getCpage() {
		if (cpage == null) {
			this.cpage = 1;
		}
		return cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public Integer getPageSize() {
		if (pageSize == null) {
			this.pageSize = 5;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
//		总页数---总条数%每页条数 == 0  ? 总条数/每页条数 : 总条数/每页条数+1
		this.totalPage = this.totalCount%getPageSize() == 0 ? this.totalCount/getPageSize() : this.totalCount/this.getPageSize()+1;
		
	}

	public Integer getTotalPage() {     
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

//	每页开始位置 ----  (当前页-1)*每页条数
	public Integer getStart() {
		start = (getCpage()-1)*getPageSize();
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
	
		
	 
	
	
	
	
	
	
	
	
	
}
