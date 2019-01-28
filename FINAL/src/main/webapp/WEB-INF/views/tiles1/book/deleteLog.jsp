<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

	$(document).ready(function() {
		
		$("#searchWord").keydown(function(event) {
	         if(event.keyCode == 13) {
	        	 search();
	         }// end of if
	    });// end of searchword keydown
		
	});// end of document ready
	
	function search() {
		var searchFrm = document.searchFrm;
		searchFrm.method = "GET";
		searchFrm.action = "deleteLog.ana";
		searchFrm.submit();
	}// end of search
	
	function searchKeep() {
		if(${searchWord != null && searchWord!= "" && searchWord != "null"}) {
			$("#colname").val("${colname}");
			$("#searchWord").val("${searchWord}");
		}// end of if
	}// end of searchKeep
	
	function recover() {
		
	}// end of 
</script>

<div class="container">
	<div class="row">

		<div class="container border" style="height: 50%; width: 100%; align-content: center;">

			<form name="searchFrm" onsubmit="return false">
				<div>
					<select id="colname" name="colname" style="height: 25px;" >
						<option value="title">도서명</option>
						<option value="author">저자</option>
						<option value="pubname">출판사</option>
					</select>

					<input type="text" id="searchWord" name="searchWord" />
					<input type="hidden" name="goBackURL" />
					<button type="button" class="btn btn-info" onclick="search();">검색</button>
				</div>

				<div style="float: left;">
					<button type="button" id="lock"class="btn btn-dark" onclick="allCheckFalse();"><i class="glyphicon glyphicon-remove"></i></button>
				</div>
				<div style="float: right;">
					<button type="button" class="btn btn-dark" onclick="recover('${goBackURL}');">복원하기<i class="glyphicon glyphicon-refresh"></i></button>
				</div>

				<table id="table-member" class="table table-striped table-hover">
					<thead>
						<tr>
							<th style="text-align: center;">선택</th>
							<th style="text-align: center;">번호(rno)</th>
							<th style="text-align: center;">도서일련번호</th>
							<th style="text-align: center;">도서명</th>
							<th style="text-align: center;">작가</th>
							<th style="text-align: center;">출판사</th>
							<th style="text-align: center;">연령대</th>
							<th style="text-align: center;">ISBN</th>
							<th style="text-align: center;">권수</th>
							<th style="text-align: center;">삭제일자</th>
							<th style="text-align: center;">처리자</th>
						</tr>
					</thead>
					<tbody align="center">
						<c:if test="${empty deleteBookList}">
						<tr>
							<td colspan="11"><span style="color: #ff4d4d; font-weight: bold;">도서가 존재하지 않습니다.</span></td>
						</tr>
						</c:if>

							<c:forEach var="deleteBookVO" items="${deleteBookList}">
						<tr class="tr-row">
							<td><input type="checkbox" class="check" name="check" /></td>
							<td class="td">${deleteBookVO.rno}</td>
							<td class="td">${deleteBookVO.bookid}</td>
							<td class="td">${deleteBookVO.title}</td>
							<td class="td">${deleteBookVO.author}</td>
							<td class="td">${deleteBookVO.pubname}</td>
							<td class="td">${deleteBookVO.agecode}</td>
							<td class="td">${deleteBookVO.iSBN}</td>
							<td class="td">${deleteBookVO.count}</td>
							<td class="td">${deleteBookVO.deldate}</td>
							<td class="td">${deleteBookVO.cleanerid}</td>
						</tr>
							</c:forEach>
					
					</tbody>
				</table>
			</form>
			
		</div>

	</div>
	
</div>