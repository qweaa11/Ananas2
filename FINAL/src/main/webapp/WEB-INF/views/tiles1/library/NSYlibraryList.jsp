<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>==== 도서관 목록 ====</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- DAUM MAP API KeyValue= 89cb38301850de4bd9d2de6028e68d65-->
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89cb38301850de4bd9d2de6028e68d65&libraries=services"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		
		goSearch("1");
		
		// ==== 검색 엔터키적용 ====
		$("#searchWord").keydown(function (key) {
            if (key.keyCode == 13) { 
               goSearch("1");
            }
        }); // end of ("#searchWord").keydown
        
	}); // end of (documnet).ready
	
	// ==== 도서관전체 목록 및 검색하여 나오는 목록을 보여주는 기능 ====
	function goSearch(currentShowPageNo){
		
		var searchWord = $("#searchWord").val();
		var colname = $("#colname").val();
		
		var form_data = {searchWord:searchWord, colname:colname, currentShowPageNo:currentShowPageNo, sizePerPage:10};
		//var url = "libraryList.ana";
		
		$.ajax({
			url:"<%=request.getContextPath()%>/getLibraryList.ana",
			type:"GET",
			data:form_data,
			dataType:"JSON",
			success: function(json){
				
				var resultHTML = "";
				
				if(json.length > 0) { 
					
					$.each(json, function(entryIndex, entry){
					
					resultHTML += 
						"<tr>"
						+"<td>"+entry.idx+"</td>"
						+"<td>"+entry.libname+"</td>"
						+"<td>["+entry.post+"]"+entry.addr1+"("+entry.addr2+")</td>"
						+"<td>"+entry.libcode+"</td>"
						+"<td>"+entry.regDate+"</td>"
						+"<td>"
						+"<button type='button' onClick='getLibraryDetailInfo("+entry.idx+")'>Info.</button>"
						+"&nbsp;<button type='button' onClick='editLibraryInfo("+entry.idx+")'>Edit.</button>"
						+"</td>"
						+"</tr>";
					});// $.each(data, function(entryIndex, entry)
							
					$("#result").empty().html(resultHTML);
					makeBarPage(currentShowPageNo);
					
				}//if_1 
				else{
					$("#result").empty();
					
				}//if_1_else_1 

			}, // end of success
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			} // error
			
		});// end of ajax
		
	}// end of goSearch
	
	function makeBarPage(currentShowPageNo){
		
		var searchWord = $("#searchWord").val();
		var colname = $("#colname").val();
		
		var form_data = {searchWord:searchWord, colname:colname, currentShowPageNo:currentShowPageNo, sizePerPage:10};
		
		$.ajax({
			url:"<%=request.getContextPath()%>/getMakeBarPage.ana",
			type:"GET",
			data:form_data,
			dataType:"JSON",
			success:function(json){
				
				if(json.totalPage !=0){
					
					var totalPage = json.totalPage;
					
					var pageBarHTML="";
					
					var blockSize = 3;
					
					var loop = 1;
					
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;

					//[이전]
					if(pageNo !=1 ){
						
						 pageBarHTML += "<li><a href='javascript:goSearch(\""+(pageNo-1)+"\");'><</a></li>";
							 

					}//if_2
					
					while(!(loop > blockSize || pageNo > totalPage)){
						
						if(pageNo == currentShowPageNo) {
							
				    		 pageBarHTML += "<li><span style='color:red; font-weight:bold; text-decoration:underline;'>"+pageNo+"</span></li>";	
						}
				    	else {
				    	  	
				    		pageBarHTML += "<li><a href='javascript:goSearch(\""+pageNo+"\");'>"+pageNo+"</a></li>";
				    	}//if_else_3

				       	 loop++;
				    	 pageNo++;
					}//while
					//[다음]
				    
					if( !(pageNo > totalPage) ) {
				    	 
						pageBarHTML += "<li><a href='javascript:goSearch(\""+pageNo+"\");'>></a></li>";
					}//if_3
				     
				     $("#pageBar").empty().html(pageBarHTML);
				     
				     pageBarHTML = "";
					
				}//if_1
				else {
					$("#pageBar").empty();
				}//if_1_else_1
				
			},//success
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			} // error
			
			
		});//end of ajax
		
	}// end of function makeBarPage()
	
	// ==== 파업창을 열어 특정도서관의 상세정보를 보여주는 기능 ====
	function getLibraryDetailInfo(idx){
		
		var frm = document.idxFrm;
		var url = "libraryDetailInfo.ana";
		var option = "width="+700+",height="+800;
		window.open("","도서관상세정보",option);
		
		frm.action = url;
		frm.idx.value =idx;
		frm.target = "도서관상세정보";
		frm.method = "GET";
		frm.submit();
	}// end of getLibraryDetailInfo(idx)
	
	// ==== 파업창을 열어 특정도서관정보 수정 페이지를 보여주는 기능 ====
	function editLibraryInfo(idx){
		
		var frm = document.idxFrm;
		var url = "editLibraryInfo.ana";
		var option = "width="+700+",height="+800;
		window.open("","도서관정보수정",option);
		
		frm.action = url;
		frm.idx.value = idx;
		frm.target = "도서관정보수정";
		frm.method = "GET";
		frm.submit();
		
	}// end of function editLibraryInfo(idx)
	
</script>

    
<style type="text/css">

	#customers {
		font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
		border-collapse: collapse;
		width: 100%;
	}

	#customers td, #customers th {
		border: 1px solid #ddd;
		padding: 8px;
	}

	#customers tr:nth-child(even){background-color: #f2f2f2;}

	#customers tr:hover {background-color: #ddd;}

	#customers th {
		padding-top: 12px;
		padding-bottom: 12px;
		text-align: center;
		background-color: #8c8c8c;
		color: white;
	}

</style>

</head>

<body>
	<div class="container" style="text-align:center;">
		<h1>도서관 목록</h1>
		<br>
		
		<form name="searchFrm" style="border:solid green 0px; text-align:right;">
			<select name="colname" id="colname" style="height:30px; border:solid red 0px;">
				<option value="addr1">주소</option>
				<option value="libname">도서관명</option>
			</select>
			<input type="text" name="searchWord" id="searchWord" placeholder="Search.." style="height:30px; border:solid blue 0px;"/>
			<button type="button" onClick="goSearch('1');" style="height:30px">Search</button>
			<!-- 엔터키 리로드 방지용 (같은 form안에 input태그가 2개 이상 있어야 한다)-->
			<input type="text" style="display:none;"/>
		</form>
		<br>		
			
		<table id="customers">
			<thead>
				<tr>
					<th>번호</th>
					<th>도서관명</th>
					<th>도서관 주소</th>
					<th>도서관코드</th>
					<th>등록 날짜</th>
					<th>정보</th>
				</tr>
			</thead>
			<!-- AJAX에서 다음 tbody태그 안에 값을 넣어준다  -->
			<tbody id="result"></tbody>
		</table>
		<br>
			
		<!-- ==== 페이지바 보여주기   -->
		<!-- AJAX에서 다음 ul태그 안에 값을 넣어준다  -->
		<ul class="pagination pagination-lg" id="pageBar" style=""></ul>	
	</div><!-- end of div class="container"  --> 
	
	<!-- 특정도서관 상세정보를 보기위해 값을 form에 저장하여 넘겨준다 -->
	<form name="idxFrm">
		<input type="hidden" id="idx" name="idx" value="">
	</form>

</body>
</html>