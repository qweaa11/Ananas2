<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>==== 예약 목록 ====</title>

<script type="text/javascript">

	$(document).ready(function(){
		// 예약목록 보여주기(검색 가능, 보여주는페이지 currentShowPageNo'1')
		goSearch("1");
		
		// ==== 검색 엔터키적용 ====
		$("#searchWord").keydown(function (key) {
            if (key.keyCode == 13) { 
               goSearch("1");
            }
        }); // end of ("#searchWord").keydown
		
	}); // end of $(document).ready()
	
	// 예약목록 가져오기 (검색기능 가능)
	function goSearch(currentShowPageNo){
		
		var searchWord = $("#searchWord").val();//검색어
		var colname = $("#colname").val();//분류
		
		var data_form = {searchWord:searchWord, colname:colname, currentShowPageNo:currentShowPageNo, sizePerPage:10};
		
		$.ajax({
			url:"getReservationList.ana",
			type:"GET",
			data:data_form,
			dataType:"JSON",
			success: function(json){
				
				var resultHTML = "";
				
				if(json.length > 0) { 
					
					$.each(json, function(entryIndex, entry){
						
						// 도서의 status값을 비교하여 대여가능 & 대여불가 판당하기
						// 도서의 status값이 0또는 2라면 '대여가능' (첫번째 <td>태그 확인, 사실 status값 2는 필요없음 그냥 아님 로직상으로는 불가능하지만 그냥 넣어둠)
						if(entry.status == 0 || entry.status == 2){
							resultHTML += 
									"<tr>"
										+"<td style='color:blue;'><input type='checkbox' name='rentalValue' value='"+entry.bookid+","+entry.memberid+"'></input>대여가능</td>"
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
						}
						// 도서의 status값이 0또는 2가 아니라면 '대여불가'
						else{
							resultHTML +=
									"<tr>"
										+"<td style='color:red;'><input type='checkbox' name='idx' value='"+entry.idx+"'disabled></input>대여불가</td>" 
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
						}		
					}); // end of $.each()
					
					//resultHTML결과값을 id=result태그에 저장
					$("#result").empty().html(resultHTML);
					// 페이지바 실행
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
	
	// 예약목록 페이지바 만들기
	function makeBarPage(currentShowPageNo){
		
		var searchWord = $("#searchWord").val();//검색어
		var colname = $("#colname").val();//검색분류
	
		var form_data = {searchWord:searchWord, colname:colname, currentShowPageNo:currentShowPageNo, sizePerPage:10};
		
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
					
					// 공식!
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;

					//[이전][ < :태그에 잘찾아보면 있다. ]
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
						
					//[다음][ > :태그에 잘찾아보면 있다. ]
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
	
	//예약목록에서 체크되어진 도서를 대여하기
	function goRental(){
		var frm = document.valueFrm;
		frm.method = "POST";
		frm.action = "rental.ana";
		frm.submit();
	}// end of goRental()
	
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

	<h3 style="border-bottom:solid 1px #a6a6a6; width:1150px; text-align:left;">
			<span style="background-color:black;">
				<img style="width:30px; height:30px;"src="resources/img/home-logo/home-logo4-mini.png">
			</span>
		대여/예약/반납>예약목록</h3>
		<br/>
	<form name="valueFrm">
		<!-- 검색분류 -->
		<div style="text-align:right;">
		<select id="colname" name="colname" style="height:30px; border:0px;">
			<option value="name">회원명</option>
			<option value="title">도서명</option>
			<option value="reserveDate">날짜</option>
		</select>
		<!-- 검색어 -->
		<input id="searchWord" name="searchWord" style="height:30px; border:0px;" placeholder="Search..">
		<button type="button" onClick="goSearch('1');" style="height:30px;">Search</button>
		<!-- 엔터키 리로드 방지용 (같은 form안에 input태그가 2개 이상 있어야 한다)-->
		<input type="text" style="display:none;"/>
		</div>
	<br/>
	
	<table id="customers" >
		<thead>
			<tr>
				<th>상태</th>
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
		
		<!-- AJAX resultHTML 예약목록 저장소 -->
		<tbody id="result"></tbody>
			
	</table>
	</form>	
	<br>
	<!-- ==== 페이지바 보여주기   -->
	<!-- AJAX에서 다음 ul태그 안에 값을 넣어준다  -->
	
	<table style="width:100%;">
		<tr>
			<!-- 페이지바 저장소 -->
			<td style="width:50%; text-align:right; margin:auto"><div class="pagination pagination-lg" id="pageBar" ></div></td>
			<td style="text-align:right;"><button type="button" onClick="goRental();">체크항목 대여하기</button></td>
		</tr>
	</table>
		
</div>

<form name="idxFrm">
	<input type="hidden" id="idx" name="idx" value="">
</form>

</body>
</html>