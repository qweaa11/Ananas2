<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<style>
th, td {
	padding: 3px;
	text-align: center;
}

.table-striped td, .table-striped th {
	border: 1px solid #ddd;    
	border-collapse: collapse;
}
.table-hover tbody tr:hover td{
	background-color: #e6e6e6;
	border-left-color: #cccccc;
	border-right-color: #cccccc;   
}
/* Tooltip */
  .btnToggle + .tooltip > .tooltip-inner {
    background-color: black; 
    color: white; 
    border: 1px solid black; 
    padding: 5px;;
    font-size: 11px;
  }
  /* Tooltip on top */
  .btnToggle + .tooltip.top > .tooltip-arrow {
    border-top: 5px solid black;
  }
  /* Tooltip on bottom */
  
 
* {box-sizing: border-box;}


/* The popup form - hidden by default */
.form-popup { 
  display: none;
  position: fixed;
  bottom: 20%;
  right: 9%; 
  border: 3px solid #f1f1f1; 
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;    
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		var rentalFlag = false;
		var reserveFlag = false;
		var extendFlag = false;
		var returnFlag = false;
		

		$("#totalbook").click(function() {

			$("input.bookChk[type='checkbox']").prop('checked', this.checked);

		});
		
		$("#totalReserve").click(function() {

			$("input.reserveChk[type='checkbox']").prop('checked', this.checked);

		});
		
		$("#btn2").hover(function(){
			var status = -1;
			var that = $(this); 
			$("input.bookChk:checkbox:checked").each(function(){
				status = $(this).parent().parent().find(".bookStatus").find("input[type=hidden]").val();
				console.log(status);
				if(status != 1)	extendFlag= true;
			});
			if(extendFlag == true){
				if(!$(this).hasClass("disabled")){
					$(this).addClass("disabled");	
				}
				$("a#toggle2[data-toggle='tooltip']").tooltip("show");
			}
		},function(){
			extendFlag = false;
			$(this).removeClass("disabled");
			$("a#toggle2[data-toggle='tooltip']").tooltip("hide");
	});
		
		
		$("#btn3").hover(function(){
			var status = -1;
			var that = $(this);
			$("input.bookChk:checkbox:checked").each(function(){
				status = $(this).parent().parent().find(".bookStatus").find("input[type=hidden]").val();
				console.log(status);
				if(status != 1)	returnFlag= true;
			});
			if(returnFlag == true){
				if(!$(this).hasClass("disabled")){
					$(this).addClass("disabled");	
				}
				$("a#toggle3[data-toggle='tooltip']").tooltip("show");
			}
				
		},function(){
			returnFlag = false;
			$(this).removeClass("disabled");
			$("a#toggle3[data-toggle='tooltip']").tooltip("hide");
	});
	
		
		$(".rental").hover(function(){
			status = $(this).parent().parent().parent().find(".bookStatus").find("input[type=hidden]").val();
				if(!(status == 0 || status ==2)){
					if(!$(this).hasClass("disabled")){
						$(this).addClass("disabled");
					}
					rentalFlag = true;
					$(this).parent().tooltip("show");
				}
		},function(){
			rentalFlag = false;
			$(this).removeClass("disabled");
			$(this).parent().tooltip("hide");
	});
		
		$(".reserve").hover(function(){
			status = $(this).parent().parent().parent().find(".bookStatus").find("input[type=hidden]").val();
				if(!(status == 1)){
					if(!$(this).hasClass("disabled")){
						$(this).addClass("disabled");
					}
					reserveFlag = true;
					$(this).parent().tooltip("show");
				}
		},function(){
			reserveFlag = false;
			$(this).removeClass("disabled");
			$(this).parent().tooltip("hide");
	});
		
		
		$("#btn2").click(function(){
			if(extendFlag == false)	console.log("click");
		});
		
});//End of Ready

function openAllEditForm() {
	  document.getElementById("allEditForm").style.display = "block";
	}

function closeAllEditForm() {
	  document.getElementById("allEditForm").style.display = "none";
	}

</script>

