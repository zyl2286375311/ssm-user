package org.gb.interceptors;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.gb.util.ConfigUtil;
import org.gb.vo.business.SessionInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录  拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	
	private List<String> excludeUrls;
	
	private Map<String,String> map;
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

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
		
		System.err.println("LoginInterceptor登录拦截器-------preHandle()执行");
		
		System.err.println("LoginInterceptor登录拦截器-------preHandle()执行时间"+new Date());
		
		System.err.println( "当前请求的URI ----- " + request.getRequestURI());
		
		System.err.println( "当前请求的URL ----- "  + request.getRequestURL());
		
		//web项目访问路径
		String  xiangmuUrl = request.getContextPath();  //   /ssi-mv-easyui
		
		//前台浏览器请求的URI路径
		String URI =  request.getRequestURI();  // /ssi-mv-easyui/user/checkSysUserLogin.do
		
		//将  项目的访问路径 去除截去掉
		String url = URI.substring(xiangmuUrl.length());  //  /user/checkSysUserLogin.do
		
		
		//startsWith() 字符串以什么开头 ---放过不需要被拦截的请求
//		boolean b =	url.startsWith("/login");
		
		
		SessionInfo sessionInfo = 	(SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if (null != sessionInfo  && null != sessionInfo.getUser()) {
			return true;
		}else if (null != request.getRequestURI() &&  excludeUrls.contains(url) ) { // excludeUrls.contains() 判断集合是否包含该元素
			return true; //放过 用户注册，用户登录，是否重名等 不需要拦截的请求
		}
		else{
			response.sendRedirect(request.getContextPath()+"/index.jsp");
//			request.getRequestDispatcher("").forward(request, response);
			return false;	
		}
	}

	/**
	 * 后处理 
	 * 但是渲染modelAndView视图之前执行！！！
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.err.println("LoginInterceptor登录拦截器-------postHandle()执行");
	}

	/**
	 * 请求处理完毕之后执行
	 * 但是只有当 preHandle()返回true才会执行 afterCompletion()该方法
	 * 当 preHandle()返回false  不不不  执行 afterCompletion()该方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.err.println("LoginInterceptor登录拦截器-------afterCompletion()执行");
		System.err.println("LoginInterceptor登录拦截器-------afterCompletion()执行时间"+new Date());
	}

}
