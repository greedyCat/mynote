<%@page import="mine.note.vo.RecordType"%>
<%@page import="mine.note.vo.RecordVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="mine.note.vo.UserVo"%>
<%@page import="mine.util.WebLevelUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>笔记|生活点滴</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/sweetalert.css">
	

  </head>
      <div id="head">
          <div class="logo">
           <a href="<%=path%>/recordcenter/upload.jsp"><img alt="welcome" src="images/logo.png"></a>
          </div>
          <div class="ulogin">
             <%
                UserVo userVo = WebLevelUtil.getUser(request);
                if(userVo!=null){
             %>
             <a href="<%=path%>/recordcenter/myinfo.jsp"><%=userVo.getAlias()%></a>
             <%}else{%>
             <a href="<%=path%>/login.jsp">登录</a>
             <%} %>
          </div> 
          <div class="ulogout">
             <a href="<%=path%>/servlet/LogoutServlet"><img src="images/power.png"></a>
          </div>
      </div>
      <div id="center">
         <div id="left">
           <div class="title">
             <p class="left-top">
               <span class="left-title"><img src="images/note.png"></span>
               <span class="left-add" onclick="newtype('<%=path %>')"><img src="images/add01.png"></span>
             </p> 
           </div>
           <div class="recordtype">
           <%
               List<RecordType> types = (List<RecordType>)request.getAttribute("recordtype");
               for(RecordType vo:types){
               
            %>
             <div class="onetype">
              <p><span class="onetype-name"><a href="<%=path %>/servlet/NewType?method=query&typeid=<%=vo.getTypeid()%>"><%=vo.getTypename()%></a></span></p>
               <img alt="edit" src="images/edit.png" class="typeedit" onclick="edittype('<%=vo.getTypeid()%>','<%=path%>')">
               <img alt="del" src="images/shanchu01.png" class="typedel" onclick="deltype('<%=vo.getTypeid()%>','<%=path%>>')">
             </div>
             <%} %>
           </div>
         </div>
         
         <div id="right">
           <div class="right-top">
              <div class="searcher">
                  <input type="text" name="keyword" class="keyword" placeholder="输入标题...">
                  <span class="keyword-search" onclick="swal('正在建设...', 'Building', 'warning')"><img alt="search" src="images/search.png"></span>
              </div>
              <div class="creatnote">
                 <span class="creatnote_add"><img alt="new" src="images/add02.png"></span>
                 <span class="creatnote_title">新建笔记</span>
              </div>
           </div>
           <%
              List<RecordVo> rvos = (List<RecordVo>)request.getAttribute("records");
              if(rvos.size()>0){
              for(RecordVo recordVo:rvos){
            %>
           <div class="info">
             <div class="info-title">
                 <p><a href="<%=path%>/servlet/DetailShowServlet?id=<%=recordVo.getRecordid()%>" class="titles" target="_blank"><%=recordVo.getRecordtitle()%></a>                 
                 <span class="times"><%=recordVo.getPastTime()%></span> 
                 </p>
                 <img alt="edit" src="images/edit.png" class="edit" onclick="swal('正在建设...', 'Building', 'warning')">
                 <img alt="delete" src="images/shanchu01.png" class="delete" onclick="delnote('<%=recordVo.getRecordid()%>','<%=path %>')">                                 
             </div>
             <div class="info-content">
               <div class="contents">
                   <%=recordVo.getRecordinfo()%>
               </div>
             </div>
             
           </div>
           <%}}else{ %>
           <div class="info">
               <p align="center" style="line-height:130px;color:#7AC161">主人偷懒，没有留下任何东西>-<</p>
           </div>
           <%} %>
            
         </div>
      </div>
  <body>
     
  </body>
  <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/mynote.js"  charset="utf-8"></script>
</html>
