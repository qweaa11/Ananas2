<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String ctxPath = request.getContextPath(); %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

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
		
		$(".showResult").hide();
			
		$(".form-control").each(function(){
				
			$(this).blur(function(){
					
				var data = $(this).val().trim();
					
				if(data == "") {					
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
				
				
		$("#post").click(function(){
				
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
	}// End of function idDuplicate(wishID)-------------

		
	// 멤버 등록가기
	function memberRegist() {
			
		var frm = document.signupFrm;
		frm.action = "memberRegistEnd.ana";
		frm.method = "POST";
		frm.submit();
		
	}

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
          <small class="showResult" id="idGuidLine" style="color: red; font-weight: bold;"> 아이디는 영문, 숫자의 조합으로 6자이상 12자이하로 만들어주세요.</small>
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
         <label class="control-label col-sm-3"><span class="text-danger">*</span>생년월일</label>
         <div class="col-xs-8">
           <div class="form-inline">
             <div class="form-group" >
               <select name="yyyy" class="form-control" style="margin-right: 30px;">
                 <option value="0">Year</option>
                 <option value="1955" >1955 </option><option value="1956" >1956 </option><option value="1957" >1957 </option>
                 <option value="1958" >1958 </option><option value="1959" >1959 </option><option value="1960" >1960 </option>
                 <option value="1961" >1961 </option><option value="1962" >1962 </option><option value="1963" >1963 </option>
                 <option value="1964" >1964 </option><option value="1965" >1965 </option><option value="1966" >1966 </option>
                 <option value="1967" >1967 </option><option value="1968" >1968 </option><option value="1969" >1969 </option>
                 <option value="1970" >1970 </option><option value="1971" >1971 </option><option value="1972" >1972 </option>
                 <option value="1973" >1973 </option><option value="1974" >1974 </option><option value="1975" >1975 </option>
                 <option value="1976" >1976 </option><option value="1977" >1977 </option><option value="1978" >1978 </option>
                 <option value="1979" >1979 </option><option value="1980" >1980 </option><option value="1981" >1981 </option>
                 <option value="1982" >1982 </option><option value="1983" >1983 </option><option value="1984" >1984 </option>
                 <option value="1985" >1985 </option><option value="1986" >1986 </option><option value="1987" >1987 </option>
                 <option value="1988" >1988 </option><option value="1989" >1989 </option><option value="1990" >1990 </option>
                 <option value="1991" >1991 </option><option value="1992" >1992 </option><option value="1993" >1993 </option>
                 <option value="1994" >1994 </option><option value="1995" >1995 </option><option value="1996" >1996 </option>
                 <option value="1997" >1997 </option><option value="1998" >1998 </option><option value="1999" >1999 </option>
                 <option value="2000" >2000 </option><option value="2001" >2001 </option><option value="2002" >2002 </option>
                 <option value="2003" >2003 </option><option value="2004" >2004 </option><option value="2005" >2005 </option>
                 <option value="2006" >2006 </option>
                </select>
             </div>
             <div class="form-group">
               <select name="mm" class="form-control">
                 <option value="">Month</option>
                 <option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option>
                 <option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option>
                 <option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option>                
                </select>
             </div>
             <div class="form-group">
               <select name="dd" class="form-control">
                 <option value="">Date</option>
                 <option value="01" >01 </option><option value="02" >02 </option><option value="03" >03 </option><option value="04" >04 </option>
                 <option value="05" >05 </option><option value="06" >06 </option><option value="07" >07 </option><option value="08" >08 </option>
                 <option value="09" >09 </option><option value="10" >10 </option><option value="11" >11 </option><option value="12" >12 </option>
                 <option value="13" >13 </option><option value="14" >14 </option><option value="15" >15 </option><option value="16" >16 </option>
                 <option value="17" >17 </option><option value="18" >18 </option><option value="19" >19 </option><option value="20" >20 </option>
                 <option value="21" >21 </option><option value="22" >22 </option><option value="23" >23 </option><option value="24" >24 </option>
                 <option value="25" >25 </option><option value="26" >26 </option><option value="27" >27 </option><option value="28" >28 </option>
                 <option value="29" >29 </option><option value="30" >30 </option><option value="31" >31 </option>                
                </select>
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
        <label class="control-label col-sm-3"><span class="text-danger">*</span>가입 도서관</label>
         <div class="col-xs-8">
           <div class="form-inline">
           	<div class="form-group" >
		       <select name="library" class="form-control">
		         <option value="0">가입 도서관</option>
		         <option value="서울시립 도서관" >서울시립 도서관 </option>
		         <option value="수원시립 도서관" >수원시립 도서관 </option>
		         <option value="인천시립 도서관" >인천시립 도서관</option>
		       </select>
        	</div>
           </div>
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
