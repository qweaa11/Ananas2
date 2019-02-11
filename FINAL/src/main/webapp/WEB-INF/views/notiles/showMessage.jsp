<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  

<style>
	
   .long {width: 470px;}
   .short {width: 120px;} 
   a:hover { cursor: pointer;}
  
</style>    

<script>
	
	
	$(document).ready(function(){
		var idx = ${idx};
		var title = "${mvo.title}";
		console.log("idx: "+ idx);
		console.log("title: "+ title);
		
	}); // end of $(document).ready(function(){});-------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	

</script>

<body>

<div class="container">
	<div class="row">
		<div class="panel panel-default">
        <div class="panel-heading clearfix">
          <h3 class="panel-title">메세지</h3>
        </div>
        <div class="panel-body">
            <form role="form" class="form-horizontal">
               
               <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">제목</label>
                  <div class="col-sm-10">${mvo.title }</div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2" for="inputTo">보낸날짜</label>
                  <div class="col-sm-10">${mvo.senddate }</div>
                </div>
                <div class="form-group">
                  <label class="col-sm-12" for="inputBody">내용</label>
                   <div class="col-sm-10">${mvo.message }</div>
                </div>
            </form>
        </div>
      </div>
	</div>
</div>
</body>
</html>