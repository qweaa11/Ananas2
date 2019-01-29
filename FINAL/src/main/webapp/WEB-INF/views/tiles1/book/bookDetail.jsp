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
.shareInfo th{
	width:30%;
}
.shareInfo td{
	width:70%;
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
  min-width: 400px;
  max-width: 800px;    
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: 60%;           
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

.form-container select{
  float:left;
  max-width: 60pt;
  min-width: 70pt;
}
 
#selectField span{
	display: inline-block;
}

</style>

<script type="text/javascript">
	$(document).ready(function() {
		console.log("${length}");
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
		
		$("#btn2").click(function(){
			var bookid = "";
			if(extendFlag == false)	{
				$("input.bookChk:checkbox:checked").each(function(){
<<<<<<< HEAD
					
				});
=======
					bookid += $(this).val()+",";
				});
				bookid = bookid.substr(0,bookid.length-1);
				console.log(bookid);
				var frm = document.extendBookForm;
				frm.extendBookid.value = bookid;
				frm.action = "extendBookList.ana";
				frm.method = "POST";
				frm.submit();
>>>>>>> branch 'master' of https://github.com/qweaa11/Ananas2.git
			}
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
		
		
		
		
		$("#bigField").change(function(){
			
			var fieldcode = $("#bigField").val();
			findDetailField(fieldcode);
		});
		
		
		$(".rental").click(function(){
			if(rentalFlag == false){
				alert("대여가 불가능한 책입니다.");
				return;
			}
			var bookid = $(this).val();
			var frm = document.rentalBookForm; 
			frm.rentalBookid.value = bookid;  
			frm.action = "r3.ana";
			frm.method = "GET";
			frm.submit();
		});
		
		$(".reserve").click(function(){
			if(reserveFlag == false){
				alert("예약이 불가능한 책입니다.");
				return;
			}
			var bookid = $(this).val();
			var frm = document.reservBookForm;
			frm.reserveBookid.value = bookid;
			frm.action = "r3.ana";
			frm.method = "GET";
			frm.submit();
		});
		
		
		 var fcode_fk = "${bookDetailList.get(0).fcode_fk}";
		  console.log(fcode_fk);
		  var bigfcode = fcode_fk.substr(0, 1);
		 findDetailField(bigfcode);
		
});//End of Ready 

function openAllEditForm() {
	  document.getElementById("allEditForm").style.display = "block";
	  
	  $("#editTitle").val("${bookDetailList.get(0).title}");
	  $("#editAuthor").val("${bookDetailList.get(0).author}");
	  $("#editNation").val("${bookDetailList.get(0).ncode_fk}")
	  $("#editCategory").val("${bookDetailList.get(0).ccode_fk}");
	  $("#editLanguage").val("${bookDetailList.get(0).lcode_fk}");
	  $("#editGenre").val("${bookDetailList.get(0).gcode_fk}");
	  $("#editAgecode").val("${bookDetailList.get(0).agecode}");
	  var fcode_fk = "${bookDetailList.get(0).fcode_fk}";
	  $("#editLibrary").val("${bookDetailList.get(0).libcode_fk}");
	  console.log(fcode_fk); 
	  var bigfcode = fcode_fk.substr(0, 1);
	  console.log(bigfcode);
	 
	  $("#bigField").val(bigfcode);
	  $("#bookid").val("${bookid}");
	  console.log("${bookid}");
	  $("select[name=editField]").val(fcode_fk);
	  $("#defaultImage").val("${bookDetailList.get(0).image}");
	}
	
	

function closeAllEditForm() {     
	  document.getElementById("allEditForm").style.display = "none";
	}
	
function openDetailEditForm(bookid,isbn,price,weight,totalpage,pdate,libcode,status){
	if(status != "0"){
		alert("반납된(기본상태) 책만 수정이 가능합니다.");
		return;
	}else{
	
	 document.getElementById("editIndivForm").style.display = "block";
			
	
		
			 $("#editISBN").val(isbn);
			 $("#editPrice").val(Number(price)); 
			 $("#editWeight").val(Number(weight));
			 $("#editTotalPage").val(Number(totalpage));
			 $("#editPdate").val(pdate);
			
		 	 $("#detailBookid").val(bookid);
		 	 
	}
	 
	 
	 
	 
	}

