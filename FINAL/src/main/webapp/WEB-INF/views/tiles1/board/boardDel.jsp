
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<style type="text/css">
	table, th, td, input, textarea {border: solid gray 1px;}
	
	#table, #table2 {border-collapse: collapse;
	 		         width: 1000px;
	 		        }
	#table th, #table td{padding: 5px;}
	#table th{width: 120px; background-color: #DDDDDD;}
	#table td{width: 860px;}
	.long {width: 470px;}
	.short {width: 120px;} 	
	
	a{text-decoration: none;}	

</style>

<script type="text/javascript">
    function goDelete() {
    	var frm = document.delFrm;
    	var pwval = frm.pw.value.trim();
    	if(pwval == "") {
    		alert("암호를 입력하세요!!");
    		return;
    	}
    	else {
    		frm.action = "<%=request.getContextPath() %>/boardDelEnd.ana";
    		frm.method = "POST";
    		frm.submit();
    	}
    } 
</script>

<div style="padding-left: 10%; border: solid 0px red;">
	<h1 style="margin-bottom: 50px;">글삭제</h1>
	
	<form name="delFrm">
		<table id="table">
			<tr>
				<th>암호</th>
				<td>
					<input type="password" name="pw"  class="short"/>
					<input type="hidden" name="idx" value="${idx}">
					<!-- 삭제해야 할 글번호와 함께 사용자가 입력한 암호를 전송하도록 한다. -->
					
				</td>
			</tr>
		</table>
		<br/>
		<button type="button" onclick="goDelete();">삭제</button>
		<button type="button" onclick="javascript:history.back();">취소</button>
	</form>
		
	<br/>
	<br/>
		
	
	<div>&nbsp;</div>
</div>











