<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ======= #23. tiles 를 사용하는 레이아웃2 페이지 만들기  ======= --%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>    

<%
	String ctxPath = request.getContextPath();
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="resources/img/home-logo/mainlogoblack.png" type="image/png">
<title>도서관리 시스템</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
  		 
</head>

<body>
	<div id="mycontainer">
		
	
		<div id="mycontent">
			<tiles:insertAttribute name="content" />
		</div>
		
	
	</div>
</body>
</html>