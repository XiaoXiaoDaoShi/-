<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
	 
		<div id="main" style="width: 95%;height:500px;"></div>
		<script src="../js/echarts.js"></script>
		<script type="text/javascript">
			var data = new Array();
			<c:forEach items="${requestScope.dishList}" var="d">
			var item = new Array()
				item["value"] = "${d.dishCount}";
				item["name"] = "${d.dishName}";
				data.push(item);
			</c:forEach>
			console.info(data);
			var myChart = echarts.init(document.getElementById('main'));
			option = {
			    backgroundColor: '#2c343c',
			
			    title: {
			        text: '销量前十',
			        left: 'center',
			        top: 20,
			        textStyle: {
			            color: '#ccc'
			        }
			    },
			
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			
			    visualMap: {
			        show: false,
			        min: 80,
			        max: 600,
			        inRange: {
			            colorLightness: [0, 1]
			        }
			    },
			    series : [
			        {
			            name:'销售量',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '50%'],
			            data:[
			            	<c:forEach items="${requestScope.dishList}" var="d" varStatus="idx">
			    				{value:"${d.dishCount}",name:"${d.dishName}"},			
			    			</c:forEach>
			            ].sort(function (a, b) { return a.value - b.value; }),
			            roseType: 'radius',
			            label: {
			                normal: {
			                    textStyle: {
			                        color: 'rgba(255, 255, 255, 0.3)'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    lineStyle: {
			                        color: 'rgba(255, 255, 255, 0.3)'
			                    },
			                    smooth: 0.5,
			                    length: 10,
			                    length2: 20
			                }
			            },
			            itemStyle: {
			                normal: {
			                    color: '#c23531',
			                    shadowBlur: 200,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            },
			
			            animationType: 'scale',
			            animationEasing: 'elasticOut',
			            animationDelay: function (idx) {
			                return Math.random() * 200;
			            }
			        }
			    ]
			};
			
			myChart.setOption(option);
		</script>
		
	</body>
</html>
