<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  

<style>
	
   .long {width: 470px;}
   .short {width: 120px;} 
   a:hover { cursor: pointer;}
  	#loadingImage{
  		 background-image: url("resources/img/loadingProgressive.gif");
  		 background-repeat: no-repeat;
  		 background-size: 200px 200px;
 		 background-position: left; 
  	}
  	
</style>    

<script>
	
	var count = 0;			// 재귀함수 호출여부를 판단하여 재귀호출시 시작 번호를 정하기 위한 변수
	var resultHTML = "";	// 검색목록에 검색된 결과를 담아줄 변수
	var cnt = 0;			// 검색된 출판사에 번호를 부여해줄 변수

	
	$(document).ready(function(){
		
		// 페이지가 열리면 공공 DB에 있는 출판사 정보를 전부 긁어 온다.
		showPublisher(1, 1000);
		
		
		// 검색 버튼을 눌렀을때.
		$("#searchPublisher").click(function(){
			$("#publisherDisplay").empty();
			// 검색된 목록은 비우고
			
			resultHTML = "";
			count = 0;
			// 재귀호출을 위한 카운트를 0으로 만들고
			
			cnt = 0;
			
			var searchWord = $("#searchWord").val();	// 검색어를 searchWord변수에 넣는다.
			
			searchPublisher(1, 1000, searchWord ); // 검색 메소드 호출
		});
		
		
		// 출판사 조회 엔터기 가능하게 하기
		$("#searchWord").keydown(function(event){
			if(event.keyCode== 13)
			{// 엔터키가 눌리면 
				cnt = 0;
				$("#publisherDisplay").empty();
				// 검색된 목록은 비우고
				
				resultHTML = "";
				count = 0;
				// 재귀호출을 위한 카운트를 0으로 만들고
				
				var searchWord = $("#searchWord").val();	
				// 검색어를 searchWord변수에 넣고
				
				searchPublisher(1, 1000, searchWord );
				// 검색 메소드 호출
			}
		});		
	}); // end of $(document).ready(function(){});-------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	

	function showPublisher(startNo, endNo)
	{
		$("#loadingImage").show();
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
					$("#loadingImage").hide();
					console.log("로딩바가 멈출거야");
					$("#loading").hide();
					
				}
				
				$("#publisherDisplay").append(resultHTML); 
			 	count++;
			 	
				if(size % 1000 == 0)
				{	// 공공 데이터에 들어가 있는 값을 한번에 1000개만 불러 올수 있기 때문에 
					// 처음에 1000개를 불러 온뒤 보여주고 1001번쨰 데이터를 불러 오기 위해
					// 누적했던 size 값을 1000으로 나눠 떨어진다면 다음값이 존재할수 있기 때문에 
					// showPublisher를 다시 한번 호출하는데 시작 번호를 1000에 재귀호출한 수를 곱하고 1을 더해 변수값을 정한다.
					showPublisher(1000*count+1, 1000*count+1000);
				}
				
			},// end of sucess---------------------------------------------------------
			error: function(request, status, error)
			{
				$("#loading").hide(); 
				$("#loadingImage").hide();
			//	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error---------------------------------------------------------
		});// end of $.ajax()---------------------------------------------------------
	}// end of function searchPublisher(startNo, endNo)--------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	function searchPublisher(startNo, endNo, searchWordNoTrim)
	{	// 담아온 publisher를 부모페이지에 넘기는 함수
		
		var size = 0;
		// 출판사의 수를 알아보기 위한 변수
		var searchWord = searchWordNoTrim.trim();
		// 검색어 양쪽에 공백 제거
		
		if(searchWord == "" || searchWord == null)
		{// 검색어가 없을시 
			
			resultHTML = "<tr>" +
							  "<td colspan=\"5\" style='text-align: center;'>"+"검색어를 입력해 주세요"+"</td>"+
						  "</tr>"  ;
			
			
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
					{	// 검색어가 있을시 
						console.log("검색어확인용3: "+searchWord);
						
						var COMPANY = $(this).find('COMPANY').text();	// 출판사명
						var ADDR = $(this).find('ADDR').text();			// 출판사 주소
						var TEL = $(this).find('TEL').text();			// 출판사 연락처 (영업을 안하는 곳은 NULL임)
						var BIZ_GUBUN = $(this).find('BIZ_GUBUN').text();	// 영업여부
						var REG_NUM = $(this).find('REG_NUM').text();	// 영업번호(UQ)
						
							if(COMPANY.indexOf(searchWord) != -1)
							{
								cnt++;
								resultHTML = "<tr>" +
												  "<td>"+cnt+"</td>"+
												  "<td><a onClick='sendBack(\""+COMPANY+"\",\""+ADDR+"\",\""+TEL+"\",\""+REG_NUM+"\");'>"+COMPANY+"</a></td>"+
								  				  				// sendBack()함수에 출판사 정보를 담아 넘김
												  "<td>"+ADDR+"</td>"+
												  "<td>"+TEL+"</td>"+
												  "<td style='text-align: center;'>"+BIZ_GUBUN+"</td>"+
											  "</tr>" ;	  
											  
								$("#publisherDisplay").append(resultHTML); 		
							}
					} 
				});
								
				count++;
			 	
			 	if(resultHTML=="")
				{// 검색 결과가 없을시 
					resultHTML = "<tr>" +
									  "<td colspan=\"5\" style='text-align: center;'>"+"검색어에 일치하는 출판사가 없습니다"+"</td>"+
								  "</tr>"  ;
					
					if(size% 1000 !=0 && $("#publisherDisplay").val == "" ){
						$("#publisherDisplay").append(resultHTML); 
					}			  
				}
			 
			 	if(size % 1000 == 0)
			 	{
			 		searchPublisher(1000*count+1, 1000*count+1000, searchWord);
				}
			 	else if(size% 1000 !=0 && $("#publisherDisplay").text() == "" ){
			 		
			 		resultHTML = "<tr>" +
									  "<td colspan=\"5\" style='text-align: center;'>"+"검색어에 일치하는 출판사가 없습니다"+"</td>"+
								  "</tr>"  ;
	
					$("#publisherDisplay").append(resultHTML); 
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
	}// end of function searchPublisher(startNo, endNo, searchWord)----------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------------
	
	
	function sendBack(COMPANY, ADDR, TEL, REG_NUM)
	{	// 담아온 publisher를 부모페이지에 넘기는 함수
		
		if(TEL == null)
			TEL == " ";
		
		$(opener.document).find("#pubName").val(COMPANY);
		// 부모페이지에서 id값이 publisher인 것을 찾아 publisher값을 넘긴다.
		$(opener.document).find("#addr").val(ADDR);
		$(opener.document).find("#tel").val(TEL);
		$(opener.document).find("#pubCode_fk").val(REG_NUM);
		
		
		self.close();
		// 넘긴후에 창 닫는 메소드
	}// end of function sendBack(publisher)-----------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	
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
	<div id="loadingImage" style="position: absolute; left:370px; top:10px; width:60%; height:600px; opacity: 0.5; "></div>
</body>
</html>