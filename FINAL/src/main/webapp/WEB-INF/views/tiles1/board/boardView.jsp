
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<%String ctxPath = request.getContextPath(); %>

<style type="text/css">
	.container-fluid{
	border: 1px solid #e6e6e6;
	}
	
	table #table {
	font-family: arial, sans-serif;
	
	width: 70%;
	}
	table #table2 {
	font-family: arial, sans-serif;
	border: 1px solid gray;
	width: 70%;
	}
 
	td, th {
	  border: 0px solid #f2f2f2;
	  text-align: left;
	  padding: 8px;
	}

	tr:nth-child(even) {
	 
	}
	
	#table, #table2 {
	 		         width: 1000px;
	 		        }
	
	#table th{width: 120px;} 
	#table td{width: 860px;}
	.long {width: 470px;}
	.short {width: 120px;} 	
	
	.move  {cursor: pointer;}
	.moveColor {color: #660029; font-weight: bold;}
	
	a{text-decoration: none;}	

</style>

<script type="text/javascript">
    
	$(document).ready(function(){
		
		$(".move").hover(function(){
		       $(this).addClass("moveColor");
		     }, 
		     function(){
		  	   $(this).removeClass("moveColor");   
		     });
	
		if(${boardvo.commentCount > 0}) {
			goViewReply("1"); // 초기치 설정(최신의 댓글을 최대 5개 까지 보여주겠다. 즉, 1페이지를 보여주겠다.)
		}		

	});// end of $(document).ready()----------------------
	
	// ==== 댓글쓰기 ====
	function goAddWrite() {
		
		var frm = document.addWriteFrm;
		
		var nameval = frm.name.value.trim();
		
		if(nameval == "") {
			alert("먼저 로그인 하세요!!");
			return;
		}
		
		var contentval = frm.content.value.trim();
		
		if(contentval == "") {
			alert("댓글 내용을 입력하세요.");
			frm.content.value = "";
			frm.content.focus();
			return;
		}
		
		var queryString = $("form[name=addWriteFrm]").serialize();
		
		$.ajax({
			url:"<%=request.getContextPath()%>/boardAddComment.ana",
			data:queryString,
			type:"POST",
			dataType:"JSON",
			success:function(json) { 
				var html = "<div>" +
						   "<div class='col-lg-2'>"+json.NAME+"</div>"+
						   "<div class='col-lg-7'>"+json.CONTENT+"</div>"+
						   "<div class='col-lg-3' style='text-align: right;'>"+json.REGDATE+"</div>"+
						   "</div>";
				var commentCount = "<div>"+json.COMMENTCOUNT+"</div>"
				$("#commentDisplay").prepend(html);		
				$("#commentCount").html(commentCount); //새로가져온값을 다시 넣어주는거야
				frm.content.value = "";
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		}); 
	}// end of function goAddWrite()-------------
	
	// ==== 댓글내용을 Ajax로 페이징 처리하여 보여주기 ====
	function goViewReply(currentShowPageNo) {
	
		var form_data = {"idx":"${boardvo.idx}",
						 "currentShowPageNo":currentShowPageNo};
		
		$.ajax({
			url:"<%=request.getContextPath()%>/replyList.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var resultHTML = "";
				$.each(json, function(entryIndex, entry){
					resultHTML += "<div>"+
								  "<div class='col-lg-2' >"+entry.NAME+"</div>"+
								  "<div class='col-lg-7'>"+entry.CONTENT+"</div>"+
								  "<div class='col-lg-3' style='text-align: right;'>"+entry.REGDATE+"</div>"+
								  "</div>";
				});// end of $.each()-----------
				
				$("#commentDisplay").empty().html(resultHTML);
				makeReplyPageBar(currentShowPageNo);
				// ==== 댓글내용 페이지바 Ajax로 만들기 ==== //
			
			},error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		
		});		
		
	}// end of function goViewReply()---------------

	// ==== 댓글 페이지바 ==== 
	function makeReplyPageBar(currentShowPageNo) {	
		
		var form_data = {"idx":"${boardvo.idx}",
						 sizePerPage:"10"};
		
		$.ajax({
			url:"<%=request.getContextPath()%>/boardCommentTotalPage.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json) {
				
				if(json.TOTALPAGE > 0) {
					// 댓글이 있는 경우
					
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					
					///////////////////////////////
					var blockSize = 10;
					
					var loop = 1;
					
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;
					
					// 이전 만들기
					if( pageNo !=1 ) {
						pageBarHTML += "&nbsp;<a href='javascript:goViewReply(\""+(pageNo-1)+"\");'>[이전]</a>&nbsp;";
					}
					
					while(!(loop > blockSize || pageNo > totalPage) ) {
						
						if(pageNo == currentShowPageNo) {
							pageBarHTML += "&nbsp;<span style='color:red; font-size:12pt; font-weight:bold; text-decoration:underline;'>"+pageNo+"</span>&nbsp;";
						}
						else {
							pageBarHTML += "&nbsp;<a href='javascript:goViewReply(\""+pageNo+"\");'>"+pageNo+"</a>&nbsp;";
						}

						loop++;
						pageNo++;
					}// end of while(! (loop > blockSize || pageNo > totalPage) )
						
					// 다음 만들기
					if( !(pageNo > totalPage) ) {
						pageBarHTML += "&nbsp;<a href='javascript:goViewReply(\""+pageNo+"\");'>[다음]</a>&nbsp;";
					}
					
					
					////////////////////////////////////////////
					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else {
					
					// 댓글이 없는 경우
					$("#pageBar").empty();
					}
				},error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			});
					
	}// end of function makeReplyPageBar()----------------
   
	
</script>

<div class="container">
<div class="row">
	<h1 style="margin-bottom: 30px;"><a href="<%=request.getContextPath()%>/boardList.ana;">자유게시판</a></h1>
<div class="col-lg-12">
	<div>
		<table>
		<tr style="display: none;">       
			<!-- <th>글번호</th> -->         
			<td>${boardvo.idx}</td>
		</tr>
		</table>  
	</div>
	<div class=" subject col-lg-12" style="border:0px solid gray; margin-top: 1%;">
		<!-- <th>제목 게시날짜</th> -->
		<div class="col-lg-10" align="left" style="border: 0px solid black;">
	       	${boardvo.subject}
       	</div>
		<!-- <th>게시날짜</th> -->
		<div class="col-lg-2" style="border: 0px solid black; font-size: 10pt;">
			${boardvo.regDate}
		</div>
	</div>
	<div class="boarder-line col-lg-12" style="border: 1px solid #f2f2f2; margin-top: 1%; margin-bottom: 1%; "></div>
	<div class="name col-lg-12" style="border: 0px solid gray; margin-bottom: 2%; font-weight: bold; cursor: pointer;">  
		<div class="col-lg-10">
		<!-- <th>성명</th> -->        
		${boardvo.name}
		</div>
	</div>
	<div class="content col-lg-12" style="border: 0px solid gray; margin-bottom: 1%;">
		<div class="col-lg-12">
		<!-- <th>내용</th> -->
		${boardvo.content}
		</div>
	</div>
	<div class="file col-lg-12" style="border: 0px solid gray;">
		<div class="col-lg-12" style="float: right; margin-bottom: 2%;">
			<!-- ==== 첨부파일 이름 및 파일크기를 보여주고 첨부파일 다운받게 만들기 ==== -->
			<!-- <th>첨부파일</th> -->
			<c:if test="${sessionScope.loginLibrarian != null}">
				<c:forEach var="file" items="${attachfilevo}">
				<img src="<%=ctxPath%>/resources/img/disc-icon.png"><a href="<%=request.getContextPath()%>/boardDownload.ana?fileidx=${file.idx}&idx=${boardvo.idx}">${file.orgfilename}</a>
				<br/>
				</c:forEach>
			 </c:if>
			
			<c:if test="${sessionScope.loginLibrarian == null}">
				${boardvo.orgFileName}
			</c:if> 
		</div>
	</div>
	<div class="Count col-lg-12" style="border: 0px solid gray; margin-bottom: 1%;">
		<div class="col-lg-1" style="font-weight: bold;">
		댓글수 <div id="commentCount">${boardvo.commentCount}</div> <!-- 여기안에 코멘트카운트를 가져왔던거고   --> 
		</div>
		<div class="col-lg-1" style="font-weight: bold;">
		<!-- <th>조회수</th> -->
		조회수 ${boardvo.readCount}
		</div>
	</div>
	<!-- ==== 댓글 내용 보여주기 ==== -->
	 <div class="comment-box col-lg-12" style="border:0px solid gray; background-color: #f2f2f2">
		<div class="commentContent">
			<div id="commentDisplay" class="col-lg-12" ></div>       
			<div id="pageBar" class="col-lg-12" align="center"></div>
		</div>
		<div class="col-lg-12">
			<!-- ===== 댓글쓰기 폼 추가 ===== -->
			<form name="addWriteFrm">     
				<input type="hidden" name="libid_fk" value="${sessionScope.loginLibrarian.libid}" readonly />
				<input type="hidden" name="name" value="${sessionScope.loginLibrarian.name}" class="short" readonly/> 
				<input type="hidden" name="parentidx" value="${boardvo.idx}" />
				<input type="hidden" name="commnetCount" value="${boardvo.commentCount}" />
				<!-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) -->
				<input type="text" style="width: 90%; height: 80px; margin-top: 5%; margin-bottom: 2%;" name="content" class="long" />
				<button type="button" style="height: 80px; width: 9.5%; margin-top: 5%; margin-bottom: 5%" class="btn btn-info btn-sm" onClick="goAddWrite();" >등록</button>  
			</form>
		</div>	
	 </div>
