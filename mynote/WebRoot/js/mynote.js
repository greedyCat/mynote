$(function(){
	
	$(".creatnote").click(function(){
		window.location.href = "recordcenter/editnote.jsp";
	});
	$.each($(".onetype"),function(p1,p2){
		
	 
	})
  
})

 function savenote(url){
	 	 
     url=url+'/servlet/MyInfo';
     var $typenote = $("#typeNote").val();    
     var $typenote1 = $("#typeNote").find("option:selected").text();   
     var $title = $(".note-title").val();
     var $detail = $(".note-detail").val();
     var $method = $(".note-method").val();
     if($title.trim()==""||$title==null){
    	 swal("忘了写标题了呢!", "标题起的好,生活没烦恼！", "warning");
    	 return false;
     }
     if(editor.isEmpty()){
    	 swal("不写笔记的孩子不是好孩子", "快点回去写笔记", "warning");
    	 return false; 
     }
	 swal({
			title: "确定保存？", 
			text: "你看着办", 
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false,
			confirmButtonText: "Okay，留下",
			confirmButtonColor: "#ec6c62"
			}, function() {
				$.ajax({
					url: url,
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					type: "post", 
					data:{
						method:$method,
						title:$title,
						typenote:$typenote,
						typename:$typenote1,
						content:editor.html()
					}
				}).done(function(data) {
					swal("操作成功!", "已成功保存数据！", "success");
					 $(".note-title").val("");
					 editor.html("");
				}).error(function(data) {
					swal("OMG", "保存操作失败了!", "error");
				});
			});
}
 
function querytype(typeid){
	
	swal("正在建设...", "Building", "warning");
}
function delnote(noteid,url){
	 url=url+'/servlet/MyInfo';
	 swal({
			title: "要删除了", 
			text: "准备好了吗", 
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false,
			confirmButtonText: "okay，删除",
			confirmButtonColor: "#ec6c62"
			}, function() {
				$.ajax({
					url: url,
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					type: "post", 
					data:{
						method:'delete',
						noteid: noteid
					}
				}).done(function(data) {
					swal("完成任务!", "进入下一环节！", "success");
					window.location.reload();
				}).error(function(data) {
					swal("OMG", "删除操作失败了!", "error");
				});
			});
}
function newtype(url){
	url=url+'/servlet/NewType';
	swal({   
		title: "新建笔记类型",   
		text: "这里可以输入并确认:",   
		type: "input",   
		showCancelButton: true,   
		closeOnConfirm: false,   
		animation: "slide-from-top",   
		inputPlaceholder: "填点东西到这里面吧" 
	}, function(inputValue){   
		if (inputValue === false) return false;      
		if (inputValue === "") {     
			swal.showInputError("请输入!");     
			return false   
		}
		$.ajax({
			url: url,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			type: "post", 
			data:{
				method:'add',
				typename: inputValue
			}
		}).done(function(data) {
			swal("操作成功!", "已成功添加类型："+inputValue, "success");
			window.location.reload();
		}).error(function(data) {
			swal("OMG", "操作失败了!", "error");
		});
	});
}

function deltype(id,url){
	swal("正在建设...", "Building", "warning");
}

function edittype(id,url){
	
	url=url+'/servlet/NewType';
	swal({   
		title:"修改笔记类型",   
		text: "这里可以输入并确认:",   
		type: "input",   
		showCancelButton: true,   
		closeOnConfirm: false,   
		animation: "slide-from-top",   
		inputPlaceholder: "填点东西到这里面吧" 
	}, function(inputValue){   
		if (inputValue === false) return false;      
		if (inputValue === "") {     
			swal.showInputError("请输入!");     
			return false   
		}
		$.ajax({
			url: url,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			type: "post", 
			data:{
				method:'update',
				typeid:id,
				typename: inputValue
			}
		}).done(function(data) {
			swal("操作成功!", "已成功修改类型："+inputValue, "success");
			window.location.reload();
		}).error(function(data) {
			swal("OMG", "操作失败了!", "error");
		});
		
	});
}
