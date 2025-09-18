package com.itwillbs.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JSFunctionClass {
	
	public static void alertAndBack(HttpServletResponse response, String msg) {
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			String script = "<script> ";
			script += "alert('"+msg+"'); ";
			script += "history.back(); ";
			script += "</script> ";
			
			out.print(script);
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void alertAndMove(HttpServletResponse response, String msg, String url) {
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			String script = "<script> ";
			script += "alert('"+msg+"'); ";
			script += "location.href='"+url+"'; ";
			script += "</script> ";
			
			out.print(script);
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