</div>        
<div class="col-lg-12">
	<div style="float:right; margin-top: 1%; margin-bottom: 1%" >
		<button type="button" class="btn btn-info btn-sm" onClick="javascript:location.href='<%=request.getContextPath()%>/boardAdd.ana?root=${boardvo.idx}&groupno=${boardvo.groupno}&depthno=${boardvo.depthno}'">답글</button>
		<button type="button" class="btn btn-info btn-sm" onClick="javascript:location.href='<%=request.getContextPath()%>/boardEdit.ana?idx=${boardvo.idx}'">수정</button>
		<button type="button" class="btn btn-info btn-sm" onClick="javascript:location.href='<%=request.getContextPath()%>/boardDel.ana?idx=${boardvo.idx}'">삭제</button>
		<button type="button" class="btn btn-info btn-sm" onClick="javascript:location.href='<%=request.getContextPath()%>/boardList.ana'">목록</button>
	</div>
	<div style="margin-top: 10%;">
		<div style="margin-bottom: 1%;">이전글 : <span class="move" onclick="javascript:location.href='boardView.ana?idx=${boardvo.previousidx}'">${boardvo.previoussubject}</span></div>
		<hr>
		<div style="margin-bottom: 1%;">다음글 : <span class="move" onclick="javascript:location.href='boardView.ana?idx=${boardvo.nextidx}'">${boardvo.nextsubject}</span></div>
	</div>
</div>
</div>
</div>

