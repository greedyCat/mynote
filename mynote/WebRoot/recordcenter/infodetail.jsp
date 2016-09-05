<%@page import="mine.note.vo.RecordVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/detail.css">

  </head>
  
  <body>
     <div class="wrapper">
     <% 
         RecordVo vo = (RecordVo)request.getAttribute("revo"); 
         if(vo!=null){ 
      %>
       <p class="detail-title" align="center" id="detail-title"><%=vo.getRecordtitle() %></p>
       <p class="detail-bywho" align="center">
         <span>时间 : <%=vo.getCtime() %></span>  <span>作者 : <%=vo.getCusername()%></span>    <span>类型  : <%=vo.getTypename()%></span>
       </p>
       <div class="detail-content">
            <%=vo.getRecordinfo()%>
       </div>
       <%}else{ %>
       
         <p class="detail-title" align="center">系统出错了！</p>
       <%} %>
     </div>
  </body>
  <script type="text/javascript">
    window.onload=function(){
   
      document.title = document.getElementById("detail-title").innerHTML;
    }
  </script>
</html>