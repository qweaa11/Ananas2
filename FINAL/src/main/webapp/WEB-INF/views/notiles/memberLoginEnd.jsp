<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String ctxPath = request.getContextPath(); %>

<script type="text/javascript">
	var loginuser = "${sessionScope.loginuser.memberid}";
	
	
	if(loginuser != null && loginuser != "") {
		alert("로그인 성공 !!");
		location.href="index.ana";
	}
</script>
    