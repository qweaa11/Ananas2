<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style type="text/css">
	table, th, td {border: solid gray 1px;}
	#table {border-collapse: collapse; width: 750px;} 
	
	.subjectstyle {font-weight: bold;
    	           color: navy;
    	           cursor: pointer; }
	
	
	#table {border-collapse: collapse; width: 920px;}
	#table th, #table td {padding: 5px;}
	#table th {background-color: #DDDDDD;}
	
	a{text-decoration: none;} 
	
	    
</style>

 
<script type="text/javascript">

	$(document).ready(function(){
		
		$(".subject").bind("mouseover", function(event){
			var $target = $(event.target);
			$target.addClass("subjectstyle");
		});
		
		$(".subject").bind("mouseout", function(event){
			var $target = $(event.target);
			$target.removeClass("subjectstyle");
		});

		
	});// end of $(document).ready()----------------------
	
	function goView(idx, gobackURL) {
		
		var frm = document.goViewFrm;
		frm.idx.value = idx;
		frm.gobackURL.value = gobackURL;
		frm.method = "GET";
		frm.action = "boardView.ana";
		frm.submit();
		
	}
	
	
	
		
</script>

<div style="padding-left: 10%; border: solid 0px red;">
	<h1 style="margin-bottom: 30px;">글목록</h1>
	
	<table id="table">
		<tr>
			<th style="width: 70px;  text-align: center;" >글번호</th>
			<th style="width: 360px; text-align: center;" >제목</th>
			<th style="width: 70px;  text-align: center;" >성명</th>
			<th style="width: 180px; text-align: center;" >날짜</th>
			<th style="width: 70px;  text-align: center;" >조회수</th>
			<th style="width: 70px;  text-align: center;" >파일</th>
			<th style="width: 100px;  text-align: center;" >크기(bytes)</th>
			
		</tr>	
		
		<c:forEach items="${boardList}" var="boardvo">
			<tr>
				<td align="center">${boardvo.idx}</td>
				<td>
				<!-- 원글 -->
				<c:if test="${boardvo.depthno == 0}">
					<c:if test="${boardvo.commentCount > 0 }">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}&nbsp;</span>
						<span style="font-weight: bold; font-style: italic; font-size: smaller; vertical-align: super; color: red;">[${boardvo.commentCount}]</span>					
					</c:if>
					<c:if test="${boardvo.commentCount == 0}">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}</span>
					</c:if>
				</c:if>	
				<!-- 답변글 -->
				<c:if test="${boardvo.depthno > 0}">
					<c:if test="${boardvo.commentCount > 0 }">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');"><span style="color: red; font-style: italic; padding-left: ${boardvo.depthno * 20}">└Re&nbsp;</span>${boardvo.subject}&nbsp;</span>
						<span style="font-weight: bold; font-style: italic; font-size: smaller; vertical-align: super; color: red;">[${boardvo.commentCount}]</span>					
					</c:if>
					<c:if test="${boardvo.commentCount == 0}">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');"><span style="color: red; font-style: italic; padding-left: ${boardvo.depthno * 20}">└Re&nbsp;</span>${boardvo.subject}</span>
					</c:if>
				</c:if>	
				</td>
				
				<td align="center">${boardvo.name}</td>
				<td align="center">${boardvo.regDate}</td>
				<td align="center">${boardvo.readCount}</td>
				<td align="center">
					<c:if test="${not empty boardvo.fileName}">
						<img src="<%=request.getContextPath()%>/resources/img/disc-icon.png" border="0">
					</c:if>
					<c:if test="${empty boardvo.fileName}">
						<img src="<%=request.getContextPath()%>/resources/img/nodisc-icon.png" border="0">
					</c:if>
				</td>
				<td >
					<c:if test="${not empty boardvo.fileSize}">
						${boardvo.fileSize}
					</c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<br/>
	
	
	<form name="goViewFrm">
		<input type="hidden" name="idx">
		<input type="hidden" name="gobackURL">
	</form>
	
	<!-- ==== 페이지바 보여주기 ====  -->
	<div align="center">
		${pagebar}
	</div>
	<br/>
	
	<!-- ==== 글 검색 폼 추가하기 : 제목, 내용, 글쓴이로 검색을 하게 한다. ==== -->
	<form name="searchFrm">
		<select name="colname" id="colname" style="height: 25px;">
			<option value="subject">제목</option>
			<option value="content">내용</option>
			<option value="name">성명</option>
		</select>
		<input type="text" name="search" id="search" size="40" />
		<button type="button" onclick="goSearch();">검색</button>
	</form>
	
</div>
