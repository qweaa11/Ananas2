<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<script>
	$(document).ready(function () {
		$(".menu-toggle, .head-toggle").click(function(e) {
		    e.preventDefault();
		    $("#wrapper").toggleClass("toggled");
		});
	})
	
</script>

	<div class="container">
		
		
		<div class="row">
			
			<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="margin-bottom: 10px;"> 
				<div class="navbar-header">
					<button type="button" class="navbar-toggle head-toggle" data-toggle="collapse" >
						<img alt="" src="resources/img/logout.png">
					</button>
					<button type="button" class="navbar-toggle head-toggle" data-toggle="collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.ana">ANANAS</a> 
				</div>  
				
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right" style="margin-right: 20px;">
						<li><a href="logout.ana">로그아웃</a></li>
					</ul>
				</div>
				
				
			</div>
			
		</div>
		
	</div>
	
	<div class="container" style="margin-top: 40px;">
		<div id="wrapper" style="clear: both;" class="toggled">      
		        
		    
	
	        <!-- Sidebar -->
	        <div id="sidebar-wrapper">
	            <ul class="sidebar-nav" style="margin-left:0;">
	                <li class="sidebar-brand">
                    
                        <a href="#menu-toggle"  class="menu-toggle" style="margin-top:20px;float:right;" > 
                        	<i class="fa fa-bars " style="font-size:20px !Important;" aria-hidden="true" aria-hidden="true"></i> 
	                    </a>
	                </li>
	                
	                <li style="visibility: hidden;"> 
	                    <a href="#"><i class="fa fa-sort-alpha-asc " aria-hidden="true"> </i></a>
	                </li>
	                
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo2-mini.png">
						<span style="margin-left: 10px;">도서관리</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="bookList.ana">도서목록</a></li>
							<li><a href="bookRegister.ana">도서등록</a></li> 
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo3-mini.png">
						<span style="margin-left: 10px;">회원관리</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="memberList.ana">회원목록</a></li>
							<li><a href="memberRegist.ana">회원등록</a></li> 
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo4-mini.png">
						<span style="margin-left: 10px;">대출/예약/반납</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="r3.ana">대출/반납/예약</a></li>
							<li><a href="bookReturned.ana">반납목록</a></li>
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo1-mini.png">
						<span style="margin-left: 10px;">도서관 관리</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="#">도서관 목록</a></li>
							<li><a href="#">사서 등록</a></li> 
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo6-mini.png">
						<span style="margin-left: 10px;">통계</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="#">통계</a></li>
						</ul>
					</li>
					
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo5-mini.png">
						<span style="margin-left: 10px;">사서게시판</span><span class="caret" style="margin-left: 3px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="#">공지 게시판</a></li>
							<li><a href="#">자유 게시판</a></li>
						</ul>
					</li>
	                
	            </ul>
	        </div>
	        
	        <!-- /#sidebar-wrapper -->
	        
			</a>
	   
	   		<!-- /#wrapper --> 
				
		</div>

	</div> 
