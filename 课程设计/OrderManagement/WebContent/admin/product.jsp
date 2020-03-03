<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />

<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<title>品牌管理</title>
</head>
<script type="text/javascript">
	
	
</script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 菜品管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<form class="Huiform" method="post" action="AdminDishAddServlet" target="_self"   enctype="multipart/form-data"><!-- enctype="multipart/form-data" -->
			<input type="text" placeholder="名称" value="" class="input-text" style="width:120px" name="dishName">
			<input type="text" placeholder="价格" value="" class="input-text" style="width:60px" name="dishPrice">
			<input type="text" placeholder="做法" value="" class="input-text" style="width:120px" name="dishCookWay">
			<input type="text" placeholder="口味" value="" class="input-text" style="width:120px" name="dishFlavour">
			<span class="btn-upload form-group">
			<input class="input-text upload-url" type="text" name="image" id="uploadfile-2" readonly style="width:200px">
			<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
			<input type="file" multiple name="dishImage" class="input-file" id="dishImage">
			</span> <span >
			
			</span><button type="submit" class="btn btn-success" id="submit-btn" name="" ><i class="Hui-iconfont">&#xe600;</i> 添加</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"></span> <span class="r">共有数据：<strong>${sessionScope.dishList.size()}</strong> 条</span> </div>
	<div class="mt-20">
	
		<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					
					<th width="70">ID</th>
					<th width="200">图片</th>
					<th width="120">名称</th>
					<th width="80">价格</th>
					
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<!--  循换输出菜品 -->
				<c:forEach items="${sessionScope.dishList}" var="d">
					<tr class="text-c">
					
					<td>${d.dishId}</td>
					<td><img width="200" height="100" src="../${d.dishImage}"></td>
					
					<td class="text-l">${d.dishName }</td>
					<td class="text-l">${d.dishPrice }</td>
					
					<td class="f-14 product-brand-manage"><a style="text-decoration:none"  href="product_modify.jsp?dishId=${d.dishId }" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" href="javascript:;" onclick="product_del(this,'${d.dishId}')" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
					
				</c:forEach>
				
			</tbody>
		</table>
	</div>
	
</div>

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> 


<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	/**"aaSorting": [[ 0, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[1,6]}// 制定列不参与排序
	]*/
});




/*商品-删除*/
function product_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		if(index==1){
			$.ajax({
			type: 'POST',
			url: 'AdminDishDeleteServlet',
			async: false,
			data:"id=" + id,
			dataType: 'text',
			success: function(data){
				if(data == 1){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					//$(obj).parents("tr").remove();
					layer.msg('删除失败!',{icon:1,time:1000});
				}
				
			},
			error:function(data) {
				console.log("error");
			},
		});		
		}else{
			;
		}
		
	});
}
$(function(){  
    /** 验证是否导入上传成功  */  
    $("form").ajaxForm(function(data){    
        if(data==1){  
        	layer.msg('上传成功',{icon:1,time:1000}); 
        	setTimeout(function(){
        		location.reload();
        	},1000);
        }else{
        	layer.msg('上传失败',{icon:1,time:1000});
        	//setTimeout(location.reload(),3000);
		}  
    });       
});  
</script>
</body>
</html>