<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<link rel="stylesheet" type="text/css" href="resources/css/r3.css">
<script src="resources/js/r3.js"></script>
<script type="text/javascript">

	$(document).ready(function(e){
		if(${rentalBookid != null}) {
			$("#search_book").val("${rentalBookid}");
			$(".booksearch").click();
		}
	
		if(${returnBookid != null}) {
			$("#search_rental").val("${returnBookid}");
			$(".rentalsearch").click();
			$(".tab2info").click();
		}
	});
	
	
</script>
    
<div>
 
	<div class="container" style="width: 100%;">
	    <div class="row" style="padding-left: 70px; padding-right: 50px;"> 
			
	        <div class="col-lg-12"> 
	            <div class="panel with-nav-tabs panel-info"> 
	            
	                <div class="panel-heading"> 
	                        <ul class="nav nav-tabs">
	                            <li class="active"><a href="#tab1info" data-toggle="tab">대여</a></li>
	                            <li><a href="#tab2info" class="tab2info" data-toggle="tab">반납</a></li>
	                            <li><a href="#tab3info" data-toggle="tab">예약</a></li>
	                        </ul>
	                </div>
	                
	                <div class="panel-body">
	                    <div class="tab-content">
	                    
	                    	<!-- 대여 -->
	                        <div class="tab-pane fade in active" id="tab1info">
	                        	
	                        	
	                        	<!-- 회원 부분 -->
	                        	<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">  
	                        	
	                        		<div>  
	                        			<img class="r3loading" id="loding1" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>회원 목록</h2>
						            </div>
								    <hr>
	                        		
	                        		<!-- 회원 검색 -->
								    <div class="input-group input-member" style="margin-bottom: 30px;">
						                <div class="input-group-btn search-panel search-member">
						                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                    	<span id="search_concept">아이디</span> <span class="caret"></span>
						                    </button>
						                    <ul class="dropdown-menu" role="menu">
						                      <li><a href="#memberid">아이디</a></li>
						                      <li><a href="#name">이름</a></li>
						                    </ul>
						                </div>
						                <input type="hidden" name="search_param" value="memberid" id="membercategory"/>      
						                <input type="text" class="form-control" id="search_member" name="x" placeholder="검색어를 입력해주세요."/>
						                <span class="input-group-btn">
						                    <button class="btn btn-default membersearch" type="button" onclick="searchMember('1');"><span class="glyphicon glyphicon-search"></span></button>
						                </span>
						            </div>
						            
						            <!-- /검색 -->
						             
								    <!-- 회원 목록 -->
								    <div class="row">
								        <div class="col-xs-12">
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="overflow: auto; min-width: 600px;">
								                	<div>
									                    <ul class="list-group list-group-header">
									                        <li class="list-group-item list-group-body">
									                            <div class="row">
									                                <div class="col-xs-6 text-left">아이디</div>
									                                <div class="col-xs-6">이름</div>
									                            </div>
									                        </li>
									                    </ul>
									                    <div></div>
									                    <ul class="list-group list-group-body memberList">   
									                        
									                    </ul>
								                    </div>
								                </div>
								            </div>
								        </div>
								    </div>
								    <!-- /회원 목록 -->
								    
								    <!-- 회원정보 표시 -->
								    <div class="alert" role="alert"> 
								    
										<div class="panel panel-primary" >
										       
											<div class="panel-heading">
												회원정보 
											</div>
											       
											<div style="max-width: 100%; overflow: auto;">       
												<div class="panel-body" style="overflow: auto; min-width: 600px;">
													
													<ul class="list-group list-group-body" style="">
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">아이디</div>
								                                <div class="col-xs-8" id="memberid" style=""></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">이름</div>
								                                <div class="col-xs-8" id="name"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">연령</div>
								                                <div class="col-xs-8" id="ages"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">주소</div>
								                                <div class="col-xs-8" id="addr1"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">상세주소</div>
								                                <div class="col-xs-8" id="addr2"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">전화번호</div>
								                                <div class="col-xs-8" id="phone"></div>
								                            </div>
								                        </li>
								                    </ul>
												          
												</div>
											</div>
										</div>
										
									</div>
									
									<!-- 회원정보 표시 끝 -->
								    
						        </div>
						        <!-- /회원 부분 -->
						        
						        <!-- 도서 부분 -->
						        <div class="col-lg-offset-1 col-lg-6 col-md-offset-1 col-md-6 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">
						        	
						            <div>  
	                        			<img class="r3loading" id="loding2" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>도서 목록</h2> 
						            </div>
								    <hr>
								    
						            <!-- 도서 검색 -->
								    <div class="input-group input-book" style="margin-bottom: 30px;">
						                <div class="input-group-btn search-panel search-book">
						                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                    	<span id="search_concept">일련번호</span> <span class="caret"></span>
						                    </button>
						                    <ul class="dropdown-menu" role="menu">
						                      <li><a href="#bookid">일련번호</a></li>
						                      <li><a href="#title">제목</a></li>
						                    </ul>
						                </div>
						                <input type="hidden" name="search_param" value="bookid" id="bookcategory">      
						                <input type="text" class="form-control" id="search_book" name="x" placeholder="검색어를 입력해주세요.">
						                <span class="input-group-btn">
						                    <button class="btn btn-default booksearch" type="button" onclick="searchBook()"><span class="glyphicon glyphicon-search"></span></button>
						                </span>
						            </div>
						            <!-- /도서 검색 -->
						            
								    
								    <!-- 도서 목록 -->
								    <div class="row">
								        <div class="col-xs-12" style="">
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 600px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                                <div class="col-xs-6 text-left">일련번호</div>
								                                <div class="col-xs-6">제목</div> 
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body bookList">
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    
								    
								    <!-- /도서 목록 -->
								    
								    <!-- 대여 대기목록 -->
									<div>  
	                        			<img class="r3loading" id="loding3" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>대여 작업</h2>
						            </div>
								    <hr>
									<div style="color: red; padding-bottom: 10px; text-align: right;">대여기간은 최대 14일 입니다.</div> 
								    
								    <div class="row">
								        <div class="col-xs-12" >
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 600px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                                <div class="col-xs-2 text-left">아이디</div>
								                                <div class="col-xs-2">이름</div> 
								                                <div class="col-xs-3">제목</div> 
								                                <div class="col-xs-2">대여기간</div>  
								                                <div class="col-xs-3">반납예정일</div> 
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body rentalList" style="">
								                        
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    <div style="float: right; margin-bottom: 30px;">
								    	<button type="button" class="btn btn-info btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="대여" onclick="rental();">
								    		<i class="glyphicon glyphicon-ok" style="vertical-align: text-top;"></i>
								    	</button>
										<button type="button" class="btn btn-warning btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="리셋" onclick="rentalReset();">
											<i class="glyphicon glyphicon-remove" style="vertical-align: text-top;"></i>
										</button>
								    </div>
								    
								    <!-- /대여 대기목록 -->
								    
									
								    
						        </div>
						        <!-- /도서 부분 -->
						        
						        
						        
	                        </div>
	                        <!-- /대여 -->
	                        
	                        
	                        <!-- 반납 -->
	                        <div class="tab-pane fade" id="tab2info">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">
						        	
						            <div>  
	                        			<img class="r3loading" id="loding4" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>대여된 목록</h2>
						            </div>
								    <hr>
								    
								    <!-- 대여 검색 -->
								    <div class="input-group input-rental" style="margin-bottom: 30px;">
						                <div class="input-group-btn search-panel search-rental">
						                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                    	<span id="search_concept">아이디</span> <span class="caret"></span>
						                    </button>
						                    <ul class="dropdown-menu" role="menu">
						                      <li><a href="#memberid">아이디</a></li>
						                      <li><a href="#name">이름</a></li>
						                      <li><a href="#bookid">일련번호</a></li>
						                      <li><a href="#title">제목</a></li>
						                    </ul>
						                </div>
						                <input type="hidden" name="search_param" value="memberid" id="rentalcategory">       
						                <input type="text" class="form-control" id="search_rental" name="x" placeholder="검색어를 입력해주세요.">
						                <span class="input-group-btn">
						                    <button class="btn btn-default rentalsearch" type="button" onclick="searchRental('1');"><span class="glyphicon glyphicon-search"></span></button>
						                </span>
						            </div>
						            <!-- /대여 검색 -->
						            
						            
						            <!-- 정렬기준 -->
						            <div class="input-group input-rental-sort col-xs-12" style="margin-bottom: 30px;">
						            	<span style="float: right; padding-right: 10px; padding-top: 7px;">정렬기준 : </span> 
						                <div class="input-group-btn search-panel sort-rental">
						                	<div style="float: right;"> 
							                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="border-radius: 5px;">
							                    	<span id="search_concept">아이디</span> <span class="caret"></span>
							                    </button>
							                    <ul class="dropdown-menu" role="menu" style="float: right;">
							                      <li><a href="#memberid" class="btnRentalSort">아이디</a></li>
							                      <li><a href="#name" class="btnRentalSort">이름</a></li>
							                      <li><a href="#deadline" class="btnRentalSort">반납예정날짜</a></li>
							                    </ul>
						                    </div>
						                </div>
						                <input type="hidden" name="search_param" value="memberid" id="sortrental"> 
						            </div>
						            <!-- /정렬기준 -->
								    
								    
								    <!-- 대여 목록 -->
								    <div class="row">
								        <div class="col-xs-12" style="">
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 1800px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                            	<div class="col-xs-1 text-left">아이디</div>
								                                <div class="col-xs-1">이름</div> 
								                                <div class="col-xs-3">제목</div>  
								                                <div class="col-xs-2 text-left">일련번호</div>
								                                <div class="col-xs-2">대여날짜</div>  
								                                <div class="col-xs-2">반납예정일</div>
								                                <div class="col-xs-1 text-center">연장 가능 여부</div>
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body rentalSearchList">
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    
								    
								    <!-- /대여 목록 -->
								    
								    <!-- 반납 대기목록 -->
									<div>  
	                        			<img class="r3loading" id="loding5" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>반납 작업</h2>
						            </div>
								    <hr>
								    
								    <div class="row">
								        <div class="col-xs-12" >
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 1800px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                                <div class="col-xs-1 text-left">아이디</div>
								                                <div class="col-xs-1">이름</div> 
								                                <div class="col-xs-3">제목</div> 
								                                <div class="col-xs-2 text-left">일련번호</div>
								                                <div class="col-xs-2">대여날짜</div>  
								                                <div class="col-xs-2">반납예정일</div>
								                                <div class="col-xs-1 text-center">연장 가능 여부</div> 
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body returnList" style="">
								                        
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    <div style="float: right; margin-bottom: 30px;">
								    	<button type="button" class="btn btn-info btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="반납" onclick="returned();">
								    		<i class="glyphicon glyphicon-ok" style="vertical-align: text-top;"></i> 
								    	</button>
								    	<button type="button" class="btn btn-success btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="연장" onclick="reservation();">
								    		<i class="glyphicon glyphicon-plus" style="vertical-align: text-top;"></i>
								    	</button>
										<button type="button" class="btn btn-warning btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="리셋" onclick="returnReset();">
											<i class="glyphicon glyphicon-remove" style="vertical-align: text-top;"></i> 
										</button>
								    </div>
								    
									<!-- /반납 대기목록 -->
								    
						        </div>
	                        </div>
	                        <!-- /반납 -->
	                         
	                         
	                        <!-- 예약 -->
	                        <div class="tab-pane fade" id="tab3info">
	                        
	                        	<!-- 회원 부분 -->
	                        	<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">  
	                        		
						            <div>  
	                        			<img class="r3loading" id="loding6" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>회원 목록</h2>
						            </div>
								    <hr>
	                        		
	                        		<!-- 회원 검색 -->
								    <div class="input-group input-member2" style="margin-bottom: 30px;">
						                <div class="input-group-btn search-panel search-member2">
						                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                    	<span id="search_concept">아이디</span> <span class="caret"></span>
						                    </button>
						                    <ul class="dropdown-menu" role="menu">
						                      <li><a href="#memberid">아이디</a></li>
						                      <li><a href="#name">이름</a></li>
						                    </ul>
						                </div>
						                <input type="hidden" name="search_param" value="memberid" id="membercategory2"/>      
						                <input type="text" class="form-control" id="search_member2" name="x" placeholder="검색어를 입력해주세요."/>
						                <span class="input-group-btn">
						                    <button class="btn btn-default membersearch2" type="button" onclick="searchMember('2');"><span class="glyphicon glyphicon-search"></span></button>
						                </span>
						            </div>
						            <!-- /검색 -->
						             
								    <!-- 회원 목록 -->
								    <div class="row">
								        <div class="col-xs-12">
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="overflow: auto; min-width: 600px;">
								                	<div>
									                    <ul class="list-group list-group-header">
									                        <li class="list-group-item list-group-body">
									                            <div class="row">
									                                <div class="col-xs-6 text-left">아이디</div>
									                                <div class="col-xs-6">이름</div>
									                            </div>
									                        </li>
									                    </ul>
									                    <div></div>
									                    <ul class="list-group list-group-body memberList2">   
									                        
									                    </ul>
								                    </div>
								                </div>
								            </div>
								        </div>
								    </div>
								    <!-- /회원 목록 -->
								    
								    <!-- 회원정보 표시 -->
								    <div class="alert" role="alert"> 
								    
										<div class="panel panel-primary" >
										       
											<div class="panel-heading">
												회원정보 
											</div>
											       
											<div style="max-width: 100%; overflow: auto;">       
												<div class="panel-body" style="overflow: auto; min-width: 600px;">
													
													<ul class="list-group list-group-body" style="">
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">아이디</div>
								                                <div class="col-xs-8" id="rmemberid" style=""></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">이름</div>
								                                <div class="col-xs-8" id="rname"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">연령</div>
								                                <div class="col-xs-8" id="rages"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">주소</div>
								                                <div class="col-xs-8" id="raddr1"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">상세주소</div>
								                                <div class="col-xs-8" id="raddr2"></div>
								                            </div>
								                        </li>
								                        <li class="list-group-item">
								                            <div class="row">
								                                <div class="col-xs-4 text-left">전화번호</div>
								                                <div class="col-xs-8" id="rphone"></div>
								                            </div>
								                        </li>
								                    </ul>
												          
												</div>
											</div>
										</div>
										
									</div>
									
									<!-- 회원정보 표시 끝 -->
								    
						        </div>
						        <!-- /회원 부분 -->
						        
						        <!-- 도서 부분 -->
						        <div class="col-lg-offset-1 col-lg-6 col-md-offset-1 col-md-6 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">
						        	
						            <div>  
	                        			<img class="r3loading" id="loding7" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>대여 도서 목록</h2>
						            </div>
								    <hr>
								    
						            <!-- 대여 검색 -->
								    <div class="input-group input-rental2" style="margin-bottom: 30px;">
						                <div class="input-group-btn search-panel search-rental2">
						                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						                    	<span id="search_concept">아이디</span> <span class="caret"></span>
						                    </button>
						                    <ul class="dropdown-menu" role="menu">
						                      <li><a href="#memberid">아이디</a></li>
						                      <li><a href="#name">이름</a></li>
						                      <li><a href="#bookid">일련번호</a></li>
						                      <li><a href="#title">제목</a></li>
						                    </ul>
						                </div>
						                <input type="hidden" name="search_param" value="memberid" id="rentalcategory2">       
						                <input type="text" class="form-control" id="search_rental2" name="x" placeholder="검색어를 입력해주세요.">
						                <span class="input-group-btn">
						                    <button class="btn btn-default rentalsearch2" type="button" onclick="searchRental('2');"><span class="glyphicon glyphicon-search"></span></button>
						                </span>
						            </div>
						            <!-- /대여 검색 -->
						            
						            
						            <!-- 정렬기준 -->
						            <div class="input-group input-rental-sort2 col-xs-12" style="margin-bottom: 30px;">
						            	<span style="float: right; padding-right: 10px; padding-top: 7px;">정렬기준 : </span> 
						                <div class="input-group-btn search-panel sort-rental2">
						                	<div style="float: right;"> 
							                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="border-radius: 5px;">
							                    	<span id="search_concept">아이디</span> <span class="caret"></span>
							                    </button>
							                    <ul class="dropdown-menu" role="menu" style="float: right;">
							                      <li><a href="#memberid" class="btnRentalSort2">아이디</a></li>
							                      <li><a href="#name" class="btnRentalSort2">이름</a></li>
							                      <li><a href="#deadline" class="btnRentalSort2">반납예정날짜</a></li>
							                    </ul>
						                    </div>
						                </div>
						                <input type="hidden" name="search_param" value="memberid" id="sortrental2"> 
						            </div>
						            <!-- /정렬기준 -->
								    
								    
								    <!-- 대여 목록 -->
								    <div class="row">
								        <div class="col-xs-12" style="">
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 1800px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                            	<div class="col-xs-1 text-left">아이디</div>
								                                <div class="col-xs-1">이름</div> 
								                                <div class="col-xs-3">제목</div>  
								                                <div class="col-xs-2 text-left">일련번호</div>
								                                <div class="col-xs-2">대여날짜</div>  
								                                <div class="col-xs-2">반납예정일</div>
								                                <div class="col-xs-1 text-center">연장 가능 여부</div>
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body rentalSearchList2">
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    
								    
								    <!-- /대여 목록 -->
								    
								    <!-- 예약 대기목록 -->
									<div>  
	                        			<img class="r3loading" id="loding8" src="resources/img/r3loading.gif" width="30px" height="30px" style="float: right;"/>
	                        			<h2>예약작업</h2>
						            </div>
								    <hr>
								    
								    <div class="row">
								        <div class="col-xs-12" >
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 1000px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                                <div class="col-xs-2 text-left">예약 아이디</div>
								                                <div class="col-xs-2">예약 이름</div> 
								                                <div class="col-xs-3">제목</div> 
								                                <div class="col-xs-3">일련번호</div>  
								                                <div class="col-xs-2">대여예정일</div> 
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body reservationList" style="">
								                        
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    <div style="float: right; margin-bottom: 30px;">
								    	<button type="button" class="btn btn-info btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="예약" onclick="reservation();">
								    		<i class="glyphicon glyphicon-ok" style="vertical-align: text-top;"></i>
								    	</button>
										<button type="button" class="btn btn-warning btn-circle btn-lg" data-toggle = "tooltip" data-placement="top" title="리셋" onclick="reservationReset();">
											<i class="glyphicon glyphicon-remove" style="vertical-align: text-top;"></i>
										</button>
								    </div>
								    
								    <!-- /예약 대기목록 -->
								    
									
								    
						        </div>
						        <!-- /도서 부분 -->
						        
	                        </div> 
	                        <!-- 예약 끝 -->
	                        
	                    </div>
	                </div>
	                
	            </div>
	        </div>
	        
		</div>
	</div>

</div>