<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <style type="text/css">
    body{
     background-color: #f1f1f1;
     color: #777;
     font-size: 15px;
    }
    .loginPanel{
     border: 1px solid #eee;
     background-color: #fff;
     width: 400px;
     height: 270px;
     margin: 180px auto;    
     border-radius:5px;   
    }
    .loginParam{
      margin: 23px 100px;
    }
    #username,#userpasswd{
     width: 200px;
     height:30px;
     overflow: hidden;
    }
    #btn{
       width: 200px;
       height:30px;
       background-color: #00A0D2;
       border-radius:5px;
       font-size: 16px;
       border: 1px solid #00A0D2;
       color: #fff;
    }
    .imglogin{
      display: block;
      position: absolute;
      left: 545px;
      top: 55px;
    }
  </style>
  <%
          String msg = (String)request.getAttribute("msg");
   %>
  <body>
   <img alt="monkey" src="images/logo1.png" class="imglogin">
    <div class="loginPanel">
     <form action="<%=path%>/servlet/LoginServlet" method="post">
     <p align="center"><font color="red">
     <%
        if(msg!=null){
        
      %>
          <%=msg %>
          <%} %>
     </font> </p>
       <div class="loginParam">
        <label for="username">用户名</label><br>
        <input type="text" name="username" id="username" required="required"><br><br>
         <label for="userpasswd">密码</label><br>
        <input type="password" name="userpasswd" id="userpasswd" required="required"><br><br>
        <input type="submit" value="登录" id="btn">
        </div>
     </form>
     </div>
  </body>
</html>
