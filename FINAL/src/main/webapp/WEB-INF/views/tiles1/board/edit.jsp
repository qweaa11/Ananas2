<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<style type="text/css">
.contact_form {
    width: 1000px;
}
.heading {
    border-bottom: 1px solid #fcab0e;
    padding-bottom: 9px;
    position: relative;
}
.heading span {
    background: #9e6600 none repeat scroll 0 0;
    bottom: -2px;
    height: 3px;
    left: 0;
    position: absolute;
    width: 75px;
}
.con_form {
    padding: 0;
    width: 99%;
}
.con_txt[type="text"] {
    margin: 10px 4px;
    padding: 10px;
    width: 800px;
}
.con_txt_3[type="text"] {
    height: 200px;
    margin: 10px 4px;
    padding: 10px;
    width: 900px;
}
.con_txt2[type="submit"] {
    background: #000 none repeat scroll 0 0;
    border: medium none;
    border-radius: 27px;
    color: rgb(255, 255, 255);
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    margin: 10px;
    padding: 7px 20px;
    text-transform: uppercase;
    transition: all 0.45s ease-in-out 0s;
}
.con_txt2 {
    background: #000 none repeat scroll 0 0;
    border: medium none;
    border-radius: 27px;
    color: rgb(255, 255, 255);
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    margin: 10px;
    padding: 7px 20px;
    text-transform: uppercase;
    transition: all 0.45s ease-in-out 0s;
}
.con_txt2:hover {
    background: #f2af1f none repeat scroll 0 0;
    color: #000;
    transition: all 0.5s ease 0s;
}
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		// ==== 스마트 에디터 구현 시작 ====
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
	 	// ==== 스마트 에디터 구현 끝 ====
	    
	    // 쓰기버튼
	    $("#btnWrite").click(function(){	    	
	    	
	    	// ==== 스마트 에디터 구현 시작 ====
	    	//id가 content인 textarea에 에디터에서 대입(스마트에디터)
	        obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	
	     	// ==== 스마트 에디터 구현 끝 ====
	     	
	     		
	        // === 유효성 검사 ===
		  	   var subject = $("#subject").val().trim();
		  	   if(subject == ""){
		  		   alert("글제목을 입력하세요");
		  		   return;
		  	   }
		  	   var pw = $("#pw").val().trim();
		  	   if(pw == ""){
		  		   alert("글암호를 입력하세요");
		  		   return;
		  	   }
		    	 
            //폼 submit
	        var editFrm = document.editFrm;
	        editFrm.subject.value = $("#subject").val();
	        editFrm.content.value = $("#content").val();
	        editFrm.pw.value = $("#pw").val();
			editFrm.action = "<%=ctxPath%>/editEnd.ana";
			editFrm.method = "POST";
			editFrm.submit();
		    });
	 		
	}); // end of ready()-------------------------------------------
		
</script>

<div class="container">
	<div class="row">
	<div class="col-md-7">
                    <div class="contact_form">
                    		<h3 class="heading"><strong class="glyphicon glyphicon-pencil"></strong> 글수정 <span></span></h3>
                            	<div class="con_form">
                                <form  name="editFrm" enctype = "multipart/form-data">
                                   <input type="hidden" name="libid_fk" value="tester00" />
                                   <input type="hidden" name="name" value="운영자"  /> 
                                    <input type="text" required="" placeholder="제목"  id="subject" name="subject" value="${boardvo.subject}" tabindex="1" name="title" class="con_txt">
                                    
                                    
                                    
                                     <textarea placeholder="" id="content" name="content" type="text" class="con_txt_3" tabindex="4">${boardvo.content }</textarea>
                                     <table>
                                     <tr>
										<!-- ===== #135. 파일첨부 타입 추가하기 =====-->
						         		<th>파일첨부 <input type="file" name="attach"/></th>
						         	</tr>
						         	<tr>
						         		<td style="padding-bottom: 10px; padding-top: 10px;">암호 <input type="password" name="pw" id="pw" class="short" /></td>
						         	</tr>
						         	</table>
                                    
                                    <button type="button" id="btnWrite" class="con_txt2">수정하기</button>
                                    <button type="button"  class="con_txt2"  onClick="javascript:history.back();">취소하기</button>
                                    
                                    <input type="hidden" name="readcount"  value="${readCount}" />
									<input type="hidden" name="status" value="${status}" />
									<input type="hidden" name="groupno" value="${groupno}" />	
									<input type="hidden" name="root"  value="${root}" />
									<input type="hidden" name="depthno" value="${depthno}" />
									<input type="hidden" name="commentcount" value="${commentCount}" />
									<input type="hidden" name="filename"  value="${fileName}" />
									<input type="hidden" name="orgfilename" value="${orgFilename}" />
									<input type="hidden" name="filesize" value="${fileSize}" />
									<input type="hidden" name="idx" value="${boardvo.idx}"/>
									
									
									
                                    
                				</form>
                                </div>
                    	
                    </div>
              
			               
                </div>
	</div>
</div>