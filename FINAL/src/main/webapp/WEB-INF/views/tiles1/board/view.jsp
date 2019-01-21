
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
	table, th, td, input, textarea {border: solid gray 1px;}
	
	#table, #table2 {border-collapse: collapse;
	 		         width: 1000px;
	 		        }
	#table th, #table td{padding: 5px;}
	#table th{width: 120px; background-color: #DDDDDD;}
	#table td{width: 860px;}
	.long {width: 470px;}
	.short {width: 120px;} 	
	
	.move  {cursor: pointer;}
	.moveColor {color: #660029; font-weight: bold;}
	
	a{text-decoration: none;}	
	
	

</style>

<script type="text/javascript">
    
	$(document).ready(function(){
	
		
		
		

	});// end of $(document).ready()----------------------
    
	
</script>

<div style="padding-left: 10%; border: solid 0px red;">
	<h1>글내용보기</h1>
	
	<table id="table">
		<tr>
			<th>글번호</th>
			<td>${boardvo.idx}</td>
		</tr>
		<tr>
			<th>성명</th>
			<td>${boardvo.name}</td>
		</tr>
		<tr>
           	<th>제목</th>
           	<td>${boardvo.subject}</td>
        	</tr>
		<tr>
			<th>내용</th>
			<td>${boardvo.content}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${boardvo.readCount}</td>
		</tr>
		<tr>
			<th>날짜</th>
			<td>${boardvo.regDate}</td>
		</tr>
		
		<!-- ==== #148. 첨부파일 이름 및 파일크기를 보여주고 첨부파일 다운받게 만들기 ==== -->
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${sessionScope.loginuser != null}">
					<a href="<%=request.getContextPath()%>/download.action?idx=${boardvo.idx}">${boardvo.orgFileName}</a>
				</c:if>
				
				<c:if test="${sessionScope.loginuser == null}">
					${boardvo.orgFileName}
				</c:if>
			</td>
		</tr>
		
		<tr>
			<th>파일크기(bytes)</th>
			<td>${boardvo.fileSize}</td>
		</tr>

	</table>
	
	<br/>
	
	<div style="margin-bottom: 1%;">이전글 : <span class="move" onclick="javascript:location.href='view.action?idx=${boardvo.previousidx}'">${boardvo.previoussubject}</span></div>
	<div style="margin-bottom: 1%;">다음글 : <span class="move" onclick="javascript:location.href='view.action?idx=${boardvo.nextidx}'">${boardvo.nextsubject}</span></div>
	
	<br/>
	
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/${gobackURL}'">목록보기</button>
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/edit.action?idx=${boardvo.idx}'">수정</button>
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/del.action?idx=${boardvo.idx}'">삭제</button>
	
	<!-- ==== #123. 답변글쓰기 버튼 추가하기 (현재 보고 있는 글이 작성하려는 답변글의 원글(부모글)이 된다. ==== -->
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/add.action?fk_idx=${boardvo.idx}&groupno=${boardvo.groupno}&depthno=${boardvo.depthno}'">답변글쓰기</button>
		
	<br/>
	<br/>
	<p style="margin-top: 3%; font-size: 16pt;">댓글쓰기</p>
	<!-- ===== #84. 댓글쓰기 폼 추가 ===== -->
	<form name="addWriteFrm">     
		      <input type="hidden" name="fk_userid" value="${sessionScope.loginuser.userid}" readonly />
		성명 : <input type="text" name="name" value="${sessionScope.loginuser.name}" class="short" readonly/>
	       댓글내용 : <input type="text" name="content" class="long" />
	    
	    <!-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) -->
	    <input type="hidden" name="parentidx" value="${boardvo.idx}" />  
	    
	    <button type="button" onClick="goAddWrite();" >쓰기</button>    
	</form>
	
	<!-- ====#91. 댓글 내용 보여주기 ==== -->
	<table id="table2" style="margin-top: 2%; margin-bottom: 3%;">
		<thead>
			<tr>
				<th style="text-align: center;">댓글작성자</th>
				<th style="text-align: center;">내용</th>
				<th style="text-align: center;">작성일자</th>
			</tr>
		</thead>
		<tbody id="commentDisplay"></tbody>
	</table>
	
	<div id="pageBar" style="height: 50px; margin-left: 30%;"></div>
</div>