<!-- Page Content -->
<div class="container-fluid" style="padding-left: 110px;">


	<div class="row">

		<div class="col-lg-9 col-sm-9" style="margin-bottom: 15px;">       
			<span style="font-weight: bold; font-size: 22pt;">도서관리<i class="glyphicon glyphicon-book" style="font-size:22px;"></i> ></span> 
			<span style="font-weight: bold; font-size: 20pt; cursor:pointer;" onClick="javascript:location.href='bookList.ana'" >도서목록&nbsp;<i class="glyphicon glyphicon-th-list" style="font-size: 19px;"></i> ></span>
			 <span style="font-weight: bold; font-size: 18pt;">도서상세<i class="glyphicon glyphicon-search" style="font-size: 17px;"></i></span>
		</div>
		<!-- Blog Post Content Column -->
		<div class="col-lg-12 col-sm-12" style="padding: 5px;">

			<div class="col-lg-4 col-sm-4">
				<div class="col-lg-4 col-sm-4">
					<!-- Preview Image -->
					<img class="img-responsive"
						src="https://image.aladin.co.kr/product/17237/69/cover/k912534091_1.jpg"
						alt="">
					
				</div>
				<div class="col-lg-offset-1 col-lg-7 col-sm-8" style="margin-top:5px;">
					<table class="table" style="background-color: #f5f5f5">
						<tr>
							<th>도서명</th>
							<td>${bookDetailList.get(0).title }</td>
							
						</tr>
						<tr>
							<th>저자/역자</th>
							<td>${bookDetailList.get(0).author }(${bookDetailList.get(0).lname })</td>
						</tr>
						<tr>
							<th>출판사</th>
							<td>${bookDetailList.get(0).pubname }</td>
						</tr>
						<tr>
							<th>자료 유형</th>
							<td>${bookDetailList.get(0).cname }</td>
						</tr>
						<tr>
							<th>장르</th>
							<td>${bookDetailList.get(0).fname } </br> ${bookDetailList.get(0).gname }</td>
						</tr>
						<tr>
							<th>도서연령</th>
							<td>${bookDetailList.get(0).transeAgecode }</td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="button" style="font-size:8pt;" class="btn btn-default open-button" onClick="openAllEditForm();" value="">수정(공용)</button>
								<div class="form-popup" id="allEditForm">
								  <form action="" class="form-container">
								    <h3>공용 정보수정</h3>
									<table style="margin-bottom:10px;">
										<tr>
											<th>도서명</th>
											<td><input/></td>
										</tr>
										<tr>
											<th>저자/역자</th>
											<td><input/></td>
										</tr>
										<tr>
											<th>언어</th>
											<td><select>
												<c:forEach var="language" items="${languageList }">
													<option value="${language.LCODE }">${language.LNAME }</option>
												</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th>자료유형</th>
											<td>
												<select>
												<c:forEach var="category" items="${categoryList }">
													<option value="${category.CCODE }">${category.CNAME }</option>
												</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th>장르</th>
											<td>
												<select>
												<c:forEach var="field" items="${fieldList }">
													<option value="${field.FCODE }">${field.FNAME }</option>
												</c:forEach>
												</select>
												<select>
												<c:forEach var="genre" items="${genreList }">
													<option value="${genre.GCODE }">${genre.GNAME }</option>
												</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th>이미지 변경</th>
											<td><input/></td>
										</tr>
									</table>
								 	<button type="submit" class="btn">수정</button>
								    <button type="button" class="btn cancel" onclick="closeAllEditForm()">닫기</button>
								  </form>
								</div>
								<input type="button" style="font-size:8pt;" class="btn btn-danger" value="삭제(전체)"/>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<!-- Post Content -->
			<div class="col-lg-7 col-sm-7" style="margin-top:5px; overflow-y:scroll; min-height:260px; border: 1px solid gray; max-height:260px;">
				<h2>책소개</h2>
				<span class="lead">${bookDetailList.get(0).intro }</span>
			</div>
		</div>

		<!-- Blog Sidebar Widgets Column -->
		<div class="col-lg-11 col-sm-11" style="margin-left: 10px;">    
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#Allbook">도서 현황</a></li>
				<li><a data-toggle="tab" href="#reservation">예약 현황</a></li>
			</ul>
			<div class="tab-content">
				<div id="Allbook" class="tab-pane fade in active">
					
					<table class="table table-striped bookstatus table-hover"
						style="border: 1px solid #ddd;" id="section1">
						<thead>
							<tr> 
								<th align="center"><input type="checkbox" id="totalbook" value="total" /><label for="totalbook">전체</label></th>
								<th>도서번호</th>
								<th>ISBN</th>
								<th>가격</th>
								<th>무게</th>
								<th>쪽수</th>
								<th>발행일자</th>
								<th>등록일자</th>
								<th>위치</th>
								<th>상태</th>
								<th>대여|예약</th>
								<th>수정|삭제</th>
							</tr>
						</thead>
						<tbody id="displayBookList">
						
						<c:if test="${ not empty bookDetailList }">
						<c:forEach var="book"	items="${bookDetailList }" varStatus="status">
							<tr class="BookInfo">
								<td align="center"><input type="checkbox" class="bookChk" value="${book.bookid }"/></td>
								<td>${book.bookid}</td>
								<td>${book.isbn }</td>
								<td><fmt:formatNumber value="${book.price}" pattern="#,###,###,###"/>&nbsp;원</td>
								<td>${book.weight}&nbsp;kg</td>
								<td>${book.totalpage}&nbsp;page</td>
								<td>${book.pdate}</td>
								<td>${book.regdate}</td>
								<td>${book.libname}</td>
								<td class="bookStatus"><input type="hidden" value="${book.status }"/>${book.transeStatus}</td>
								
								<td>
								<c:if test="${book.status == 0 }">
									<a class="btnToggle"  href="#rental${status.index }" data-toggle="tooltip" data-placemnet="top" title="대여중인 책은 대여 불가능합니다." aria-describedby="tooltip100">
										<button type="button" id="rental${status.index }" class="btn rental" value="${book.bookid }">대여</button>
									</a>
								</c:if>
								<c:if test="${book.status == 1 }">
									<a class="btnToggle"  href="#reserve${status.index }" data-toggle="tooltip" data-placemnet="top" title="대여중인 책만 가능합니다.">
										<button type="button" id="reserve${status.index }" class="btn btn-warning reserve" value="${book.bookid }">예약</button>
									</a>
								</c:if>
								</td>
								<td><button type="button" style="font-size:9pt;" class="btn btn-default">개별 수정</button>&nbsp;|&nbsp;<button style="font-size:9pt;" type="button" class="btn btn-danger">개별 삭제</button></td>
							</tr>       
						</c:forEach>
						</c:if>	

						</tbody>
					</table>
				</div>
				<div id="reservation" class="tab-pane fade">
					<table class="table table-striped bookstatus table-hover"
						style="border: 1px solid #ddd;" id="section1">   
						<thead>
							<tr>
								<th align="center"><input type="checkbox"	id="totalReserve" value="total" /><label for="totalReserve">전체</label></th>
								<th>도서번호</th>
								<th>도서명</th>
								<th>ISBN</th>
								<th>도서 등록일</th>
								<th>예약 회원(이름)</th>
								<th>연락처</th>
								<th>회원 등록일</th>
								<th>예약일</th>
								<th>회원 상태</th>
								<th>대여&nbsp;|&nbsp;예약취소</th>
							</tr>
						</thead>
						<tbody id="displayBookList">
						<c:if test="${ empty bookReservateList }">
							<tr>
								<td colspan="11" style="font-size: 17pt; font-weight:bold;">예약된 책이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty bookReservateList }">
							<c:forEach var="bookReserv" items="${bookReservateList }" varStatus="status">    
								<tr class="BookInfo">
									<td><input type="checkbox" class="reserveChk"/></td>
									<td>${bookReserv.BOOKID}</td>
									<td>${bookReserv.TITLE }</td>
									<td>${bookReserv.ISBN }</td>
									<td>${bookReserv.BOOKREGDATE }</td>
									<td>${bookReserv.MEMBERID }(${bookReserv.NAME })</td>
									<td>${bookReserv.PHONE }</td>
									<td>${bookReserv.MEMBERREGDATE }</td>
									<td>${bookReserv.RESERVEDATE }</td>
									<td>${bookReserv.STATUS }</td>
									<td><button type="button" class="btn">대여</button>&nbsp;|&nbsp;<button type="button" class="btn btn-warning">예약취소</button></td>
								</tr>
							</c:forEach>
						</c:if>	

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-sm-4" style="margin-left:15px;">
			
			<a class="btnToggle" id="toggle2" href="#btn2" data-toggle="tooltip" data-placemnet="top" title="대여중인 책만 가능합니다.">
				<button type="button" id="btn2" class="btn btn-primary" value="">연장</button>
			</a>
			<a class="btnToggle" id="toggle3" href="#btn3" data-toggle="tooltip" data-placemnet="top" title="대여중인 책만 가능합니다.">
				<button type="button" id="btn3" class="btn btn-info" value="">반납</button>
			</a>
			
		</div>




	</div>
	<!-- /.row -->

</div>
<!-- /.container -->


