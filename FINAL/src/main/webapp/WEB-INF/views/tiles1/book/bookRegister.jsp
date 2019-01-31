<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script> 
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<style>

	.border { border: solid 0px red; }

</style>



<script>
	var resultHTML = "";

	$(document).ready(function()
	{
		showLibrary();
		
		// field select 이후에 세부 필드 나타내주는 AJAX 만들기
		$("#field").change(function(){
			var fieldCode = $("#field").val();
			$("#fieldDisplay").empty();
			
			resultHTML = "";
			showFieldDetail(fieldCode);
		});	// $("#field").change(function(){});	
	
		
		if( ${sessionScope.loginLibrarian != null} )
		{	// 도서관 사서의 경우 자기 도서관에만 책을 넣을수 있게 만들고 도서관 번호에는 사서의 도서관 번호가 자동으로 들어감
			$("#libCode").val("${sessionScope.loginLibrarian.libcode_fk}");
		}
		
		
		
		
		// 출판사 조회등록 버튼을 누룰때 
		$("#Submit").click(function(){
			// 정규화 포맷
			var regExp_num = new RegExp(/^[0-9]+$/);	// 숫자인지
			var regExp_date = new RegExp(/^[0-9]{8}$/);	// 8자리 숫자인지
			var regExp_ISBN = new RegExp(/^[a-z0-9]{10,13}$/);	// 영어와 숫자가 혼합된 10자리에서 13자리인지
			
			// 유효성 검사
			if( $("#title").val().trim() == "" )
			{
				alert("도서명을 입력하세요");
				return;
			}
			if( $("#author").val().trim() == "" )
			{
				alert("저자명을 입력하세요");
				return;
			}
			if( $("#pubName").val().trim() == "" )
			{
				alert("출판사명을 입력하세요");
				return;
			}
			if( $("#ISBN").val().trim() == "" )
			{
				alert("ISBN을 입력하세요");
				return;
			}
			else if($("#ISBN").val().trim() != "")
			{
				var bool = regExp_ISBN.test( $("#ISBN").val() );
				if(!bool)
				{
					alert("ISBN은 10자리 혹은 13자리 입니다.");
					return;
				}
			}			
			if( $("#lcode_fk").val().trim() == "" )
			{
				alert("언어를 골라주세요");
				return;
			}
			if( $("#ncode_fk").val().trim() == "" )
			{
				alert("국가를 골라주세요");
				return;
			}
			if( $("#ccode_fk").val().trim() == "" )
			{
				alert("종류를 골라주세요");
				return;
			}
			if( $("#fcode_fk").val().trim() == "" )
			{
				alert("분야를 골라주세요");
				return;
			}
			if( $("#gcode_fk").val().trim() == "" )
			{
				alert("장르를 골라주세요");
				return;
			}
			if( $("#ageCode").val().trim() == "" )
			{
				alert("연령대를 골라주세요");
				return;
			}
			if( $("#price").val().trim() == "" )
			{
				alert("가격을 적어주세요");
				return;
			}
			else if($("#price").val().trim() != "")
			{
				var bool = regExp_num.test( $("#price").val() );
				if(!bool)
				{
					alert("숫자만 입력해 주세요");
					return;
				}	
			}
			if( $("#weight").val().trim() == "" )
			{
				alert("무게를 적어주세요");
				return;
			}
			else if($("#weight").val().trim() != "")
			{
				var bool = regExp_num.test( $("#weight").val() );
				if(!bool)
				{
					alert("숫자만 입력해 주세요");
					return;
				}
			}
			if( $("#totalPage").val().trim() == "" )
			{
				alert("총 페이지 수를 적어주세요");
				return;
			}
			else if($("#totalPage").val().trim() != "")
			{
				var bool = regExp_num.test( $("#totalPage").val() );
				if(!bool)
				{
					alert("숫자만 입력해 주세요");
					return;
				}
			}
			if( $("#pDate").val().trim() == "" )
			{
				alert("발행일자를 적어주세요");
				return;
			}
			else if($("#pDate").val().trim() != "")
			{
				var bool = regExp_date.test( $("#pDate").val() );
				if(!bool)
				{
					alert("발행일자는 YYYYMMDD 로 숫자 8자리로  입력해 주세요");
					return;
				}
			}
			if( $("#intro").val().trim() == "" )
			{
				alert("책소개를 적어주세요");
				return;
			}
			if( $("#attach").val().trim() == "" )
			{
				alert("책의 이미지를 첨부해주세요");
				return;
			}
			
			// 유효성 검사가 끝난다면 폼에 담긴 정보들은 보냄
			var signupFrm = document.signup;
			signupFrm.action = "<%= request.getContextPath()%>/bookRegisterEnd.ana";
			signupFrm.method="POST";
			
			signupFrm.submit();
		});// end of $("#Submit").click(function()----------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------
				
		
		// 출판사 조회 버튼을 누룰때 새창으로 출판사 조회하기
		$("#searchPublisher").click(function(){
			
			var url="findPublisher.ana";
			window.open(url, "publisher", "left=500px, top=100px, width=1100px, height=600px");
			
		});// end of $("#searchPublisher").click(function()---------------------------------------------------------
		//----------------------------------------------------------------------------------------------------------
				
		
		$("#spinnerCount").spinner({ // 도서를 몇권 등록 할것인지 정하기
			spin: function(event, ui){
				if(ui.value > 100)
				{ // 최대 100권
					$(this).spinner("value", 100);
					return false;
				}
				else if(ui.value<1)
				{ //최소 1권
					$(this).spinner("value", 1);
					return false;
				}
			}
		});// end of spinner--------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		
		
	});  // end of $(document).ready(function(){});-------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------
	
	
	function showFieldDetail(fieldCode)
	{	// 첫번째 큰 필드를 정하면 두번째 상세 필드를 나타내주는 function
		var form_data = {"fieldCode":fieldCode}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/showFieldDetail.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				resultHTML = "<select id='fcode_fk' name='fcode_fk' class='custom-select' style='width: 200px;'>"+
							 "<option value=\"\" selected> 세부 주제 </option>";
				
				$.each(json, function(entryIndex, entry){
					
					resultHTML += "<option value=\""+entry.FCODE+"\">"+entry.FNAME+"</option>";
									
				}); // end of each()---------------------
				 
				resultHTML += "</select>";
				
				$("#fieldDisplay").append(resultHTML);
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});
	}// end of showFieldDetail(fieldCode)-----------------------------------------------------------------------------------
	
	
	
	function showLibrary()
	{	// 관리자의 경우 도서관번호가 없기떄문에 정해줘야 한다.
		if( ${sessionScope.loginLibrarian == null} )
		{
			$.ajax({
				url:"<%=request.getContextPath()%>/showLibrary.ana",
				type:"GET",
				dataType:"JSON",
				success:function(json){
					resultHTML = "";
					
					$.each(json, function(entryIndex, entry){
						
						resultHTML += "<option value=\""+entry.LIBCODE+"\">"+entry.LIBNAME+"</option>";
									
					}); // end of each()---------------------------------------------------------------------------
					
					
					$("#libCode").append(resultHTML);
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
				}// end of error---------------------------------------------------------
					
			});// end of ajax()----------------------------------------------------------------------------
		}// end of if()-------------------------------------------------------------------------------------------		
	}// end of showLibrary()-----------------------------------------------------------------------------------------------------------------------
	
	
	
	
