<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<script type="text/css">
	table, th, td, input, textarea {border: solid gray 1px;}
	
	#table, #table2 {border-collapse: collapse;
	 		         width: 1000px;
	 		        }
	#table th, #table td{padding: 5px;}
	#table th{width: 120px; background-color: #DDDDDD;}
	#table td{width: 860px;}
	.long {width: 470px;}
	.short {width: 120px;} 	
	
	.move  {cursor: pointer;}
	.moveColor {color: #660029; font-weight: bold;}
	
	a{text-decoration: none;}	

</script>



<script type="text/javascript">

	$(document).ready(function () 
	{
		showSendMessage(1);
		
		
	});

	
	function showSendMessage(currentShowPageNo)
	{
		var form_data = {"seq":"${YMHMessageVO.idx}",
						 "currentShowPageNo":currentShowPageNo,
						 "userid":"${sessionScope.loginLibrarian.libid}"}


		
		$.ajax({
			url:"<%=request.getContextPath() %>/showSendMessage.ana", 
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var resultHTML = "";

				$.each(json, function(entryIndex, entry){
					resultHTML += "<tr>"+
					              "<td style='text-align: center;'>"+entry.IDX+"</td>"+
					              "<td>"+entry.TARTGETID+" - "+entry.TARTGETNAME+"</td>"+
					              "<td><a onClick=\"window.open(\'showYMHMessage.ana?idx="+entry.IDX+"\',\'Message\',\'width=1000,height=600,top=280, left=460,status=no,scrollbars=no\');\">"+entry.TITLE+"</a></td>"+
					              "<td style='text-align: center;'>"+entry.SENDDATE+"</td>";
					              
               if(entry.OPENDATE != null){
            	    resultHTML += "<td style='text-align: center;'>"+entry.OPENDATE+"</td>";
               }
               else{
			 	    resultHTML += "<td style='text-align: center;'>X</td>";
	           }
			   
			   
				    resultHTML += "<td><button type='button' onClick='javascript:location.href=\"deleteSendMessage.ana?idx="+entry.IDX+"\"'> 삭제 </button></td>"+
					              "</tr>";
				});// end of $.each()-------------
				
				
				$("#messageDisplay").empty().html(resultHTML);
				
				makeSendMessagePageBar(currentShowPageNo); 
				// 페이지바함수 호출 
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}
	
	
	
	
	
	// ==== 댓글내용 페이지바 Ajax 로 만들기 ==== //
	function makeSendMessagePageBar(currentShowPageNo) 
	{

		var form_data = {"userid":"${sessionScope.loginLibrarian.libid}",
				         sizePerPage:"10", "currentShowPageNo":currentShowPageNo};


		
		$.ajax({
			url:"<%=request.getContextPath() %>/getSendMessageTotalPage.ana", 
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				
				if(json.TOTALPAGE > 0) {
					// 댓글이 있는 경우
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";

					/////////////////////////////////
					var blockSize = 10;
					// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 갯수이다. 
					/*
					    1 2 3 4 5 6 7 8 9 10   -- 1개블럭
					   11 12 13 14 15 16 17 18 19 20   -- 1개블럭
					   21 22 23 24 25 26 27 28 29 30   -- 1개블럭
					*/
					
					var loop = 1;
					/*
					   loop 는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 갯수(지금은 10개)까지만
					     증가하는 용도이다.
					*/
					
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize) * blockSize + 1;
					// !!! 공식이다. !!! //
					
					 // !!! [이전]만들기 !!! //
				    if( pageNo != 1 )
				    {
				    	pageBarHTML += "&nbsp;<a href='javascript:showSendMessage(\""+(pageNo-1)+"\");'>[이전]</a>&nbsp;";
				    }
					
					// ------------------------------ //
					
					while(!(loop > blockSize || pageNo > totalPage)) 
					{
						if(pageNo == currentShowPageNo)
						{
							pageBarHTML += "&nbsp;<span style='color:red; font-size:12pt; font-weight:bold; text-decoration:underline;'>"+pageNo+"</span>&nbsp;";
						}	
						else
						{
							pageBarHTML += "&nbsp;<a href='javascript:showSendMessage(\""+pageNo+"\");'>"+pageNo+"</a>&nbsp;";
						}	
						loop++;
						pageNo++;
					}
					
				    // ------------------------------ //
				    // !!! [다음]만들기 !!! //
				    if( !(pageNo > totalPage) )
				    {
				    	pageBarHTML += "&nbsp;<a href='javascript:showSendMessage(\""+pageNo+"\");'>[다음]</a>&nbsp;";
				    }
				    // ------------------------------ //
				    
					
					$("#sendPageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}  
				else {
					// 댓글이 없는 경우 
					$("#sendPageBar").empty();
				}
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
	}// end of function makeCommentPageBar(currentShowPageNo)------------- 
	
	
</script>

<div style="margin: 1%; width: 90%;">
	<div id="" style="width: 90%; order: solid red 0px;">
		<table style="margin: 1%; width: 99%;"> 
			<thead> 
				<tr>
					<th style="width: 8%;  border: solid red 0px; align-content: center;">쪽지번호</th>
					<th style="width: 20%; border: solid red 0px;">받는사람</th>
					<th style="width: 25%; border: solid red 0px;">제목</th>
					<th style="width: 20%; border: solid red 0px;">발송일자</th>
					<th style="width: 20%; border: solid red 0px;">열람일자</th>
					<th style="width: 7%;  border: solid red 0px;">삭제</th>
				</tr>
			</thead>
			<tbody id="messageDisplay"></tbody>
		</table>
		<div id="sendPageBar" align="center"></div>
	</div>
</div>
