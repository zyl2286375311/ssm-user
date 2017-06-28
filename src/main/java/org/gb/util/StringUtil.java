package org.gb.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	
	
	/*
	 * 截取字符串
	 * --去除项目的访问路径
	 */
	public static String subStringWebProjectUrl(HttpServletRequest request, String uri){
		
		String webUrl =  request.getContextPath();
		
		if (null != uri) {
			String url = uri.substring(webUrl.length());
			return url;
		}
		
		return "";
	}

}
