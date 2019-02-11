<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% String ctxPath = request.getContextPath(); %>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- // jQuery UI CSS파일  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- // jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- // jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700);
@import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700);
  

	body {
	    background: #fff;
		font-family: 'Roboto', sans-serif;
		color:#333;
		line-height: 22px;	
	}
	h1, h2, h3, h4, h5, h6 {
		font-family: 'Roboto Condensed', sans-serif;
		font-weight: 400;
		color:#111;
		margin-top:5px;
		margin-bottom:5px;
	}
	h1, h2, h3 {
		text-transform:uppercase;
	}
	
	input.upload {
	    position: absolute;
	    top: 0;
	    right: 0;
	    margin: 0;
	    padding: 0;
	    font-size: 12px;
	    cursor: pointer;
	    opacity: 1;
	    filter: alpha(opacity=1);    
	}
	
	.form-inline .form-group{
	    margin-left: 0;
	    margin-right: 0;
	    
	}
	.control-label {
	    color:#333333;
	}
	
	.address {
		margin-top: 3px;
	}
	
	.hold{
		cursor: no-drop;
		opacity: 0.4;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#memberid").focus();
		
		$(".showResult").hide(); // 유효성 검사 결과를 모두 감춰준다.
			
		$(".form-control").each(function(){
				
			$(this).blur(function(){
					
				var data = $(this).val().trim(); // 포커스가 되어있는 곳의 값을 공백제거후 저장해온다.
					
				if(data == "") {
					
					var btn = document.getElementById('registUser'); // 공백값이 있을경우 버튼을 비활성화 시킨다.
					btn.disabled = 'disabled';

					$(":input").attr("disable", true).addClass("hold");
					$(this).attr("disable", false).removeClass("hold");
					$(this).focus();
					return;
				}
				else{					
					$(":input").attr("disabled", false).removeClass("hold");	
				}// end of if~else
					
				// 아이디 유효성 검사
				var memberid = $("#memberid").val().trim();
				//console.log(memberid);
				
				if(memberid != null && memberid != "") {
					
					var checkID = /^[A-Za-z0-9]{6,12}$/g; //아이디 체크 영문+숫자로 6이상 12자 미만
					var bool = checkID.test(memberid);
					if(!bool) {
						$("#idGuidLine").show();
						$("#memberid").val("");
						$(":input").attr("disable", true).addClass("hold");
						$(this).attr("disable", false).removeClass("hold");
						$(this).focus();
						return;
					}
					
					$("#idGuidLine").hide();
				}

				// 비밀번호 유효성 검사
				var pwd = $("#pwd").val().trim();
				
				if(pwd != null && pwd != "") {
		
				 	var checkPW = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
					var bool = checkPW.test(pwd);
					
					if(!bool){
						
						$("#pwGuidLine").show();
						$("#pwd").val("");
						$(":input").attr("disable", true).addClass("hold");
						$(this).attr("disable", false).removeClass("hold");
						$(this).focus();
						return;
					}
					
					$("#pwGuidLine").hide();
				}
				
				// 비밀번호 확인
				var confirmpwd = $("#confirmpwd").val().trim();		
				
				 if(pwd != null && pwd != "" &&
					confirmpwd != null && confirmpwd != "" &&
					pwd != confirmpwd){

					 $("#unmatch").show();
					 $(":input").attr("disable", true).addClass("hold");
					 $("#pwd").attr("disable", false).removeClass("hold");
					 $("#pwd").empty().val("");
					 $("#confirmpwd").empty().val("");
				 	 $("#pwd").focus();
				 	 
				 	 return;
				 }
				 
				//1차와 2차 비밀번호가 서로 일치 하지 않을 때 에러를 표기
				$("#unmatch").hide();
				
				var email = $("#email").val().trim();
				
				// 이메일 주소 유효성 검사
				if(email != null && email != "") {
					
					var checkEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //이메일 체크
					var bool = checkEmail.test(email);
					
					if(!bool){
						
						$("#emailGuidLine").show();
						$("#email").val("");
						$(":input").attr("disable", true).addClass("hold");
						$(this).attr("disable", false).removeClass("hold");
						$(this).focus();
						return;
					}
					
					$("#emailGuidLine").hide();
				}
				
				// 휴대폰 번호 유효성 검사
				var phone = $("#phone").val().trim();
				
				if(phone != null && phone != "") {
					
					var checkNumber = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/; 
					var bool = checkNumber.test(phone);
					
					if(!bool){
						
						$("#phoneGuidLine").show();
						$("#phone").val("");
						$(":input").attr("disable", true).addClass("hold");
						$(this).attr("disable", false).removeClass("hold");
						$(this).focus();
						return;
					}
					
					$("#phoneGuidLine").hide();
				}
				
			});//$(this).blur(function()---------
		});// end of $(".form-control").each(function()-----------
				
				
		$("#post").click(function(){// 우편번호를 클릭하면 다음지도 찾기 앱을 불러온다.
				
			new daum.Postcode({
				oncomplete: function(data) {
					$("#post").val(data.zonecode);
					$("#addr1").val(data.address);
					$("#addr2").focus();
					$("#addr2").attr("disable", false).removeClass("hold");
				}
			}).open();
		
		}); // end of $("#post").click(function()
				
				
	});// end of $(document).ready(function()---------------
			

	// 아이디 중복체크 기능
	function idDuplicate() {
			
			var memberid = $("#memberid").empty().val();
			
			if(memberid.trim() == "") {
				
				alert("아이디 중복 검사를 할 아이디를 입력해 주세요.");
			}
			else{
				
				var form_data = {"memberid":memberid}
				
					$.ajax({
						url:"idDuplicate.ana",
						data:form_data,
						type:"POST",
						dataType:"JSON",
						success:function(json){
							
							console.log(json.result);
							
							if(json.result == 0){
								
								$("#unAvailable").hide();
								$("#available").show();
							}
							else{
								
								$("#available").hide();
								$("#unAvailable").show();
								
							}
						},
						error: function(request, status, error){
							alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
						}
						
					});
			 }
	}// End of function idDuplicate(wishID)-------------

		
	// 멤버 등록가기
	function memberRegist() {
		
		// 인풋 태그에 기입 된 모든 값을 가져온다.
		var memberid = $.trim($("#memberid").val());
		var email = $.trim($("#email").val());
		var pwd = $.trim($("#pwd").val());
		var name = $.trim($("#name").val());
		var phone = $.trim($("#phone").val());
		var birth = $.trim($("#birth").val());
		var post = $.trim($("#post").val());
		
		// 인풋 태그에 기입 된 값이 공백이 없는 경우 회원 등록을 한다.
		if( memberid != "" &&
			email != "" &&   
			pwd != "" &&
			name != "" &&
			phone != "" &&
			birth != "" &&
			post != "") {
			
			var frm = document.signupFrm;
			frm.action = "memberRegistEnd.ana";
			frm.method = "POST";   
			frm.submit();
			
		}
		else{
			
			alert("필수 가입정보가 미입력 되었습니다.");
		}
	}

	
	$(function() { // 생년월일 등록 페이지에 사용 되는 데이터 피커의 설정
		  $("#birth").datepicker({
		    dateFormat: 'yymmdd',
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames: ['일','월','화','수','목','금','토'],
		    dayNamesShort: ['일','월','화','수','목','금','토'],
		    dayNamesMin: ['일','월','화','수','목','금','토'],
		    showMonthAfterYear: true,
		    changeMonth: true,
		    changeYear: true
		  });
		});

