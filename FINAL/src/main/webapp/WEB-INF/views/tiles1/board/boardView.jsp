
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

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
				var html = "<tr>" +
						   "<td style='text-align: center'>"+json.NAME+"</td>"+
						   "<td>"+json.CONTENT+"</td>"+
						   "<td style='text-align: center'>"+json.REGDATE+"</td>"+
						   "</tr>";
				$("#commentDisplay").prepend(html);
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
					resultHTML += "<tr>"+
								  "<td style='text-align: center;'>"+entry.NAME+"</td>"+
								  "<td>"+entry.CONTENT+"</td>"+
								  "<td style='text-align: center;'>"+entry.REGDATE+"</td>"+
								  "</tr>";
				});// end of $.each()-----------
				
				$("#commentDisplay").empty().html(resultHTML);
				makeReplyPageBar(currentShowPageNo);
				// ==== 댓글내용 페이지바 Ajax로 만들기 ==== //
			
			},error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		
		});		
		
	}// end of function goViewReply()---------------
	
	function makeReplyPageBar(currentShowPageNo) {	
		
		var form_data = {"idx":"${boardvo.idx}",
						 sizePerPage:"5"};
		
		$.ajax({
			url:"<%=request.getContextPath()%>/getCommentTotalPage.ana",
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
		
		<!-- ==== 첨부파일 이름 및 파일크기를 보여주고 첨부파일 다운받게 만들기 ==== -->
		<tr>
			<th>첨부파일</th>
			<td>
				<%-- <c:if test="${sessionScope.loginuser != null}"> --%>
					<a href="<%=request.getContextPath()%>/download.ana?idx=${boardvo.idx}">${boardvo.orgFileName}</a>
				<%-- </c:if> --%>
				
				<%-- <c:if test="${sessionScope.loginuser == null}">
					${boardvo.orgFileName}
				</c:if> --%>
			</td>
		</tr>
		
		<tr>
			<th>파일크기(bytes)</th>
			<td>${boardvo.fileSize}</td>
		</tr>

	</table>
	
	<br/>
	
	<div style="margin-bottom: 1%;">이전글 : <span class="move" onclick="javascript:location.href='boardView.ana?idx=${boardvo.previousidx}'">${boardvo.previoussubject}</span></div>
	<div style="margin-bottom: 1%;">다음글 : <span class="move" onclick="javascript:location.href='boardView.ana?idx=${boardvo.nextidx}'">${boardvo.nextsubject}</span></div>
	
	<br/>
	
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/boardList.ana'">목록보기</button>
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/boardEdit.ana?idx=${boardvo.idx}'">수정</button>
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/boardDel.ana?idx=${boardvo.idx}'">삭제</button>
	
	<!-- ==== 답변글쓰기 버튼 추가하기 (현재 보고 있는 글이 작성하려는 답변글의 원글(부모글)이 된다. ==== -->
	<button type="button" onClick="javascript:location.href='<%=request.getContextPath()%>/add.ana?root=${boardvo.root}&groupno=${boardvo.groupno}&depthno=${boardvo.depthno}'">답변글쓰기</button>
		
	<br/>
	<br/>
	<p style="margin-top: 3%; font-size: 16pt;">댓글쓰기</p>
	<!-- ===== 댓글쓰기 폼 추가 ===== -->
	<form name="addWriteFrm">     
		      <input type="hidden" name="libid_fk" value="classfor" readonly />
		성명 : <input type="text" name="name" value="양정구" class="short" readonly/>
	       댓글내용 : <input type="text" name="content" class="long" />
	    
	    <!-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) -->
	    <input type="hidden" name="parentidx" value="${boardvo.idx}" />  
	    
	    <button type="button" onClick="goAddWrite();" >쓰기</button>    
	</form>
	
	<!-- ==== 댓글 내용 보여주기 ==== -->
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

