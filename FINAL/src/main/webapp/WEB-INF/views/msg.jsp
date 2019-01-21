<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript">

	if(${msg != ""}){
		alert("${msg}");
	}
	
	location.href="${loc}"; // 스크립트에서 페이지이동

</script>