<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>==== 도서예약 목록 ====</title>

<script type="text/javascript">

	$(document).ready(function(){
		goSearch("1");
	}); // end of $(document).ready()
	
	function goSearch(currentShowPageNo){
		
		var searchWord = $("#searchWord").val();
		var colname = $("#colname").val();
		var sort = $("#sort").val();
		
		var data_form = {sort:sort, searchWord:searchWord, colname:colname, currentShowPageNo:currentShowPageNo, sizePerPage:5};
		
		$.ajax({
			url:"getReservationList.ana",
			type:"GET",
			data:data_form,
			dataType:"JSON",
			success: function(json){
				
				var resultHTML = "";
				
				if(json.length > 0) { 
					
					$.each(json, function(entryIndex, entry){
						
						resultHTML += 
									"<tr>"
										+"<td><input type='checkbox' value='"+entry.idx+"'></input></td>"
										+"<td>"+entry.rno+"</td>"
										+"<td>"+entry.reserveDate+"</td>"
										+"<td>"+entry.memberid+"</td>"
										+"<td>"+entry.name+"</td>"
										+"<td>["+entry.post+"]"+entry.addr1+"("+entry.addr2+")</td>"
										+"<td>"+entry.bookid+"</td>"
										+"<td>"+entry.title+"</td>"
										+"<td>"+entry.author+"</td>"
										+"<td>"+entry.pubcode+"</td>"
										+"<td>"+entry.pubname+"</td>"
									+"</tr>";     
						
					}); // end of $.each()
					
					$("#result").empty().html(resultHTML);
					makeBarPage(currentShowPageNo);
					
				} // if 
				else {
					
					$("#result").empty();
					
				} // end of if_else
				
			}, // end of success
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			} // error
			
		});// end of AJAX
		
	}// end of goSearch
	
function makeBarPage(currentShowPageNo){
		
		var searchWord = $("#searchWord").val();
		var colname = $("#colname").val();
	
		var form_data = {searchWord:searchWord, colname:colname, currentShowPageNo:currentShowPageNo, sizePerPage:5};
		
		$.ajax({
			url:"getMakeBarPage_ReservationList.ana",
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

	#customers tr:hover {background-color: #ddd;
						border-left-color:#cccccc;
						border-right-color:#cccccc;}

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

<div class="container">
<h1>도서예약 목록</h1>
<br>
	<form name="valueFrm">
		<select id="colname" name="colname" style="height:30px;">
			<option value="name">회원명</option>
			<option value="title">도서명</option>
			<option value="reserveDate">날짜</option>
		</select>
		<input id="searchWord" name="searchWord" style="height:30px;" placeholder="Search..">
		<button type="button" onClick="goSearch('1');" style="height:30px;">검색</button>
		<span style="margin-left:50%; font-size:14pt; font-weight:bold;">정렬</span>
		<select id="sort" name="sort" style="height:30px; align:right;">
			<option value="">전체</option>
			<option value="name">회원명</option>
			<option value="title">도서명</option>
			<option value="reserveDate">날짜</option>
		</select>
	</form>	
	<br>
	
	<table id="customers" >
		<thead>
			<tr>
				<th></th>
				<th>번호</th>
				<th>예약날짜</th>
				<th>회원아이디</th>
				<th>이름</th>
				<th>주소</th>
				<th>도서번호</th>
				<th>도서명</th>
				<th>저자명</th>
				<th>출판사번호</th>
				<th>출판사명</th>
			</tr>
		</thead>
		
		<tbody id="result">
		</tbody>
			
	</table>
	<br>
	<!-- ==== 페이지바 보여주기   -->
	<!-- AJAX에서 다음 ul태그 안에 값을 넣어준다  -->
	<ul class="pagination pagination-lg" id="pageBar" style=""></ul>	

</div>

<form name="idxFrm">
	<input type="hidden" id="idx" name="idx" value="">
</form>

</body>
</html>