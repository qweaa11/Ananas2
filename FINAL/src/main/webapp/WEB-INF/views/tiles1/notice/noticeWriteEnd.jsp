<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	if(${n==1})
		alert("공지사항 등록 성공!!");
	else
		alert("공지사항 등록 실패!!");
	
	location.href="${loc}";
</script>