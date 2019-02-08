<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>

	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
	
	<style type="text/css">
		table, th, td {border: 1px solid lightgrey;}
		th {background-color: #f2f2f2;
			text-align: center;}
		td {text-align: center;}
	</style>
	
	<script type="text/javascript">
	
		// alert("${gobackURL}");
		
		$(document).ready(function(){
			
			if(${noticevo.commentCount > 0}) {
				goViewComment("1");
			}
			
			$("#WriteComment").keydown(function(event){
				if(event.keyCode == 13) {
					goCommentWrite();
				}
			});
			
			
		});// end of $(document).ready()
		
		// 댓글 쓰기
		function goCommentWrite() {
			
			var frm = document.commentWriteFrm;
			
			var content = frm.content.value.trim();
			
			if(content == "") {
				alert("내용을 입력하세요~!");
				frm.content.value = "";
				frm.content.focus();
				return;
			}
			
			var queryString = $("form[name=commentWriteFrm]").serialize();
			
			$.ajax({
				url:"commentWrite.ana",
				data:queryString,
				type:"POST",
				dataType:"JSON",
				success:function(json){
					var html = "";
					
					html = "<div>"+
								"<i class='fas fa-book-reader' style='font-size:15px;'> "+json.LIBID_FK+"("+json.NAME+")</i>&nbsp;&nbsp;&nbsp;&nbsp;"+
								"<i class='far fa-bookmark' style='font-size:15px;'> "+json.LIBCLASS+"("+json.LIBNAME+")</i>"+
								"<pre>"+json.CONTENT+"</pre>"+
							"</div>"+
							"<div align='right'>"+
								"<i class='far fa-calendar-alt' style='font-size:15px; text-align: right;'> "+json.REGDATE+"</i>"+
							"</div>";
							 
				//	$("#commentList").prepend(html);
					frm.content.value = "";
					goViewComment("1");
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
		}// end of goCommentWrite()
		
		// 댓글내용 보여주기
		function goViewComment(currentShowPageNo) {
			
			var form_data = {"idx":"${noticevo.idx}",
							 "currentShowPageNo":currentShowPageNo};
			
			$.ajax({
				url:"commentListlib.ana",
				data:form_data,
				type:"GET",
				dataType:"JSON",
				success:function(json){
					var html = "";
					
					$.each(json, function(entryIndex, entry){
						if(entry.STATUS == 0) {
							html += "<div>"+
										"<i class='fas fa-book-reader' style='font-size:15px;'> "+entry.LIBID_FK+"("+entry.NAME+")</i>&nbsp;&nbsp;&nbsp;&nbsp;"+
										"<i class='far fa-bookmark' style='font-size:15px;'> "+entry.LIBCLASS+"("+entry.LIBNAME+")</i>&nbsp;&nbsp;&nbsp;&nbsp;"+
										
										((entry.LIBID_FK == "${sessionScope.loginLibrarian.libid}")?
										"<i class='fas fa-comment-slash'><a href='#' onClick='goCommentDelete(\""+entry.IDX+"\",\""+entry.LIBID_FK+"\")'>삭제</a></i>":"&nbsp;")
										
										+"<pre>"+entry.CONTENT+"</pre>"+
									"</div>"+
									"<div align='right'>"+
										"<i class='far fa-calendar-alt' style='font-size:15px; text-align: right;'> "+entry.REGDATE+"</i>"+
									"</div>";
						}
					});// end of $.each()
					
					$("#commentList").empty().html(html);
					
					makeCommentPageBar(currentShowPageNo); // 페이지바함수 호출
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
			
		}// end of function goViewComment(currentShowPageNo)
		
		function makeCommentPageBar(currentShowPageNo) {
			var form_data = {idx:"${noticevo.idx}",
							 sizePerPage:"5"};
			
			$.ajax({
				url:"getLibCommentTotalPage.ana",
				data:form_data,
				type:"GET",
				dataType:"JSON",
				success:function(json){
					
					if(json.TOTALPAGE > 0) {
						var totalPage = json.TOTALPAGE;
						var pageBarHTML = "";
						var blockSize = 5;
						var loop = 1;
						var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;
						
						if( pageNo != 1 ) {
					    	pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-default btn-circle'><</button></a>&nbsp;";
					    }
						while(!(loop > blockSize || pageNo > totalPage)) {
							if(pageNo == currentShowPageNo) {						
								pageBarHTML += "&nbsp;<button type='button' class='btn btn-default btn-circle'><span style='color: #df80ff; font-size: 12pt; font-weight: bold; text-decoration: underline;'>"+pageNo+"</span></button>&nbsp;"; 
							}
							else {
								pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\""+pageNo+"\");'><button type='button' class='btn btn-default btn-circle'>"+pageNo+"</button></a>&nbsp;";
							}
							
							loop++;
							pageNo++;
						}// end of while
						
						if( !(pageNo > totalPage) ) {
					    	pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\""+pageNo+"\");'><button type='button' class='btn btn-default btn-circle'>></button></a>&nbsp;";
					    }
						$("#pageBar").empty().html(pageBarHTML);
						pageBarHTML = "";
						
					}
					else {
						// 댓글이 없는 경우 
						$("#pageBar").empty();
					}
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
		}
		
		// 댓글 삭제
		function goCommentDelete(cmtIDX, cmtLibId) {
			var msg = confirm("댓글을 삭제하시겠습니까?");
			
			if(msg == true){
				deleteCmt(cmtIDX, cmtLibId);
			}
			else {
				return false;	// 삭제 취소
			}
		}
		
		function deleteCmt(cmtIDX, cmtLibId) {
			
			var frm = document.cmtDelFrm;
			frm.idx.value = cmtIDX;
			frm.libid_fk.value = cmtLibId;
			
			frm.action = "commentDelete.ana";
			frm.method = "POST";
			frm.submit();
		}
		
	</script>

</head>
<body>

<div class="container">
	<h1 style="text-align: center;">공지사항</h1>
	<div class="row">
		<table class="table">			
			<tbody>
				<tr>
					<th class="col-md-2">글번호</th>
						<td>${noticevo.idx}</td>
					<th class="col-md-2">작성자</th>
						<c:if test="${noticevo.adminid_fk != null}">
							<td>${noticevo.adminid_fk}(${noticevo.name})</td>
						</c:if>
						<c:if test="${noticevo.libid_fk != null}">
							<td>${noticevo.libid_fk}(${noticevo.name})</td>
						</c:if>
					<th class="col-md-2">작성일자</th>
						<td>${noticevo.regDate}</td>
					<th class="col-md-2">조회수</th>
						<td>${noticevo.readCount}</td>
				</tr>				
			</tbody>
		</table>
		
		<table class="table">			
			<tbody>
				<c:if test="${not empty noticevo.orgFileName}">
					<tr>
						<th class="col-md-4">첨부파일</th>
							<td><a href="fileDownload.ana?idx=${noticevo.idx}"><i class='fas fa-file-download' style='font-size:14px;'> ${noticevo.orgFileName}</i></a></td>
						<th class="col-md-4">파일크기(bytes)</th>
							<td><fmt:formatNumber value="${noticevo.fileSize}" pattern="###,###" /></td>
					</tr>
				</c:if>
				<c:if test="${empty noticevo.orgFileName}">
					<tr>
						<th class="col-md-4">첨부파일</th>
							<td>X</td>
						<th class="col-md-4">파일크기(bytes)</th>
							<td>X</td>
					</tr>
				</c:if>				
			</tbody>
		</table>
		
		<table class="table">			
			<tbody>
				<tr>
					<th class="col-md-2">내용</th>
						<td class="col-md-8">${noticevo.content}</td>
				</tr>				
			</tbody>
		</table>
	</div>
	
</div>

<div class="container" align="right">
	<div class="btn-group">
		<button type="button" class="btn" onClick="javascript:location.href='${gobackURL}'">목록보기</button>
		<c:if test="${sessionScope.loginAdmin.adminid != null}">
			<button type="button" class="btn btn-default" onClick="javascript:location.href='noticeEdit.ana?idx=${noticevo.idx}'">수정</button>
			<button type="button" class="btn btn-primary" onClick="javascript:location.href='noticeDelete.ana?idx=${noticevo.idx}'">삭제</button>
		</c:if>
		<c:if test="${sessionScope.loginLibrarian.status == 1}">
			<button type="button" class="btn btn-default" onClick="javascript:location.href='noticeEdit.ana?idx=${noticevo.idx}'">수정</button>
			<button type="button" class="btn btn-primary" onClick="javascript:location.href='noticeDelete.ana?idx=${noticevo.idx}'">삭제</button>
		</c:if>
	</div>
</div>

<div class="container">                  
  <ul class="pager">
  <c:if test="${not empty noticevo.previousseq && not empty noticevo.nextseq}">
    <li class="previous"><a href="noticeView.ana?idx=${noticevo.previousseq}">이전글 : ${noticevo.previousseq}. ${noticevo.previoussubject}</a></li>
    <li class="next"><a href="noticeView.ana?idx=${noticevo.nextseq}">다음글 : ${noticevo.nextseq}. ${noticevo.nextsubject}</a></li>
  </c:if>
  <c:if test="${empty noticevo.previousseq}">
  	<li class="next"><a href="noticeView.ana?idx=${noticevo.nextseq}">다음글 : ${noticevo.nextseq}. ${noticevo.nextsubject}</a></li>
  </c:if>
  <c:if test="${empty noticevo.nextseq}">
  	<li class="previous"><a href="noticeView.ana?idx=${noticevo.previousseq}">이전글 : ${noticevo.previousseq}. ${noticevo.previoussubject}</a></li>
  </c:if>
  </ul>
</div>
<br/>
<br/>

<div class="container">
<label for="content" style="font-weight: bold; font-size: 20px;">Comment</label>
	<c:if test="${sessionScope.loginLibrarian.libid != null}">
		<form name="commentWriteFrm" id="WriteComment" onsubmit="return false">
			<div class="input-group">
				<input type="hidden" name="libid_fk" value="${sessionScope.loginLibrarian.libid}"/>
				<input type="hidden" name="name" value="${sessionScope.loginLibrarian.name}"/>
				<input type="text" class="form-control" name="content" placeholder="내용을 입력하세요.">
				<input type="hidden" name="parentIdx" value="${noticevo.idx}"/>
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" onClick="goCommentWrite();">등록</button>
			</span>
			</div>
		</form>
	</c:if>
</div>
<br/>    

<div id="commentList" class="container"></div>

<br/>
<div id="pageBar" style="height: 50px; text-align: center;"></div>
<br/>
<br/>

<form name="cmtDelFrm">
	<input type="hidden" name="idx" />
	<input type="hidden" name="libid_fk" />
	<input type="hidden" name="viewIdx" value="${noticevo.idx}">
</form>

<!-- <div class="modal fade" id="modifyModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">댓글 수정창</h4>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div> -->

</body>
</html>