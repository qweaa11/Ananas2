<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<link rel="stylesheet" type="text/css" href="resources/css/r3.css">

<script type="text/javascript">

	$(document).ready(function(e){
		
		// 회원 검색 카테고리
	    $('.search-member').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-member span#search_concept').text(concept);
			$('.input-member #membercategory').val(param);
		});
	    
		// 책 검색 카테고리
	    $('.search-book').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-book span#search_concept').text(concept);
			$('.input-book #bookcategory').val(param);
		});
		
	 	// 대여 검색 카테고리
	    $('.search-rental').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-rental span#search_concept').text(concept);
			$('.input-rental #rentalcategory').val(param);
		});
	 	
	 	// 반납 정렬 카테고리
	    $('.sort-rental').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.sort-rental span#search_concept').text(concept);
			$('.input-rental-sort #sortrental').val(param);
		});
	    
	    // 회원 목록 스타일 부여
	    $(document).on("mouseover", ".hover", function () {
	    	$(this).addClass("hoverStyle");
		});
	    
	    $(document).on("mouseout", ".hover", function () {
	    	$(this).removeClass("hoverStyle");
		});
	    
	    // 엔터 쳤을 시 회원 검색
	    $("#search_member").keydown(function(event) {
			if(event.keyCode == 13) {
				searchMember();
			}
		});
	    
	    // 엔터 쳤을 시 도서 검색
	    $("#search_book").keydown(function(event) {
			if(event.keyCode == 13) {
				searchBook();
			}
		});
	    
	 	// 엔터 쳤을 시 대여 검색
	    $("#search_rental").keydown(function(event) {
			if(event.keyCode == 13) {
				searchRental();
			}
		});
	    
	    // 회원 상세 정보 표시
	    $(document).on("click", ".memberselect", function () {
	    	
	    	var memberid = $(this).find(".memberid").text();
	    	
	    	var data_form = {"memberid":memberid}
	    	
	    	$.ajax({
				
				url:"r3findOneMember.ana",
				type:"GET",
				data:data_form,
				dataType:"json",
				success:function(json) {
					
					$("#memberid").text(json.MEMBERID);
					$("#name").text(json.NAME);
					$("#ages").text(json.AGES);
					$("#addr1").text(json.ADDR1);
					$("#addr2").text(json.ADDR2);
					$("#phone").text(json.PHONE);
					
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
				
			});// end of $.ajax()-------------------------
	    	
		});// end of $(document).on()--------------------------------------
		
		
		// 대여 대기 창으로 옮기기
		$(document).on("click", ".bookselect", function () {

	    	var memberid = $("#memberid").text();
	    	
	    	if(memberid == null || memberid == "") {
	    		alert("회원을 선택해주세요");
	    		return;
	    	}
	    	
	    	var bookid = $(this).find(".bookid").text();
	    	
	    	var title = $(this).find(".bookid").next().text();
	    	
	    	var flag = false;
	    	
	    	$(".bookval").each(function () {
				if($(this).val() == bookid){
					flag = true;
					return false;
				}
			});
	    	
	    	if(flag) {
	    		alert("이미 대여대기 목록에 들어있는 책 입니다.");
	    		return;
	    	}
	    	
	    	title = title.length > 15?title.substring(0, 15) + "...":title; 
	    	
	    	var name = $("#name").text();
	    	
	    	var today = new Date();
	    	var deadline = new Date();
	    	deadline.setDate(today.getDate() + 14);
	    	
	    	var dd = deadline.getDate();
	    	var mm = deadline.getMonth()+1; //January is 0!
	    	var yyyy = deadline.getFullYear();
	    	
	    	html = 	"<li class=\"list-group-item hover rentalredy\">\n" + 
					"    <div class=\"row\">\n" + 
					"        <div class=\"col-xs-2 memberidval text-left\">" + memberid + "</div>\n" + 
					"        <div class=\"col-xs-2 nameval\">" + name + "</div>\n" + 
					"        <div class=\"col-xs-3 \">" + title + "</div>\n" + 
					"        <div class=\"col-xs-2 \">14일</div>\n" + 
					"        <div class=\"col-xs-3 deadlineval\">" + yyyy + "/" + mm + "/" + dd + "</div>\n" +
					"		 <input type='hidden' class='bookval' value='" + bookid + "'/>" +
					"    </div>\n" + 
					"</li>";
					
			$(".rentalList").append(html);
	    	
		});// end of $(document).on()--------------------------------------
		
		
		// 대여 대기창에서 지우기 
		$(document).on("click", ".rentalredy", function () {
			$(this).empty().hide();
		});// end of $(document).on()----------------------------
		
		
		if(${bookid != null}) {
			$("#search_book").val("${bookid}");
			$(".booksearch").click();
		}
	    
		
		$("#sortrental").change(function () {
			searchRental();
		});
		
	});// end of ready()------------------
	
	
	
	function searchMember() {
		
		var cateogry = $("#membercategory").val();
		var searchWord = $("#search_member").val();
		
		var data_form = {"searchWord":searchWord, "cateogry":cateogry}
		
		$.ajax({
			
			url:"r3searchMember.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				var html = "";
				
				if(json.length > 0){
					
					$.each(json, function (entryIndex, entry) {
					 	 
						html += "<li class=\"list-group-item hover memberselect\">\n" + 
								"	<div class=\"row\">\n" + 
								"		<div class=\"col-xs-6 memberid text-left\" style=\" \">" + entry.MEMBERID + "</div>\n" + 
								"		<div class=\"col-xs-6\" style=\"\">" + entry.NAME + "</div>\n" + 
								"	</div>\n" + 
								"</li>";
						
					});// end of $.each()---------------------------
					
				}
				else {
					html += "<li class=\"list-group-item\">\n" + 
					"	<div class=\"row\">\n" + 
					"		<div class=\"col-xs-12 memberid text-left\" style=\"text-align: center;\">검색 결과가 없습니다.</div>\n" + 
					"	</div>\n" + 
					"</li>";
				}
				
				
				$(".memberList").html(html);
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()---------------------
		
	}
	
	function searchBook() {
		
		var cateogry = $("#bookcategory").val();
		var searchWord = $("#search_book").val();
		
		var data_form = {"searchWord":searchWord, "cateogry":cateogry}
		
		$.ajax({
			
			url:"r3searchBook.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				var html = "";
				
				if(json.length > 0){
					
					$.each(json, function (entryIndex, entry) {
					 	 
						html += "<li class=\"list-group-item hover bookselect\">\n" + 
								"    <div class=\"row\">\n" + 
								"        <div class=\"col-xs-6 bookid text-left\" style=\" \">" + entry.BOOKID + "</div>\n" + 
								"        <div class=\"col-xs-6\" style=\"\">" + entry.TITLE + "</div>\n" + 
								"    </div>\n" + 
								"</li>";
						
					});// end of $.each()---------------------------
					
				}
				else {
					html += "<li class=\"list-group-item\">\n" + 
					"	<div class=\"row\">\n" + 
					"		<div class=\"col-xs-12 memberid text-left\" style=\"text-align: center;\">검색 결과가 없습니다.</div>\n" + 
					"	</div>\n" + 
					"</li>";
				}
				
				
				$(".bookList").html(html);
				
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()---------------------
		
	}
	
	// 책 대여 기능
	function rental() {
		
		var bookids = "";
		
		$(".bookval").each(function () {
			bookids += $(this).val() + ","; 
		});
		
		bookids = bookids.substring(0, bookids.length-1);
		
		var memberids = "";
		
		$(".memberidval").each(function () {
			memberids += $(this).text() + ",";
		});
		
		memberids = memberids.substring(0, memberids.length-1);
		
		if(bookids.trim() == "" || memberids.trim() == "") {
			alert("목록에 등록해주세요");
			return;
		}
		
		var names = "";
		
		$(".nameval").each(function () {
			names += $(this).text() + ","; 
		});
		
		names = names.substring(0, names.length-1);
		
		var deadlines = "";
		
		$(".deadlineval").each(function () {
			deadlines += $(this).text() + ","; 
		});
		
		deadlines = deadlines.substring(0, deadlines.length-1);
		
		var data_form = {"bookids":bookids, "memberids":memberids, "names":names, "deadlines":deadlines}
		
		
		$.ajax({
			url:"rentalInsert.ana",
			type:"POST",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				if(json.RESULT == "1"){
					alert("대여가 되었습니다.");
					$(".rentalList").empty();
					$(".membersearch").click();
					$(".booksearch").click();
				}
				else {
					alert(json.MSG);
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()-------------------------
		
		
	}// end of rental
	
	
	// 대여 대기목록 리셋
	function rentalReset() {
		$(".rentalList").empty();
	}
	
	
	// 대여된 목록을 불러오기
	function searchRental() {
		
		var category = $("#rentalcategory").val();
		var searchWord = $("#search_rental").val();
		var sort = $("#sortrental").val();
		
		console.log(sort);
		
		 var data_form = {"searchWord":searchWord, "category":category, "sort":sort}
		
		$.ajax({
			
			url:"r3searchRental.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				console.log(json.length);
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()---------------------
		
	}// end of searchRental()--------------------- 
	

</script>
    
<div>
 
	<div class="container" style="width: 100%;">
	    <div class="row" style="padding-left: 70px; padding-right: 50px;"> 
			
	        <div class="col-lg-12"> 
	            <div class="panel with-nav-tabs panel-info"> 
	            
	                <div class="panel-heading"> 
	                        <ul class="nav nav-tabs">
	                            <li class="active"><a href="#tab1info" data-toggle="tab">대여</a></li>
	                            <li><a href="#tab2info" data-toggle="tab">반납</a></li>
	                            <li><a href="#tab3info" data-toggle="tab">예약</a></li>
	                        </ul>
	                </div>
	                
	                <div class="panel-body">
	                    <div class="tab-content">
	                    
	                    	<!-- 대여 -->
	                        <div class="tab-pane fade in active" id="tab1info">
	                        	
	                        	
	                        	<!-- 회원 부분 -->
	                        	<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">  
	                        		
						            <h2>회원 목록</h2>  
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
						                    <button class="btn btn-default membersearch" type="button" onclick="searchMember();"><span class="glyphicon glyphicon-search"></span></button>
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
						        	
						            <h2>도서 목록</h2>
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
									<h2>대여 작업</h2>
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
								    	<button type="button" class="btn btn-info btn-circle btn-lg" onclick="rental();"><i class="glyphicon glyphicon-ok"></i></button>
										<button type="button" class="btn btn-warning btn-circle btn-lg" onclick="rentalReset();"><i class="glyphicon glyphicon-remove"></i></button>
								    </div>
								    
								    <!-- /대여 대기목록 -->
								    
									
								    
						        </div>
						        <!-- /도서 부분 -->
						        
						        
						        
	                        </div>
	                        <!-- /대여 -->
	                        
	                        
	                        <!-- 반납 -->
	                        <div class="tab-pane fade" id="tab2info">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 border-radius" style="margin-top: 30px; margin-bottom: 30px;">
						        	
						            <h2>대여된 목록</h2>
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
						                    <button class="btn btn-default booksearch" type="button" onclick="searchRental();"><span class="glyphicon glyphicon-search"></span></button>
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
							                      <li><a href="#memberid">아이디</a></li>
							                      <li><a href="#name">이름</a></li>
							                      <li><a href="#deadline">반납예정날짜</a></li>
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
								                                <div class="col-xs-2">이름</div> 
								                                <div class="col-xs-2">제목</div> 
								                                <div class="col-xs-2 text-left">일련번호</div>
								                                <div class="col-xs-2">대여날짜</div>  
								                                <div class="col-xs-2">반납예정일</div>
								                                <div class="col-xs-1">연장 가능 여부</div>
								                            </div>
								                        </li>
								                    </ul>
								                    
								                    <ul class="list-group list-group-body rentalList">
								                    </ul>
								                    
								                </div>
								            </div>
								        </div>
								    </div>
								    
								    
								    <!-- /대여 목록 -->
								    
								    <!-- 반납 대기목록 -->
									<h2>반납 작업</h2>
								    <hr>
								    
								    <div class="row">
								        <div class="col-xs-12" >
								            <div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
								                <div class="panel-body" style="min-width: 1800px; overflow: auto;">
								                
								                    <ul class="list-group list-group-header">
								                        <li class="list-group-item list-group-body">
								                            <div class="row">
								                                <div class="col-xs-1 text-left">아이디</div>
								                                <div class="col-xs-2">이름</div> 
								                                <div class="col-xs-3">제목</div> 
								                                <div class="col-xs-2">일련번호</div> 
								                                <div class="col-xs-2">대여기간</div>  
								                                <div class="col-xs-2">반납예정일</div> 
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
								    	<button type="button" class="btn btn-info btn-circle btn-lg" onclick="rental();"><i class="glyphicon glyphicon-ok"></i></button>
								    	<button type="button" class="btn btn-success btn-circle btn-lg"><i class="glyphicon glyphicon-plus"></i></button>
										<button type="button" class="btn btn-warning btn-circle btn-lg" onclick="rentalReset();"><i class="glyphicon glyphicon-remove"></i></button>
								    </div>
								    
									<!-- /반납 대기목록 -->
								    
						        </div>
	                        </div>
	                        <!-- /반납 -->
	                         
	                         
	                        <!-- 예약 -->
	                        <div class="tab-pane fade" id="tab3info">
	                        	하하하하하하하하하
	                        </div> 
	                        <!-- 예약 끝 -->
	                        
	                    </div>
	                </div>
	                
	            </div>
	        </div>
	        
		</div>
	</div>

</div>