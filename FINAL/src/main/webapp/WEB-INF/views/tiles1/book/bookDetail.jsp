<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Bootstrap Core JavaScript -->
<script src="resources/js/bootstrap.min.js"></script>

<!-- jQuery -->
<script src="resources/js/jquery.js"></script>
<!-- Custom CSS -->



<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Navigation -->

<style>
th, td {
	padding: 5px;
	text-align: center;
}

.table-striped td, .table-striped th {
	border: 1px solid #ddd;    
	border-collapse: collapse;
}
.table-hover tbody tr:hover td{
	background-color: #e6e6e6;
	border-left-color: #cccccc;
	border-right-color:#cccccc;   
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$("#totalck").click(function() {

			$("input[type='checkbox']").prop('checked', this.checked);

		});

	});
</script>

<!-- Page Content -->
<div class="container-fluid" style="padding-left: 110px;">


	<div class="row">

		<div class="col-lg-9" style="margin-bottom: 15px;">       
			<span style="font-weight: bold; font-size: 22pt;">도서관리<i class="glyphicon glyphicon-book" style="font-size:22px;"></i> ></span> 
			<span style="font-weight: bold; font-size: 20pt; cursor:pointer;" onClick="javascript:location.href='bookList.ana'" >도서목록&nbsp;<i class="glyphicon glyphicon-th-list" style="font-size: 19px;"></i> ></span>
			 <span style="font-weight: bold; font-size: 18pt;">도서상세<i class="glyphicon glyphicon-search" style="font-size: 17px;"></i></span>
		</div>
		<!-- Blog Post Content Column -->
		<div class="col-lg-12" style="padding: 5px;">

			<div class="col-lg-4">
				<div class="col-lg-4">
					<!-- Preview Image -->
					<img class="img-responsive"
						src="https://image.aladin.co.kr/product/17237/69/cover/k912534091_1.jpg"
						alt="">
					<div align="center" style="margin-top:">
						<input type="button" value="수정"/>
						<input type="button" value="삭제"/>
					</div>
				</div>
				<div class="col-lg-offset-1 col-lg-7">
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
					</table>
				</div>
			</div>

			<!-- Post Content -->
			<div class="col-lg-col-lg-offset-1 col-lg-7" style="margin-top:5px; overflow-y:scroll; min-height:210px; border: 1px solid gray; max-height:210px;">
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
								<th align="center"><input type="checkbox"	id="totalck" value="total" /><label for="totalck">전체</label></th>
								<th><label for="totalck">도서번호</label></th>
								<th>ISBN</th>
								<th>가격</th>
								<th>무게</th>
								<th>쪽수</th>
								<th>발행일자</th>
								<th>등록일자</th>
								<th>위치</th>
								<th>상태</th>
								<th>수정/삭제</th>

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
								<td>${book.transeStatus}</td>
								<td><button>수정</button>&nbsp;|&nbsp;<button>삭제</button></td>
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
								<th align="center"><input type="checkbox"	id="totalck" value="total" /><label for="totalck">전체</label></th>
								<th><label for="totalck">도서번호</label></th>
								<th>도서명</th>
								<th>ISBN</th>
								<th>도서 등록일</th>
								<th>예약 회원(이름)</th>
								<th>연락처</th>
								<th>회원 등록일</th>
								<th>예약일</th>
								<th>회원 상태</th>
							</tr>
						</thead>
						<tbody id="displayBookList">
						<c:if test="${ empty bookReservateList }">
							<tr>
								<td colspan="10" style="font-size: 17pt; font-weight:bold;">예약된 책이 없습니다.</td>
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
								</tr>
							</c:forEach>
						</c:if>	

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-4" style="margin-left:15px;">
			<input type="button" value="대여"/>
			<input type="button" value="연장"/>
			<input type="button" value="예약"/>
			<input type="button" value="반납"/>
		</div>




	</div>
	<!-- /.row -->




</div>
<!-- /.container -->


