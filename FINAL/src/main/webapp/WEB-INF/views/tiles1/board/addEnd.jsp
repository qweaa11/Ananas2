<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	if(${n==1})
		alert("글쓰기성공");
	else
		alert("글쓰기실패");
	
	location.href = "${loc}";
</script>