function closeDetailEditForm() {     
	  document.getElementById("editIndivForm").style.display = "none";
	}
	
function findDetailField(fieldcode){
	
	var data_form = {"bigfcode":fieldcode};
	
	$.ajax({
		
		url:"findDetailField.ana",
		data:data_form,
		type:"GET",
		dataType:"JSON",
		success:function(json){
			
			var html ="";
			//html += "</br> >><select name='eidtField' id='editField' >";
			$.each(json,function(fieldIndex, field){
				html+="<option value='"+field.FCODE+"' >"+field.FNAME+"</option>";
			});
			//html +="</select>";
			
			$("#selectDetailField").html(html);
			
		},error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		 }
	
	});
	
}

function openAddForm(){
	var reg = new RegExp('^[0-9]+$');
	var count = prompt("추가하실 권수를 입력해주세요. 추가되는 책은 기존의 정보로 입력됩니다.", "ex) 3");
	
	if(reg.test(count)){
		var flag = confirm(count+"권만큼 추가하시겠습니까?");
		if(flag == true){
			var frm = document.addBookForm;
			frm.count.value = count;
			frm.bookid.value = "${bookid}";
			frm.action = "AddBook.ana";
			frm.method = "POST";
			frm.submit();
		}
	}else if(reg.test(count) == false){
		alert("숫자만 입력 가능합니다.");
		return;
	}
}

