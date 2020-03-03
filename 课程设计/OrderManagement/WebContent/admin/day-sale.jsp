<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title>日销量折线图	</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		
	</head>
	
	<body>
	
		<div id="main" style="width: 70%;height:500px;"></div>
		<script src="../js/echarts.js"></script>
		<script type="text/javascript">
			var myChart = echarts.init(document.getElementById('main'));
			var dateList = new Array();
			var dataList = new Array();
			<c:forEach items="${requestScope.daySaleList}" var="d">
				dateList.push("${d.getPraseDate()}");
				dataList.push("${d.sale}");
			</c:forEach>
			
			option = {
			    title: {
			        text: '每日销售额(万元)'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['每日销售额']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: dateList//['周一','周二','周三','周四','周五','周六','周日']//x轴
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'营业额',
			            type:'line',
			            stack: '总量',
			            data:dataList//[120, 132, 101, 134, 90, 230, 210]
			        }
			       
			    ]
			};
	myChart.setOption(option);
		</script>		
	</body>

</html>
