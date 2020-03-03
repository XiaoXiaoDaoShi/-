<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />。
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />

<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<title>订单管理</title>
</head>
<script type="text/javascript">
	
	
	finish_order = function(){
		
	}
	
</script>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 订单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <span class="r">共有数据：<strong>${requestScope.orderFormList.size() }</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="30">ID</th>
					<th width="30">订单号</th>
					<th width="10">价格</th>
					<th width="60">创建日期</th>
					<th width="30">客户名字</th>
					<th width="30">客户手机</th>
					<th width="20">取餐方式</th>
					<th width="200">备注</th>
					<th width="50">状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.orderFormList }" var="o">
				<tr class="text-c">
					<td><input name="" type="checkbox" value=""></td>
					<td>${o.orderId }</td>
					<td>${o.orderNumber }</td>
					<td>${o.orderPrice }</td>
					<td class="text-l">${o.getParseDate()}</td>
					<td>${o.orderGuestName }</td>
					<td>${o.orderGuestPhone }</td>
					<c:if test="${o.orderGetWay ==1}">
						<td>自己取</td>
					</c:if>
					
					<c:if test="${o.orderGetWay==2 }">
						<td>派送</td>
					</c:if>
					
					
					<td class="text-l">${o.getRemark() }</td>
					<c:if test="${o.orderState==0 }">
						<td class="text-l">未完成</td>
					</c:if>
					<c:if test="${o.orderState==1 }">
						<td class="text-l">已完成</td>
					</c:if>
					
					<td class="f-14 product-brand-manage">
					<c:if test="${o.orderState==0 }">
					<a style="text-decoration:none"  href="javascript:void(0)" onClick="order_finish(this,'${o.orderId}')" title="完成"><i class="Hui-iconfont">&#xe6df;</i></a> </c:if><a style="text-decoration:none" class="ml-5" onClick="order_del(this,'${o.orderId}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,6]}// 制定列不参与排序
	]
});

/*订单-删除*/
function order_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		if(index==1){
			$.ajax({
			type: 'POST',
			url: 'AdminOrderDeleteServlet',
			async: false,
			data:"id=" + id,
			dataType: 'text',
			success: function(data){
				if(data==1){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					//$(obj).parents("tr").remove();
					layer.msg('删除失败!',{icon:1,time:1000});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
		}else{
			;
		}
		
	});
}
 
function order_finish(obj,id){
	layer.confirm('确认要完成订单吗？',function(index){
		if(index==1){
			$.ajax({
			type: 'POST',
			url: 'AdminOrderFinishServlet',
			async: false,
			data:"id=" + id,
			dataType: 'text',
			success: function(data){
				if(data==1){
					
					layer.msg('已完成!',{icon:1,time:1000});
					setTimeout(location.reload(),3000);
				}else{
					//$(obj).parents("tr").remove();
					layer.msg('无法完成!',{icon:1,time:1000});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
		}else{
			;
		}
		
	});
} 
</script>
</body>
</html>