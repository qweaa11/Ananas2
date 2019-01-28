<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  
  <style type="text/css">
  .button {
    background-color: transparent;
    text-align: center;
    display: inline-block;
    text-decoration: none;
    border: 1px solid;
    -webkit-transition: all 0.2s ease-in;
    -moz-transition: all 0.2s ease-in;
    -o-transition: all 0.2s ease-in;
    transition: all 0.2s ease-in;
    padding: 10px 30px 8px;
    font-size: 16px;
    font-family: "proxima-nova", sans-serif;
    font-weight: 300;
    letter-spacing: 0.2rem;
    line-height: 1;
    text-transform: uppercase;
	}
	.button:hover{
	    text-decoration: none;
	    cursor: pointer;
	    color: #000;
	}
	
	.button-neutral{
	    color: #000;
	    border-color: #000;
	}
	.button-neutral:hover{
	    color: #fff;
	    background-color: #000;
	}
  </style>
  
  <script type="text/javascript">
  
  	$(document).ready(function(){
  		
  		if(${sessionScope.loginAdmin.adminid != null}) {
  			searchKeep();
  	  		showAdminNoticeList("1");
  	  		
  	  		$("#search").keydown(function(event){
  	  			if(event.keyCode == 13) {
  	  				goSearch("1");
  	  			}
  	  		});
  		}
  		
  		if(${sessionScope.loginLibrarian.libid != null}) {
  			searchKeep1();
  	  		showlib1NoticeList("1");
  	  		
  	  		$("#search").keydown(function(event){
  	  			if(event.keyCode == 13) {
  	  				goSearchlib1('1');
  	  			}
  	  		});
  		}
  		
  		
  	});// end of $(document).ready()
  	
  	// 총관리자 공지사항(검색이 없을 경우)
  	function showAdminNoticeList(currentShowPageNo) {
  		var form_data = {"currentShowPageNo":currentShowPageNo};
  		
  		$.ajax({
  			url:"adminNoticeList.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				var html = "";
  				
  				if(json.length <= 0) {
  					html += "<tr>"+
  								"<td>공지사항이 없습니다.</td>"+
  							"</tr>";
  				}  				
  				$.each(json, function(entryIndex, entry){
  					if(entry.adminid_fk != null){
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td></td>"+
										"<td></td>"+
									"</tr>";
  						}
  						
  					}
  					else if(entry.libid_fk != null) {
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'></td>"+
										"<td style='text-align:center;'></td>"+
									"</tr>";
  						}  						
  					}  					
  				});// end of $.each
  				$("#result").empty().html(html);
  				makePageBar(currentShowPageNo);
  			},
  			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax
  	}// end of showAdminNoticeList(currentShowPageNo)
  	
	// 총관리자 공지사항(검색이 있는 경우)
  	function goSearch(currentShowPageNo, colname, search) {
  		
  		var colname = $("#colname").val();
  		var search = $("#search").val().trim();
  		
  		var form_data = {"currentShowPageNo":currentShowPageNo,
  						 "COLNAME":colname,
  						 "SEARCH":search}
  		$.ajax({
  			url:"adminNoticeList.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				var html = "";
  				
  				if(json.length <= 0) {
  					html += "<tr>"+
  								"<td>공지사항이 없습니다.</td>"+
  							"</tr>";
  				}
  				$.each(json, function(entryIndex, entry){
  					
  					if(entry.adminid_fk != null){
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td></td>"+
										"<td></td>"+
									"</tr>";
  						}
  						
  					}
  					else if(entry.libid_fk != null) {
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr style='cursor: pointer;' onClick='goView("+entry.idx+");'>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'></td>"+
										"<td style='text-align:center;'></td>"+
									"</tr>";
  						}  						
  					}
  					
  				});
  				
  				$("#result").empty().html(html);  				
  				makePageBar(currentShowPageNo, colname, search);  				
  			},
  			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax  		
  	}// end of function goSearch()
  	
  	// 총관리자 페이징 처리(검색이 없는 경우)
  	function makePageBar(currentShowPageNo) {
  		var form_data = {sizePerPage:"10"};
  		
  		$.ajax({
  			url:"getNotiecTotalPage.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				if(json.TOTALPAGE > 0) {
  					// 댓글이 있는 경우
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					var blockSize = 5;
					var loop = 1;
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize+1;
					
					// 이전 만들기
					if(pageNo != 1) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showAdminNoticeList(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-primary btn-circle'><</button></a>&nbsp;";
					}
					while(!(loop>blockSize || pageNo>totalPage)) {
						if(pageNo == currentShowPageNo) {
							pageBarHTML += "&nbsp;<button type='button' class='btn btn-primary btn-circle'><span style='color:#ff9999; font-size:12pt; font-weight: bold; text-decoration:underline;'>"+pageNo+"</span></button>&nbsp;";
						}
						else {
							pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showAdminNoticeList(\""+pageNo+"\");'><button type='button' class='btn btn-primary btn-circle'>"+pageNo+"</button></a>&nbsp;";
						}
						loop++;
						pageNo++;
					}// end of while
					
					// 다음 만들기
					if(!(pageNo > totalPage)) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showAdminNoticeList(\""+(pageNo)+"\");'><button type='button' class='btn btn-primary btn-circle'>></button></a>&nbsp;";
					}					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else {
					// 댓글이 없는 경우
					$("#pageBar").empty();
				}
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax
  	}// end of makePageBar(currentShowPageNo)
  	
  	// 총관리자 페이징 처리(검색이 있는 경우)
  	function makePageBar(currentShowPageNo, colname, search) {
  		var form_data = {sizePerPage:"10",
  						"COLNAME":colname,
  						"SEARCH":search};
  		
  		$.ajax({
  			url:"getNotiecTotalPage.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				if(json.TOTALPAGE > 0) {  					
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					var blockSize = 5;
					var loop = 1;
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize+1;
					
					// 이전 만들기
					if(pageNo != 1) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:goSearch(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-primary btn-circle'><</button></a>&nbsp;";
					}
					while(!(loop>blockSize || pageNo>totalPage)) {
						if(pageNo == currentShowPageNo) {
							pageBarHTML += "&nbsp;<button type='button' class='btn btn-primary btn-circle'><span style='color:#ff9999; font-size:12pt; font-weight: bold; text-decoration:underline;'>"+pageNo+"</span></button>&nbsp;";
						}
						else {
							pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:goSearch(\""+pageNo+"\");'><button type='button' class='btn btn-primary btn-circle'>"+pageNo+"</button></a>&nbsp;";
						}
						loop++;
						pageNo++;
					}// end of while
					
					// 다음 만들기
					if(!(pageNo > totalPage)) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:goSearch(\""+(pageNo)+"\");'><button type='button' class='btn btn-primary btn-circle'>></button></a>&nbsp;";
					}					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else {					
					$("#pageBar").empty();
				}
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax
  	}// end of makePageBar(currentShowPageNo)
	
	function searchKeep() {
  		if(${search != null && search != "" && search != "null"}) {
  			$("#colname").val("${colname}");
			$("#search").val("${search}");
  		}
  	}// end of searchKeep()
	
	
  	
  	
  	// **********************************************************************************//
  	// 도서관장,사서 공지사항(검색이 없을 경우)
  	function showlib1NoticeList(currentShowPageNo) {
  		var form_data = {"currentShowPageNo":currentShowPageNo};
  		
  		$.ajax({
  			url:"lib1NoticeList.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				var html = "";
  				
  				if(json.length <= 0) {
  					html += "<tr>"+
  								"<td>공지사항이 없습니다.</td>"+
  							"</tr>";
  				}  				
  				$.each(json, function(entryIndex, entry){
  					if(entry.adminid_fk != null){
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td></td>"+
										"<td></td>"+
									"</tr>";
  						}
  						
  					}
  					else if(entry.libid_fk != null) {
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'></td>"+
										"<td style='text-align:center;'></td>"+
									"</tr>";
  						}  						
  					}  					
  				});// end of $.each
  				$("#resultLib1").empty().html(html);
  				makePageBarLib1(currentShowPageNo);
  			},
  			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax
  	}// end of showlib1NoticeList(currentShowPageNo)
  	// 도서관장,사서 공지사항(검색이 있을 경우)
	function goSearchlib1(currentShowPageNo, colname, search) {
  		
  		var colname = $("#colname").val();
  		var search = $("#search").val().trim();
  		
  		var form_data = {"currentShowPageNo":currentShowPageNo,
  						 "COLNAME":colname,
  						 "SEARCH":search};
  		$.ajax({
  			url:"lib1NoticeList.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				var html = "";
  				
  				if(json.length <= 0) {
  					html += "<tr>"+
  								"<td>공지사항이 없습니다.</td>"+
  							"</tr>";
  				}
  				$.each(json, function(entryIndex, entry){
  					
  					if(entry.adminid_fk != null){
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr>" +
										"<td>"+entry.idx+"</td>"+									
										"<td>"+entry.adminid_fk+"</td>"+
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold; color: red;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td></td>"+
										"<td></td>"+
									"</tr>";
  						}
  						
  					}
  					else if(entry.libid_fk != null) {
  						if(entry.fileName != null && entry.fileSize != null){
	  						html += "<tr>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'>파일</td>"+
										"<td style='text-align:center;'>"+entry.fileSize+"</td>"+
									"</tr>";
  						}
  						else {
  							html += "<tr>" +
										"<td>"+entry.idx+"</td>"+
										"<td>"+entry.libid_fk+"</td>"+									
										"<td>"+entry.name+"</td>"+
										"<td><span style='font-weight: bold;'><"+entry.libname+"></span>"+entry.subject+"</td>"+  								
										"<td>"+entry.regDate+"</td>"+
										"<td style='text-align:center;'>"+entry.readCount+"</td>"+  								
										"<td style='text-align:center;'></td>"+
										"<td style='text-align:center;'></td>"+
									"</tr>";
  						}  						
  					}
  					
  				});
  				
  				$("#resultLib1").empty().html(html);  				
  				makePageBarLib1(currentShowPageNo, colname, search);  				
  			},
  			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax  		
  	}// end of function goSearchlib1()
  	// 도서관장,사서 페이징처리(검색이 없을 경우)
  	function makePageBarLib1(currentShowPageNo) {
  		var form_data = {sizePerPage:"10"};
  		
  		$.ajax({
  			url:"lib1getNotiecTotalPage.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				if(json.TOTALPAGE > 0) {
  					// 댓글이 있는 경우
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					var blockSize = 5;
					var loop = 1;
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize+1;
					
					// 이전 만들기
					if(pageNo != 1) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showlib1NoticeList(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-primary btn-circle'><</button></a>&nbsp;";
					}
					while(!(loop>blockSize || pageNo>totalPage)) {
						if(pageNo == currentShowPageNo) {
							pageBarHTML += "&nbsp;<button type='button' class='btn btn-primary btn-circle'><span style='color:#ff9999; font-size:12pt; font-weight: bold; text-decoration:underline;'>"+pageNo+"</span></button>&nbsp;";
						}
						else {
							pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showlib1NoticeList(\""+pageNo+"\");'><button type='button' class='btn btn-primary btn-circle'>"+pageNo+"</button></a>&nbsp;";
						}
						loop++;
						pageNo++;
					}// end of while
					
					// 다음 만들기
					if(!(pageNo > totalPage)) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:showlib1NoticeList(\""+(pageNo)+"\");'><button type='button' class='btn btn-primary btn-circle'>></button></a>&nbsp;";
					}					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else {
					// 댓글이 없는 경우
					$("#pageBar").empty();
				}
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax
  	}// end of makePageBarLib1(currentShowPageNo)
  	// 도서관장,사서 페이징처리(검색이 있을 경우)
  	function makePageBarLib1(currentShowPageNo, colname, search) {
  		var form_data = {sizePerPage:"10",
  						"COLNAME":colname,
  						"SEARCH":search};
  		
  		$.ajax({
  			url:"lib1getNotiecTotalPage.ana",
  			data:form_data,
  			type:"GET",
  			dataType:"JSON",
  			success:function(json){
  				if(json.TOTALPAGE > 0) {  					
					var totalPage = json.TOTALPAGE;
					var pageBarHTML = "";
					var blockSize = 5;
					var loop = 1;
					var pageNo = Math.floor((currentShowPageNo - 1)/blockSize)*blockSize+1;
					
					// 이전 만들기
					if(pageNo != 1) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:goSearchlib1(\""+(pageNo-1)+"\");'><button type='button' class='btn btn-primary btn-circle'><</button></a>&nbsp;";
					}
					while(!(loop>blockSize || pageNo>totalPage)) {
						if(pageNo == currentShowPageNo) {
							pageBarHTML += "&nbsp;<button type='button' class='btn btn-primary btn-circle'><span style='color:#ff9999; font-size:12pt; font-weight: bold; text-decoration:underline;'>"+pageNo+"</span></button>&nbsp;";
						}
						else {
							pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:goSearchlib1(\""+pageNo+"\");'><button type='button' class='btn btn-primary btn-circle'>"+pageNo+"</button></a>&nbsp;";
						}
						loop++;
						pageNo++;
					}// end of while
					
					// 다음 만들기
					if(!(pageNo > totalPage)) {
						pageBarHTML += "&nbsp;<a style='color:white;' href='javascript:goSearchlib1(\""+(pageNo)+"\");'><button type='button' class='btn btn-primary btn-circle'>></button></a>&nbsp;";
					}					
					$("#pageBar").empty().html(pageBarHTML);
					pageBarHTML = "";
				}
				else {					
					$("#pageBar").empty();
				}
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error );
			}// end of error
  		});// end of $.ajax
  	}// end of makePageBarLib1(currentShowPageNo)
  	
  	function searchKeep1() {
  		if(${search != null && search != "" && search != "null"}) {
  			$("#colname").val("${colname}");
			$("#search").val("${search}");
  		}
  	}// end of searchKeep()
  	
  	
  	
  	function goView(idx, gobackURL) {
  		var frm = document.goViewFrm;
  		frm.idx.value = idx; 
  		frm.gobackURL.value = gobackURL;
  		
  		frm.method = "GET";
  		frm.action = "noticeView.ana";
  		frm.submit();
  	}
  	
  	
  </script>
  
</head>
<body>

<c:if test="${sessionScope.loginAdmin.adminid != null}">
<div class="container">
  <h2>공지사항 <button class="button button-neutral" onClick="javascript:location.href='noticeWrite.ana'">공지사항 등록</button></h2>
	
   	<div class="col-sm-12 pull-center well">
       	<form name="searchFrm" class="form-inline" onsubmit="return false">              
			<select name="colname" id="colname" class="form-control">
				<option value="name">성명</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>	
				<option value="adminid_fk">관리자ID</option>
				<option value="libid_fk">도서관장ID</option>
			</select>
                   
			<div class="input-group custom-search-form">
				<input type="text" name="search" id="search" class="form-control" placeholder="Search...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onClick="goSearch('1');">
						<i>검색</i>
					</button>
				</span>
			</div>									
       	</form>
   	</div>
   	
   	<%-- <a href="<%=request.getContextPath() %>/noticeWrite.ana"><button>공지사항 등록</button></a> --%>
	         
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>ID</th>                        
        <th>성명</th>
        <th>제목</th>
        <th>날짜</th>
        <th style="text-align: center;">조회수</th>
        <th style="text-align: center;">파일</th>
        <th style="text-align: center;">크기(bytes)</th>
      </tr>
    </thead>
    <tbody id="result"></tbody>
  </table>
  <ul>
  <div id="pageBar" style="height: 50px; text-align: center;"></div>
  </ul>
  
</div>
</c:if>


<c:if test="${sessionScope.loginLibrarian.libid != null}">
<div class="container">
  <h2>공지사항 <c:if test="${sessionScope.loginLibrarian.status == 1}"><button class="button button-neutral" onClick="javascript:location.href='noticeWrite.ana'">공지사항 등록</button></c:if></h2> 
   	<div class="col-sm-12 pull-center well">
       	<form name="searchFrm" class="form-inline" onsubmit="return false">              
			<select name="colname" id="colname" class="form-control">
				<option value="name">성명</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>	
				<option value="adminid_fk">관리자ID</option>
				<option value="libid_fk">도서관장ID</option>
			</select>
                   
			<div class="input-group custom-search-form">
				<input type="text" name="search" id="search" class="form-control" placeholder="Search...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onClick="goSearchlib1('1');">
						<i>검색</i>
					</button>
				</span>
			</div>									
       	</form>
   	</div>
   	
   	<%-- <a href="<%=request.getContextPath() %>/noticeWrite.ana"><button>공지사항 등록</button></a> --%>
	         
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>ID</th>                        
        <th>성명</th>
        <th>제목</th>
        <th>날짜</th>
        <th style="text-align: center;">조회수</th>
        <th style="text-align: center;">파일</th>
        <th style="text-align: center;">크기(bytes)</th>
      </tr>
    </thead>
    <tbody id="resultLib1"></tbody>
  </table>
  <ul>
  <div id="pageBar" style="height: 50px; text-align: center;"></div>
  </ul>
</div>
</c:if>


<form name="goViewFrm">
	<input type="hidden" name="idx" />
	<input type="hidden" name="gobackURL" />
</form>


</body>
</html>



  