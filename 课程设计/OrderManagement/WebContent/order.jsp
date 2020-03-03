<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>

	<head>
		<title>订单填写</title>
		<!-- Meta tags -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Catering  Order Form Responsive Widget" />
		
		<script src="js/jquery-3.4.1.js"></script>
		<script src="js/ajax-form.js"></script>
		
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
			/**
			$(function(){  
			    //验证上传是否成功
			    $("form").ajaxForm(function(data){   
			    	console.info(data);
			        if(data==1){  
			            //alert("上传成功！");     
			        }else{
						//alert("上传失败");
					}  
			    });          
			}); 
			*/
		</script>
		<!-- //Meta tags -->
		<!-- Stylesheet -->
		<link href="css/wickedpicker.css" rel="stylesheet" type='text/css' media="all" />
		<link rel="stylesheet" href="css/jquery-ui.css" />
		<link href="css/style-order.css" rel='stylesheet' type='text/css' />
		<!-- //Stylesheet -->
		<!--fonts-->
		<link href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
		<link href="//fonts.googleapis.com/css?family=Josefin+Sans:300,400,600,700" rel="stylesheet">
		<!--//fonts-->
	</head>

	<body>
		<!--background-->
		<h1> 创建你的订单 </h1>
		<div class="bg-agile">
		
			<div class="book-appointment">

				<div class="book-agileinfo-form">
					<form action="CreateOrderSevlet" method="post">

						<h2 class="sub-head-w3ls">Catering Order Form</h2>
						<div class="main-agile-sectns">
							<div class="agileits-btm-spc form-text1">
								<input type="text" name="orderGuestName" placeholder="名字" required="">
							</div>
							<div class="agileits-btm-spc form-text2">
								<input type="text" name="orderGuestPhone" placeholder="电话号码" required="">
							</div>
						</div>
						
						<div class="agileits-btm-spc form-text2">
							<input type="text" name="orderRemark" placeholder="备注(没有则输入无)" required="" value="无">
						</div>
						<div class="main-agile-sectns">
							<div class="agileits-btm-spc form-text1">
								<select id="country1" onchange="change_country(this.value)" class="frm-field required sect">
									<option value="">用餐人数(可选)</option>
									<option value="">1~2</option>
									<option value="">3~4</option>
									<option value="">5~6</option>
									<option value="">7~8</option>
									<option value="">9+</option>
								</select>
							</div>
							<div class="agileits-btm-spc form-text2">
								<select id="country" onchange="change_country(this.value)" class="frm-field required">
									<option value="">取餐时间(可选)</option>
									<option value="8:00am"> 8:00am </option>
									<option value="9:00am"> 9:00am </option>
									<option value="10:00am"> 10:00am </option>
									<option value="11:00am"> 11:00am </option>
									<option value="12:00pm"> 12:00pm </option>
									<option value="1:00pm"> 1:00pm </option>
									<option value="2:00pm"> 2:00pm </option>
									<option value="3:00pm"> 3:00pm </option>
									<option value="4:00pm"> 4:00pm </option>
									<option value="5:00pm"> 5:00pm </option>
									<option value="6:00pm"> 6:00pm </option>
									<option value="7:00pm"> 7:00pm </option>
									<option value="8:00pm"> 8:00pm </option>
									<option value="9:00pm"> 9:00pm </option>
									<option value="10:00pm"> 10:00pm </option>
									<option value="11:00pm"> 11:00pm </option>
								</select>
							</div>

						</div>
						<div class="wthree-text">
							<h6>派送方法</h6>
							<ul class="radio-w3ls">
								<li>
									<input type="radio" id="a-option" name="orderGetWay" value="1"> 
									<label for="a-option">自己取</label>
									<div class="check"></div>
								</li>
								<li>
									<input type="radio" id="b-option" name="orderGetWay" value="2">
									<label for="b-option">送达</label>
									<div class="check">
										<div class="inside"></div>
									</div>
								</li>

							</ul>
							<div class="clear"></div>
						</div>
						<c:set var="totalPrice" value="0"></c:set>
						<c:forEach items="${sessionScope.orderDishList}" var="d">					
					
							<c:set var="totalPrice" value="${d.getTotalPrice() + totalPrice}"></c:set>
							
						</c:forEach>
							<input type="hidden" value="${totalPrice}" name="totalPrice">
						<div class="clear"></div>
						
						<input type="submit" value="Submit">

						<div class="clear"></div>
					</form>
				</div>

			</div>
			<div class="dish-list">
				<h3>What we provide you</h3>
				
				<ul>
				<c:forEach items="${sessionScope.orderDishList}" var="d">					
					<li><span>.</span>${d.dishName} x${d.orderNumber}   ----- ￥${d.getTotalPrice()}</li><!-- 菜名 数量 总计 -->
					
				</c:forEach>
				</ul>
				
				
				<h3>总计: ￥${totalPrice }</h3>
			</div>

		</div>
		<!--copyright-->

		<!--//copyright-->
		
		<!-- Time -->
		<script type="text/javascript" src="js/wickedpicker.js"></script>
		<script type="text/javascript">
			$('.timepicker').wickedpicker({
				twentyFour: false
			});
		</script>
		<!--// Time -->
		<!-- Calendar -->
		<script src="js/jquery-ui.js"></script>
		<script>
			$(function() {
				$("#datepicker,#datepicker1,#datepicker2,#datepicker3").datepicker();
				
				
			});
			
			
		</script>
		<!-- //Calendar -->

	</body>

</html>
