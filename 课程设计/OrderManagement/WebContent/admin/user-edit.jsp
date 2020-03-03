<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />

<title>编辑用户 </title>

</head>
<body>
<article class="page-container">
	<form action="AdminUserEditServlet" method="post" class="form form-horizontal" >
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>Id：${requestScope.tempUser.userId }</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" class="input-text" value="${requestScope.tempUser.userId }"  id="userId" name="userId">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${requestScope.tempUser.userName }" placeholder="${requestScope.tempUser.userName }" id="username" name="userName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${requestScope.tempUser.userPassword }" placeholder="${requestScope.tempUser.userPassword }" id="userPassword" name="userPassword">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio-box">
					<c:if test="${requestScope.tempUser.userGender==1 }">				<!-- 默认选中一个性别 -->
					<input value="1" name="userGender" type="radio" id="sex-1" checked="checked" >
					</c:if>
					<c:if test="${requestScope.tempUser.userGender!=1 }">
					<input value="1" name="userGender" type="radio" id="sex-1" >
					</c:if>
					<label for="sex-1">男</label>
				</div>
				<div class="radio-box">
					
					<c:if test="${requestScope.tempUser.userGender ==0}">
					<input value="0" type="radio" id="sex-2" name="userGender" checked="checked">
					</c:if>
					<c:if test="${requestScope.tempUser.userGender !=0}">
					<input value="0" type="radio" id="sex-2" name="userGender">
					</c:if>
					<label for="sex-2">女</label>
				</div>
				<div class="radio-box">
					<c:if test="${requestScope.tempUser.userGender ==2}">
					<input value="2" type="radio" id="sex-3" name="userGender" checked="checked"> 
					</c:if>
					
					<c:if test="${requestScope.tempUser.userGender !=2}">
					<input value="2" type="radio" id="sex-3" name="userGender" > 
					</c:if>
					<label for="sex-3">保密</label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${requestScope.tempUser.phone }" placeholder="${requestScope.tempUser.phone }" id="mobile" name="phone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="email" class="input-text" placeholder="${requestScope.tempUser.email }" value="${requestScope.tempUser.email }" name="email" id="email">
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>


<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>


<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>

<script type="text/javascript">


$(function(){
	$("form").ajaxForm(function(data){   
    	console.info(data);
        if(data==1){  
        	layer.msg('修改成功',{icon:1,time:1000}); 
        	var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			//parent.layer.close(index);
        	setTimeout(function(){
        		parent.layer.close(index);
        	},1000);
        	    
        }else{
        	layer.msg('修改失败',{icon:1,time:1000}); 
        	setTimeout(location.reload(),3000);
		}  
    })
});

</script> 

</body>
</html>