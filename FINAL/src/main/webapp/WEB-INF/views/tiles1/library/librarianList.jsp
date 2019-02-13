<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String ctxPath = request.getContextPath(); %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<style type="text/css">

.well {
    min-height: 20px;
    padding: 0px;
    margin-bottom: 20px;
    background-color: #D9D9D9;
    border: 5px solid #0d0d0d;   
    border-radius: 2px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.05);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.05);
    padding-left: 15px;
    border:0px;
	}

.thumbnail .caption{
    padding: 9px;
    color: #333;
    padding-left: 0px;
    padding-right: 0px;
	}   

.icon-style{
    margin-right:15px;
    font-size:18px;
    margin-top:20px;
	}

p{margin:3px;}

.well-add-card{margin-bottom:10px;}

.btn-add-card{margin-top:20px;}

.thumbnail{
	display: block;
	padding: 4px;
	margin-bottom: 20px;
	line-height: 1.42857143;
	background-color: #fff;
	border: 5px solid #666666;   
	border-radius: 15px;
	-webkit-transition: border .2s ease-in-out;
	-o-transition: border .2s ease-in-out;
	transition: border .2s ease-in-out;
	padding-left: 0px;
	padding-right: 0px;
	}

.btn{border-radius:0px;}
	 
.btn-update {margin-left:15px;}

.modal-dialog {
  width: 45%;
  height: 55%;
}

.modal-content {
  height: auto;
  min-height: 100%;
}

.modal-body li {
	list-style:none;
	padding:2%;

}