</script>

<body>
<!------ Include the above in your HEAD tag ---------->
<div class="container">
	<div class="row">
    <div class="col-md-8">
      <section>      
        <h1 class="entry-title"><span>회원 등록</span> </h1>
        <hr>
      <form class="form-horizontal" method="post" name="signupFrm" id="signupFrm" enctype="multipart/form-data" >       
      <div class="form-group">
        <label class="control-label col-sm-3"><span class="text-danger">*</span>회원 ID</label>
        <div class="col-md-8 col-sm-9">
            <div class="input-group">
	            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
	            <input type="text" class="form-control" name="memberid" id="memberid" placeholder="ID를 입력해 주세요." value="">
          	</div>
          <a onClick="idDuplicate()" style="cursor: pointer;"><img src="<%=ctxPath%>/resources/img/b_id_check.gif"></a>
          <input type="hidden" value=""/>   
          <small style="color: red;">*</small><small> ID는 반드시 중복체크를 해주셔야 합니다! </small><br/>
          <small class="showResult" id="available" style="color: green; font-weight: bold;"> 사용가능한 아이디 입니다. </small>
          <small class="showResult" id="unAvailable" style="color: red; font-weight: bold;"> 이미 사용중인 아이디 입니다.</small>
          <small class="showResult" id="idGuidLine" style="color: red; font-weight: bold;"> 아이디는 영문, 숫자로 6자이상 12자이하로 만들어주세요.</small>
         </div>
       </div>
        
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>회원 Email</label>
         <div class="col-md-8 col-sm-9">
             <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
             <input type="email" class="form-control" name="email" id="email" placeholder="Email 주소를 입력해 주세요." value="">
           </div>
           <small> Email 주소는 사이트 이용에 사용됩니다. 정확한 메일 주소를 넣어주세요. </small><br/>
           <small class="showResult" id="emailGuidLine" style="color: red; font-weight: bold;">이메일 주소가 형식에 맞지 않습니다.재입력 해주세요.</small>
          </div>
       </div>
        
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>비밀번호</label>
         <div class="col-md-5 col-sm-8">
           <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
             <input type="password" class="form-control" name="pwd" id="pwd" placeholder="비밀번호는 8자이상 영문,숫자,특수기호의 조합입니다." value="">
          </div> 
          <small class="showResult" id="pwGuidLine" style="color: red; font-weight: bold;"> 비밀번호는 영문, 숫자, 특수기호의 조합으로 8자이상 15자이하로 만들어주세요.</small>  
         </div>
       </div>
       
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>비밀번호 확인</label>
         <div class="col-md-5 col-sm-8">
           <div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
             <input type="password" class="form-control" name="confirmpwd" id="confirmpwd" placeholder="비밀번호를 한 번 더 입력해 주세요." value="">
           </div>
           <small class="showResult" id="unmatch" style="color: red; font-weight: bold;"> 비밀번호가 서로 일치하지 않습니다. 재입력해주세요. </small>  
         </div>
       </div>
       
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>회원 이름</label>
         <div class="col-md-8 col-sm-9">
           <input type="text" class="form-control" name="name" id="name" placeholder="이름을 입력해 주세요." value="">
         </div>
       </div>
       
        <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>휴대폰 번호</label>
         <div class="col-md-5 col-sm-8">
         	<div class="input-group">
             <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
           <input type="text" class="form-control" name="phone" id="phone" placeholder="핸드폰 번호를 - 없이 입력해 주세요." value="">
           <small class="showResult" id="phoneGuidLine" style="color: red; font-weight: bold;"> 올바른 핸드폰 번호를 입력해주세요.</small>
           </div>
         </div>
       </div>
       
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>생년월일</label>
         <div class="col-xs-8">
           <div class="form-inline">
             <div class="form-group" >
                 <input type="text" class="form-control" name="birth" id="birth" placeholder="생년월일을 선택해 주세요." />
             </div>
           </div>
         </div>
       </div>
       
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>성별</label>
         <div class="col-md-8 col-sm-9">
           <label><input name="gender" type="radio" value="0" checked>남성 </label>
           <label><input name="gender" type="radio" value="1" >여성 </label>
         </div>
       </div>
       
       <div class="form-group">
         <label class="control-label col-sm-3"><span class="text-danger">*</span>회원 집주소</label>
         <div class="col-md-8 col-sm-9">
           <label><input name="addressSort" type="radio" value="home" checked>자택 </label>
           <label><input name="addressSort" type="radio" value="company" >회사 </label>
           <input type="text" class="form-control address" name="post" id="post" style="width: 120px;" placeholder="우편번호" value="" />
           <input type="text" class="form-control address" name="addr1" id="addr1" placeholder="집주소" value="" />
           <input type="text" class="form-control address" name="addr2" id="addr2" placeholder="상세주소" value="" />
         </div>
       </div>
        
        <div class="form-group">
          <label class="control-label col-sm-3">프로필 사진<br>
          <small>(선택사항)</small></label>
          <div class="col-md-5 col-sm-8">  
            <div class="input-group"> <span class="input-group-addon" id="file_upload"><i class="glyphicon glyphicon-upload"></i></span>
              <input type="file" name="attach" id="attachs" class="form-control upload" value="" aria-describedby="file_upload">
            </div>
          </div>
        </div>

        <div class="form-group">
          <div class="col-xs-offset-3 col-md-8 col-sm-9"><span class="text-muted"><span class="label label-danger">*주의!</span><br/>
          	등록해주신 모든 개인정보는 <a href="#">도서관 이용규칙</a>에 따라서 도서관 서비스 이용에 사용됩니다!</span> </div>
        </div>
        <div class="form-group">
          <div class="col-xs-offset-3 col-xs-10">
            <button id="registUser" type="button" class="btn btn-primary" onClick="memberRegist()">회원가입</button> 
          </div>
        </div>
      </form>
    </div>
</div>
</div>
