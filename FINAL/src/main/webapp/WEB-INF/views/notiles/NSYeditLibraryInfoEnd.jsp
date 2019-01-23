<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	if(${n==1}){
		alert("도서관정보 수정 성공!!");
		self.close();
	}	
	else {
		alert("도서관정보 수정 실패!!");
		self.close();
	}
		
	location.href="${loc}";	

</script>