<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'down.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="down.jsp">
    <input type="hidden" name="filename" value="参观者.xls">
    <input type="submit" value="下载Excel模版">
  </form>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String filename = request.getParameter("filename");
		if(filename!=null && filename!=""){
		
		filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(filename);
		OutputStream o = response.getOutputStream();
		byte b[] = new byte[500];	 
		String serverpath = request.getRealPath("\\");
		File fileLoad = new File(serverpath + filename);
	 
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition","attachment; filename="
				+ URLEncoder.encode(filename,"utf-8"));
		long fileLength = fileLoad.length();
		String length1 = String.valueOf(fileLength);
		response.setHeader("Content_Length", length1);
		FileInputStream in = new FileInputStream(fileLoad);
		int n;
		while ((n = in.read(b)) != -1) {
			o.write(b, 0, n);
		}
		in.close();
		out.clear();
		out = pageContext.pushBody();
		}
	%>
</body>
</html>
