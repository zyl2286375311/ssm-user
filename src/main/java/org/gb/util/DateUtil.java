package org.gb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {
	
	//log DateUtil的日志记录器
	private static final  Logger log = Logger.getLogger(DateUtil.class);
	
	public static String formatDateToString(Date date,String format) throws Exception{//yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
		String str = "";
		SimpleDateFormat sdf = null;
		
		if (null != format && !"".equals(format)) {
			sdf = new SimpleDateFormat(format);
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			str = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();//打印异常堆栈信息---详细
			e.getMessage(); //获取异常信息
			throw new Exception("日期转换为字符串失败。。。。");
		}finally {
			//日志记录的级别
			log.info("");//信息
			log.error("日期转换为字符串失败");//错误
			log.warn("");//警告
			log.debug("");//debug断点
			log.fatal("");//fatal 致命，重大错误
			log.trace("");//trace 追踪 追溯
		}
		return str;
	}

}
