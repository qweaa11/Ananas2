<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

	<style type="text/css">
		table, th, td {border: 1px solid lightgrey;}
		th {background-color: #f2f2f2;
			text-align: center;}
		td {text-align: center;}
	</style>

	<script type="text/javascript">
		function goDelete() {
			var frm = document.deleteFrm;
			var pw = frm.pw.value.trim();
			
			if(pw == "") {
				alert("암호를 입력하세요~");
				return frm.pw.focus();
			}
			
			frm.action = "noticeDeleteEnd.ana";
			frm.method = "POST";
			frm.submit();
		}
	</script>

</head>
<body>

<h1 style="text-align: center;">공지사항 삭제</h1>

<form name="deleteFrm">
	<div class="container">
		<br/>
		<div class="row" align="center">
			<table class="table" style="width: 23%;">
				<tr>
					<th>암호</th>
					<td>
						<input type="password" name="pw" class="short" />
						<input type="hidden" name="idx" value="${noticevo.idx}" />
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="container" align="center">
		<div class="btn-group">
			<button type="button" class="btn" onClick="goDelete();">삭제</button>
			<button type="button" class="btn btn-default" onClick="javascript:history.back();">취소</button>
		</div>
	</div>
</form>

</body>
</html>