<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  

<style>
	
   .long {width: 470px;}
   .short {width: 120px;}     
   
   a:hover { 
  			cursor: pointer;
			}
  
</style>    

<script>
	
	//재귀함수 호출시 링크 인덱스의 처음 값을 설정하기 위한 변수
	var count = 0;
	var resultHTML = "";
	var cnt = 0;

	
	$(document).ready(function(){
		
		
		
		// 검색 버튼을 눌렀을때.
		$("#searchPublisher").click(function(){
			$("#publisherDisplay").empty();
			resultHTML = "";
			count = 0;
			cnt = 0;
			
			var searchWord = $("#searchWord").val();	// 검색어를 searchWord변수에 넣는다.
			
			
			searchPublisher(1, 1000, searchWord );
			
		});
		
		$("#searchWord").keydown(function(event){
			if(event.keyCode== 13)
			{
				cnt = 0;
				$("#publisherDisplay").empty();
				resultHTML = "";
				count = 0;
				
				var searchWord = $("#searchWord").val();	// 검색어를 searchWord변수에 넣는다.
				
				searchPublisher(1, 1000, searchWord );
			}
		});
		
		
		
		
		
	
		
		showPublisher(1, 1000);
		// 페이지가 열리면 공공 DB에 있는 출판사 정보를 전부 긁어 온다.
		
		
	}); // end of $(document).ready(function(){});---------------------------------
	
	
	
	function searchPublisher(startNo, endNo, searchWord)
	{	// 담아온 publisher를 부모페이지에 넘기는 함수
		var size = 0;
		if(searchWord == "" || searchWord == null)
		{// 검색어가 없을시 
			
			resultHTML = "<tr>" +
							  "<td colspan=\"5\" style='text-align: center;'>"+"검색어에 일치하는 출판사가 없습니다"+"</td>"+
						  "</tr>"  ;
			
			
			console.log("검색어 없음");
			$("#publisherDisplay").append(resultHTML); 
			return false;
			
		}
	
	
		$.ajax({
			url:"http://openapi.sdm.go.kr:8088/73664e51596462613931546f536775/xml/SeodaemunPublisherPrintBiz/"+startNo+"/"+endNo,
		// 	data:form_data,
			type:"GET",
			dataType:"XML",
			success:function(XML){
				
			
				var endInter = 0;
				
				$(XML).find('row').each(function(){
					size++;	
					resultHTML = "";
					
					if(!searchWord == "" || searchWord != null)
					{// 검색어가 있을시 
						console.log("검색어확인용3: "+searchWord);
						
						var COMPANY = $(this).find('COMPANY').text();
						var ADDR = $(this).find('ADDR').text();
						var TEL = $(this).find('TEL').text();
						var BIZ_GUBUN = $(this).find('BIZ_GUBUN').text();
						var REG_NUM = $(this).find('REG_NUM').text();
						
							if(COMPANY.indexOf(searchWord) != -1)
							{
								cnt++;
								resultHTML = "<tr>" +
												  "<td>"+cnt+"</td>"+
												  "<td><a onClick='sendBack(\""+COMPANY+"\",\""+ADDR+"\",\""+TEL+"\",\""+REG_NUM+"\");'>"+COMPANY+"</a></td>"+
								  				  				// sendBack()함수에 출판사 이름을 담아 넘김
												  "<td>"+ADDR+"</td>"+
												  "<td>"+TEL+"</td>"+
												  "<td style='text-align: center;'>"+BIZ_GUBUN+"</td>"+
											  "</tr>" ;	  
											  
								$("#publisherDisplay").append(resultHTML); 		
							}
							
							
							
							
					} 
					 
				});
				
				
			
				
				count++;
			 	console.log("count: "+count);
			 	
			 	if(resultHTML=="")
				{// 검색 결과가 없을시 
					
					resultHTML = "<tr>" +
									  "<td colspan=\"5\" style='text-align: center;'>"+"검색어에 일치하는 출판사가 없습니다"+"</td>"+
								  "</tr>"  ;
					
					if(size% 1000 !=0){
						$("#publisherDisplay").html(resultHTML); 
					}			  
				
					console.log("검색어 없음");
				
					
				}
			 
			 	console.log("size: "+size);
			 	if(size % 1000 == 0)
			 	{
			 		console.log("한번 더 돌꺼야");
			 		searchPublisher(1000*count+1, 1000*count+1000, searchWord);
				}
			 	else if(size % 1000 != 0)
			 	{
			 		console.log("한번 더 돌면안돼");
			 	}
			
				
			},// end of sucess---------------------------------------------------------
			error: function(request, status, error){
			//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
			
			
			
		});// end of $.ajax()---------------------------------------------------------
		

		if( $("#publisherDisplay").val == "" )
		{
			resultHTML = "<tr>" +
			  			 "<td colspan=\"5\" style='text-align: center;'>"+"검색어에 일치하는 출판사가 없습니다"+"</td>"+
		  				 "</tr>"  ;
			
			console.log("검색어 없음");
			$("#publisherDisplay").append(resultHTML); 
		}
		
		
	}// end of function searchPublisher(startNo, endNo, searchWord)-----------------------------------------------
	
	
	
	function sendBack(COMPANY, ADDR, TEL, REG_NUM)
	{	// 담아온 publisher를 부모페이지에 넘기는 함수
		
		if(TEL == null)
			TEL == " ";
		
		$(opener.document).find("#pubName").val(COMPANY);
		// 부모페이지에서 id값이 publisher인 것을 찾아 publisher값을 넘긴다.
		
		$(opener.document).find("#addr").val(ADDR);
		$(opener.document).find("#tel").val(TEL);
		$(opener.document).find("#pubCode_fk").val(REG_NUM);
		// 부모페이지에서 id값이 publisher인 것을 찾아 publisher값을 넘긴다.
		
		
		
		self.close();
		// 넘긴후에 창 닫는 메소드
	}// end of function sendBack(publisher)-----------------------------------------------
	
	
	
	
	function showPublisher(startNo, endNo)
	{
		$("#loading").show();
		console.log("로딩바가 돌거야");
		
		$.ajax({
			url:"http://openapi.sdm.go.kr:8088/73664e51596462613931546f536775/xml/SeodaemunPublisherPrintBiz/"+startNo+"/"+endNo,
		// 	data:form_data,
			type:"GET",
			dataType:"XML",
			success:function(XML){
				
				var resultHTML = "";
				var size = 0;
				
				$(XML).find('row').each(function(){
					cnt++;
					
					var COMPANY = $(this).find('COMPANY').text();
					var ADDR = $(this).find('ADDR').text();
					var TEL = $(this).find('TEL').text();
					var BIZ_GUBUN = $(this).find('BIZ_GUBUN').text();
					var REG_NUM = $(this).find('REG_NUM').text();
					
					resultHTML += "<tr>" +
									  "<td style='text-align: center;'>"+cnt+"</td>"+
									  "<td><a onClick='sendBack(\""+COMPANY+"\",\""+ADDR+"\",\""+TEL+"\",\""+REG_NUM+"\");'>"+COMPANY+"</a></td>"+
					  				   				  // sendBack()함수에 출판사 이름을 담아 넘김
									  "<td>"+ADDR+"</td>"+
									  "<td style='text-align: center;'>"+TEL+"</td>"+
									  "<td style='text-align: center;'>"+BIZ_GUBUN+"</td>"+
								  "</tr>" ;
					 
					size++;	 
				});
				
				if(size != 0)
				{
					console.log("로딩바가 멈출거야");
					$("#loading").hide(); 
				}
				
				$("#publisherDisplay").append(resultHTML); 
			 	count++;
			 	
				if(size % 1000 == 0)
				{
					showPublisher(1000*count+1, 1000*count+1000);
				}
				
			},// end of sucess---------------------------------------------------------
			error: function(request, status, error)
			{
				$("#loading").hide(); 
			//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});// end of $.ajax()---------------------------------------------------------
	}// end of function searchPublisher(startNo, endNo)---------------------------------------------------------
	
	
	
	
	
	
</script>

<body>

	<div id="search">
		<input type="text" id="searchWord" name="searchWord" placeholder="출판사 이름을 적어주세요" style="width: 300px;"/>
		<button id="searchPublisher">검색</button><img id="loading" class="loading" style="width: 20px; height: 20px;" src="resources/img/loadingProgressive.gif"/>
	</div>

	<div>
		<table id="table" class="table table-hover">
			<thead>
				<tr>
					<th style="width: 5%; text-align: center;">No</th>
					<th style="width: 20%; text-align: center;">출판사명</th>
					<th style="width: 45%; text-align: center;">출판사 주소</th>
					<th style="width: 20%; text-align: center;">전화번호</th>
					<th style="width: 10%; text-align: center;">영업상태</th>
				</tr>
			</thead>
			<tbody id="publisherDisplay"></tbody>
		</table>
	
	</div>

</body>
</html>