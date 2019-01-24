<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">

h1 {border-bottom: 2px solid #D9D9D9;}

th td {text-align: center;}

</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		var currentPage = "1";
		var searchWord = $("#searchWord").val();
		var sort = $("#sort").val();
		rentalList(currentPage, searchWord, sort);
		
		$("#selectAll").click(function(){
			
			if($("#selectAll").prop("checked")){
				
	            $("input[name=rentalInfo]").prop("checked",true);
	            
	        }else{
	        	
	            $("input[name=rentalInfo]").prop("checked",false);
	        }
			
		});// end of $("#selectAll").click(function()
		
		
		$("#return").click(function(){
			
			var rowData = new Array();
			
			var idxArr = new Array();
			var bookidArr = new Array();
			var memberidArr = new Array();
			var rentaldateArr = new Array();
			var delaydateArr = new Array();
			 
			var rentalInfo = $("input[name=rentalInfo]:checked");
			
			rentalInfo.each(function(i){
				
				var tr = rentalInfo.parent().parent().eq(i);
				var td = tr.children();
				
				rowData.push(tr.text());
				
				console.log(rowData);
				
				var idx = td.eq(1).text();
				var bookid = td.eq(2).text();
				var memberid = td.eq(6).text();
				var rentaldate = td.eq(8).text();
				var delaydate = td.eq(10).text();
				
				idxArr.push(idx);
				bookidArr.push(bookid);
				memberidArr.push(memberid);
				rentaldateArr.push(rentaldate);
				delaydateArr.push(delaydate);
				
				if(idx != null && idx != "") {
					
					var frm = document.returnedFrm;
					frm.idx.value = idxArr;
					frm.bookid.value = bookidArr;
					frm.memberid.value = memberidArr;
					frm.rentaldate.value = rentaldateArr;
					frm.delaydate.value = delaydateArr;
					frm.action = "returned.ana";
					frm.method = "POST";
					frm.submit();
					
				}
				else {
					
					alert("반납하려는 항목을 선택해 주세요.");
					return;
				}
				
			});// end of rentalInfo.each(function(i)			
		});// end of $("#return").click(function()
				
		
				
		$("#extend").click(function(){
			
			var rowData = new Array();
			
			var idxArr = new Array();
			 
			var rentalInfo = $("input[name=rentalInfo]:checked");
			
			rentalInfo.each(function(i){
				
				var tr = rentalInfo.parent().parent().eq(i);
				var td = tr.children();
				
				rowData.push(tr.text());
				
				console.log(rowData);
				
				var idx = td.eq(1).text();
				
				idxArr.push(idx);
				
				if(idx != null && idx != "") {
					
					var frm = document.extendFrm;
					frm.idx.value = idxArr;
					frm.action = "extend.ana";
					frm.method = "POST";
					frm.submit();
					
				}
				else {
					
					alert("연장하려는 대여 항목을 선택해 주세요.");
					return;
				}
				
			});// end of rentalInfo.each(function(i)			
		});// end of $("#extend").click(function()
				
		$("#search").click(function(){
			
			var currentPage = "1";
			var searchWord = $("#searchWord").value();
			var sort = $("#sort").value();
			
			rentalList(currentPage, searchWord, sort);
			
		});
		
	});// end of $(document).ready(function()
			
		
	function rentalList(currentPage, searchWord, sort) {
		
		var form_data = {"currentPage":currentPage,
						 "searchWord" :searchWord,
						 "sort":sort}
		
		$.ajax({
			url:"findRentalList.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				
				var resultData = "";
				
				$.each(json, function(entryIndex, entry){
					
					if(json != null) {
						resultData +=	   "<tr style='margin: 10px; width: 100%; color: black;'>"+
									            "<td style='width: 6%; height: 3%; text-align: center;'><input name='rentalInfo' style='margin-right: 5px;' type='checkbox'/></td>"+	            
									            "<td style='width: 6%; height: 3%; text-align: center;'><input type='hidden' name='idx' value='"+entry.IDX+"'/>"+entry.IDX+"</td>"+
									            "<td style='width: 12%; height: 3%; text-align: center;'><input type='hidden' name='bookid' value='"+entry.BOOKID+"' />"+entry.BOOKID+"</td>"+
									            "<td style='width: 9%; height: 3%; text-align: center;'><input type='hidden' name='booktitle' value='"+entry.BOOKTITLE+"'/>"+entry.BOOKTITLE+"</td>"+
									            "<td style='width: 10%; height: 3%; text-align: center;'><input type='hidden' name='bookauthor' value='"+entry.BOOKAUTHOR+"'/>"+entry.BOOKAUTHOR+"</td>"+
									            "<td style='width: 12%; height: 3%; text-align: center;'><input type='hidden' name='pubname' value='"+entry.PUBNAME+"'/>"+entry.PUBNAME+"</td>"+
									            "<td style='width: 7%; height: 3%; text-align: center;'><input type='hidden' name='memberid' value='"+entry.MEMBERID+"'/>"+entry.MEMBERID+"</td>"+
									            "<td style='width: 7%; height: 3%; text-align: center;'><input type='hidden' name='membername' value='"+entry.MEMBERNAME+"'/>"+entry.MEMBERNAME+"</td>"+   
									            "<td style='width: 9%; height: 3%; text-align: center;'><input type='hidden' name='rentaldate' value='"+entry.RENTALDATE+"'/>"+entry.RENTALDATE+"</td>"+
									            "<td style='width: 9%; height: 3%; text-align: center;'><input type='hidden' name='deadline' value='"+entry.DEADLINE+"'/>"+entry.DEADLINE+"</td>"+
									            "<td style='width: 7%; height: 3%; text-align: center;'><input type='hidden' name='delaydate' value='"+entry.DELAYDATE+"'/>"+entry.DELAYDATE+"</td>"+  
							            	 "</tr>";
					}
					else{
						resultData += "<tr><td style='width: 6%; height: 3%; text-align: center;'>대여 목록이 없습니다.<td></tr>"
					}
					
					$("#resultList").empty().html(resultData);
					rentalPagebar(currentPage, searchWord, sort);
					
					
				});
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});
		
	}
	
	
	function rentalPagebar(currentPage, searchWord, sort){
		
		var form_data = {"searchWord" :searchWord,
						 "sort":sort}
		
		$.ajax({
			url:"rentalPagebar.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				// 돌아올 곳! pageBarHTML
				if(json.TOTALPAGE > 0) {
					// 댓글이 있는 경우
					var pageBarHTML = "";
					var totalPage = json.TOTALPAGE;
					
					// blockSize 는 1개의 블럭당 보여지는 페이지 번호의 갯수이다.
					var blockSize = 10;
					
					var pageNo = Math.floor( (currentPage -1)/blockSize ) * blockSize +1;
		
					var loop = 1;
					/*
						loop 는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 갯수(blockSize)이다.
					*/
					
					// *** [이전] ***
					if( pageNo != 1 ) {
						pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\""+(pageNo-1)+"\")'>[이전]</a>&nbsp;";
					}
					
					// 한 페이지에 보여질 페이지 수만큼 loop를 반복 시킨다. 
					while(!(loop > blockSize || pageNo > totalPage)) {
					//		1	>	10			1		>	21
					
						if(pageNo == currentPage) {
							// 현재 보고 있는 페이지
							pageBarHTML += "&nbsp;<span style='color:red; font-size: 12pt; font-weight: bold; text-decoration: underline;'>"+pageNo+"</span>&nbsp;";
						}
						else {
							// 페이지 이동
							pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\""+pageNo+"\")'>"+pageNo+"</a>&nbsp;";
						}
						
						loop++;
						pageNo++;
					}// end of while
						
					// *** [다음] ***
					if( !(pageNo > totalPage) ) {
						pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\""+pageNo+"\")'>[다음]</a>&nbsp;";
					}
					
					////////////////////////////////////////
					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else{
					// 댓글이 없는 경우
					$("#pageBar").empty();
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
					
		});// end of $.each(json, function(entryIndex, entry)-----------------
		
	}// end of function makeCommentPageBar(currentPage)
	


</script>
	
<!------ Include the above in your HEAD tag ---------->

<body>
<div class="container" style="border: 1px solid #D9D9D9;">
	<h1> 대여 목록 </h1>
	<h4>
		<select name="sort" id="sort" style="margin-top: 3.5px; width: 80px; font-size: 11pt; padding-bottom: 1px;">
        					<option value="">목록</option>
        					<option value="memberId">회원ID</option>
        					<option value="bookTitle">도서명</option>
        					<option value="bookAuthor">저자</option>
        					<option value="rentalDate">대여일</option>
        					<option value="bookId">책번호</option>
       	</select>
       	<input type="text" id="searchWord" name="searchWord" style="margin: 2px; width: 250px; height: 25.5px;" />
       	<button type="button" id="search"><i class="fa fa-search"></i></button>
		<button style="margin-left: 5px;" type="button" class="btn btn-outline btn-primary pull-right" id="return">반납</button>
		<button type="button" class="btn btn-outline btn-primary pull-right" id="reservation">연장</button>	
	</h4>
	
	
	<div style="margin-top: 3%;">
	    <table class="table table-striped">
	    	<thead>
		     	<tr style="max-width: 100%; max-height: 300px; overflow: auto;">
			       	<th style="width: 6%; text-align: center;">전체<input style="margin-left: 1px; text-align: center;" type="checkbox" id="selectAll"/></th>
			       	<th style="width: 6%; text-align: center;">번호</th>
			       	<th style="width: 15%; text-align: center;">책번호</th>
			        <th style="width: 12%; text-align: center;">도서명</th>
			        <th style="width: 12%; text-align: center;">저자</th>
			        <th style="width: 12%; text-align: center;">출판사</th>  
			        <th style="width: 7%; text-align: center;">아이디</th>      
			        <th style="width: 7%; text-align: center;">성명</th>        
			        <th style="width: 9%; text-align: center;">대여일</th>
			        <th style="width: 9%; text-align: center;">반납일</th>
			        <th style="width: 5%; text-align: center;">연체</th>
		        </tr>
	      	</thead>   
	        <tbody id="resultList">
	      		
	      		
	    	</tbody>
	    	<tr><td id="pageBar"></td></tr>
	    </table>
	</div>
</div>

<form name="returnedFrm">
	<input type="hidden" name="idx" value="" />
	<input type="hidden" name="bookid" value="" />
	<input type="hidden" name="memberid" value="" />
	<input type="hidden" name="rentaldate" value="" />
	<input type="hidden" name="delaydate" value="" />
</form>

<form name="extendFrm">
	<input type="hidden" name="idx" value="" />
</form>
 

</body>
