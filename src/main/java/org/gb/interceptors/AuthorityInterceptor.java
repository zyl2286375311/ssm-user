package org.gb.interceptors;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.gb.sys.service.SysResourceService;
import org.gb.util.ConfigUtil;
import org.gb.util.StringUtil;
import org.gb.vo.SysResource;
import org.gb.vo.business.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限拦截器
 * @author Administrator
 *
 *    比对  当前用户请求的URL    与  当前用户拥有的resource的url
 */
public class AuthorityInterceptor  extends HandlerInterceptorAdapter {
	
	@Autowired
	private SysResourceService sysResourceService;
	
	private List<String> excludeUrls;
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}


	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//当前用户请求的url
		String uri = request.getRequestURI();
		
		System.out.println("当前请求的URL-----"+uri);
		
		//去除项目访问路径
		String url = StringUtil.subStringWebProjectUrl(request, uri);
		
		
		if (null != request.getRequestURI() &&  excludeUrls.contains(url) ) { // excludeUrls.contains() 判断集合是否包含该元素
			return true; //放过 用户注册，用户登录，是否重名等 不需要拦截的请求
		}
		
		//当前用户 拥有的resource的url
		//获取当前用户的id
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String userId = sessionInfo.getUser().getId();
		
		//调用service方法查询数据库---根据用户id查询拥有的resource资源权限
		List<SysResource> resourceList =   sysResourceService.selectResourceByUserId(userId);
		
		//将url存放到list集合中
		List<String> urlList = new ArrayList<>();
		for (int i = 0; i <resourceList.size() ; i++) {
			urlList.add(resourceList.get(i).getUrl());
		}
		
//		比对  当前用户请求的URL    与  当前用户拥有的resource的url
		if (!"".equals(url)  &&  urlList.contains(url) ) {
			return true;
		}else if(!"".equals(url) && url.startsWith("/main")){
			return true;
		}else{
			request.getSession().setAttribute("msg", "您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
			//如果是ajax请求响应头会有x-requested-with 
			//ajax请求--无权限时提示信息
			// equalsIgnoreCase() 比较字符串忽略大小写
			if (request.getHeader("x-requested-with")!= null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
					//response响应流
					response.setCharacterEncoding("UTF-8");
					
//					字符流
					PrintWriter pw =   response.getWriter();
					pw.write("noAuthority您没有访问此资源的权限！<br/>请联系超管赋予您<br/>");
					
					//字节流
					/*
					 * ServletOutputStream out = response.getOutputStream();
                    out.print("noAuthority您没有访问此资源的权限！<br/>请联系超管赋予您<br/>");//返回给前端页面的没有权限的标志
                    out.flush();
                    out.close();*/
					pw.flush();
					pw.close();
					pw = null;
                    return false;
			}else{
				//非ajax请求--无权限时提示信息
				response.sendRedirect(request.getContextPath()+"/error/noAuthority.jsp");
				return false;
			}
		}
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	

}
