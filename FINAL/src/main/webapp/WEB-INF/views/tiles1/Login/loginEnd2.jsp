<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String ctxPath = request.getContextPath(); %>

<script type="text/javascript">
	var loginAdmin = "${sessionScope.loginAdmin.adminid}";
	var gobackURL = "${requestScope.gobackURL}";
	
	if(loginAdmin != null && loginAdmin != "" && (gobackURL == null || gobackURL == "") ) {
		//alert("로그인 성공 !!");
		location.href="<%=ctxPath%>/index.ana";
	}
	
	if(loginAdmin != null && loginAdmin != "" && (gobackURL != null && gobackURL != "")) {
		alert("로그인 성공 !!");
		location.href=gobackURL;
	}
</script>
    