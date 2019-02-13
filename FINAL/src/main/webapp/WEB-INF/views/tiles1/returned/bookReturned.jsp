<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  
  
  
<script type="text/javascript">
	
	$(document).ready(function()
	{
		searchKeep();
		showReturned("1","idx");
		
		var sort = $("#sortname").val();
		console.log("sort: "+sort);
		
		$("#search").keydown(function(event){
			if(event.keyCode== 13)
			{
				goSearch("1");
			}
		});
		
	
		
		$("#sortname").change(function(){
			if($("#search").val() == "")
			{// 검색어가 없는경우
				showReturned("1", sort);
			}
			else
			{// 검색어가 있는경우
				goSearch("1", sort);
			}
		});
		
		
		
		
		
		
		
	
		
	}); // end of $(document).ready(function(){});------------------------------------------------------------------
	
	
	
	function showReturned(currentShowPageNo, sort)
	{
		var sort = $("#sortname").val();
		console.log("확인용"+sort);
		
		var form_data = {"currentShowPageNo":currentShowPageNo,
						 "sort": sort }
		
		$.ajax({
			url:"<%=request.getContextPath() %>/showReturned.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var html = "";
				
				$.each(json, function(entryIndex, entry){
					html += "<tr>" +
							   "<td style='text-align:center;'>"+entry.idx+"</td>"+
							   "<td style='text-align:center;'>"+entry.returnlibcode+"</td>"+
							   "<td style='text-align:center;'>"+entry.memberID+"</td>"+
							   "<td style='text-align:center;'>"+entry.name+"</td>"+
							   "<td style='text-align:center;'>"+entry.phone+"</td>"+
							   "<td style='text-align:center;'>"+entry.title+"</td>"+
							   "<td style='text-align:center;'>"+entry.author+"</td>"+
							   "<td style='text-align:center;'>"+entry.rentalDate+"</td>"+
							   "<td style='text-align:center;'>"+entry.returnDate+"</td>"+
						   "</tr>";
						   
						   
				});
				
				$("#returnedBookDisplay").empty().html(html); 
				makePageBar(currentShowPageNo);
			},
			error: function(request, status, error){
				//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});// end of ajax()
	}// end of showReturned(currentShowPageNo)------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	function makePageBar(currentShowPageNo)
	{
		var form_data = {sizePerPage:"10"};
		
		$.ajax({
			url:"<%=request.getContextPath() %>/getReturnedTotalPage.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				if(json.TOTALPAGE > 0)
				{
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					var blockSize = 10;
					var loop = 1;
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize+1;
					
					if(pageNo != 1)
					{
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showReturned(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-primary btn-circle'><</button></a>&nbsp;";
					}
					while(!(loop>blockSize || pageNo>totalPage))
					{
						if(pageNo == currentShowPageNo)
						{
							pageBarHTML += "&nbsp;<button type='button' class='btn btn-primary btn-circle'><span style='color:#ff9999; font-size:12pt; font-weight: bold; text-decoration:underline;'>"+pageNo+"</span></button>&nbsp;";
						}
						else
						{
							pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showReturned(\""+pageNo+"\");'><button type='button' class='btn btn-primary btn-circle'>"+pageNo+"</button></a>&nbsp;";
						}
						loop++;
						pageNo++;
					}
					if(!(pageNo > totalPage))
					{
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showReturned(\""+(pageNo)+"\");'><button type='button' class='btn btn-primary btn-circle'>></button></a>&nbsp;";
					}
					/////////////////////////////////	/////////////////////////////////
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else
				{
					$("#pageBar").empty();
				}
			},
			error: function(request, status, error){
				//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});// end of ajax()
	}// end of makePageBar(currentShowPageNo)------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	function goSearch(currentShowPageNo,sort)
	{
		var sort = $("#sortname").val();
		
		var colname = $("#colname").val();
		var search = $("#search").val();
		var form_data = {"currentShowPageNo":currentShowPageNo,
						 "COLNAME": colname,
						 "SEARCH": search,
						 "SORT":sort}
		
		$.ajax({
			url:"<%=request.getContextPath() %>/showReturnedSearch.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var html = "";
				
				$.each(json,function(entryIndex, entry){
					html += "<tr>" +
							   "<td style='text-align:center;'>"+entry.idx+"</td>"+
							   "<td style='text-align:center;'>"+entry.returnlibcode+"</td>"+
							   "<td style='text-align:center;'>"+entry.memberID+"</td>"+
							   "<td style='text-align:center;'>"+entry.name+"</td>"+
							   "<td style='text-align:center;'>"+entry.phone+"</td>"+
							   "<td style='text-align:center;'>"+entry.title+"</td>"+
							   "<td style='text-align:center;'>"+entry.author+"</td>"+
							   "<td style='text-align:center;'>"+entry.rentalDate+"</td>"+
							   "<td style='text-align:center;'>"+entry.returnDate+"</td>"+
						   "</tr>";
				});
				
				$("#returnedBookDisplay").empty().html(html); 
				makePageBarWithSearch(currentShowPageNo, colname, search);
			},
			error: function(request, status, error){
				//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});// end of ajax()
	}// end of goSearch()------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	function makePageBarWithSearch(currentShowPageNo, colname, search)
	{
		var form_data = {sizePerPage:"10",
						"COLNAME": colname,
						"SEARCH": search};
		
		console.log("colname: "+ colname);
		console.log("search: "+ search);
		
		$.ajax({
			url:"<%=request.getContextPath() %>/getReturnedTotalPageWithSearch.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				if(json.TOTALPAGE > 0)
				{
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					var blockSize = 10;
					var loop = 1;
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize+1;
					
					if(pageNo != 1)
					{
						pageBarHTML += "&nbsp;<a href='javascript:goSearch(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-primary'><</button></a>&nbsp;";
					}
					while(!(loop>blockSize || pageNo>totalPage))
					{
						if(pageNo == currentShowPageNo)
						{
							pageBarHTML += "&nbsp;<button type='button' class='btn btn-primary'><span style='color:#ff9999; font-size:12pt; font-weight: bold; text-decoration:underline;'>"+pageNo+"</span></button>&nbsp;";
						}
						else
						{
							pageBarHTML += "&nbsp;<a href='javascript:goSearch(\""+pageNo+"\");'><button type='button' class='btn btn-primary'>"+pageNo+"</button></a>&nbsp;";
						}
						loop++;
						pageNo++;
					}
					if(!(pageNo > totalPage))
					{
						pageBarHTML += "&nbsp;<a href='javascript:goSearch(\""+(pageNo)+"\");'><button type='button' class='btn btn-primary'>></button></a>&nbsp;";
					}
					/////////////////////////////////	/////////////////////////////////
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else
				{
					$("#pageBar").empty();
				}
			},
			error: function(request, status, error){
				//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});// end of ajax()
	}// end of makePageBar(currentShowPageNo)------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	function searchKeep()
	{
		if( ${search != null && search != "" && search != "null"} )
		{
			$("#colname").val("${colname}");
			$("#search").val("${search}");
		}
	}// end of searchKeep()------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</script>


<div style="margin-left: 70px; margin-right: 30px; border: 0px solid gray;">
	    
	        <h1 class="entry-title"><span>반납목록</span> <img src="<%=request.getContextPath() %>/resources/img/BWFile.jpg">  <small> 대출/예약/반납 > 반납목록</small> </h1>
	        <hr>
<div class="row">
	<div class="col-xs-12">
		<div class="panel panel-default list-group-panel" style="max-width: 100%; max-height: 90%; overflow: auto;">
        	<div class="panel-body" style="overflow: auto; min-width: 600px; ">
        		<div style="height: 30px; margin-bottom: 3%;" >
        			<form name="searchFrm" onsubmit="return false">
        				<select name="colname" id="colname" style="height: 30px; margin-top: 1px;">
        					<option value="memberid">회원ID</option>
        					<option value="title">도서명</option>
        					<option value="author">작가명</option>
        				</select>&nbsp;&nbsp;&nbsp;
        				<input type="text" name="search" id="search" size="40" style="height:30px;" placeholder="검색어를 입력하세요" />
        				<input type="hidden" name="test"></input>
        				<button type="button" class="search btn btn-primary" onClick="goSearch('1');" style="height:30px;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</button>
        				&nbsp;&nbsp;&nbsp;&nbsp;정렬:&nbsp;
        				<select name="sortname" id="sortname" style="height: 30px; margin-right: 70px; margin-top: 1px;">
        					<option value="b.idx">반납번호</option>
        					<option value="memberid">회원ID</option>
        					<option value="title">도서명</option>
        					<option value="author">작가명</option>
        				</select>
        				
        				
        			</form>
        		</div>
        	
            	<div>
					<table id="table" class="table table-hover">
						<thead>
							<tr>
								<th style="width: 6%; text-align: center;">번호</th>
								<th style="width: 6%; text-align: center;">도서관코드</th>
								<th style="width: 8%; text-align: center;">회원ID</th>
								<th style="width: 6%; text-align: center;">회원명</th>
								<th style="width: 10%; text-align: center;">회원연락처</th>
								<th style="width: 20%; text-align: center;">도서명</th>
								<th style="width: 20%; text-align: center;">작가명</th>
								<th style="width: 12%; text-align: center;">대여일자</th>
								<th style="width: 12%; text-align: center;">반납일자</th>
							</tr>
						</thead>
					<tbody id="returnedBookDisplay"></tbody>
					</table>
					<ul>
					<div id="pageBar"  style="height: 50px; text-align: center;"></div>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

	
	

</div>