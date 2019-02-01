<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>

	var toggled;
	
	$(document).ready(function () {
		$(".menu-toggle, .head-toggle").click(function(e) {
		    e.preventDefault();
		    $("#wrapper").toggleClass("toggled");         
		});
		
		$(".sidetoggle").click(function () {
			
			var toggled = $("#wrapper").prop("class");
			
			if(toggled == "toggled") {
				$(".menu-toggle").click();
			}
		});
	})
	
</script>

	<div class="container">
		
		
		<div class="row">
			
			<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="margin-bottom: 10px;"> 
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" onclick="javascript:location.href='logout.ana'">    
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

						<c:if test="${sessionScope.loginLibrarian != null}">
							<li style="color: #ffffff; margin-top: 15px;">${sessionScope.loginLibrarian.libid}(${sessionScope.loginLibrarian.name}) 
								<c:if test="${sessionScope.loginLibrarian.status == 0}">[사서]</c:if>
								<c:if test="${sessionScope.loginLibrarian.status == 1}">[도서관장]</c:if>
							</li>
						</c:if>

						<c:if test="${sessionScope.loginAdmin != null}">
							<li style="color: #ffffff; margin-top: 15px;">${sessionScope.loginAdmin.adminid}(${sessionScope.loginAdmin.name})[관리자]</li>
							<li style="color: #ffffff; margin-top: 15px;">${sessionScope.loginAdmin.adminid}[관리자]</li>
						</c:if>
						
						<li><a href="schedulerRun.ana">스케줄러실행<i class="glyphicon glyphicon-hourglass"></i></a></li>
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
                    
                        <a href="#menu-toggle" class="menu-toggle" style="margin-top:20px;float:right;" > 
                        	<i class="fa fa-bars" style="font-size:20px !Important;" aria-hidden="true" aria-hidden="true"></i> 
	                    </a>
	                </li>
	                
	                <li style="visibility: hidden;"> 
	                    <a href="#"><i class="fa fa-sort-alpha-asc " aria-hidden="true"> </i></a>
	                </li>
	                
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo2-mini.png">
						<span style="margin-left: 10px;">도서관리</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="bookList.ana">도서목록</a></li>
							<li><a href="bookRegister.ana">도서등록</a></li> 
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo3-mini.png">
						<span style="margin-left: 10px;">회원관리</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="memberList.ana">회원목록</a></li>
							<li><a href="memberRegist.ana">회원등록</a></li> 
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo4-mini.png">
						<span style="margin-left: 10px;">대출/예약/반납</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="r3.ana">대출/반납/예약</a></li>
							<li><a href="rentalList.ana">대여목록</a></li>
							<li><a href="reservationList.ana">예약목록</a></li>
							<li><a href="bookReturned.ana">반납목록</a></li>
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo1-mini.png">
						<span style="margin-left: 10px;">도서관 관리</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
								<li><a href="resisterLibrary.ana">도서관 동록</a></li>
							<li><a href="libraryList.ana">도서관 목록</a></li>
							<li><a href="librarianRegist.ana">사서 등록</a></li> 
								<li><a href="librarianList.ana">사서 목록</a></li>
						</ul>
					</li>
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo6-mini.png">
						<span style="margin-left: 10px;">통계</span><span class="caret" style="margin-left: 3px; margin-right: 20px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="chart.ana">통계</a></li>
						</ul>
					</li>
					
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo5-mini.png">
						<span style="margin-left: 10px;">사서게시판</span><span class="caret" style="margin-left: 3px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="noticeList.ana">공지 게시판</a></li>
							<li><a href="board.ana">자유 게시판</a></li>
						</ul>       
					</li>
					
					<li class="dropdown" style="margin-left: 15px;"> 
						<a href="#" class="dropdown-toggle sidetoggle" data-toggle="dropdown">
						<img src="resources/img/home-logo/home-logo7.png">
						<span style="margin-left: 10px;">쪽지</span><span class="caret" style="margin-left: 3px;"></span></a>  
						<ul class="dropdown-menu forAnimate drop" role="menu" style="margin-bottom: 20px;">
							<li><a href="messageForm.ana">발신/수신/보관함</a></li>
						</ul>       
					</li>
	                
	            </ul>
	        </div>
	        
	        <!-- /#sidebar-wrapper -->
	        
			</a>
	   
	   		<!-- /#wrapper --> 
				
		</div>

	</div> 
