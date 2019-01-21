<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String ctxPath = request.getContextPath(); %>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		
		
<style type="text/css">

.btnContactSubmit
{
    width: 50%;
    border-radius: 1rem;
    padding: 1.5%;
    color: #fff;
    background-color: #0062cc;
    border: none;
    cursor: pointer;
    margin-right: 6%;
    background-color: white;
    color: #8c8c8c;
    margin-top: 4%;
    font-size: 12pt;
}
.register .nav-tabs .nav-link:hover{
    border: none;
    
}
.text-align{
    margin-top: -3%;
    margin-bottom: -9%;

    padding: 10%;
    margin-left: 30%;
}
.form-new{
    margin-right: 22%;
    margin-left: 20%;
}
.register-heading{
    margin-left: 40%;
    margin-bottom: 10%;
    align-content: center;
    color: #e9ecef;
}
.register-heading h1{
    margin-left: 21%;
    margin-bottom: 10%;
    color: #e9ecef;
}
.btnLoginSubmit{
    border: none;
    padding: 2%;
    width: 25%;
    cursor: pointer;
    background: #29abe2;
    color: #fff;
}
.btnForgetPwd{
    cursor: pointer;
    margin-right: 5%;
    color: #f8f9fa;
}
.btnForgetUserid{
    cursor: pointer;
    margin-right: 5%;
    color: #f8f9fa;
}
.register{
    background: #cccccc;
    margin-top: 3%;
    margin-bottom: 10%;
    padding: 3%;
    border-radius: 2.5rem;
}
.nav-tabs .nav-link{
    border: 1px solid transparent;
    border-top-left-radius: .25rem;
    border-top-right-radius: .25rem;
    color: white;
    font-size: 14pt;
    font-weight: bold;
}	

</style>

<script type="text/javascript">
 
     $(document).ready(function(){
    	 
    	 $("#btnLOGIN1").click(function() {
    		 func_Login1();
    	 }); // end of $("#btnLOGIN1").click();-----------------------
    	 
    	 $("#btnLOGIN2").click(function() {
    		 func_Login2();
    	 }); // end of $("#btnLOGIN2").click();-----------------------
    	 
    	$("#pwd1").keydown(function(event){
  			
  			if(event.keyCode == 13) { // 엔터를 했을 경우
  				func_Login1();
  			}
  			
    	 }); // end of $("#pwd1").keydown();-----------------------
    	 
    	 $("#pwd2").keydown(function(event){
	  			
	  			if(event.keyCode == 13) { // 엔터를 했을 경우
	  				func_Login2();
	  			}
    	 });
    	 
    	 if(${flag1 == true}){
    		 $("#saveid1").prop("checked", true);
    	 } 
    	 
    	 if(${flag2 == true}){
    		 $("#saveid2").prop("checked", true);
    	 }
    	
    	 
    }); // end of $(document).ready()---------------------------	 

    
    function func_Login1() {
    	
		 var libid = $("#libid1").val(); 
		 var pwd = $("#pwd1").val(); 
		
		 if(libid.trim()=="") {
		 	 alert("아이디를 입력하세요!!");
			 $("#libid1").val(""); 
			 $("#libid1").focus();
			 return;
		 }
		
		 if(pwd.trim()=="") {
			 alert("비밀번호를 입력하세요!!");
			 $("#pwd1").val(""); 
			 $("#pwd1").focus();
			 return;
		 }

			 var frm = document.loginFrm1;
			 frm.action = "<%=ctxPath%>/loginEnd1.ana";
			 frm.submit();
		
    } // end of function func_Login(event)-----------------------------
    
    
    function func_Login2() {
    	
		 var adminid = $("#adminid").val(); 
		 var pwd2 = $("#pwd2").val(); 
		
		 if(adminid.trim()=="") {
		 	 alert("아이디를 입력하세요!!");
			 $("#adminid").val(""); 
			 $("#adminid").focus();
			 return;
		 }
		
		 if(pwd2.trim()=="") {
			 alert("비밀번호를 입력하세요!!");
			 $("#pwd2").val(""); 
			 $("#pwd2").focus();
			 return;
		 }

			 var frm = document.loginFrm2;
			 frm.action = "<%=ctxPath%>/loginEnd2.ana";
			 frm.submit();
		
    } // end of function func_Login(event)-----------------------------
 
     
</script>

       <!--  <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> -->
   <!--      <script src="Styles/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="Styles/bootstrap-4.1.0.min.js" type="text/javascript"></script>
        <link href="Styles/bootstrap-4.1.0.min.css" rel="stylesheet" type="text/css"/>
        <link href="Styles/MainStyle.css" rel="stylesheet" type="text/css"/> -->
 		<?php
        session_start();
        ?>
        <div class="container register">
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">사서/관장</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">총관리자</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active text-align form-new" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <h3  class="register-heading">Login</h3>
                            <div class="row register-form">
                                <div class="col-md-12">
                                    <form name="loginFrm1" class="loginFrm1" method="POST">
                                        <div class="form-group">
                                            <input type="text" name="libid" id="libid1" class="form-control" placeholder="Your Userid *" value="${saveid1}" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="pwd" id="pwd1" class="form-control" placeholder="Your Password *" value="" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="button" name="LGform1" class="btnContactSubmit" id="btnLOGIN1" value="Login" />
								            <input type="checkbox" id="saveid1" name="saveid1" value="saveid1"><label for="idcheck">아이디 저장</label>
								            
                                            <%-- <a href="<%=ctxPath%>/idFind.ana" name="libid" id="libid" class="btnForgetUserid" value="Login">Forget Userid?</a>
                                            <a href="/Login/pwdFind.jsp" name="pwd" id="pwd" class="btnForgetPwd" value="Login">Forget Password?</a> --%>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade show text-align form-new" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <h3 class="register-heading" style="color:#8c8c8c; ">Login</h3>
                            <div class="row register-form">
                                <div class="col-md-12">
                                    <form name="loginFrm2" class="loginFrm2" method="POST">
                                        <div class="form-group">
                                            <input type="text" name="adminid" id="adminid" class="form-control" placeholder="Your Userid *" value="${saveid2}" required="" />
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="pwd" id="pwd2" class="form-control" placeholder="Your Password *" value="" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" name="LGform2" class="btnContactSubmit" id="btnLOGIN2" value="Login" />

                                            <input type="checkbox" id="saveid2" name="saveid2" value="saveid2"><label for="idcheck">아이디 저장</label> 
                                            
                                           <!--  <a href="ForgetUserid.php" name="adminid" id="adminid" class="btnForgetUserid" value="Login">Forget Userid?</a>
                                            <a href="ForgetPassword.php" name="pwd" id="pwd" class="btnForgetPwd" value="Login">Forget Password?</a> -->
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



  