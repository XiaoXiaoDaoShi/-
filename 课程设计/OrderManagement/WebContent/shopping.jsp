<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List,pojo.Dish"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>购物车</title>
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<!-- <script src="js/jquery.min.js"></script> -->
		 <!-- Custom Theme files -->
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
								<li class="active"><a href="menu.jsp">菜单</a></li>
								<li><a href="">${sessionScope.user.userName}</a>欢迎您</li>
								<c:if test="${sessionScope.user.userType==2}">
									<li><a href="LogOutServlet">注销</a></li>
								</c:if>
							</ul>
							<a href="#" id="pull"><img src="images/nav-icon.png" title="menu" /></a>
						</nav>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- //End-header---->
			<!----- menu-page ----->
			<div class="news">
				<!------ menu-header ----->
				<div class="news-header">
					<div class="container">
						<h1><span>Resto</span> News</h1>
					</div>
				</div>
				<!------ menuheader ----->
			<div class="clearfix"> </div>
			<!----- reservation ---->
			<div class="News-page">
				<div class="container">
					<h2><span> </span>&nbsp;&nbsp;&nbsp;&nbsp; 购物车 &nbsp;<span> </span></h2>
					<p>在这里查看您所选择的菜品</p>
				<!----- recent-news ----->		
				<div class="recent-news" style="display: flex;flex-wrap: wrap;">
					<%List<Dish> orderDishList = (List<Dish>)session.getAttribute("orderDishList"); %>
					<c:set var="totalPrice" value="0"></c:set>
						<c:if test="${orderDishList==null }">
						<c:out value="啥都没有"></c:out>
						</c:if>
						
						<c:if test="${orderDishList!=null }">
						
						<c:forEach items="${orderDishList}" var="d" varStatus="idx">
					
						<div class="recent-news-grid">
							<div class="recent-news-grid-left">
								<a href="#"><img src="${d.getDishImage()}" title="name" /></a>
							</div>
							<div class="recent-news-grid-right">
								<p>名称：${d.getDishName() }</p>
								<p>小计：￥ ${d.getDishPrice() }</p>
								<p>数量：${d.orderNumber}</p>
								<p>总计：￥${d.getTotalPrice() }</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					
					<div class="clearfix"> </div>
					<c:set var="totalPrice" value="${d.getTotalPrice() + totalPrice}"></c:set>
					</c:forEach>
					</c:if>
				</div>
				<!----- recent-news ----->		
				<span id="">
					总价：	￥${totalPrice }
				</span>
				<a class="news-btn" href="order.jsp">提交</a>
				</div>
				
			<!----- fearuted-diesh ----->
			<div class="fearuted-diesh">
				<div class="container">
					<h3>Featured Dishes<span> </span></h3>
					<!----sreen-gallery-cursual---->
						<div class="sreen-gallery-cursual">
							 <!-- requried-jsfiles-for owl -->
							<link href="css/owl.carousel.css" rel="stylesheet">
							    <script src="js/owl.carousel.js"></script>
							        <script>
							    $(document).ready(function() {
							      $("#owl-demo").owlCarousel({
							         items :4,
							        lazyLoad : true,
							        autoPlay : true,
							        navigation : false,
							        navigationText :  false,
							        pagination : true,
							      });
							    });
							    </script>
							 <!-- //requried-jsfiles-for owl -->
							 <!-- start content_slider -->
						       <div id="owl-demo" class="owl-carousel">
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d1.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Fugiat nulla sint</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$30</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d2.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Daute irure dolor</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$24</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d3.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Officia deserunt mollit</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$60</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d4.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Pim minim veniam</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$17</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d1.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Fugiat nulla sint</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$30</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d2.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Daute irure dolor</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$24</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d3.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Officia deserunt mollit</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$60</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
					                <div class="item">
					                	<div class="item-grid">
					                		<div class="item-pic">
					                			<img src="images/d4.jpg" title="dish-name" />
					                		</div>
					                		<div class="item-info">
					                			<div class="item-info-left">
					                				<h4>Pim minim veniam</h4>
					                				<span class="item-rate"> </span>
					                			</div>
					                			<div class="item-info-right">
					                				<label>$17</label>
					                			</div>
					                			<div class="clearfix"> </div>
					                		</div>
					                	</div>
					                </div>
					                <!----->
				              </div>
						<!--//sreen-gallery-cursual---->
				</div>
			</div>
			</div>
			</div>
			</div>
			<!----- fearuted-diesh ----->
			<!----- End-Menu-page ----->
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
							<p>Copyright &copy; 2014.Company name All rights reserved.<a target="_blank" href="http://www.freemoban.com/">HTML5网站</a></p>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- footer ----->
	
</body>
</html>

