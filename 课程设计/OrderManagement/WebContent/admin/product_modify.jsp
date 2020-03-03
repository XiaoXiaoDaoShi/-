<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />

<script src="../js/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<form action="AdminProductModifyServlet" method="post" class="form form-horizontal" id="form-article-add" enctype="multipart/form-data">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" class="input-text" value="${requestScope.tempDish.dishId}" placeholder="" id="" name="dishId">
				<label >
					${requestScope.tempDish.dishId}
				</label>
				
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>菜品名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${requestScope.tempDish.dishName}" placeholder="${requestScope.tempDish.dishName}" id="" name="dishName">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="dishPrice" id="" placeholder="${requestScope.tempDish.dishPrice }" value="${requestScope.tempDish.dishPrice }" class="input-text">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">做法：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="dishCookWay" id="" placeholder="${requestScope.tempDish.dishCookWay}" value="${requestScope.tempDish.dishCookWay}" class="input-text">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">口味：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="dishFlavour" id="" placeholder="${requestScope.tempDish.dishFlavour }" value="${requestScope.tempDish.dishFlavour }" class="input-text">
			</div>
		</div>
		<input type="hidden" name="dishImageUrl" value="${requestScope.tempDish.dishImage }">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">图片：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="uploader-thum-container">
					<div id="fileList" class="uploader-list"></div>
					<input type="file" name="dishImage" id="filePicker" value="选择图片" />
					<!-- <div id="filePicker" class="webuploader-pick">选择图片</div> -->
					<button type="submit" id="btn-star" class="btn btn-default btn-uploadstar radius ml-10">确认修改</button>
				</div>
			</div>
		</div>
		
	</form>
</div>
</body>

<script type="text/javascript">
	$(function(){  
	    /** 验证是否导入上传成功  */  
	    $("form").ajaxForm(function(data){   
	    	console.info(data);
	        if(data==1){  
	        	layer.msg('修改成功',{icon:1,time:1000}); 
	        	setTimeout(function(){
	        		location.reload();
	        	},1000);
	        	    
	        }else{
	        	layer.msg('修改失败',{icon:1,time:1000}); 
	        	setTimeout(function(){
	        		location.reload();
	        	},3000);
			}  
	    });       
	}); 
</script>
</html>