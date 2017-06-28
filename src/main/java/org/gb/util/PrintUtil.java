package org.gb.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class PrintUtil {
	
	/**
	 * <pre>ajaxOut(ajax打印流打印信息)   
	 * 创建人：朱冀京   
	 * 创建时间：2017年2月28日 上午11:15:20    
	 * 修改人：朱冀京     
	 * 修改时间：2017年2月28日 上午11:15:20    
	 * 修改备注： 
	 * @param msg</pre>
	 */
	public static void ajaxOut(String msg ,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (null != pw) {
				pw.close();
			}
		}
	}
	
	

}
