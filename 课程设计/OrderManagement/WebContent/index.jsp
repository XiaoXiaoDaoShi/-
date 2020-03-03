<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
		<title>首页</title>
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="js/jquery-3.4.1.js"></script>
		 <!-- Custom Theme files -->
		<link href="css/style.css" rel='stylesheet' type='text/css' />
   		 <!-- Custom Theme files -->
   		 <!---- start-smoth-scrolling---->
		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
		 <!---- start-smoth-scrolling---->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		</script>
		<!----start-top-nav-script---->
		<script>
			$(function() {
				var pull 		= $('#pull');
					menu 		= $('nav ul');
					menuHeight	= menu.height();
				$(pull).on('click', function(e) {
					e.preventDefault();
					menu.slideToggle();
				});
				$(window).resize(function(){
	        		var w = $(window).width();
	        		if(w > 320 && menu.is(':hidden')) {
	        			menu.removeAttr('style');
	        		}
	    		});
			});
		</script>
		<!----//End-top-nav-script---->
	</head>
	<body>
		<!----- start-header---->
			<div id="home" class="header">
					<div class="top-header">
						<div class="container">
						<div class="logo">
							<a href="index.jsp"><img src="images/logo.png" title="Resto" /></a>
						</div>
						<!----start-top-nav---->
						 <nav class="top-nav">
							<ul class="top-nav">
								<li><a href="index.jsp">主页</a></li>
								<li ><a href="menu.jsp">菜单</a></li>
								<c:if test="${sessionScope.user.userType==1 }"><!-- 给游客提供的登录和注册 -->
								<li><a href="register.html">注册</a></li>
								<li><a href="login.html"> 登录</a></li>
								</c:if>
								<li><a href="">${sessionScope.user.userName}</a>欢迎您</li>
								<c:if test="${sessionScope.user.userType==2}">		<!-- 会员提供注册功能 -->
									<li><a href="LogOutServlet">注销</a></li>
								</c:if>
							</ul>
							
						</nav>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- //End-header---->
			<!----- banner ---->
			<div class="banner text-center">
				<div class="container">
					<div class="banner-info">
						<h1>This Restaurant Is Awesome</h1>
						<h2><label> </label>欢迎光临<label> </label></h2>
					</div>
				</div>
			</div>
			<!----- banner ---->
			<!----- menu ---->
			<div class="menu">
				<div class="container">
					<div class="menu-head text-center">
						<h3><span>热门推荐</span></h3>
					</div>
				</div>
				<!----- main-menus ----->
				<div class="main-menus">
					<div class="container">
					<div class="menus-left col-md-6">
						
						<c:forEach items="${sessionScope.hotDishList}" var="d" begin="0" end="4">
						
						<div class="menus-left-grid">
							<div class="item">
								<h3>${d.dishName }</h3>
								<span>${d.dishCookWay }~${d.dishFlavour }~人气:${d.dishCount}</span>
							</div>
							<div class="item-line">
								<span> </span>
							</div>
							<div class="item-price">
								<label>￥${d.dishPrice}</label>
							</div>
							<div class="clearfix"> </div>
						</div>
						</c:forEach>
					</div>
					<div class="menus-right col-md-6">
						<c:forEach items="${sessionScope.hotDishList}" var="d" begin="5" end="9">
						
						<div class="menus-left-grid">
							<div class="item">
								<h3>${d.dishName }</h3>
								<span>${d.dishCookWay }~${d.dishFlavour }~人气:${d.dishCount}</span>
							</div>
							<div class="item-line">
								<span> </span>
							</div>
							<div class="item-price">
								<label>￥${d.dishPrice}</label>
							</div>
							<div class="clearfix"> </div>
						</div>
						</c:forEach>
						
					</div>
					<div class="clearfix"> </div>
				
				<!----- load- more-items ----->
				<a class="loadmore" href="menu.jsp">去菜单看看吧<!-- <label><span> </span></label> --></a>
				<!----- load- more-items ----->
				</div>
				</div>
				<!----- main-menus ----->
			</div>
			<div class="clearfix"> </div>
			<!----- menu ---->
			<!----- fearuted-diesh ----->
			
			<!----- fearuted-diesh ----->
			<!----- gallery ----->
			<div class="gallery">
				<div class="container">
					<h3>The Gallery</h3>
					<div class="gallery-grids">
						<div class="gallery-grids-left">
							<div class="gallery-grid-left">
								<img src="images/g1.jpg" title="name" />	
								<div class="gallery-grid-left-caption">
									<h4>Item Name</h4>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi convallis, mi ac placerat iaculis, nulla sem eleifend felis, sed ullamcorper augue massa in</p>
									<a class="more" href="#">ReadMore</a>
								</div>
							</div>
							<div class="clearfix"> </div>
						</div>
						<!----->
						<div class="gallery-grids-right">
							<div class="gallery-grids-right-top">
								<div class="gallery-grids-right-top-grid">
									<div class="gallery-grids-right-top-grid-pic">
										<img src="images/g2.jpg" title="name" />	
									</div>
									<div class="gallery-grids-right-top-grid-caption">
										<h4>Item Name</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi convallis, mi ac placerat iaculis, nulla sem eleifend felis, sed ullamcorper augue massa in</p>
										<a class="more" href="reservation.html">ReadMore</a>
									</div>
								</div>
								<div class="gallery-grids-right-top-grid">
									<div class="gallery-grids-right-top-grid-pic">
										<img src="images/g3.jpg" title="name" />	
									</div>
									<div class="gallery-grids-right-top-grid-caption">
										<h4>Item Name</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi convallis, mi ac placerat iaculis, nulla sem eleifend felis, sed ullamcorper augue massa in</p>
										<a class="more" href="reservation.html">ReadMore</a>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							<!----->
							<div class="gallery-grids-right-bottom">
								<div class="gallery-grids-right-top-grid">
									<div class="gallery-grids-right-top-grid-pic">
										<img src="images/g4.jpg" title="name" />	
									</div>
									<div class="gallery-grids-right-top-grid-caption">
										<h4>Item Name</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi convallis, mi ac placerat iaculis, nulla sem eleifend felis, sed ullamcorper augue massa in</p>
										<a class="more" href="reservation.html">ReadMore</a>
									</div>
								</div>
								<div class="gallery-grids-right-top-grid">
									<div class="gallery-grids-right-top-grid-pic">
										<img src="images/g5.jpg" title="name" />	
									</div>
									<div class="gallery-grids-right-top-grid-caption">
										<h4>Item Name</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi convallis, mi ac placerat iaculis, nulla sem eleifend felis, sed ullamcorper augue massa in</p>
										<a class="more" href="reservation.html">ReadMore</a>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- gallery ----->
			<!----- footer ----->
			<div class="footer">
				<div class="container">
					<div class="footer-grids">
						<div class="col-md-3 footer-grid">
							<p>
								<span>New York Restaurant</span>
								<span>3926 Anmoore Road</span>
								<span>New York, NY 10014</span>
								<span>718-749-1714</span>
							</p>
						</div>
						<div class="col-md-3 footer-grid">
							<p>
								<span>France Restaurant</span>
								<span>68, rue  de la Couronne</span>
								<span>75002 PARIS</span>
								<span>02.94.23.69.56</span>
							</p>
						</div>
						<div class="col-md-3 footer-grid">
							<ul>
								<li><a href="#">Blog</a></li>
								<li><a href="#">Careers</a></li>
								<li><a href="#">Privacy Policy</a></li>
								<li><a href="contact.html">Contact</a></li>
							</ul>
						</div>
						<div class="col-md-3 footer-grid copy-right">
							<a href="#"><img src="images/flogo.png" title="resto" /></a>
							<p>Copyright &copy; 2014.Company name All rights reserved.</p>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- footer ----->
	
</body>
</html>

