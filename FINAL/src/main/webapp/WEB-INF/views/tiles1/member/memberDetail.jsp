<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->

<style type="text/css">
	table, th, td {border: 1px solid lightgrey;}
	th {background-color: #f2f2f2}
	
	.btn3d {
    position:relative;
    top: -6px;
    border:0;
     transition: all 40ms linear;
     margin-top:10px;
     margin-bottom:10px;
     margin-left:2px;
     margin-right:2px;
	}
	.btn3d:active:focus,
	.btn3d:focus:hover,
	.btn3d:focus {
	    -moz-outline-style:none;
	         outline:medium none;
	}
	.btn3d:active, .btn3d.active {
	    top:2px;
	}
	.btn3d.btn-white {
	    color: #666666;
	    box-shadow:0 0 0 1px #ebebeb inset, 0 0 0 2px rgba(255,255,255,0.10) inset, 0 8px 0 0 #f5f5f5, 0 8px 8px 1px rgba(0,0,0,.2);
	    background-color:#fff;
	}
	.btn3d.btn-white:active, .btn3d.btn-white.active {
	    color: #666666;
	    box-shadow:0 0 0 1px #ebebeb inset, 0 0 0 1px rgba(255,255,255,0.15) inset, 0 1px 3px 1px rgba(0,0,0,.1);
	    background-color:#fff;
	}
	.btn3d.btn-primary {
	    box-shadow:0 0 0 1px #417fbd inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #4D5BBE, 0 8px 8px 1px rgba(0,0,0,0.5);
	    background-color:#4274D7;
	}
	.btn3d.btn-primary:active, .btn3d.btn-primary.active {
	    box-shadow:0 0 0 1px #417fbd inset, 0 0 0 1px rgba(255,255,255,0.15) inset, 0 1px 3px 1px rgba(0,0,0,0.3);
	    background-color:#4274D7;
	}
	.btn3d.btn-success {
	    box-shadow:0 0 0 1px #31c300 inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #5eb924, 0 8px 8px 1px rgba(0,0,0,0.5);
	    background-color:#78d739;
	}
	.btn3d.btn-success:active, .btn3d.btn-success.active {
	    box-shadow:0 0 0 1px #30cd00 inset, 0 0 0 1px rgba(255,255,255,0.15) inset, 0 1px 3px 1px rgba(0,0,0,0.3);
	    background-color: #78d739;
	}
	.btn3d.btn-danger {
	    box-shadow:0 0 0 1px #b93802 inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #AA0000, 0 8px 8px 1px rgba(0,0,0,0.5);
	    background-color:#D73814;
	}
	.btn3d.btn-danger:active, .btn3d.btn-danger.active {
	    box-shadow:0 0 0 1px #b93802 inset, 0 0 0 1px rgba(255,255,255,0.15) inset, 0 1px 3px 1px rgba(0,0,0,0.3);
	    background-color: #D73814;
	}
	
	
	
</style>


<script type="text/javascript">

	$(document).ready(function(){
		
		
		
	});// end of $(document).ready()---------------
	
	
	function goStatusEdit0(idx) { // 활동
		var bool = confirm(idx+" 번 회원을 회원상태 활동으로 변경 하시겠습니까?");
		
		if(bool) {
			var frm = document.idxFrm;
			frm.idx.value = idx;		
			
			frm.method = "POST";
			frm.action = "goStatusEdit0.ana";
			frm.submit();
		}
	}
	function goStatusEdit1(idx) { // 휴면해제
		var bool = confirm(idx+" 번 회원을 회원상태 휴면해제(활동)으로 변경 하시겠습니까?");
		
		if(bool) {
			var frm = document.idxFrm;
			frm.idx.value = idx;		
			
			frm.method = "POST";
			frm.action = "goStatusEdit1.ana";
			frm.submit();
		}
	}
	function goStatusEdit2(idx) { // 정지
		var bool = confirm(idx+" 번 회원을 회원상태 정지로 변경 하시겠습니까?");
		
		if(bool) {
			var frm = document.idxFrm;
			frm.idx.value = idx;		
			
			frm.method = "POST";
			frm.action = "goStatusEdit2.ana";
			frm.submit();
		}
	}
	function goStatusEdit3(idx) { // 탈퇴
		var bool = confirm(idx+" 번 회원을 회원상태 탈퇴로 변경 하시겠습니까?");
		
		if(bool) {
			var frm = document.idxFrm;
			frm.idx.value = idx;
			
			frm.method = "POST";
			frm.action = "goStatusEdit3.ana";
		//	frm.submit();
		}
	}
	
	
	
	
	
</script>


<div class="container">
	<h1>회원 기본정보</h1>
	<table class="table">
	    <tbody>
	        <tr>
	          <th>회원번호</th>
	            <td>${pmgMemberVO.idx }</td>
	          <th>회원명</th>
	          	<td>${pmgMemberVO.name }</td>
	        </tr>
	        <tr>
	          <th>ID</th>
	            <td>${pmgMemberVO.memberid }</td>
	          <th>E-mail</th>
	          	<td>${pmgMemberVO.email }</td>
	        </tr>
	        <tr>
	          <th>생년월일</th>
	            <td>${pmgMemberVO.birth }</td>
	            
	          <th>나이</th>
	           <c:if test="${pmgMemberVO.age <= 13}">
	            <td>${pmgMemberVO.age }세 전체(<span style="color: #ffffcc">아동</span>)</td>
			   </c:if>
			   <c:if test="${pmgMemberVO.age > 13 && pmgMemberVO.age <= 19}">
	            <td>${pmgMemberVO.age }세 전체(<span style="color: #80dfff">청소년</span>)</td>
			   </c:if>
			   <c:if test="${pmgMemberVO.age > 19}">
	            <td>${pmgMemberVO.age }세 전체(<span style="color: #ff9999">성인</span>)</td>
			   </c:if>
			  
	        </tr>
	      	<tr>
	          <th>연락처</th>
	          	<td>${pmgMemberVO.phone }</td>
	          <th>주소</th>
	            <td>${pmgMemberVO.address }</td>
	        </tr>
	        <tr>
	          <th>성별</th>
	          	<td>${pmgMemberVO.gender }</td>	          
	          <th>회원상태</th>
	            <td>${pmgMemberVO.status }</td>
	        </tr>
	        <tr>
	          <th>가입일자</th>
	          	<td>${pmgMemberVO.regDate }</td>
	          <th>로그인 기록</th>
	          	<td>마지막 로그인(${pmgMemberVO.lastDate }), 마지막 암호 변경일(${pmgMemberVO.pwDate })</td>	          
	        </tr>
	    </tbody>
	</table>
</div>

<c:if test="${\"활동\".equals(pmgMemberVO.status)}">
	<div class="container">	        
		<button type="button" class="btn btn-success btn-lg btn3d" onClick="goStatusEdit2('${pmgMemberVO.idx }')"><span class="glyphicon glyphicon-ok"></span> 정지</button>        
		<%-- <button type="button" class="btn btn-danger btn-lg btn3d" onClick="goStatusEdit3('${pmgMemberVO.idx }')"><span class="glyphicon glyphicon-remove"></span> 탈퇴</button> --%>       	
	</div>
</c:if>
<c:if test="${\"휴면\".equals(pmgMemberVO.status)}">
	<div class="container">
		<button type="button" class="btn btn-primary btn-lg btn3d" onClick="goStatusEdit1('${pmgMemberVO.idx }')"><span class="glyphicon glyphicon-cloud"></span> 휴면해제</button>	               	
	</div>
</c:if>
<c:if test="${\"정지\".equals(pmgMemberVO.status)}">
	<div class="container">
		<button type="button" class="btn3d btn btn-white btn-lg" onClick="goStatusEdit0('${pmgMemberVO.idx }')"><span class="glyphicon glyphicon-tag"></span> 활동</button>	               	
	</div>
</c:if>
<%-- <c:if test="${\"탈퇴\".equals(pmgMemberVO.status)}">
	<div class="container">
		<button type="button" class="btn3d btn btn-white btn-lg" onClick="goStatusEdit0('${pmgMemberVO.idx }')"><span class="glyphicon glyphicon-tag"></span> 활동</button>	               	
	</div>
</c:if> --%>
<!-- 
<button type="button" class="btn3d btn btn-white btn-lg"><span class="glyphicon glyphicon-tag"></span> 활동</button>
<button type="button" class="btn btn-primary btn-lg btn3d"><span class="glyphicon glyphicon-cloud"></span> 휴면해제</button>
<button type="button" class="btn btn-success btn-lg btn3d"><span class="glyphicon glyphicon-ok"></span> 정지</button>        
<button type="button" class="btn btn-danger btn-lg btn3d"><span class="glyphicon glyphicon-remove"></span> 탈퇴</button>
 -->
<form name="idxFrm">
	<input type="hidden" name="idx" />
</form>


 
<br/>
<br/>
<br/>
<%-- 
<div class="container">
 <h1>회원 도서정보</h1>
  <div class="row">
   <div class="col-md-4">
	<table class="table">
	    <tbody>
	        <tr>
	          <th>총 도서대여</th>
	            <td>10권</td>
	    	</tr>
	    	<tr>        
	          <th>총 도서반납</th>
	          	<td style="color: blue;">8권</td>
	        </tr>
	        <tr> 	
	          <th>총 도서미반납</th>
	          <td style="color: red;">2권</td>
	        </tr>
	        <tr>  	          	
	          <th>총 도서분실</th>
	          	<td>0권</td>
	        </tr>	        	     	        	       
	    </tbody>
	</table>
   </div>
   <div class="col-md-4">
	<table class="table">
	    <tbody>
	        <tr>
	          <th>도서대여</th>
	            <td>2권</td>
	    	</tr>
	    	<tr>        
	          <th>도서연장신청</th>
	          	<td>1권</td>
	        </tr>
	        <tr> 	
	          <th>예약</th>
	          <td>2019-01-14 월요일</td>
	        </tr>
	        <tr>  	          	
	          <th>대여유무</th>
	          	<td style="color: red;">가능 or 불가</td>
	        </tr>	        	     	        	       
	    </tbody>
	</table>
   </div>
  </div>	
</div>
--%>



<div class="container">
	<h1>회원 도서정보</h1>
	<div>
	  <ul class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active">
	    	<a href="#rental" aria-controls="rental" role="tab" data-toggle="tab">대여</a>
	    </li>
	    <li role="presentation">
	    	<a href="#reservation" aria-controls="reservation" role="tab" data-toggle="tab">예약</a>
	    </li>
	    <!-- <li role="presentation">
	    	<a href="#return" aria-controls="return" role="tab" data-toggle="tab">반납</a>
	    </li> -->		    
	  </ul>

	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="rental">	    			    	
	    	<div style="overflow: auto;">
				<table class="table" style="width: 1500px;">
					<thead>	    
					<tr>
						<th>번호</th>
						<th>도서일련번호</th>
						<th>도서명</th>
						<th>저자명</th>
						<th>종류</th>
						<th>출판사</th>
						<th>도서관명</th>
						<!-- <th>상태</th> -->
						<th>대여일</th>
						<th>반납일</th>
						<th>연장신청</th>
						<th>연체일</th>
						<th>연체료</th>
					</tr>
					</thead>		
					<tbody style="text-align: center;">					
						<c:if test="${not empty rentallist}">
							<c:forEach var="map" items="${rentallist}">
								<tr>													
									<td>${map.ROWNUM}</td>							
									<td>${map.BOOKID}</td>
									<td>${map.TITLE}</td>
									<td>${map.AUTHOR}</td>
									<td>${map.CATEGORYNAME}</td>
									<td>${map.PUBLISHERNAME}</td>
									<td>${map.LIBRARYNAME}</td>
									<%-- <td>
								<c:if test="${map.STATUS == 1}">
									대여중
								</c:if>
									</td> --%>
									<td>${map.RENTALDATE}</td>
									<td>${map.DEADLINE}</td>
									<td>${map.RENEW}</td>
								<c:if test="${map.OVERDUE <= 0}">
									<td>0일</td>
									<td>없음</td>
								</c:if>
								<c:if test="${map.OVERDUE > 0}">	
									<td>${map.OVERDUE}일</td>
									<td><fmt:formatNumber value="${map.LATEFEE}" pattern="###,###" />원</td>	
								</c:if>					
								</tr>
							</c:forEach>
						</c:if>
						
						<c:if test="${empty rentallist}">
							<tr>
								<td colspan="12" style="text-align: center;">현재 대여중인 도서가 없습니다.</td>
							</tr>
						</c:if>					
					</tbody>
				</table>
			</div>						    
	    </div>
	    <div role="tabpanel" class="tab-pane" id="reservation">
	    	<table class="table">
				<thead>	    
				<tr>
					<th>번호</th>
					<th>도서일련번호</th>
					<th>도서명</th>
					<th>저자명</th>
					<th>종류</th>
					<th>출판사</th>
					<th>도서관명</th>
					<!-- <th>상태</th> -->
					<th>예약일</th>					
				</tr>
				</thead>		
				<tbody>
					<c:if test="${not empty reservationList}">
						<c:forEach var="map" items="${reservationList}">
							<tr>
								<td>${map.ROWNUM}</td>
								<td>${map.BOOKID}</td>
								<td>${map.TITLE}</td>
								<td>${map.AUTHOR}</td>
								<td>${map.CATEGORYNAME}</td>
								<td>${map.PUBLISHERNAME}</td>
								<td>${map.LIBRARYNAME}</td>
								<%-- <td>${map.STATUS}</td> --%>
								<td>${map.RESERVEDATE}</td>						
							</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty reservationList}">
						<tr>
							<td colspan="8" style="text-align: center;">현재 예약중이 도서가 없습니다.</td>
						</tr>
					</c:if>					
				</tbody>
			</table>	    
	    </div>
	    	    		    
	  </div>
	</div>
</div>



<!-- <div role="tabpanel" class="tab-pane" id="return">	    
	    	<div class="col-sm-12 pull-center well">
				<form class="form-inline" action="#" method="POST">				
					<select class="form-control">
						<option>도서명</option>
						<option>저자</option>
						<option>출판사</option>
						<option>도서번호</option>
					</select>
			                  
					<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="Search..." />
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i>search</i>
								</button>
							</span>
					</div>				
				</form>
			</div>
	    
	    </div> -->
<!--  
<div class="container" style="text-align: center;">
	<ul class="pagination">
              <li class="disabled"><a href="#">«</a></li>
              <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">»</a></li>
	</ul>
</div>
-->
<br/>
<br/>































