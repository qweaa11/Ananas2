<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cxtPath = request.getContextPath();
%>

<style type="text/css">
.subjectstyle {
	font-weight: bold;
	color: navy;
	cursor: pointer;
}
</style>

<script type="text/javascript">

$(document).ready(function(){
	$('.search-panel .dropdown-menu').find('a').click(function(e) {		
		e.preventDefault();
		
		var param = $(this).attr("href").replace("#","");		
		var concept = $(this).text();
		
		$('.search-panel span#search_concept').text(concept);		
		$('.input-group #search_param').val(param);
	});
		
		$(".subject").bind("mouseover", function(event){
			 var $target = $(event.target);
			 $target.addClass("subjectstyle");
		});
		  
		$(".subject").bind("mouseout", function(event){
			 var $target = $(event.target);
			 $target.removeClass("subjectstyle");
		});
		
		$(document).on("click", ".deletebutton", function(){
			
			var idx = $(this).parent().parent().parent().find(".boardidx").text();
			
			console.log(idx);
			
			$("#idx").val(idx);
			
		});
		
		if(${search != "" && search != null && search != "null"}){
			searchKeep();	
		}	
			
		
		
	
});

function goView(idx, gobackURL) {
	
	var frm = document.goViewFrm;
	frm.idx.value = idx;
	frm.gobackURL.value = gobackURL;
	
	frm.method = "GET";
	frm.action = "BSBview.ana";
	frm.submit();
	
}	

function goDelete(){
	var frm = document.delFrm;
	var pwval = frm.pw.value.trim();
	if(pwval == ""){
		alert("암호를 입력하세요!");
		return;
	}
	
	frm.action = "<%=request.getContextPath()%>/delEnd.ana";
	frm.method = "POST";
	frm.submit();
}

function goSearch() {
	var frm = document.searchFrm;
	
	frm.action = "<%=request.getContextPath()%>/board.ana";
	frm.method = "GET";
	frm.submit();
	
}	

function searchKeep(){
	
	$("#colname").val("${colname}");
	$("#search").val("${search}");
	
}	
</script>




<div class="container">
	<div class="row">

		<div class="col-md-12">
			<h4>
				<a style="color: black;" href="board.ana"><img alt="boardlist"
					src="resources/img/boardlist.png" width="30px;" height="30px;">&nbsp;<span
					style="font-weight: bolder;">자유 게시판</span></a>
			</h4>
			<div class="table-responsive">

				<!-- =====#105. 글검색 폼 추가하기 : 제목, 내용, 글쓴이로 검색을 하도록 한다. ===== -->
				<div class="container">
					<div class="row">
						<div style="float: right; padding-right: 50px;">
							<form name="searchFrm">
								<select name="colname" id="colname"
									style="height: 30px; vertical-align: middle;">
									<option value="subject">제목</option>
									<option value="content">내용</option>
									<option value="name">성명</option>
								</select> <input type="text" name="search" id="search" size="40"
									style="height: 30px; vertical-align: middle;" />
								<button class="btn btn-default" type="button"
									style="vertical-align: middle;" onClick="goSearch();">
									<span class="glyphicon glyphicon-search"></span>
								</button>

							</form>
						</div>
					</div>
				</div>



				<table id="mytable" class="table table-bordred table-striped">

					<thead>
						<th>번호</th>
						<th style="width: 60%">제목</th>
						<th>게시일자</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>삭제</th>
					</thead>

					<tbody>
						<c:forEach var="boardvo" items="${boardList}">
							<tr>
								<td class="boardidx">${boardvo.idx}</td>
								<c:if test="${boardvo.commentCount > 0}">
									<td><span class="subject"
										onClick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}&nbsp;<span
											style="color: red; font-weight: bold; font-style: italic; font-size: xx-small; vertical-align: super;">[${boardvo.commentCount}]</span></span></td>
								</c:if>
								<c:if test="${boardvo.commentCount == 0}">
									<td><span class="subject"
										onClick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}</span></td>
								</c:if>
								<td>${boardvo.regDate}</td>
								<td>${boardvo.name}</td>
								<td>${boardvo.readCount}</td>
								<td><p data-placement="top" data-toggle="tooltip"
										title="Delete">
										<button class="btn btn-danger btn-xs deletebutton"
											data-title="Delete" data-toggle="modal" data-target="#delete">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</p></td>
							</tr>

						</c:forEach>

						<tr>
							<td colspan="7" style="background-color: #ffffff;"><a
								href="<%=cxtPath%>/add.ana"><button
										class="btn btn-primary btn-xs" data-title="Edit"
										data-target="#edit" style="float: right;">
										<span class="glyphicon glyphicon-pencil">&nbsp;글쓰기</span>
									</button></a></td>
						</tr>

					</tbody>

				</table>

				<form name="goViewFrm">
					<input type="hidden" name="idx"> <input type="hidden"
						name="gobackURL">
				</form>


				<!-- ==== #121. 페이지바 보여주기 ==== -->
				<div align="center">
					<span id="pagebar">${pagebar}</span>
				</div>




			</div>

		</div>
	</div>
</div>



<div class="modal fade" id="edit" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
				<h4 class="modal-title custom_align" id="Heading">Edit Your
					Detail</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<input class="form-control " type="text" placeholder="Mohsin">
				</div>
				<div class="form-group">

					<input class="form-control " type="text" placeholder="Irshad">
				</div>
				<div class="form-group">
					<textarea rows="2" class="form-control"
						placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>


				</div>
			</div>
			<div class="modal-footer ">
				<button type="button" class="btn btn-warning btn-lg"
					style="width: 100%;">
					<span class="glyphicon glyphicon-ok-sign"></span> Update
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<div class="modal fade" id="delete" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="delFrm">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align" id="Heading">글삭제하기</h4>
				</div>
				<div class="modal-body">

					<div class="alert alert-danger">
						<span class="glyphicon glyphicon-warning-sign"></span>정말 삭제하시겠습니까?
					</div>
					<table id="table">
						<tr>
							<th>암호</th>
							<td><input type="password" name="pw" class="short" /> <input
								type="hidden" name="idx" id="idx" value="${idx}" /> <!-- 삭제해야할 글번호와 함께 사용자가 입력한 암호를 전송하도록 한다. -->

							</td>

						</tr>

					</table>

				</div>
				<div class="modal-footer ">
					<button type="button" class="btn btn-success" onClick="goDelete();">
						<span class="glyphicon glyphicon-ok-sign"></span>삭제
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span>취소
					</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