function deleteAllBook(bookid){
	var flag1  = false;
	<c:forEach var="book" items="${bookDetailList}">
		if(${book.status != 0}){
			flag1 = true;
		}
	</c:forEach>
	if(flag1 == true) {
		alert("모든 책이 반납된 상태(기본)일때만 삭제가 가능합니다.");
		return;
	}
	
	var approval = false;
	var flag = confirm("삭제된 도서정보는 삭제목록에 저장됩니다. 정말로 삭제하시겠습니까?");
	if(flag == true){
		var id = prompt("관리자 아이디를 입력해주세요.");
		if(${sessionScope.loginLibrarian != null}) {
			if(id == "${sessionScope.loginLibrarian.libid}"){
				approval = true;
			}
		} else if(${sessionScope.loginAdmin != null}) {
			if(id == "${sessionScope.loginAdmin.adminid}"){
				approval = true;
			}
		}
		
		if(approval == true){
			
			var frm  = document.deleteForm;
			frm.bookid.value = bookid;
			frm.action = "deleteAllBook.ana";
			frm.method = "POST";
			frm.submit();
			
		}else{
			alert("현재 로그인한 아이디를 입력해 주세요.")
			return;
		}
		
	}
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
					<img  style="width: 100%; height:auto;"   
						src="resources/img/${bookDetailList.get(0).image}"
						alt="도서 이미지">
					
				</div>
				<div class="col-lg-offset-1 col-lg-7 col-sm-8" style="margin-top:5px;">
					<table class="table shareInfo" style="background-color: #f5f5f5">
						<tr>
							<th>도서명</th>
							<td >${bookDetailList.get(0).title }</td>
							
						</tr>
						<tr>
							<th>저자/역자</th>
							<td >${bookDetailList.get(0).author }(${bookDetailList.get(0).lname })</td>
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
							<th>분야/장르</th>
							<td>${bookDetailList.get(0).fname } </br> ${bookDetailList.get(0).gname }</td>
						</tr>
						<tr>
							<th>위치</th>
							<td>${bookDetailList.get(0).libname }</td>
						</tr>
						<tr>
							<th>도서연령</th>
							<td>${bookDetailList.get(0).transeAgecode }</td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="button" style="font-size:8pt;" class="btn btn-primary open-button" onClick="openAddForm();">추가</button>
								<button type="button" style="font-size:8pt;" class="btn btn-default open-button" onClick="openAllEditForm();">수정(공용)</button>
								
								<button type="button" style="font-size:8pt;" class="btn btn-danger" onClick="deleteAllBook('${bookid}')">삭제(전체)</button>
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
			<div class="tab-content ">
				<div id="Allbook" class="tab-pane fade in active" style="overflow-y:scroll; max-height:420px;">       
					
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
								<td><button type="button" style="font-size:9pt;" class="btn btn-default " onClick="openDetailEditForm('${book.bookid}','${book.isbn }','${book.price }','${book.weight }','${book.totalpage }','${book.pdate }','${book.libcode_fk }','${book.status }')">개별 수정</button>
								&nbsp;|&nbsp;
								<button style="font-size:9pt;" type="button" class="btn btn-danger" onClick="deleteIndivBook('${book.bookid}','${book.status }')">개별 삭제</button></td>
							</tr>       
						</c:forEach>
						</c:if>	

						</tbody>
					</table>
				</div>
				<div id="reservation" class="tab-pane fade" style="overflow-y:scroll; max-height:420px;">
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
		<div class="col-lg-12 col-sm-12" style="margin-left:15px; margin-top:15px;">
			<div class="col-lg-10">
			<a class="btnToggle" id="toggle2" href="#btn2" data-toggle="tooltip" data-placemnet="top" title="대여중인 책만 가능합니다.">
				<button type="button" id="btn2" class="btn btn-primary" value="">연장</button>
			</a>
			<a class="btnToggle" id="toggle3" href="#btn3" data-toggle="tooltip" data-placemnet="top" title="대여중인 책만 가능합니다.">
				<button type="button" id="btn3" class="btn btn-info" value="">반납</button>
			</a>
			</div>
			<div class=" col-lg-2">
			<a href="bookList.ana"><button type="button" class="">목록으로</button></a>
			</div>
			
			
		</div>
		


	</div>
	<!-- /.row -->

</div>
<!-- /.container -->

			<!--  공용 도서정보 수정 form-popup 페이지 시작 -->
					<div class="form-popup" id="allEditForm">
								
								  <form name="editPublicForm"  class="form-container" enctype="multipart/form-data">
								    <h3>공용 정보수정</h3>
									<table style="margin-bottom:10px;">
										<tr>
											<th>도서명</th>
											<td style="float: left;"><input name="editTitle" id="editTitle"/></td>
										</tr>
										<tr>
											<th>저자/역자</th>
											<td  style="float: left;"><input name="editAuthor" id="editAuthor"/></td>
										</tr>
										<tr>
											<th>도서 위치</th>
											<td style="float: left;"><select name="editLibrary" id="editLibrary">
												<c:forEach var="library" items="${libraryList }">
													<option value="${library.LIBCODE }">${library.LIBNAME }</option>
												</c:forEach>
												</select>
											</td>       
										</tr>
										<tr>
											<th>국내/국외</th>
											<td style="float: left;">
											<select name="editNation" id="editNation">
													<option value="0">국내</option>
													<option value="1">국외</option>
												</select>
											</td>       
										</tr>
										<tr>
											<th>언어</th>
											<td style="float: left;"><select name="editLanguage" id="editLanguage">
												<c:forEach var="language" items="${languageList }">
													<option value="${language.LCODE }">${language.LNAME }</option>
												</c:forEach>
												</select>
											</td>       
										</tr>
										<tr>
											<th>자료유형</th>
											<td style="float: left;">
												<select name="editCategory" id="editCategory">
												<c:forEach var="category" items="${categoryList }">
													<option value="${category.CCODE }">${category.CNAME }</option>
												</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th>분야</th>
											<td style="float: left;">
												<div id="selectField">
													<span><select id="bigField">
													<c:forEach var="field" items="${fieldList }">
														<option value="${field.FCODE }">${field.FNAME }</option>
													</c:forEach>
													</select>
													</span>
													<span>>></span>
													<span>
													<select name="editField" id="selectDetailField">
													
													</select>
													</span>
												</div>
												
											</td>
										</tr>
										<tr>
											<th>장르</th>
											<td style="float: left;">
											<select name="editGenre" id="editGenre">
												<c:forEach var="genre" items="${genreList }">
													<option value="${genre.GCODE }">${genre.GNAME }</option>
												</c:forEach>
											</select>
											</td>
										</tr>
											<tr>
											<th>도서연령</th>
											<td style="float: left;">
											<select name="editAgecode" id="editAgecode" style="width:auto;">
													<option value="0">전체 연령가</option>
													<option value="1">아동</option>
													<option value="2">청소년</option>
													<option value="3">성인</option>
											</select>
											</td>
										</tr>
										<tr>
											<th>이미지 변경</th>        
											<td style="float: left;"><input id="defaultImage" style=" float:left; width:100%;" readonly="readonly"/>
											<br/>
											<input type="file" name="editImage" id="editImage"/></td>
										</tr>
									</table>
									<input type="hidden" name="bookid" id="bookid">
									<input type="hidden" name="bookListLength" value="${length }"/>
								 	<button type="button" class="btn" onClick="editAllBookInfo()">수정</button>
								    <button type="button" class="btn cancel" onclick="closeAllEditForm()">닫기</button>
								  </form>
								</div>
					<!--  공용 도서정보 수정 form-popup 페이지 끝 -->
								
					<!--  개별 도서수정 div 페이지 시작 -->
						<div class="form-popup" id="editIndivForm">
								
								  <form name="editIndivForm" class="form-container">
								    <h3>개별 정보수정</h3>
									<table style="margin-bottom:10px;">
										<tr>
											<th>ISBN</th>
											<td><input name="editISBN" style="text-align: right;" id="editISBN"/></td>
										</tr>
										<tr>
											<th>가격</th>
											<td><input type="number"style="text-align: right;" name="editPrice" id="editPrice"/></td>
										</tr>
										<tr>
											<th>무게</th>
											<td style="padding-left:15px;"><input style="text-align: right;" type="number" name="editWeight" id="editWeight"/> g</td>
										</tr>
										<tr>
											<th>쪽수</th>
											<td style="padding-left:38px;"><input style="text-align: right;" type="number" name="editTotalPage" id="editTotalPage"/> page</td>
										</tr>
										<tr>
											<th>발행일자</th>
											<td align="center"><input type="date" name="editPdate" id="editPdate"/></td>
										</tr>
									</table>
									<input type="hidden" name="status" id="editStatus"/>
									<input type="hidden" name="bookid" id="detailBookid">
								 	<button type="button" class="btn" onClick="editIndivBookInfo();">수정</button>
								    <button type="button" class="btn cancel" onclick="closeDetailEditForm()">닫기</button>
								  </form>
								</div>
								<!-- 개별 도서 수정 div 페잊 끝 -->
								
								<form name="deleteForm">
									<input type="hidden" name="bookid"/>
								</form>
								
								<form name="addBookForm">
									<input type="hidden" name="bookid"/>
									<input type="hidden" name="count"/>
								</form>
								
								<form name="rentalBookForm">
									<input type="hidden" name="rentalBookid"/>
								</form>
								<from name="reservBookForm">
									<input type="hidden" name="reserveBookid"/>
								</from>
								<form name="extendBookForm">
									<input type="hidden" name="extendBookid"/>
								</form>
<script>
function editAllBookInfo(){
	var flag  = false;
	<c:forEach var="book" items="${bookDetailList}">
		if(${book.status != 0}){
			flag = true;
		}
	</c:forEach>
	if(flag == true) {
		alert("모든 책이 반납된 상태(기본)일때만 수정이 가능합니다.");
		return;
	}
	if($("#editTitle").val().trim() == ""){
		alert("도서명은 빈칸으로 입력할 수 없습니다.");
		return;
	}if($("#editAuthor").val().trim() == ""){
		alert("저자/역자는 입력해야 합니다.");
		return;
	}
	
	var frm = document.editPublicForm;
	frm.action = "editPublicBookInfo.ana"; 
	frm.method = "POST";
	frm.submit();
	
}

function editIndivBookInfo(){
	var frm = document.editIndivForm;
	frm.action = "editIndivBookInfo.ana";
	frm.method = "POST";
	frm.submit();
}

function deleteIndivBook(bookid,status){
	if(status != 0){
		alert("반납된(기본상태)인 책만 삭제가 가능합니다.");
		return;
	}
		
	var flag = confirm("삭제하시겠습니까(DB에서 영구 삭제됩니다.)?");
	if(flag == false){
		return;
	}
	var frm = document.deleteForm;
	frm.bookid.value=bookid;
	frm.action = "deleteIndivBook.ana",
	frm.method="POST";
	frm.submit();
}
</script>
