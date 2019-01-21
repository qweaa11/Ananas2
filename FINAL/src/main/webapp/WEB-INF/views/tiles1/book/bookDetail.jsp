<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		<div class="col-lg-offset-3 col-lg-9" style="margin-bottom: 15px;">
			<span style="font-weight: bold; font-size: 22pt;">도서관리 ></span> <span
				style="font-weight: bold; font-size: 20pt;">도서목록 ></span> <span
				style="font-weight: bold; font-size: 18pt;">도서상세</span>
		</div>
		<!-- Blog Post Content Column -->
		<div class="col-lg-4" style="padding: 5px;">

			<div class="col-lg-12 ">
				<div class="col-lg-4">
					<!-- Preview Image -->
					<img class="img-responsive"
						src="https://image.aladin.co.kr/product/17237/69/cover/k912534091_1.jpg"
						alt="">
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
							<td>${bookDetailList.get(0).fname } / ${bookDetailList.get(0).gname }</td>
						</tr>
						<tr>
							<th>도서연령<th>
							<td>${bookDetailList.get(0).agecode }</td>
					</table>
				</div>
			</div>

			<!-- Post Content -->
			<div class="col-lg-12" style="margin-top: 20px;">
				<h2>책소개</h2>
				<span class="lead">${bookDetailList.get(0).intro }</span>
			</div>
		</div>

		<!-- Blog Sidebar Widgets Column -->
		<div class="col-lg-7 col-sm-7" style="margin-left: 10px;">
			<ul class="nav nav-tabs">
				<li><a data-toggle="tab" href="#Allbook">도서 현황</a></li>
				<li><a data-toggle="tab" href="#reservation">예약 현황</a></li>
			</ul>
			<div class="tab-content">
				<div id="Allbook" class="tab-pane fade in active">
					
					<table class="table table-striped bookstatus"
						style="border: 1px solid #ddd;" id="section1">
						<thead>
							<tr>
								<th><input type="checkbox" style="float: left;"
									id="totalck" value="total" /><label for="totalck">도서번호</label></th>
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
						<c:forEach var="book"	items="${bookDetailList }" varStatus="status">
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />book.bookid</td>
								<td>book.iSBN</td>
								
								<td>book.price</td>
								<td>book.weight&nbsp;kg</td>

								<td>book.totalpage&nbsp;page</td>
								<td>book.pdate</td>
								<td>book.regdate</td>
								<td>book.libname</td>
								<td>book.transeStatus</td>
								<td><button>수정</button>|<button>삭제</button></td>
								
							</tr>
						</c:forEach>
							

						</tbody>
					</table>
				</div>
				<div id="reservation" class="tab-pane fade">
					<div
						style="font-size: 15pt; padding-left: 6px; margin-bottom: 5px; margin-top: 30px;">예약
						현황&nbsp;</div>
					<table class="table table-striped bookstatus"
						style="border: 1px solid #ddd;" id="section1">
						<thead>
							<tr>
								<th><input type="checkbox" style="float: left;"
									id="totalck" value="total" /><label for="totalck">도서번호</label></th>
								<th>ISBN</th>
								<th>예약회원</th>
								<th>이름</th>
								<th>연락처</th>
								<th>발행일자</th>
								<th>등록일자</th>
								<th>위치</th>
								<th>상태</th>

							</tr>
						</thead>
						<tbody id="displayBookList">
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145281502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/09/09</td>
								<td>2018/11/12</td>
								<td>지도</td>
								<td>대여 중</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145281502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/09/09</td>
								<td>2018/11/12</td>
								<td>지도</td>
								<td>분실</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145281502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/09/09</td>
								<td>2018/11/12</td>
								<td>지도</td>
								<td>대여가능</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145753502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/12/09</td>
								<td>2019/01/12</td>
								<td>지도</td>
								<td>예약중</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145753502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/12/09</td>
								<td>2019/01/12</td>
								<td>지도</td>
								<td>대여가능</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145753502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/12/09</td>
								<td>2019/01/12</td>
								<td>지도</td>
								<td>대여 중</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145753502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/12/09</td>
								<td>2019/01/12</td>
								<td>지도</td>
								<td>예약 중</td>
							</tr>
							<tr class="BookInfo">
								<td><input type="checkbox" style="float: left;" />도서번호1</td>
								<td>145753502</td>
								<td>30000</td>
								<td>2.1kg</td>

								<td>350page</td>
								<td>2018/12/09</td>
								<td>2019/01/12</td>
								<td>지도</td>
								<td>대여가능</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>




	</div>
	<!-- /.row -->




</div>
<!-- /.container -->


