<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	$(document).ready(function() {
		searchKeep();
		
		// 한 행을 클릭할 경우 체크박스 선택, 해제
		$(".td").click(function() {
			var flag = $(this).parent().find(".check").is(":checked");
			$(this).parent().find(".check").prop("checked",!flag);
		});// end of tr click
		
		$("#searchWord").keydown(function(event) {
	         if(event.keyCode == 13) {
	        	 search();
	         }// end of if
	    });// end of searchword keydown
		
	});// end of document ready
	
	function search() {// 검색
		var searchFrm = document.searchFrm;
		var searchWord = $("#searchWord").val();
		searchFrm.method = "GET";
		searchFrm.action = "deleteLog.ana";
		searchFrm.submit();
	}// end of search
	
	function searchKeep() {// 검색어 유지
		if(${searchWord != null && searchWord!= "" && searchWord != "null"}) {
			$("#colname").val("${colname}");
			$("#searchWord").val("${searchWord}");
		}// end of if
	}// end of searchKeep
	
	function allCheckFalse() {// 체크박스 전체해제
		var flag = $("input:checkbox[name=check]").prop("checked",false);
	}// end of allCheck
	
	function restore(delid) {// 삭제도서 복구
		var restoreFrm = searchFrm;
		restoreFrm.method = "POST";
		restoreFrm.action = "restore.ana";
		
		var flag = false;
		var count = 0;
		$(".tr-row").each(function() {
			flag = $(this).find(".check").is(":checked");
			
			if(!flag) {
				$(this).find(".delid").attr("disabled", true);
			} else {
				count++;
			}// end of if~else
		});// end of each

		var choice = confirm(count+"권의 도서를 모두 복구하시겠습니까?");
		if(count < 1) {
			alert("복구할 도서를 선택하지 않았습니다.");
			$(".tr-row").find(".delid").attr("disabled", false);
			
			return ;
		}// end of if
		
		if(!choice) {
			return ;
		}// end of if

		restoreFrm.submit();
	}// end of restore
	
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
				
				<br/>
				<div style="float: right;">
					<div class="collapse navbar-collapse" id="bs-slide-dropdown">
				        <ul class="nav navbar-nav navbar-right">
				            <li class="dropdown">
				              <i class="dropdown-toggle glyphicon glyphicon-th-list" data-toggle="dropdown" style="cursor: pointer;"></i>
				              <ul class="dropdown-menu" role="menu">
				                <li><a href="#">도서일련번호 오름차순</a></li>
				                <li><a href="#">도서명 오름차순</a></li>
				                <li><a href="#">작가명 오름차순</a></li>
				              </ul>
				            </li>
				        </ul>
					</div>
				</div>
				<br/>
				
				<div style="margin-bottom: 2%; margin-top: 2%;">
					<div style="float: left;">
						<button type="button" id="lock"class="btn btn-dark" onclick="allCheckFalse();"><i class="glyphicon glyphicon-remove"></i></button>
					</div>
					
					<div style="float: right;">
						<button type="button" class="btn btn-success" onclick="restore();">복원<i class="glyphicon glyphicon-refresh"></i></button>
					</div>
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
							<th style="text-align: center;">권수</th>
							<th style="text-align: center;">삭제일자</th>
							<th style="text-align: center;">처리자</th>
						</tr>
					</thead>
					<tbody align="center">
						<c:if test="${empty deleteBookList}">
						<tr>
							<td colspan="10"><span style="color: #ff4d4d; font-weight: bold;">도서가 존재하지 않습니다.</span></td>
						</tr>
						</c:if>

							<c:forEach var="deleteBookVO" items="${deleteBookList}">
						<tr class="tr-row" style="cursor: pointer;">
							<td>
								<input type="checkbox" class="check" name="check" />
								<input type="text" class="delid" name="delid" value="${deleteBookVO.delid}"/>
							</td>
							<td class="td">${deleteBookVO.rno}</td>
							<td class="td">${deleteBookVO.bookid}</td>
							<td class="td">${deleteBookVO.title}</td>
							<td class="td">${deleteBookVO.author}</td>
							<td class="td">${deleteBookVO.pubname}</td>
							<td class="td">${deleteBookVO.agecode}</td>
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