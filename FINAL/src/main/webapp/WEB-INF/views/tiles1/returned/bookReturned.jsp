<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<script>
	
	$(document).ready(function(){
		
		
		
		
	}); // end of $(document).ready(function(){});---------------------------------
	
	
	
	function showReturned(currentShowPageNo)
	{
		var form_data = {"currentShowPageNo":currentShowPageNo}
		
		$.ajax({
			url:"<%=request.getContextPath() %>/showReturned.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var html = "";
				
				$.each(json,function(entryIndex, entry){
					html = "<tr>" +
							   "<td>"+entry.idx+"</td>"+
							   "<td>"+entry.memberID+"</td>"+
							   "<td>"+entry.name+"</td>"+
							   "<td>"+entry.phone+"</td>"+
							   "<td>"+entry.title+"</td>"+
							   "<td>"+entry.author+"</td>"+
							   "<td>"+entry.ageCode+"</td>"+
							   "<td>"+entry.returnDate+"</td>"+
							   "<td>"+entry.rentalDate+"</td>"+
						   "</tr>";
				});
				
				
				$("#returnedBookDisplay").append(html); 
				
			},
			error: function(request, status, error){
				//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
				
		});// end of ajax()
		
	}

</script>


<div style="margin-left: 70px; margin-right: 30px; border: 0px solid gray;">
	 
<div class="row">
	<div class="col-xs-12">
		<div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 300px; overflow: auto;">
        	<div class="panel-body" style="overflow: auto; min-width: 600px;">
        		<div>
        			<form name="searchFrm" >
        				<select name="colname" id="colname" style="height: 26px;">
        					<option value="memberID">회원ID</option>
        					<option value="title">도서명</option>
        					<option value="author">작가명</option>
        					<option value="ageCode">연령대</option>
        				</select>
        				<input type="text" name="search" id="search" size="40" />
        				<button type="button" onclick="goSearch();">검색</button>
        			</form>
        		</div>
        	
            	<div>
					<table id="table" class="table table-hover">
						<thead>
							<tr>
								<th style="width: 6%; text-align: center;">번호</th>
								<th style="width: 7%; text-align: center;">회원ID</th>
								<th style="width: 7%; text-align: center;">회원명</th>
								<th style="width: 10%; text-align: center;">회원연락처</th>
								<th style="width: 20%; text-align: center;">도서명</th>
								<th style="width: 20%; text-align: center;">작가명</th>
								<th style="width: 6%; text-align: center;">연령대</th>
								<th style="width: 12%; text-align: center;">대여일자</th>
								<th style="width: 12%; text-align: center;">반납일자</th>
							</tr>
						</thead>
					<tbody id="returnedBookDisplay"></tbody>
					</table>
			
				</div>
			</div>
		</div>
	</div>
</div>

	
	

</div>