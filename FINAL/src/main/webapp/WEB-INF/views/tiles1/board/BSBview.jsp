<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% String ctxPath = request.getContextPath(); %>


<style type="text/css">
/*Comment List styles*/
.comment-list .row {
	margin-bottom: 0px;
}

.move  {cursor: pointer;}
.moveColor {color: #660029; font-weight: bold;}

.comment-list .panel .panel-heading {
	padding: 4px 15px;
	position: absolute;
	border: none;
	/*Panel-heading border radius*/
	border-top-right-radius: 0px;
	top: 1px;
}

.comment-list .panel .panel-heading.right {
	border-right-width: 0px;
	/*Panel-heading border radius*/
	border-top-left-radius: 0px;
	right: 16px;
}

.comment-list .panel .panel-heading .panel-body {
	padding-top: 6px;
}

.comment-list figcaption {
	/*For wrapping text in thumbnail*/
	word-wrap: break-word;
}
/* Portrait tablets and medium desktops */
@media ( min-width : 768px) {
	.comment-list .arrow:after, .comment-list .arrow:before {
		content: "";
		position: absolute;
		width: 0;
		height: 0;
		border-style: solid;
		border-color: transparent;
	}
	.comment-list .panel.arrow.left:after, .comment-list .panel.arrow.left:before
		{
		border-left: 0;
	}
	/*****Left Arrow*****/
	/*Outline effect style*/
	.comment-list .panel.arrow.left:before {
		left: 0px;
		top: 30px;
		/*Use boarder color of panel*/
		border-right-color: inherit;
		border-width: 16px;
	}
	/*Background color effect*/
	.comment-list .panel.arrow.left:after {
		left: 1px;
		top: 31px;
		/*Change for different outline color*/
		border-right-color: #FFFFFF;
		border-width: 15px;
	}
	/*****Right Arrow*****/
	/*Outline effect style*/
	.comment-list .panel.arrow.right:before {
		right: -16px;
		top: 30px;
		/*Use boarder color of panel*/
		border-left-color: inherit;
		border-width: 16px;
	}
	/*Background color effect*/
	.comment-list .panel.arrow.right:after {
		right: -14px;
		top: 31px;
		/*Change for different outline color*/
		border-left-color: #FFFFFF;
		border-width: 15px;
	}
}

.comment-list .comment-post {
	margin-top: 6px;
}

.td {
	margin-bottom: 30px;
}

.one-post {
	margin: 0px 0px 25px 0px;
}

.bd-radius {
	border-radius: 4px;
	background-color: #ffffff;
	box-shadow: 0 1px 7px rgba(0, 0, 0, 0.05);
	border: 1px solid rgba(0, 0, 0, 0.09) !important;
}

.bd-radius .img-height {
	width: 100% !important;
	height: auto !important;
}

.bd-radius .img-height .video {
	width: 100% !important;
	height: auto !important;
}

.bd-radius .post-post-content {
	padding: 10px 15px 10px 15px !important;
}

.bd-radius .post-post-content.postMetaInline-media {
	width: 100%;
	border-top: 1px solid #eee;
}

.bd-radius .post-post-content.postMetaInline-media .media .media-left a
	{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	line-height: normal;
}

.bd-radius .post-post-content.postMetaInline-media .media .media-left a .media-object
	{
	border-radius: 50%;
	width: 46px !important;
	height: 46px !important;
}

.bd-radius .post-post-content.postMetaInline-media .media .media-body {
	display: table-cell;
	vertical-align: middle;
	font-size: 14px;
	line-height: 1.4;
	text-rendering: auto;
}

.bd-radius .post-post-content.postMetaInline-media .media .media-body .media-heading
	{
	font-weight: 400;
	color: #000;
	font-size: 17px;
}

.bd-radius .post-post-content.postMetaInline-media .media .media-body .media-heading-muted
	{
	color: rgba(0, 0, 0, 0.44) !important;
	font-size: 12px;
	display: flex;
}

.bd-radius .post-post-content.postMetaInline-media .media .media-body .media-heading-muted .fa-map-marker
	{
	font-size: 14px;
	margin-top: 2px;
}

.bd-radius .post-post-content.postMetaInline-media .nav li .timed {
	font-size: 15px;
	color: #adadad;
}

.bd-radius .post-post-content.postMetaInline-media .nav li .icondisplay
	{
	display: flex;
}

.bd-radius .post-post-content.postMetaInline-media .nav li .icondisplay span
	{
	font-size: 16px;
	color: #adadad;
}

.bd-radius .post-post-content.postMetaInline-media .nav li .icondisplay .line
	{
	width: 1px !important;
	height: 20px !important;
	background-color: #bababa;
	margin: 2px 5px 0px 5px;
}

.posttitle-font {
	font-size: 23px;
	font-weight: 600;
}

.postdesc-font {
	font-size: 17px;
	word-spacing: 2px;
	font-weight: 400;
}

.subpostMetaInline-media {
	width: 100%;
	border-top: 1px solid #eee;
	padding: 15px 15px 15px 15px !important;
}

.subpostMetaInline-media .media .media-left a {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	line-height: normal;
}

.subpostMetaInline-media .media .media-left a .media-object {
	border-radius: 50%;
	width: 46px !important;
	height: 46px !important;
}

.subpostMetaInline-media .media .media-body {
	display: table-cell;
	vertical-align: middle;
	font-size: 14px;
	line-height: 1.4;
	text-rendering: auto;
}

.subpostMetaInline-media .media .media-body .media-heading {
	font-weight: 400;
	color: #000;
	font-size: 17px;
}

.subpostMetaInline-media .media .media-body .media-heading-muted {
	color: rgba(0, 0, 0, 0.44) !important;
	font-size: 12px;
	display: flex;
}

.subpostMetaInline-media .media .media-body .media-heading-muted .fa-map-marker
	{
	font-size: 14px;
	margin-top: 2px;
}

.subpostMetaInline-media .media .media-body .form-control.commentpostinput
	{
	border-radius: 0px;
	padding: 6px 35px 6px 6px;
}

.subpostMetaInline-media .nav li .timed {
	font-size: 15px;
	color: #adadad;
}

.subpostMetaInline-media .nav li .icondisplay {
	display: flex;
	float: right;
}

.subpostMetaInline-media .nav li .icondisplay span {
	font-size: 16px;
	color: #adadad;
}

.subpostMetaInline-media .nav li .icondisplay .line {
	width: 1px !important;
	height: 20px !important;
	background-color: #bababa;
}

.readmore {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	text-align: center;
	margin: 0;
	padding: 42px 0px 0px 0px;
	background: -webkit-linear-gradient(rgba(255, 255, 255, 0) 0%, #ffffff
		100%);
	background-image: -moz-linear-gradient(rgba(255, 255, 255, 0) 0%,
		#ffffff 100%);
	background-image: -o-linear-gradient(rgba(255, 255, 255, 0) 0%, #ffffff
		100%);
	background-image: linear-gradient(rgba(255, 255, 255, 0) 0%, #ffffff
		100%);
	background-image: -ms-linear-gradient(rgba(255, 255, 255, 0) 0%, #ffffff
		100%);
}

.readmore .btn-default:hover {
	color: #FFF;
	background-color: #ffcd13;
	border-color: #f9e69d;
}

.readmore .btn-default .a-dec:hover {
	color: #FFF;
}

.backbtn {
	border-radius: 8px 8px 0px 0px !important;
	box-shadow: 0px 1px 7px 0px rgba(0, 0, 0, 0.2);
}

.a-dec {
	text-decoration: none !important;
}

body {
	background: #fafafa;
}

.widget-area.blank {
	background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	-ms-box-shadow: none;
	-o-box-shadow: none;
	box-shadow: none;
}

body .no-padding {
	padding: 0;
}

.widget-area {
	background-color: #fff;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-ms-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
	-moz-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
	-ms-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
	-o-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
	box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
	float: left;
	margin-top: 30px;
	padding: 25px 30px;
	position: relative;
	width: 100%;
}

.status-upload {
	background: none repeat scroll 0 0 #f5f5f5;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-ms-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	float: left;
	width: 100%;
}

.status-upload form {
	float: left;
	width: 100%;
}

.status-upload form textarea {
	background: none repeat scroll 0 0 #fff;
	border: medium none;
	-webkit-border-radius: 4px 4px 0 0;
	-moz-border-radius: 4px 4px 0 0;
	-ms-border-radius: 4px 4px 0 0;
	-o-border-radius: 4px 4px 0 0;
	border-radius: 4px 4px 0 0;
	color: #777777;
	float: left;
	font-family: Lato;
	font-size: 14px;
	height: 142px;
	letter-spacing: 0.3px;
	padding: 20px;
	width: 100%;
	resize: vertical;
	outline: none;
	border: 1px solid #F2F2F2;
}

.status-upload ul {
	float: left;
	list-style: none outside none;
	margin: 0;
	padding: 0 0 0 15px;
	width: auto;
}

.status-upload ul>li {
	float: left;
}

.status-upload ul>li>a {
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-ms-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	color: #777777;
	float: left;
	font-size: 14px;
	height: 30px;
	line-height: 30px;
	margin: 10px 0 10px 10px;
	text-align: center;
	-webkit-transition: all 0.4s ease 0s;
	-moz-transition: all 0.4s ease 0s;
	-ms-transition: all 0.4s ease 0s;
	-o-transition: all 0.4s ease 0s;
	transition: all 0.4s ease 0s;
	width: 30px;
	cursor: pointer;
}

.status-upload ul>li>a:hover {
	background: none repeat scroll 0 0 #606060;
	color: #fff;
}

.status-upload form button {
	border: medium none;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-ms-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	color: #fff;
	float: right;
	font-family: Lato;
	font-size: 14px;
	letter-spacing: 0.3px;
	margin-right: 9px;
	margin-top: 9px;
	padding: 6px 15px;
}

.dropdown>a>span.green:before {
	border-left-color: #2dcb73;
}

.status-upload form button>i {
	margin-right: 7px;
}

.user_name {
	font-size: 14px;
	font-weight: bold;
}

.comments-list .media {
	border-bottom: 1px dotted #ccc;
}

#table, #table2 {
	border-collapse: collapse;
	width: 1000px;
}
</style>

<script type="text/javascript">
    
	$(document).ready(function(){
	
		$(".move").hover(function(){
					       $(this).addClass("moveColor");
					     }, 
					     function(){
					  	   $(this).removeClass("moveColor");   
					     });
		
		
		
		goViewComment("1");// 초기치 설정(초기치로 최신의 댓글을 최대5개 까지 보여주겠다. 즉, 1페이지를 보여주겠다.)
		
		
	
	});// end of $(document).ready()----------------------
	
	    

	// ==== 댓글 쓰기 ====
	function goAddWrite() {
		
		var frm = document.addWriteFrm;
		
		var nameval = frm.name.value.trim();
		
		if(nameval == ""){
			alert("먼저 로그인 하세요.");
			return;
		}
		
		var contentval = frm.content.value.trim();
		
		if(contentval == ""){
			alert("댓글 내용을 입력하세요.");
			frm.content.value="";
			frm.content.focus();
			return;
		}
		
		var queryString = $("form[name=addWriteFrm]").serialize();
		
		//console.log(queryString);
		// fk_userid=kimkh1&name=%EA%B9%80%EA%B5%AD%ED%95%981&content=.&parentSeq=3
		
		$.ajax({
			
			url:"<%=request.getContextPath() %>/addComment.ana",			
			data:queryString,
			type:"POST",
			dataType:"JSON",
			success:function(json){
				
				goViewComment('1');
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
		
				
		
	
	} // end of function goWrite()--------------------------------
	
	
	// === 댓글내용 Ajax 로 페이징 처리하여 보여주기
	function goViewComment(currentShowPageNo){
		
		var form_data = {"idx":${boardvo.idx},
						 "currentShowPageNo":currentShowPageNo};
		
		$.ajax({
			url:"<%=request.getContextPath()%>/commentList.ana", 
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var resultHTML = "";
				$.each(json, function(entryIndex, entry){
					
					resultHTML += 
					   "<article class='row'>"+
			            "<div class='col-md-2 col-sm-2 hidden-xs'>"+
			              "<figure class='thumbnail'>"+
			                "<img class='img-responsive' src='resources/img/librarianm.png' />"+
			                "<figcaption class='text-center'>"+entry.NAME+"</figcaption>"+
			              "</figure>"+
			            "</div>"+
			            "<div class='col-md-10 col-sm-10'>"+
			            "<form name='delFrm'>"+
			              "<div class='panel panel-default arrow left'>"+
			                "<div class='panel-body'>"+
			                  "<header class='text-left'>"+
			                    
			                   "<time class='comment-date' datetime="+entry.REGDATE+"><i class='fa fa-clock-o'></i>&nbsp;"+entry.REGDATE+"</time>"+
			                  "</header>"+
			                  "<div class='comment-post'>"+
			                    "<p>"+
			                    entry.CONTENT+
			                    "</p>"+
			                  "</div>";
					if("${sessionScope.loginLibrarian.name}" == entry.NAME ){
						resultHTML  +=  	  "<p class='text-right'><a href='#'  id='btnDel' class='btn btn-default btn-sm' onClick='delComment(\"${boardvo.idx}\", \"" + entry.IDX + "\")'><i class='glyphicon glyphicon-trash'></i> 댓글삭제</a></p>"+
					       "</div>"+
				              "</div>"+
				              "</form>"+
				            "</div>"+
				          "</article>";
					}	else{
						
						                  
						resultHTML  +=         
			                  
			                  
			                "</div>"+
			              "</div>"+
			              "</form>"+
			            "</div>"+
			          "</article>";
					}
				});// end of $.each()
				
				$("#commentDisplay").empty().html(resultHTML); // 기존것은 비우고 새롭게채워라
				
				makeCommentPageBar(currentShowPageNo);// 페이지바함수 호출
				
			
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});
		
				
		
	}// end of function goViewComment(currentShowPageNp)
	
	
	
	// === 댓글내용  페이지바 Ajax 로 만들기
	function makeCommentPageBar(currentShowPageNo){
		
		var form_data = {idx:"${boardvo.idx}",
				         sizePerPage:"5"};
		
		$.ajax({
			url:"<%=request.getContextPath()%>/getCommentTotalPage.ana",
			data:form_data,
			type:"GET",
			dataType:"JSON",
			success:function(json){

				var temp = json.TOTALPAGE;

				if(temp > 0){
					//댓글이 있는 경우
					
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					
					/////////////////////////////////////////
					var blockSize = 10;
					// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 갯수이다.
					/*
					    1  2  3  4  5  6  7  8  9 10 -- 1개블럭
					   11 12 13 14 15 16 17 18 19 20 -- 1개블럭
					   21 22 23 24 25 26 27 28 29 30 -- 1개블럭
					*/
					var loop = 1;
					/*
					  loop 는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 갯수(지금은 10개) 이때까지만
					  증가하는 용도이다.
					*/
					
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize + 1;
					// !!! 공식이다. !!! //
					/*
						currentShowPageNo   pageNo
						------------------------------------
							1                 1 Math.floor((1 - 1)/10)*10 + 1
							2                 1 Math.floor((2 - 1)/10)*10 + 1  자바에서는 1/10은 0이되지만, 자바스크립트에서는 1/10은 0.1이된다. 그래서 0.1을 0으로 만들기위해 자바스크립트에서는 Math.floor(0.1)을 쓰면 0.1보다 작은 최대의 정수가 나온다. 즉, 0
							3                 1
							4                 1
							..               ..
							9                 1
							10                1
							11               11 Math.floor((11 - 1)/10)*10 + 1
							12               11 Math.floor((12 - 1)/10)*10 + 1
							13               11
							..               ..
							19               11
							20               11
							21               21 Math.floor((21 - 1)/10)*10 + 1
							22               21
							23               21
							..               ..
							29               21
							30               21							
					*/
					
					// *** [이전] 만들기 ***//
					if( pageNo != 1){
						pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\"" + (pageNo-1) + "\");'>[이전]</a>&nbsp;";
						
					}
					
					
					// ------------------------------ //
					
					while(!(loop > blockSize || pageNo > totalPage )){
						
						if(pageNo == currentShowPageNo){						
												
							pageBarHTML += "&nbsp;<span style='color:red; font-size:12pt; font-weight:bold; text-decoration:underline;'>" + pageNo + "</span>&nbsp;";
						
						}
						else{
													
							pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\"" + pageNo + "\");'>" + pageNo + "</a>&nbsp;";
						
						}
												
						loop ++;
						pageNo ++;
						
					}// end of while						
					// ------------------------------------- //
					
					// *** [다음] 만들기 ***//
					if( !(pageNo > totalPage) ){
						pageBarHTML += "&nbsp;<a href='javascript:goViewComment(\"" + pageNo + "\");'>[다음]</a>&nbsp;";
						
					}
					
					
					
					////////////////////////////////////////
					
					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else{
					// 댓글이 없는 경우
					$("#pageBar").empty();
				}
				
				$(".comments").html(json.TOTALCOUNT + " comments");
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
		
	}// end of function makeCommentPageBar(currentShowPageNo)
    
	
	
		
	
	
	function delComment(orgIdx, idx){
		var frm = document.deleteFrm;
		
		var form_data = {"orgIdx":orgIdx,
						"idx":idx};
		
		$.ajax({
			
			url:"<%=request.getContextPath()%>/commentdelEnd.ana",
			data: form_data,
			type:"POST",
			dataType:"JSON",
			success:function(json){
				
				if(json.RESULT == 1) {
					alert("댓글이 삭제되었습니다.");
					goViewComment('1');
				}
				else {
					alert("댓글삭제에 실패했습니다.");
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
			
		});
		
		
	}
	
</script>

<form name="deleteFrm">
	<input type="hidden" name="orgIdx" value="" /> <input type="hidden"
		name="idx" value="" />

</form>


<div class="container">
	<div class="row">
		<h2 style="margin-bottom: 30px;">
			<img alt="boardlist" src="resources/img/boardlist.png" width="25px;"
				height="25px;">&nbsp;<span style="font-size: 15pt; font-weight: bolder;">글번호 : ${boardvo.idx}</span>
		</h2>
		<div class="">
			<div class="one-post">
				<!--Post Description-->
				<div class="bd-radius">
					<div class="post-post-content postMetaInline-media" style="">
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 pd">
							<div class="media">
								<div class="media-left">
									<a href="#"> <img src="resources/img/kinglibrarian.png"
										class="media-object" alt="Sample Image">
									</a>
								</div>
								<div class="media-body">
									<h4 class="media-heading mg mg-t-5">
										<a href="#"> <span>${boardvo.name}</span>
										</a>
									</h4>

									<span class="media-heading-muted">${boardvo.libid_fk} </span>
								</div>
								<h4 class="mg mg-t-5 mg-b-5 posttitle-font">${boardvo.subject }</h4>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 pd">
							<ul class="nav list-unstyled">
								<li>
									<div class="text-right timed">${boardvo.regDate}</div>
								</li>
								<li>
									<div class="text-right timed">

										<span> <i class="glyphicon glyphicon-eye-open"></i>&nbsp;조회수
											: &nbsp;${boardvo.readCount}
										</span>

									</div>
								</li>
							</ul>
						</div>
						<div class="clearfix"></div>
					</div>

					<div class="pd-b-10 pd-t-10 pd-r-15 pd-l-15"
						style="max-height: 250px; position: relative; overflow: hidden; overflow-y: scroll; height: 300px;">


						<div style="padding-left: 40px;">${boardvo.content}</div>

					</div>

					<div class="post-post-content postMetaInline-media">
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 pd">
							<table>
								<!-- ====#148. 첨푸파일 이름 및 파일크기를 보여주고 첨부파일 다운받게 만들기 ==== -->
								<tr>
									<th>첨부파일 :</th>
									<td><a
										href="<%=request.getContextPath() %>/download.ana?idx=${boardvo.idx}">${boardvo.orgFilename}</a>


									</td>
								</tr>

								<tr>
									<th>파일크기(bytes)</th>
									<td style="padding: 10px;">${boardvo.fileSize}</td>
								</tr>

							</table>
						</div>

						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 pd">
							<ul class="nav list-unstyled">
								<li>
									<div class="icondisplay pull-right">
										<a data-toggle="collapse" href="#collapseExample"
											aria-expanded="false" aria-controls="collapseExample"> </a>다음글
										:&nbsp;
										<sapn class="move"
											onClick="javascript:location.href='BSBview.ana?idx=${boardvo.previousidx}'">
										${boardvo.previoussubject} </sapn>



									</div>
								</li>
							</ul>
						</div>


						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 pd">
							<ul class="nav list-unstyled">
								<li>
									<div class="icondisplayy pull-right">
										<a data-toggle="collapse" href="#collapseExample"
											aria-expanded="false" aria-controls="collapseExample"> </a>
										<div style="margin-top: 10px;">
											이전글 :&nbsp;<span class="move"
												onClick="javascript:location.href='BSBview.ana?idx=${boardvo.nextidx}'">${boardvo.nextsubject}</span>
										</div>



									</div>
								</li>
							</ul>
						</div>





						<div class="clearfix"></div>
					</div>
				</div>
				

				<div style="margin-top: 30px;">
					<button class="btn btn-primary btn-xs" data-title="board"
						data-target="#board" style="float: left; vertical-align: middle; "
						onClick="javascript:location.href='<%=request.getContextPath()%>/edit.ana?idx=${boardvo.idx}'">
						<span class="glyphicon glyphicon-pencil">&nbsp;글수정</span>
						
					</button>
					
						<button class="btn btn-primary btn-xs" data-title="board"
						data-target="#board" style="margin-left:5px; float: left; vertical-align: middle; "
						onClick="javascript:location.href='<%=request.getContextPath()%>/add.ana?root=${boardvo.idx}&groupno=${boardvo.groupno}&depthno=${boardvo.depthno}'">
						<span class="glyphicon glyphicon-edit">&nbsp;답글쓰기</span>
						
					</button>
				
				
					<button class="btn btn-primary btn-xs" data-title="board"
						data-target="#board" style="float: right;"
						onClick="javascript:location.href='<%=request.getContextPath()%>/${sessionScope.listgobackURL}'">
						<span class="glyphicon glyphicon-menu-hamburger">&nbsp;목록보기</span>
					</button>
				</div>
             
				<div class="container">
					<div class="row">
						<div class="col-md-11">
							<div class="page-header">
								<h1>
									<small class="pull-right comments"></small> Comments
								</h1>
							</div>
							<div class="comments-list">



								<!-- 	==== #91. 댓글 내용 보여주기 ==== -->

								<table id="table2" style="margin-top: 2%; margin-bottom: 3%;">
									<thead>
										<tr>
											<th style="text-align: center;"></th>
											<th style="text-align: center;"></th>
											<th style="text-align: center;"></th>
										</tr>
									</thead>
									<tbody id="commentDisplay"></tbody>
								</table>



							</div>
						</div>
					</div>
					<div id="pageBar" style="height: 50px; margin-left: 30%;">&nbsp;</div>
				</div>
					
				


				<div class="container">
					<div class="row">
						<h3>댓글쓰기</h3>
					</div>

					<div class="row">

						<div class="col-md-6">
							<div class="widget-area no-padding blank">
								<div class="status-upload">
									<form name="addWriteFrm">
										<input type="hidden" name="libid_fk" value="${sessionScope.loginLibrarian.libid}" readonly />
										성명 : <input type="text" name="name" value="${sessionScope.loginLibrarian.name}" class="short"
											readonly />
										<textarea name="content" placeholder="내용입력"></textarea>

										<!-- 댓글에 달리는 원게시물 글번호(즉, 댓글의 부모글 글번호) -->
										<input type="hidden" name="parentidx" value="${boardvo.idx}" />

										<button type="button" id="something"
											class="btn btn-success green" onClick="goAddWrite();">
											<i class="fa fa-share"></i> 작성하기
										</button>
									</form>
								</div>
								<!-- Status Upload  -->
							</div>
							<!-- Widget Area -->
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</div>