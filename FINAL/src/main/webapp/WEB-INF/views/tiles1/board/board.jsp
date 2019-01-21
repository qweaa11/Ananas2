<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String cxtPath = request.getContextPath();%>

<style type="text/css">
.subjectstyle {font-weight: bold;
    	           color: navy;
    	           cursor: pointer; }
</style>

<script type="text/javascript">

$(document).ready(function(){
	$('.search-panel .dropdown-menu').find('a').click(function(e) {		
		e.preventDefault();
		
		var param = $(this).attr("href").replace("#","");		
		var concept = $(this).text();
		
		$('.search-panel span#search_concept').text(concept);		
		$('.input-group #search_param').val(param);
	});
		
		$(".subject").bind("mouseover", function(event){
			 var $target = $(event.target);
			 $target.addClass("subjectstyle");
		});
		  
		$(".subject").bind("mouseout", function(event){
			 var $target = $(event.target);
			 $target.removeClass("subjectstyle");
		});
		
		
	
});

function goView(idx, gobackURL) {
	
	var frm = document.goViewFrm;
	frm.idx.value = idx;
	frm.gobackURL.value = gobackURL;
	
	frm.method = "GET";
	frm.action = "BSBview.ana";
	frm.submit();
	
}	
</script>


<div class="container">
	<div class="row">		
        
	<div class="col-md-12">
		<h4><img alt="boardlist" src="resources/img/boardlist.png" width="30px;" height="30px;">&nbsp;<span style="font-weight: bolder;">자유 게시판</span></h4>
	<div class="table-responsive">
        
   <div class="container" style="margin-bottom: 30px;">
    <div class="row">    
        <div style="margin-left: 650px;" class="col-xs-5 col-xs-offset-2">
		    <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">검색 조건</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#all">전체</a></li>
                      <li><a href="#title">제목</a></li>
                      <li><a href="#author">작성자</a></li>
                    </ul>
                </div>
                <input type="hidden" name="colname" value="all" id="colname">         
                <input type="text" class="form-control" name="search" id="search" placeholder="검색">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
        </div>
	</div>
</div>


                
<table id="mytable" class="table table-bordred table-striped">
                   
	<thead>                   
		<th>번호</th>
		<th style="width: 60%">제목</th>
		<th>게시일자</th>
		<th>작성자</th>
		<th>조회수</th>                                                              
		<th>삭제</th>
	</thead>
	
   <tbody> 
   	<c:forEach var="boardvo" items="${boardList}">
	 	<tr>
		    <td>${boardvo.idx}</td>
		    <td><span class="subject" onClick="goView('${boardvo.idx}','${gobackURL}');">${boardvo.subject}</span></td>
		    <td>${boardvo.regDate}</td>
		    <td>${boardvo.name}</td>
		    <td>${boardvo.readCount}</td>
		    <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
	    </tr>    
	    
    </c:forEach>
    	     
	   <tr>
			<td colspan="7" style="background-color: #ffffff;">
				<a href="<%=cxtPath%>/add.ana"><button class="btn btn-primary btn-xs" data-title="Edit"  data-target="#edit" style="float: right;" ><span class="glyphicon glyphicon-pencil">&nbsp;글쓰기</span></button></a>
			</td>		    
	   </tr>
    
    </tbody>
        
</table>

    <form name="goViewFrm">
		<input type="hidden" name="idx">
		<input type="hidden" name="gobackURL">
	</form>
	

	<!-- ==== #121. 페이지바 보여주기 ==== -->
	<div align="center">	
		<span id="pagebar">${pagebar}</span>			
	</div>
	


                
            </div>
            
        </div>
	</div>
</div>



<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
      </div>
          <div class="modal-body">
          <div class="form-group">
        <input class="form-control " type="text" placeholder="Mohsin">
        </div>
        <div class="form-group">
        
        <input class="form-control " type="text" placeholder="Irshad">
        </div>
        <div class="form-group">
        <textarea rows="2" class="form-control" placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>
    
        
        </div>
      </div>
          <div class="modal-footer ">
        <button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>
      </div>
        </div>
    <!-- /.modal-content --> 
  </div>
      <!-- /.modal-dialog --> 
    </div>
   
    
    
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
      </div>
          <div class="modal-body">
       
       <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
       
      </div>
        <div class="modal-footer ">
        <button type="button" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
      </div>
        </div>
    <!-- /.modal-content --> 
  </div>
      <!-- /.modal-dialog --> 
    </div>
    
   