package com.zyp.log;

import android.util.Log;

import com.zyp.app.AppConfig;
import com.zyp.util.CommonUtil;

/**
 * Created by zhangyipeng on 2017/6/15.
 */
public class Logger {

	private static final boolean IS_PRINT_LOG = AppConfig.IS_LOG;
	
	private static String logFormat(Object... args) {
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			if (!(args[i] == null)){
				if (i > 0) {
					sb1.append(" ");
				}
				sb1.append(args[i]);
			}
		}

		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
		StringBuilder sb = new StringBuilder();

		sb.append("(")
				.append(stackTrace.getFileName())
				.append(":")
				.append(stackTrace.getLineNumber())
				.append(")")
				.append("#")
				.append(stackTrace.getMethodName())
				.append(":")
				.append(CommonUtil.unicode2GBK(sb1.toString()));
		return sb.toString();
	}
	
	public static void v(String tag, Object...messages){
		if(IS_PRINT_LOG){
			Log.v(tag, logFormat(messages));
		}
	}
	
	public static void d(String tag, Object...messages){
		if(IS_PRINT_LOG){
			Log.d(tag, logFormat(messages));
		}
	}
	
	public static void i(String tag, Object...messages){
		if(IS_PRINT_LOG){
			Log.i(tag, logFormat(messages));
		}
	}
	
	/**
	 * 程序crash后捕获log干扰信息太多，修改为Log.d() 如需要使用Log.e()函数，调用Logger.error();
	 * @param tag
	 * @param messages
	 */
	public static void e(String tag, Object...messages){
		if(IS_PRINT_LOG){
			Log.e(tag, logFormat(messages));
		}
	}
	
	public static void error(String tag, Object...messages){
		if(IS_PRINT_LOG){
			Log.e(tag, logFormat(messages));
		}
	}
	
	public static void w(String tag, Object...messages){
		if(IS_PRINT_LOG){
			Log.w(tag, logFormat(messages));
		}
	}
	
	public static void log(String str) {
		if (IS_PRINT_LOG) {
			System.out.print(str + "\n");
		}
	}
	public static void eSuper(String tag, String info) {
		StackTraceElement[] ste = new Throwable().getStackTrace();
		int i = 1;
			StackTraceElement s = ste[i];
			String className = s.getClassName().contains(".") ? s
					.getClassName().substring(
							s.getClassName().lastIndexOf("."),
							s.getClassName().length()) : s.getClassName();

			Log.e(tag, String.format("======[%s][%s][%s]=====%s",
					className, s.getLineNumber(), s.getMethodName(),
					info));
	}
}
