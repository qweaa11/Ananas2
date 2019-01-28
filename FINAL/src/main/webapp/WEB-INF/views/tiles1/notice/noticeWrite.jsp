<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style type="text/css">
	table, th, td, input, textarea {border: solid gray 1px;}
	
	#table {border-collapse: collapse;
	 		width: 1000px;
	 		}
	#table th, #table td{padding: 5px;}
	#table th{width: 120px; background-color: #DDDDDD;}
	#table td{width: 860px;}
	.long {width: 470px;}
	.short {width: 120px;} 		

</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		/* ==== 스마트 에디터 구현 시작 ==== */
		//전역변수
	    var obj = [];
	    
	    //스마트에디터 프레임생성
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: obj,
	        elPlaceHolder: "content",
	        sSkinURI: "<%= request.getContextPath() %>/resources/smarteditor/SmartEditor2Skin.html",
	        htParams : {
	            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseToolbar : true,            
	            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseVerticalResizer : true,    
	            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseModeChanger : true,
	        }
	    });
	    /* ==== 스마트 에디터 구현 끝 ==== */
	    
	    // 쓰기버튼
	    $("#btnWrite").click(function(){
	    	
	    	/* ==== 스마트 에디터 구현 시작 ==== */
	    	//id가 content인 textarea에 에디터에서 대입
	        obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	        /* ==== 스마트 에디터 구현 끝 ==== */
	    	
            //폼 submit
	        var addFrm = document.addFrm;
	        
	        if(addFrm.subject.value.trim() == ""){
	        	alert("제목을 입력해주세요.");
	        	return addFrm.subject.focus();
	        }	        
	        if(addFrm.pw.value.trim() == ""){
	        	alert("암호를 입력해주세요.");
	        	return addFrm.pw.focus();
	        }
	        
			addFrm.action = "<%=ctxPath%>/noticeWriteEnd.ana";
			addFrm.method = "POST";
			addFrm.submit();
	    });
		
	}); // end of ready()-------------------------------------------
		
</script>

<div style="padding-left: 10%; margin-bottom: 0.2%; border: solid 0px red;">
	<h1>공지사항 작성</h1>
	<!-- ===== 파일 첨부하기 enctype="multipart/form-data" 을 해주어야만 파일첨부가 된다. ===== -->
	<form name="addFrm" enctype="multipart/form-data">
		<table id="table">
			<tr>
				<th>ID</th>
				<td>
				    <%-- <input type="hidden" name="fk_userid" value="${sessionScope.loginuser.userid}" readonly /> --%>
				    <c:if test="${sessionScope.loginAdmin.adminid ne null}">
				    	<input type="hidden" name="adminid_fk" value="${sessionScope.loginAdmin.adminid}" class="short" readonly />
				    	<input type="hidden" name="name" value="${sessionScope.loginAdmin.name}" readonly />
				    	<span style="font-weight: bold;">${sessionScope.loginAdmin.adminid}(${sessionScope.loginAdmin.name})</span>		    	
				    </c:if>
				    <c:if test="${sessionScope.loginLibrarian.status eq 1}">				
						<input type="hidden" name="libid_fk" value="${sessionScope.loginLibrarian.libid}" class="short" readonly />
						<input type="hidden" name="name" value="${sessionScope.loginLibrarian.name}" readonly />
						<span style="font-weight: bold;">${sessionScope.loginLibrarian.libid}(${sessionScope.loginLibrarian.name})</span>
						<input type="hidden" name="libcode" value="${sessionScope.loginLibrarian.libcode_fk}">
					</c:if>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" id="subject" class="long" /></td>
			</tr>
			<tr>
            	<th>내용</th>
            	<td>
            	<textarea name="content" id="content" rows="10" cols="100" style="width:95%; height:412px;"></textarea>
            	<%-- ** textarea 태그에서 required="required" 속성을 사용하면 
      					스마트 에디터는 오류가 발생하므로 사용하지 않는다. ** --%>
            	</td>
         	</tr>
         	
         	<!-- ===== 파일첨부 타입 추가하기 ===== -->
         	<tr>
         		<th>파일첨부</th>
         		<td><input type="file" name="attach" /></td>
         	</tr>

			<tr>
				<th>암호</th>
				<td><input type="password" name="pw" id="pw" class="short" /></td>
			</tr>
		</table>
		<br/>
		
		<button type="button" id="btnWrite">쓰기</button>
		<button type="button" onClick="javascript:history.back();">취소</button>
	</form>

</div>	