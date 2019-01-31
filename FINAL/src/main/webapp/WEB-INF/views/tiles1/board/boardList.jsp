<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
<style type="text/css">
	/* table, th, td {border: solid gray 1px;}
	#table {border-collapse: collapse; width: 750px;} 
	
	.subjectstyle {font-weight: bold;
    	           color: navy;
    	           cursor: pointer; }
	
	
	#table {border-collapse: collapse; width: 920px;}
	#table th, #table td {padding: 5px;}
	#table th {background-color: #DDDDDD;}
	
	a{text-decoration: none;}  */
	
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 70%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

ul {
	padding-left: 0px;
}
.subject {
	cursor: pointer;
}

.pagination {
  display: inline-block;
} 

.pagination a {
  color: black;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
}

.pagination a.active {
  background-color: #5bc1de;
  color: white;
  border: 1px solid #5bc1de;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
 
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
		
		if(${search != null && search != '' && search != 'null'}) {
			searchkeep();
		}

		
	});// end of $(document).ready()----------------------
	
	function goView(idx, gobackURL) {
		
		var frm = document.goViewFrm;
		frm.idx.value = idx;
		frm.gobackURL.value = gobackURL;
		frm.method = "GET";
		frm.action = "boardView.ana";
		frm.submit();
		
	}	
	
	function goSearch() {
		var frm = document.searchFrm;
		
		frm.action="<%=request.getContextPath()%>/boardList.ana";
		frm.method="GET";
		frm.submit();
	}
	
	function searchkeep(){
		
		$("#colname").val("${colname}");
		$("#search").val("${search}");
		
	}
		
</script>

<div style="padding-left: 20%; border: solid 0px red;">
	<h1 style="margin-bottom: 30px;"><a href="<%=request.getContextPath()%>/boardList.ana;">자유게시판</a></h1>
		
		<!-- ==== 글 검색 폼 추가하기 : 제목, 내용, 글쓴이로 검색을 하게 한다. ==== -->      
		<form name="searchFrm" style="padding-bottom: 1%;">
			<select name="colname" id="colname" style="height: 30px;">
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="name">성명</option>
			</select>
			<input type="text" name="search" id="search" size="40" style="height: 30px;" />
			<button type="button" style="margin-bottom: 4px;" class="btn btn-info btn-sm" onclick="goSearch();">검색</button>
			<!-- ==== 글 쓰기 버튼 ==== -->
			<button style="float:right; margin-right:30%;" type="button" class="btn btn-info btn-sm" onclick="javascript:location.href='<%=request.getContextPath()%>/boardAdd.ana'">글 쓰기</button>
		</form>
		
	<table id="table">     
		<tr>
			<th style="width: 70px;  text-align: center;" >글번호</th>
			<th style="width: 360px; text-align: center;" >제목</th>
			<th style="width: 70px;  text-align: center;" >성명</th>
			<th style="width: 180px; text-align: center;" >날짜</th>
			<th style="width: 70px;  text-align: center;" >조회수</th>
			<!-- <th style="width: 70px;  text-align: center;" >파일</th>	 -->		
		</tr>	
		
		<c:forEach items="${boardList2}" var="boardvo">
			<tr>
				<td style="text-align: center;">${boardvo.idx}</td>
				<td>
				<!-- 원글 -->
				<c:if test="${boardvo.depthno == 0}">
					<c:if test="${boardvo.commentCount > 0 }">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}&nbsp;</span>
						<span style="font-weight: bold; font-style: italic; font-size: smaller; vertical-align: super; color: red;">[${boardvo.commentCount}]</span>
						<span>
							<c:if test="${boardvo.fileCount > 0}">
								<img src="<%=request.getContextPath()%>/resources/img/disc-icon.png" border="0">
							</c:if>
						</span>					
					</c:if>
					<c:if test="${boardvo.commentCount == 0}">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}</span>
						<span>
							<c:if test="${boardvo.fileCount > 0}">
								<img src="<%=request.getContextPath()%>/resources/img/disc-icon.png" border="0">
							</c:if>
						</span>	
					</c:if>
				</c:if>	
				<!-- 답변글 -->
				<c:if test="${boardvo.depthno > 0}">
					<c:if test="${boardvo.commentCount > 0 }">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');"><span style="color: red; font-style: italic; padding-left: ${boardvo.depthno * 20}">&nbsp;└Re&nbsp;</span>${boardvo.subject}&nbsp;</span>
						<span style="font-weight: bold; font-style: italic; font-size: smaller; vertical-align: super; color: red;">[${boardvo.commentCount}]</span>
						<span>
							<c:if test="${boardvo.fileCount > 0}">
								<img src="<%=request.getContextPath()%>/resources/img/disc-icon.png" border="0">
							</c:if>
						</span>					
					</c:if>
					<c:if test="${boardvo.commentCount == 0}">
						<span class="subject" onclick="goView('${boardvo.idx}','${gobackURL}');"><span style="color: red; font-style: italic; padding-left: ${boardvo.depthno * 20}">&nbsp;└Re&nbsp;</span>${boardvo.subject}</span>
						<span>
							<c:if test="${boardvo.fileCount > 0}">
								<img src="<%=request.getContextPath()%>/resources/img/disc-icon.png" border="0">
							</c:if>
						</span>	
					</c:if>
				</c:if>	
				</td>
				
				<td style="text-align: center;">${boardvo.name}</td>
				<td style="text-align: center;">${boardvo.regDate}</td>
				<td style="text-align: center;">${boardvo.readCount}</td>

				<%-- <td align="center">
						<c:if test="${boardvo.fileCount > 0}">
							<img src="<%=request.getContextPath()%>/resources/img/disc-icon.png" border="0">
						</c:if>
						<c:if test="${boardvo.fileCount == 0 }">
							<img src="<%=request.getContextPath()%>/resources/img/nodisc-icon.png" border="0">
						</c:if>
					</td> --%>
				</tr>
				</c:forEach>
	
		
		<c:if test="${boardList2 == null || empty boardList2 }">
			<tr>
				<td colspan="6" style="text-align: center;">검색 된 게시물이 없습니다.</td>
			</tr>
		</c:if>
		
		</table>

	<form name="goViewFrm">
		<input type="hidden" name="idx">
		<input type="hidden" name="gobackURL">
	</form>

</div>

<!-- ==== 페이지바 보여주기 ====  -->
<div class="pagination" style=" margin-left: 33%">
	${pagebar}
</div>
