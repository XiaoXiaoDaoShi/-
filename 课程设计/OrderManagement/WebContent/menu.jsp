<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List,pojo.Dish"
    pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE HTML>
<html>
	<head>
		<title>菜单</title>
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		
		<link href="css/style.css" rel='stylesheet' type='text/css' />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style-self.css"/>
		<script src="js/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>
		
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event) {
					event.preventDefault();
					$('html,body').animate({
						scrollTop: $(this.hash).offset().top
					}, 1000);
				});
			});
			
			$(function(){ 
			    $(".add").click(function(){ 									//添加一个数量
			        var t=$(this).parent().find('input[class*=text_box]'); 
			        t.val(parseInt(t.val())+1) 
			        
			    }) 
			    
			    
			    $(".min").click(function(){ 								//减去一个数量
			        var t = $(this).parent().find('input[class*=text_box]'); 
			            t.val(parseInt(t.val())-1) 
			            if(parseInt(t.val())<0){ 
			                t.val(0); 
			            } 
			    }) 
			});
			
			
			

		</script>
		<!---- start-smoth-scrolling---->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
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
							<c:if test="${sessionScope.user.userType==1 }">			<!-- 游客 ttpe==1 -->
								<li><a href="register.html">注册</a></li>
								<li><a href="login.html"> 登录</a></li>
								</c:if>
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
		<div class="menu-page">
			<!------ menu-header ----->
			<div class="menu-header">
				<div class="container">
					<h1><span>Resto</span> Menu</h1>
				</div>
			</div>
			<!------ menuheader ----->
			<!----- menu ---->
			<div class="menu">
				<!----- main-menus ----->		
				<div class="main-menus menu-menu">
					<form action="OrderDishServlet" method="post">
					<div class="container">
						<h5><span> </span> To day special Dishes <span> </span></h5>
						<div class="menus-left">
							<%List<Dish> dishList = (List<Dish>)session.getAttribute("dishList"); %>
							<input type="hidden" name="" id="total" value="${dishList.size() }" />
						<c:forEach items="${dishList}" var="d" varStatus="idx">				<!-- 显示菜品 -->
						
							<div class="menus-left-grid" style="display: flex;align-items: center;">												
							<div class="item" style="display: inline-block;">						
								<h3>${d.dishName }</h3>			<!-- 菜名-->
								<span>做法: ${d.dishCookWay }, 口味: ${d.dishFlavour }, 人气: ${d.dishCount }</span>  	<!--口味-->
							</div>
							<div class="item-line">
								<span> </span>
							</div>
							<div class="item-price">
								<label>￥${d.dishPrice }</label>					<!--价格-->
							</div>
							<input type="checkbox" name="dishCheck" id="" value="${ idx.index}" style="margin: 0 10px;"/>
								
								<button type="button" class="min" style="background: url(images/min.jpg); height: 20px;width: 20px;background-size:100% 100%;margin: 0 2px;"> </button>
								<input class="text_box" name="number_${idx.index }" type="text" value="0" style="width: 50px;" /> 
								
								<button type="button" class="add" style="background: url(images/add.jpg); height: 20px;width: 20px;background-size:100% 100%;margin: 0 2px;"> </button>
								
							<div class="clearfix"> </div>
						</div>
						
						</c:forEach>
							
						</div>

						<div class="clearfix"> </div>
							<div class="page-normal">
							<!-- 
								<span class="page-prev">&lt;</span>
								
								<span class="page-current">1</span>
								<a href="#">2</a>
								<a href="#">3</a>
								<a href="#">4</a>
								<a href="#">5</a>
								……
								<a href="#">199</a>
								<a href="#">200</a>
								<a href="#">&gt;</a>
								 -->
							</div>
							<input type="hidden" name="" id="current-index" value="" />
						<c:if test="${sessionScope.user.userType==2}">
						<input class="loadmore" type="submit" name="" id="" value="去购物车" />
						</c:if>
						<c:if test="${sessionScope.user.userType!=2}">
							<span class="loadmore">要登录后才能点单噢！ </span>
						</c:if>
						<!-- <a class="loadmore" href="#" style="text-align: center;"></a> -->
						<!----- load- more-items ----->
					</form>
					</div>
				</div>
				<!----- main-menus ----->
			</div>
			<div class="clearfix"> </div>
			<!----- menu ---->
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
									items: 4,
									lazyLoad: true,
									autoPlay: true,
									navigation: false,
									navigationText: false,
									pagination: true,
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
							<span>68, rue de la Couronne</span>
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
						<p>Copyright &copy; 2014.Company name All rights reserved.<a target="_blank" href="http://www.freemoban.com/"></a></p>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
		</div>
		<!----- footer ----->

	</body>
	<script type="text/javascript">
		var total = $("#total").val();
		total = parseInt(total); //总数
		var items = document.getElementsByClassName("menus-left-grid"); //所有的menu块 
		var eachNumber = 10; //每页数量
		var totalPages = parseInt(total / eachNumber);

		function setDivNone() {
			for (var i = 0; i < total; i++) {
				// items[i].style.display="none";
				$(items[i]).css("display", "none");
			}
		};

		function changePage(index) {
			var begin = eachNumber * index;
			begin = begin - 1;
			var end = begin + eachNumber;
			if (end > total) {
				end = total;
			}
			setDivNone();
			for (var i = begin; i < end; i++) {
				$(items[i]).css("display", "flex");

			}
			changeIndex(index);
			$("#current-index").val(index);
		};

		function clearChildren() {
			$(".page-normal").empty();
		};

		function changeIndex(index) {
			//<span class="page-prev">&lt;</span>
			var index_block = $(".page-normal");
			clearChildren();

			//前5个的时候
			if (index < 5) {

				if (index == 1) {
					var block = '<span class="page-prev-none">&lt;</span>';

				} else {
					var block = '<a href="javascript:void(0)" onclick="prev_click()" class="page-prev">&lt;</a>'		//上一页
				}
				index_block.append(block);
				for (var i = 0; i < 5; i++) {

					if (i == (index - 1)) {
						var block = '<a class="page-current" >' + index + '</a>'; //当前页

					} else {
						var block = '<a href="javascript:void(0)" onclick="changePage(' + (i + 1) + ')">' + (i + 1) + '</a>';
					}

					index_block.append(block);
				}
				index_block.append("<label>...</label>");
				var block_2 = '<a href="javascript:void(0)" onclick="changePage(' + (totalPages - 1) + ')">' + (totalPages - 1) +
					'</a>';
				var block_3 = '<a href="javascript:void(0)" onclick="changePage(' + totalPages + ')">' + totalPages + '</a>';
				var block_4 = '<a href="javascript:void(0)" onclick="back_click()">&gt;</a>';
				index_block.append(block_2);
				index_block.append(block_3);
				index_block.append(block_4);
			} else { //5个之后
				var block = '<a class="page-prev" href="javascript:void(0)" onclick="prev_click()">&lt;</a>'; //上一页
				index_block.append(block);
				
				if (index < totalPages - 5) {		//	还不是最后五个
					for (var i = index - 5; i < index; i++) {
						if (i == (index - 1)) {
							var block = '<a class="page-current" >' + index + '</a>'; //当前页
						} else {
							var block = '<a href="javascript:void(0)" onclick="changePage(' + (i + 1) + ')">' + (i + 1) + '</a>';
						}
						index_block.append(block);
					}
					
					index_block.append("<label>...</label>");
					var block_2 = '<a href="javascript:void(0)" onclick="changePage(' + (totalPages - 1) + ')">' + (totalPages - 1) +
						'</a>';
					var block_3 = '<a href="javascript:void(0)" onclick="changePage(' + totalPages + ')">' + totalPages + '</a>';

					var block_4 = '<a class="page-back" href="javascript:void(0)" onclick="back_click()">&gt;</a>';
					index_block.append(block_2);
					index_block.append(block_3);
					index_block.append(block_4);
				}else{//最后五个了
					
					var block_1 = '<a href="javascript:void(0)" onclick="changePage(' + 1 + ')">' + 1 + '</a>';
					var block_2 = '<a href="javascript:void(0)" onclick="changePage(' + 2 + ')">' + 2 + '</a>';
					
					index_block.append(block_1);
					index_block.append(block_2);
					index_block.append("<label>...</label>");
					
					for(var i=totalPages-5;i<totalPages;i++){
						if (i == (index - 1)) {
							var block = '<a class="page-current" >' + index + '</a>'; //当前页
						} else {
							var block = '<a href="javascript:void(0)" onclick="changePage(' + (i + 1) + ')">' + (i + 1) + '</a>';
						}
						index_block.append(block);
					}
					
					if(index == totalPages){
						var block_4 = '<a class="page-prev-none">&gt;</a>';
					}else{
						var block_4 = '<a href="javascript:void(0)" onclick="back_click()" class="page-prev">&gt;</a>';		//下一页
					}
					index_block.append(block_4);
				}
			}	
		};
		
		function prev_click(){
			var current_page = $("#current-index").val();
			var targe_index = parseInt(current_page) - 1;
			changePage(targe_index);
		}
		
		
		function back_click(){
			var current_page = $("#current-index").val();
			var targe_index = parseInt(current_page) + 1;
			changePage(targe_index);
		}
		$(function(){
			changePage(1);
		});
	</script>
</html>
