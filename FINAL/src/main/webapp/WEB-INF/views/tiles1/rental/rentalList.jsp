<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

</style>

<script type="text/javascript">

	

</script>
	
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <h1>대출 목록</h1>   <div><input type="text" /></div>
    <div>
        <div class="col-lg-12 col-sm-12" style="">
            <div class="panel panel-default list-group-panel">
                <div class="panel-body col-lg-12 col-sm-12">
                            <table class="col-lg-12 col-sm-12">
                            	<thead>
                            	<tr class="col-lg-12 col-sm-12">
	                            	<th class="col-lg-1 col-sm-1">전체선택 </th>  
	                                <th class="col-lg-1 col-sm-1">대출번호</th>
	                                <th class="col-lg-1 col-sm-1">도서명</th>
	                                <th class="col-lg-1 col-sm-1">저자</th>
	                                <th class="col-lg-2 col-sm-2">출판사</th>  
	                                <th class="col-lg-1 col-sm-1">아이디</th>      
	                                <th class="col-lg-1 col-sm-1">성명</th>        
	                                <th class="col-lg-1 col-sm-1">대여일</th>
	                                <th class="col-lg-1 col-sm-1">반납일</th>  
                                </tr>
                                </thead>
                                <tbody>
                            <c:forEach var="rental" items="${LIST}">
                            	<tr class="col-lg-12 col-sm-12">
	                                <td class="col-lg-1 col-sm-1"><input style="margin-right: 5px;" type="checkbox" /></td>
	                                <td class="col-lg-1 col-sm-1">${rental.IDX}</td>  
	                                <td class="col-lg-1 col-sm-1">${rental.BOOKTITLE}</td>
	                                <td class="col-lg-1 col-sm-1">${rental.BOOKAUTHOR}</td>
	                                <td class="col-lg-2 col-sm-2">${rental.PUBNAME}</td>
	                                <td class="col-lg-1 col-sm-1">${rental.MEMBERID}</td>
	                                <td class="col-lg-1 col-sm-1">${rental.MEMBERNAME}</td>   
	                                <td class="col-lg-1 col-sm-1">${rental.RETALDATE}</td>
	                                <td class="col-lg-1 col-sm-1">${rental.DEADLINE}</td>  
                                <tr>
                            </c:forEach>
                            	</tbody>
                            </table>
                </div>
            </div>
        </div>
    </div>
</div>