</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		var searchWord = $("#searchWord").val();
		var sort = $("#sort").val();
		
		librarianList(sort, searchWord, "1");
		
		// 페이징 바를 위해서 가져온 총 사서수
		var totalCount = ${TOTALCOUNT};
		var itemNum = 9;
		
		if(totalCount <= itemNum) {
			
			$("#more").hide();
		}
		
		// 더보기 버튼 설정
		$("#more").click(function(){
			
			if($(this).text() == "처음으로"){
				$("#resultList").empty();
				librarianList(sort, searchWord, "1");
				$(this).text("더보기");
			}
			else{
				librarianList(sort, searchWord, $(this).val());
			}
			
		});
		
		
	});
	
	 
	// ajax에 검색 조건 넣어주는 기능
	function searchList(){
		
		var searchWord = $("#searchWord").val();
		var sort = $("#sort").val();
		
		librarianList(sort, searchWord, "1");
	}// end of function searchList()
	
	
	// 사서 리스트 보여주기
	function librarianList(sort, searchWord, pageNum) {
		
		//console.log("으아");
		
		var searchWord = $("#searchWord").val();
		var sort = $("#sort").val();
		var itemNum = 9;  
		
		var form_data = {"searchWord":searchWord,
						 "sort":sort,
						 "pageNum":pageNum,
						 "itemNum":itemNum}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/findLibrarianList.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				
				var result = "";
				var fileName = "";
				
				//console.log("xxxxxx");

				$.each(json, function(entryIndex, entry){
					
					if(json.length != 0){
						
						fileName += entry.IMGFILENAME
						
						result += "<div class='col-xs-12 col-sm-4 col-md-4 col-lg-4'>"+
							        "<div class='thumbnail' style='background-color: #ffffff;'>"+
						              "<div class='caption'>"+
						                 "<div class='col-lg-12' style='background-color: #ffffff;'>"+
						                    "<span class='glyphicon glyphicon-credit-card'></span>"+
						                    "<span class='glyphicon glyphicon-trash pull-right text-primary' style='cursor: pointer;' onClick='deleteLibrarian("+entry.LIBRARIANIDX+")'></span>"+				               
						                   "</div>"+ 
						                "<div class='col-lg-12 well well-add-card'>"+   
						                    "<h4> <span style='color: #004080; font-weight: bold;'>사서명 : "+entry.LIBRARIANNAME+"</span></h4>"+
						                "</div>"+
						                "<div class='col-lg-12'>"+
						                    "<p><span style='color: #004080; font-weight: bold;'>휴대폰 : "+ entry.LIBRARIANTEL+"</span></p>"+
						                    "<p class'text-muted'><span style='color: #004080; font-weight: bold;'>아이디 : "+entry.LIBID+"</span></p>"+
						                "</div>"+
						                //앵커태그의 버튼을 클릭하면 해당 사서의 정보를 모달 창으로 띄워주기 위해서 앵커태그를 클릭 할 때 해당 사서의 정보를 담아서 보내 주도록 한다. 
						                "<a class='btn btn-primary btn-xs btn-update btn-add-card updateInfo' data-toggle='modal' data-personal='"+entry.LIBRARIANIDX+","+entry.LIBID+","+entry.LIBCODE_FK+","+entry.LIBRARIANNAME+","+entry.LIBRARIANTEL+","+entry.STATUS+","+entry.IMGFILENAME+","+entry.LIBNAME+","+entry.LIBTEL+","+entry.ADDR+"' href='#updateInfo'>Update Info.</a>"+
						                "<a class='btn btn-danger btn-xs btn-update btn-add-card detailInfo' data-toggle='modal' data-personal='"+entry.LIBRARIANIDX+","+entry.LIBID+","+entry.LIBCODE_FK+","+entry.LIBRARIANNAME+","+entry.LIBRARIANTEL+","+entry.STATUS+","+entry.IMGFILENAME+","+entry.LIBNAME+","+entry.LIBTEL+","+entry.ADDR+"' href='#detailInfo'>Detail Info.</a>"+						
						             "</div>"+
						           "</div>"+
						         "</div>";
							
					}
					else{
						result += "<h1>가입 된 사서가 없습니다!<h1>";
					}
						
				
				});
				
				//console.log(fileName);
			
				$("#resultList").html(result);
				$("#more").val(parseInt(pageNum) + itemNum);
				var itemCount = $("#more").val();
				
				//console.log(itemCount);
				var totalCount = ${TOTALCOUNT};
				if((totalCount+1) <= itemCount) {
					
					console.log(itemCount);
					$("#more").text("처음으로");
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
		
	}
	
	
	// 해당 사서의 카드에서 업데이트 버튼을 누르면 보여줄 상세 정보를 넘겨주기
	$(document).on("click", ".updateInfo", function() {
		
		 // data-personal에 담겨져있는 정보를 담아온다.
	     var personalInfo = $(this).data('personal');
	     
	     var infoSpliter = personalInfo.split(',');   
	     
	     // 분리해온 정보를 담아서 modal-body 안으로 값을 넣어준다. 
	     for(var i in infoSpliter){// 그중 7번째 값은 이미지 파일의 파일명이 들어있다.
	    	if(i == 6){
	    		// 이미 파일의 경로+파일명을  faceImgInUpdate 아이디를 가진 src 에 넣어준다. 
				$(".modal-body #faceImgInUpdate").attr("src", "resources/librarian/"+infoSpliter[i]);
			}else{
				$(".modal-body .personalInfo"+i+"").val(infoSpliter[i]);
			}
	     	
	     }
	     //console.log($(".modal-body #faceImg").attr("src"));
	     
	});// end of $(document).on("click", ".updateInfo", function()
	
	
	
	// 해당 사서의 카드에서 상세정보 버튼을 누르면 보여줄 상세 정보를 넘겨주기
	$(document).on("click", ".detailInfo", function() {
		
	     var personalInfo = $(this).data('personal');

		 var infoSpliter = personalInfo.split(',');
		 
		 for(var i in infoSpliter){
			 
			if(i == 6){
				
				$(".modal-body #faceImgInDetail").attr("src", "resources/librarian/"+infoSpliter[i]);
				console.log($(".modal-body #faceImgInDetail").attr("src"));
			}else{
				
				$(".modal-body #personalInfo"+i+"").val(infoSpliter[i]);
			}
	     }
	});// end of $(document).on("click", ".detailInfo", function()
	  
	
	
	function goUpdate() {
		
		var finalconfirm = prompt( '관리자 암호를 넣어주세요', '비밀번호' );
		//prompt 를 통해서  alert 형식으로 문장을 띄워주고 입력한 비밀번호를 받아온다.
		
		if(finalconfirm == "qwer1234") { // 입력한 비밀번호가 특정값과 일치하는지 검사하고 작업을 실행
			
			confirm("정말 정보 수정을 진행하시겠습니까?");
			
			if(confirm) {
				
				var frm = document.updateInfoFrm;
				frm.action = "updatelibrarianInfo.ana";
				frm.method = "POST";
				frm.submit();
			}
			
		}
		else {
			
			alert("수정, 삭제는 관리자 비밀번호가 반드시 필요합니다.")
		}
		
	}
	
	
	//해당 사서의 삭제를 진행하기
	function deleteLibrarian(idx) {
		
		var finalconfirm = prompt( '관리자 암호를 넣어주세요', '비밀번호' );
		
		if(finalconfirm == "qwer1234") {
			
			confirm("정말 사서 삭제를 진행하시겠습니까?");
			
			if(confirm) {
				
				var frm = document.deleteFrm;
				frm.idx.value = idx;
				frm.action = "deleteLibrarian.ana";
				frm.method = "POST";
				frm.submit();
				
				
			}
			
		}
		else {
			
			alert("수정, 삭제는 관리자 비밀번호가 반드시 필요합니다.")
		}
		
	}
	

</script>
     

<body style="background-color: #ffffff;">
	<div class="container" id="tourpackages-carousel">
      <div class="row">
        <div class="col-lg-12" class="">
        	<h3>등록 사서 카드</h3>
        		<select id="sort" name="sort">
        			<option value="a.name">사서명</option>
        			<option value="a.tel">전화번호</option>
        		</select>
        		<input type="text" id="searchWord" name="searchWord" style="width: 30%; margin-left: 30px;" placeholder="검색 할 사서 정보" />
        		<button type="button" onClick="searchList()">검색</button>
  
        	<a class="btn icon-btn btn-primary pull-right" style="margin-bottom: 10px;" href="librarianRegist.ana">
        		<span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span>새로운 사서 등록
        	</a>
        </div>
       	
       	
        <div id="resultList">
        </div>
       
      </div><!-- End row -->
    </div><!-- End container -->
    
     <button type="button" id="more" style="margin-left: 50%;" value="">더보기</button><br/>
                
      <!-- Modal -->
  <div class="modal fade" id="detailInfo" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">사서 상세 정보</h2>
        </div>
        	<div class="modal-body">
        			<div class="col-xs-6 col-sm-6 col-lg-3" style="float: left"><img name="faceImgInDetail" id="faceImgInDetail" style="cursor: pointer;" width="250" height="220" alt="이미지 없음" src=""></div>
	        	<div class="col-xs-6 col-sm-6 col-lg-4" style="margin-left: 40px;">
	        		<ul>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 번호</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 아이디</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 코드</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 성명</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 연락처</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 직책</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 이름</span></li>
	        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 전화번호</span></li>
						<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 주소</span></li>
	          		</ul>
	          	</div>
	          	<div class="col-xs-6 col-sm-6 col-lg-4">
	          		<ul>
	          			<li><input type="text" name="personalInfo0" id="personalInfo0" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo1" id="personalInfo1" value="" readonly/></li>
						<li><input type="text" name="personalInfo2" id="personalInfo2" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo3" id="personalInfo3" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo4" id="personalInfo4" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo5" id="personalInfo5" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo7" id="personalInfo7" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo8" id="personalInfo8" value="" readonly/></li>
	          			<li><input type="text" name="personalInfo9" id="personalInfo9" value="" readonly/></li>
	          		</ul>
	          	</div>
        	</div>
        <div class="modal-footer">

        </div>
        
       	<div>
          <button type="button" style="align-content: center; margin-left: 40%; margin-top: 2%;" class="btn btn-default" data-dismiss="modal">확인</button>
          <button type="button" style="align-content: center; margin-left: 1%; margin-top: 2%;" class="btn btn-default" data-dismiss="modal">닫기</button>
     	</div>
      </div>
      
    </div>
  </div>
  
  
  
  <div class="modal fade" id="updateInfo" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">사서 정보 수정</h2>
        </div>
        	<form name="updateInfoFrm" enctype="multipart/form-data">
        		<div class="modal-body">
	        		<div class="col-xs-6 col-sm-6 col-lg-3" style="float: left"><img name="faceImgInUpdate" id="faceImgInUpdate" width="250" height="220" alt="이미지 없음" src=""></div>
		        	<div class="col-xs-6 col-sm-6 col-lg-4" style="margin-left: 40px;">
		        		<ul>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 번호</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 아이디</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: x#666666;">도서관 코드</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 성명</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 연락처</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">사서 직책</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 이름</span></li>
		        			<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 전화번호</span></li>
							<li><span style="font-size: 14pt; font-weight:bold; color: #666666;">도서관 주소</span></li>
		          		</ul>
		          	</div>
			          	
		          	<div class="col-xs-6 col-sm-6 col-lg-4">
		          		<ul>
		          			<li><input type="text" name="personalInfo0" class="personalInfo0" value="" readonly/></li>
		          			<li><input type="text" name="personalInfo1" class="personalInfo1" value="" readonly/></li>
		          			<li><input type="text" name="personalInfo2" class="personalInfo2" value="" readonly/></li>
		          			<li><input type="text" name="personalInfo3" class="personalInfo3" value=""/></li>
		          			<li><input type="text" name="personalInfo4" class="personalInfo4" value=""/></li>
		          			<li><input type="text" name="personalInfo5" class="personalInfo5" value=""/></li>
		          			<li><input type="text" name="personalInfo7" class="personalInfo7" value="" readonly/></li>
		          			<li><input type="text" name="personalInfo8" class="personalInfo8" value="" readonly/></li>
		          			<li><input type="text" name="personalInfo9" class="personalInfo9" value="" readonly/></li>
		          		</ul>
		          	</div>
        		</div>
        <div class="modal-footer">

        </div>
        
         <div class="input-group" style="margin-left: 20%;"> <span class="input-group-addon" id="file_upload"><i class="glyphicon glyphicon-upload"></i></span>
        	<input type="file" style="width: 70%;" name="attach" id="attach" class="form-control upload" value="" aria-describedby="file_upload">
        </div>
        </form>
          
       	<div>
          <button type="button" style="align-content: center; margin-left: 40%; margin-top: 2%;" class="btn btn-default" data-dismiss="modal" onclick="goUpdate()">수정</button>
          <button type="button" style="align-content: center; margin-left: 1%; margin-top: 2%;" class="btn btn-default" data-dismiss="modal">닫기</button>
     	</div>
      </div>
    
    </div>  
  </div>
  
  <form name="deleteFrm"><input type="hidden" id="idx" name="idx" value="" /></form>
 
</body>



</html>