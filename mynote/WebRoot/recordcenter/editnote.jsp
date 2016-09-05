<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文字|生活</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/edit.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/sweetalert.css"> 
    <link rel="stylesheet" href="<%=path%>/kindeditor/themes/default/default.css" />
  </head>
  
  <body>
     <div class="edit-left">
        <a href="<%=path%>/servlet/BackCenter"><img alt="" src="<%=path%>/images/logos.png"></a>
     </div>
     <div class="edit-right">
       <div class="savebt" onclick="savenote('<%=path%>')"><p>保存</p></div>
       <div class="right-top">
         <span class="noteimg noteback"><img alt="" src="<%=path%>/images/snote.png"></span>
         <span class="noteback">笔记类型: <select id="typeNote">
            
         </select>
         </span>
       </div>
       <div class="right-next">
          <form action="#" method="post">
            <input type="hidden" name="method" value="create" class="note-method">
            <p class="wrap-title"><input type="text" class="note-title" name="title" placeholder="写下笔记标题"></p>
            <p class="wrap-detail"><textarea cols="123" rows="25" class="note-detail" name="content" id="content"></textarea></p>
          </form>
       </div>
     </div>
  </body>
  <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/sweetalert.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/mynote.js"  charset="utf-8"></script>
  <script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
  <script charset="utf-8" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
</html>
<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
			
			$(function(){
			  
			 $.ajax({
			 
					url: "<%=path%>/servlet/OjectJson",
					type: "post",
					dataType: "json", 
					success:function(data){
					  var typeNote = $("#typeNote");
					  $.each(data,function(i){
					    typeNote.append("<option value="+data[i].typeid+">"+data[i].typename+"</option>")
					  })
					}
			})
		})
</script>