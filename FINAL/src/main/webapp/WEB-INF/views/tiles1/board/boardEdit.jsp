
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/jquery-ui-1.11.4.custom/jquery-ui.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/jquery-ui-1.11.4.custom/jquery-ui.js"></script>

<% String ctxPath = request.getContextPath(); %>
   
<style type="text/css">
	
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

	/* table, th, td, input, textarea {border: solid gray 1px;} */
	
	#table {border-collapse: collapse;
	 		width: 1000px;
	 		}
	/* #table th, #table td{padding: 5px;}
	#table th{width: 120px; background-color: #DDDDDD;}
	#table td{width: 860px;} */
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
            bUseModeChanger : false,
        	}
	    });
	    /* ==== 스마트 에디터 구현 끝 ==== */
		 
	    
	    // 수정완료버튼
	    $("#btnUpdate").click(function(){
	    	
	    	/* ==== 스마트 에디터 구현 시작 ==== */
	    	//id가 content인 textarea에 에디터에서 대입
	        obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	        /* ==== 스마트 에디터 구현 끝 ==== */
	    	
	        // ==== 유효성 검사 ====
	        if($("#subject").val().trim() == "") {
	    		alert("제목을 적어주세요!")
	    		return;
	    	}
	       	        
	    	if($("#pw").val().trim() == "") {
	    		alert("암호를 적어주세요!")
	    		return;
	    	}
	
	        
	       	 //폼 submit
	        var editFrm = document.editFrm;
	        editFrm.subject.value = $("#subject").val();
	        editFrm.content.value = $("#content").val();
	        editFrm.pw.value = $("#pw").val();
	        editFrm.action = "<%=ctxPath%>/boardEditEnd.ana";
	        editFrm.method = "POST";
	        editFrm.submit();
	    });
		
	});// end of $(document).ready()----------------------
    

	function goWrite() {
		
		
	
	} // end of function goWrite()--------------------------------
    
</script>

<div style="padding-left: 20%; border: solid 0px red;">
	<h1>글 수정하기</h1>
	
	<table id="table">
		<tr>
			<th>성명</th>
			<td>${boardvo.name}</td>
		</tr>
		<tr>
           	<th>제목</th>
           	<td><input type="text" id="subject" value="${boardvo.subject}" style="width: 300px;"/></td>
        	</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea name="content" id="content" rows="10" cols="100" style="width:95%; height:412px;">${boardvo.content}</textarea>
            	<%-- **  textarea 태그에서 required="required" 속성을 사용하면 
    			 		  스마트 에디터는 오류가 발생하므로 사용하지 않는다. ** --%>
			</td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" id="pw" class="short" /> </td>
		</tr>

	</table>
	
	<br/>
	
	<div style="float: right; margin-right: 34.3%">
		<button type="button" class="btn btn-info btn-sm" id="btnUpdate">완료</button>
		<button type="button" class="btn btn-info btn-sm" onClick="javascript:history.back();">취소</button>
		<button type="button" class="btn btn-info btn-sm" onClick="javascript:location.href='<%=request.getContextPath()%>/del.ana?idx=${boardvo.idx}'">삭제</button>
	</div>
	
	<form name="editFrm">
		<input type="hidden" name="idx" value="${boardvo.idx}"/>
		<input type="hidden" name="subject"/>
		<input type="hidden" name="content"/>
		<input type="hidden" name="pw"/>
	</form>
		
	<br/>
	<br/>
		
	
	<div>&nbsp;</div>
</div>











