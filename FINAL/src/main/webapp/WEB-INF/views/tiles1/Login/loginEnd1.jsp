<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String ctxPath = request.getContextPath(); %>

<script type="text/javascript">
	var loginLibrarian = "${sessionScope.loginLibrarian.libid}";
	var gobackURL = "${requestScope.gobackURL}";
	
	if(loginLibrarian != null && loginLibrarian != "" && (gobackURL == null || gobackURL == "") ) {
		//alert("로그인 성공 !!");
		location.href="<%=ctxPath%>/index.ana";
	}
	
	if(loginLibrarian != null && loginLibrarian != "" && (gobackURL != null && gobackURL != "")) {
		alert("로그인 성공 !!");
		location.href=gobackURL;
	}
</script>
    