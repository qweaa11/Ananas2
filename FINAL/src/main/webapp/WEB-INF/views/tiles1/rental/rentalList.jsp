<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">

h1 {border-bottom: 2px solid #0d0d0d;}

th td {text-align: center;}

</style>

<script type="text/javascript">

	$(document).ready(function(){
		
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
				

		
	});// end of $(document).ready(function()
	

</script>
	
<!------ Include the above in your HEAD tag ---------->

<body>
<div class="container">
	<h1>
		대여 목록
		<input style="margin-left: 20px; width: 250px; height: 32px;" type="text" />
		<button style="margin-left: 5px;" type="button" class="btn btn-outline btn-primary pull-right" id="return">반납</button>
		<button type="button" class="btn btn-outline btn-primary pull-right" id="reservation">연장</button>	
	</h1>
	
	<div style="margin-top: 3%;">
	    <table class="table table-striped">
	    	<thead>
		     	<tr style="max-width: 100%; max-height: 300px; overflow: auto;">
			       	<th style="width: 6%; text-align: center;">전체<input style="margin-right: 5px;" type="checkbox" /></th>
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
	        <tbody>
	      		<c:forEach var="rental" items="${LIST}">
	      		<c:if test="${rental.DELAYDATE > 0}">
	      			<tr style="width: 100%; color: red;">
	      		</c:if>
	       			<tr style="margin: 10px;">
			            <td style="width: 6%; height: 3%; text-align: center;"><input name="rentalInfo" style="margin-right: 5px;" type="checkbox"/></td>			            
			            <td style="width: 6%; height: 3%; text-align: center;"><input type="hidden" name="idx" value="${rental.IDX}"/>${rental.IDX}</td>
			            <td style="width: 12%; height: 3%; text-align: center;"><input type="hidden" name="bookid" value="${rental.BOOKID}" />${rental.BOOKID}</td>
			            <td style="width: 9%; height: 3%; text-align: center;"><input type="hidden" name="booktitle" value="${rental.BOOKTITLE}"/>${rental.BOOKTITLE}</td>
			            <td style="width: 10%; height: 3%; text-align: center;"><input type="hidden" name="bookauthor" value="${rental.BOOKAUTHOR}"/>${rental.BOOKAUTHOR}</td>
			            <td style="width: 12%; height: 3%; text-align: center;"><input type="hidden" name="pubname" value="${rental.PUBNAME}"/>${rental.PUBNAME}</td>
			            <td style="width: 7%; height: 3%; text-align: center;"><input type="hidden" name="memberid" value="${rental.MEMBERID}"/>${rental.MEMBERID}</td>
			            <td style="width: 7%; height: 3%; text-align: center;"><input type="hidden" name="membername" value="${rental.MEMBERNAME}"/>${rental.MEMBERNAME}</td>   
			            <td style="width: 9%; height: 3%; text-align: center;"><input type="hidden" name="rentaldate" value="${rental.RENTALDATE}"/>${rental.RENTALDATE}</td>
			            <td style="width: 9%; height: 3%; text-align: center;"><input type="hidden" name="deadline" value="${rental.DEADLINE}"/>${rental.DEADLINE}</td>
			            <td style="width: 7%; height: 3%; text-align: center;"><input type="hidden" name="delaydate" value="${rental.DELAYDATE}"/>${rental.DELAYDATE}</td>  
	            	 </tr>
	      		</c:forEach>
	    	</tbody>
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