</script>



<div class="container border" style="">
	<div class="row border">
    	<div class="col-md-8">
	        <h1 class="entry-title"><span>자료 등록</span> <img src="<%= request.getContextPath()%>/resources/img/BWFile.jpg">  <small> 자료관리 > 자료등록</small> </h1>
	        <hr>
	    <!-- 도서 등록시 필요한 도서 등록 폼 추가 -->    
        <form class="form-horizontal" name="signup" enctype="multipart/form-data" >
        
	        <div class="form-group">
	          <label class="control-label col-sm-3">도서명 <span class="text-danger">*</span></label>
	          <div class="col-md-8 col-sm-9">
	          	<div class="input-group">
		              <span class="input-group-addon"><i class=""></i></span>
		              <input type="text" class="form-control" name="title" id="title" placeholder="도서명을 적어주세요" value="">
		        </div>
	            <small> 원서의 경우 영어로 적어주세요.</small> 
	          </div>
	        </div>
        
        	<!-- 도서 등록시 도서 작가 추가 -->
			<div class="form-group">
				<label class="control-label col-sm-3">저자명 <span class="text-danger">*</span></label>
				<div class="col-md-8 col-sm-9">
					<div class="input-group">
						<span class="input-group-addon"><i class=""></i></span>
						<input type="text" class="form-control" name="author" id="author" placeholder="공동저자의 경우 모두 적어주세요..." value="">
					</div>   
					<small> 작가가 2명 이상인 경우 콤마(,)으로 구분해주세요 </small>
				</div>
			</div>
		
			<!-- 도서 등록시 도서 출판사  추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">출판사 <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	            	<span class=""><i class=""></i></span>
	              	<input type="text"    name="pubName" 	id="pubName" 	placeholder="조회버튼을 클릭하세요" value="" style="width: 300px;" readonly>
            		<input type="hidden"  name="addr" 	 	id="addr" 		value="" style="width: 300px;">
	            	<input type="hidden"  name="tel" 	 	id="tel" 		value="" style="width: 300px;">
	            	<input type="hidden"  name="pubCode_fk" id="pubCode_fk" value="" style="width: 300px;">
	            	<!-- 출판사 등록을 위한 정보들을 담아오는 히든 타입 인풋 -->
	            <button type="button" id="searchPublisher" name="searchPublisher">조회</button> 
	          	</div>
	        </div>
	        
	        <!-- 도서 등록시 ISBN 추가 -->
	        <div class="form-group">
	          	<label class="control-label col-sm-3">ISBN <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	            	<div class="input-group">
			        	<span class="input-group-addon"><i class=""></i></span>
			            <input type="text" class="form-control" name="ISBN " id="ISBN" placeholder="발급받은 ISBN을 적어주세요" maxlength="">
	            	</div>  
	          	</div>
	        </div>
        
        	<!-- 도서 등록시 도서 언어 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">  언어  <span class="text-danger">*</span></label>
					<div class="col-md-8 col-sm-9">
					    <select id="lcode_fk" name="lcode_fk" class="custom-select" style="width: 200px;">
						    <option value="" selected>언어</option>
						    <option value="KR">한국어</option>
						    <option value="EN">영어</option>
						    <option value="JP">일본어</option>
						    <option value="CH">중국어</option>
						    <option value="FR">프랑스어</option>
						    
						</select>
					</div>
	        </div>
	        
	        
	        <!-- 도서 등록시 도서 국가분류 추가 -->
	        <div class="form-group">
	          	<label class="control-label col-sm-3">국가분류 <span class="text-danger">*</span></label>
	          
	          	<div class="col-md-8 col-sm-9">
		            <label> <input name="ncode_fk" id="ncode_fk" type="radio" value="0" checked> 국내 </label>
		            <label> <input name="ncode_fk" id="ncode_fk" type="radio" value="1" > 해외 </label>
	          	</div>
	        </div>
	        
	        <!-- 도서 등록시 도서 종류 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">  종류  <span class="text-danger">*</span></label>
					<div class="col-md-8 col-sm-9">
					    <select id="ccode_fk" name="ccode_fk" class="custom-select" style="width: 200px;">
						    <option value="" selected>종류</option>
						    <option value="E01">수필</option>
						    <option value="E02">에세이</option>
						    <option value="P01">시</option>
						    <option value="D01">사전</option>
						    <option value="F01">동화</option>
						    <option value="F02">소설</option>
						    <option value="M01">잡지</option>
						    <option value="C01">만화</option>
						    <option value="S01">참고서</option>
						</select>
					</div>
	        </div>
        
        	<!-- 도서 등록시 도서 주제 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">  분야   <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	            	<select id="field" name="field" class="custom-select" style="width: 200px;">
						<option value="" selected>분야</option>
					    <option value="000">총류</option>
					    <option value="100">철학</option>
					    <option value="200">종교</option>
					    <option value="300">사회과학</option>
					    <option value="400">자연과학</option>
					    <option value="500">기술과학</option>
					    <option value="600">예술</option>
					    <option value="700">언어</option>
					    <option value="800">문학</option>
					    <option value="900">역사</option>
					</select>
					
	          	</div>
	          	
				<div class="col-md-8 col-sm-9" id="fieldDisplay">	</div>
					
	        </div>
        
        	<!-- 도서 등록시 도서 장르 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3"> 장르  <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	              	<select id="gcode_fk" name="gcode_fk" class="custom-select" style="width: 200px;">
					    <option value="" selected>장르</option>
					    <option value="UN">미분류</option>
					    <option value="RM">로맨스</option>
					    <option value="TH">스릴러</option>
					    <option value="SC">공포</option>
					    <option value="DE">추리</option>
					    <option value="FN">판타지</option>
					    <option value="SF">공상과학</option>
					    <option value="DR">드라마</option>
					    <option value="CM">코미디</option>
					    <option value="MA">무협</option>
					    <option value="AC">액션</option>
					    
					</select>
	          	</div>
	        </div>
        
        	<!-- 도서 등록시 추천 연령대 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">연령대 <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	              	<select id="ageCode" name="ageCode" class="custom-select" style="width: 200px;">
					    <option value="" selected>연령대</option>
					    <option value="0">전체</option>
					    <option value="1">아동</option>
					    <option value="2">청소년</option>
					    <option value="3">성인</option>
					</select>
	          	</div>
	        </div>
        
        	<!-- 도서 등록시 도서가격 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">가격 <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	            	<div class="input-group">
			          	<span class="input-group-addon"><i class=""></i></span>
			          	<input type="text" class="form-control" name="price" id="price" placeholder="도서 가격(원)" value="">
	            	</div>  
	          	</div>
	        </div>
        
        	<!-- 도서 등록시 도서 무게 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">무게 <span class="text-danger">*</span></label>
          		<div class="col-md-8 col-sm-9">
		            <div class="input-group">
		              <span class="input-group-addon"><i class=""></i></span>
		              <input type="text" class="form-control" name="weight" id="weight" placeholder="도서무게(g)" value="">
		            </div>  
	          	</div>
	        </div>
        
        	<!-- 도서 등록시 도서 페이지수 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">쪽수 <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
		            <div class="input-group">
			        	<span class="input-group-addon"><i class=""></i></span>
			            <input type="text" class="form-control" name="totalPage" id="totalPage" placeholder="도서총페이지수(쪽)" value="">
		            </div>  
	          	</div>
	        </div> 
          	
          	<!-- 도서 등록시 도서 발행일자 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">발행일자 <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
            		<div class="input-group">
	              		<span class="input-group-addon"><i class=""></i></span>
	             		<input type="text" class="form-control" name="pDate" id="pDate" placeholder="YYYYMMDD" value="" maxlength="8">
	            	</div>  
	          	</div>
	        </div>
        	
        	<!-- 도서 등록시 도서 소개 추가 -->
	        <div class="form-group">
	          	<label class="control-label col-sm-3">도서 소개 <span class="text-danger">*</span></label>
	          	<div class="col-md-8 col-sm-9">
	            	<div class="input-group">
	              		<span class="input-group-addon"><i class=""></i></span>
	            		<textarea rows="" cols="" type="text" class="form-control" name="intro" id="intro" placeholder="도서소개" value="" style="height: 200px;"></textarea>
	            	</div>  
	          	</div>
	        </div>
        
        
	        <!-- 총괄관리자일때만 보여주기 -->
	       	<c:if test="${sessionScope.loginLibrarian == null}">
	     			<!-- 도서 등록시 도서관명 추가 -->
		       	<div class="form-group">
		       		<label class="control-label col-sm-3">도서관명 <span class="text-danger">*</span></label>
		          	<div class="col-md-5 col-sm-8">
		          	<select id='libCode' name='libCode' class='custom-select' style='width: 200px;'>
		          		
		          	</select>
		          	</div>
		        </div>
	       	</c:if>
	        <!-- 사서일때는 hidden 타입으로  -->
	       	<c:if test="${sessionScope.loginLibrarian != null}">
	     			<!-- 도서 등록시 도서관명 추가 -->
		       	<div class="form-group">
		          	<input type="hidden" id='libCode' name='libCode' class='custom-select' style='width: 200px;'/>
		        </div>
	       	</c:if>
        	
        	
        	<!-- 도서 등록시 이미지 파일 추가 -->
			<div class="form-group">
				<label class="control-label col-sm-3">도서 이미지 <span class="text-danger">*</span> <br> </label>
				<div class="col-md-5 col-sm-8">
					<input type="file" name="attach" id="attach" class="upload" aria-describedby="file_upload">
				</div>
			</div>
			
			<!-- 도서 등록시 도서 권수 추가 -->
	        <div class="form-group">
	        	<label class="control-label col-sm-3">추가 권수 <span class="text-danger">*</span></label>
	          	<div class="col-md-5 col-sm-8">
            		<div class="input-group">
	             		<input name="bookCount" id="spinnerCount" value="1" readonly>
	            	</div>  
	          	</div>
	        </div>
			
        	</form>
        	
        	
        	<div class="form-group">
	          	<div class="col-xs-offset-3 col-xs-10">
	            	<button type="button" id="Submit" class="btn btn-primary">등록</button>  
	          	</div>
	        </div>
         
      	
   	 	</div>
	</div>
	
</div>