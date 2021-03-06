<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    

<style>
* {
  box-sizing: border-box;
}

input[type=text], select, textarea {
  width: 90%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 0;
  display: inline-block;
}

input[type=button] {   
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  margin-left: 76%;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}        

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
}

.col-20 {
  float: left;
  margin-left: 5%;
  width: 10%;
  margin-top: 6px;
}

.col-70 {
  float: left;
  margin-left: 5%;  
  width: 70%;
  margin-top: 6px;
}

.col-10 {
  float: left;
  margin-left: 5%;
  width: 10%;
  margin-top: 6px;
}

.col-30 {
  float: left;
  margin-left: 5%;  
  width: 30%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}



@media screen and (max-width: 550px) {
  .col-20, .col-70, input[type=submit] {
    width: 80%;
    margin-top: 0;
  }
}
</style>


<script type="text/javascript">

	$(document).ready(function(){
		
		var libname =  $("#library").val();
		//console.log(libname);
		findRecipient(libname);
		
		$("#library").change(function(){
			
			var libname =  $("#library").val();
			//console.log(libname);
			findRecipient(libname);
		});
		
		$("#sendMessage").click(function(){
			
			var loginLibrarian = "${sessionScope.loginLibrarian.libid}";
			
			var frm = document.messageFrm;
			frm.library.value = $("#library").val();
			frm.loginLibrarian.value = loginLibrarian;
			frm.recipient.value = $("#recipient").val();
			frm.messageTitle.value = $("#messageTitle").val();
			frm.message.value = $("#message").val();
			frm.action = "sendMessage.ana";
			frm.method = "POST";
			frm.submit();
		});
	
		if(${flag1 !=null}){
			$("#receiveMsg").addClass("active");
			$("#receiveMsgTab").click();
			$("#sendMessageFrm").removeClass("active");
			$("#sendMessageFrm").removeClass("in");
			$("#receiveMessage").addClass("active");
			$("#receiveMessage").addClass("in");
		}
		
		if(${flag2 !=null}){
			$("#sendMsg").addClass("active");
			$("#sendMsgTab").click();
			$("#receiveMessage").removeClass("active");
			$("#receiveMessage").removeClass("in");
			$("#sendMessageFrm").addClass("active");
			$("#sendMessageFrm").addClass("in");
		}
		
		
	});// end of 
	
	function findRecipient(libname) {
		
		var form_data = {"libname":libname}
		
		$.ajax({
			
			url:"findRecipient.ana",
			type:"GET",
			data:form_data,
			dataType:"JSON",
			success: function(json){
				
				var recipientList = "";
				/* var libname = "";
				var position = "";
				var name = "";
				var librarianid = ""; */
				
				$.each(json, function(entryIndex, entry){
					
					if(json.length != 0){
						
						recipientList += "<option>"+entry.POSITION+" -- "+entry.NAME+"["+entry.LIBRARIANID+"]"+"</option>";
						
					}
					else {
						
						recipientList = "해당 도서관에 등록 된 사서및 관리자가 없습니다.";
					}
					
					$("#recipient").html(recipientList);
					
				});
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			} // error
		});
	}

</script>



<div class="container">
  <h2>메세지</h2>

  <ul class="nav nav-pills">
    <li id="send"><a data-toggle="tab" href="#sendMessageFrm">메세지 보내기</a></li>
    <li id="receiveMsg"><a data-toggle="tab" id="receiveMsgTab" href="#receiveMessage">받은 메세지</a></li>
    <li id="sendMsg"><a data-toggle="tab" id="sendMsgTab" href="#mailbox">보낸 메세지</a></li>
  </ul>

  <div class="tab-content">
    <div id="sendMessageFrm" class="tab-pane fade in active">
    	<h3></h3>
	    <div class="panel panel-primary">
	    	<div class="panel-heading">
			</div>
	    	<div class="panel-body">
	   			<div class="row">
				    <div class="col-10">
				      <label for="country">도서관</label>
				    </div>
				    <div class="col-30">
				      <select id="library" name="library">
				      	<c:forEach var="library" items="${libraryName}">
				      		<option value="${library.LIBNAME}">${library.LIBNAME}</option>
				      	</c:forEach>
				      </select>
				    </div>
			  	</div>
			  	
				<div class="row">
				  <div class="col-10">
				    <label for="lname">사서 아이디</label>
				  </div>
				  <div class="col-30">
				    <select id="recipient" name="recipient">
				    </select>
				  </div>
				</div>
				
				<div class="row">
			    	<div class="col-10">
			      		<label for="fname">제목</label>
			    	</div>
				    <div class="col-30">
				    	<input type="text" id="messageTitle" name="messageTitle" placeholder="제목">
				    </div>
			  	</div>
				
				<div class="row">
				  <div class="col-20">
				    <label for="subject">내용</label>
				  </div>
				  <div class="col-70">
				    <textarea id="message" name="message" placeholder="보낼 메세지 내용..." style="height:200px"></textarea>
				  </div>
				</div>
				
				<div class="row">
				  <input type="button" id="sendMessage" name="sendMessage" value="보내기" />
				</div>
			</div>
			
			<form name="messageFrm">
				<input type="hidden" name="library" value=""/>
				<input type="hidden" name="loginLibrarian" value=""/>
				<input type="hidden" name="recipient" value=""/>
				<input type="hidden" name="messageTitle" value=""/>
				<input type="hidden" name="message" value=""/>
			</form>
	    </div>
    </div>
    

    
    
    <div id="receiveMessage" class="tab-pane fade">
    	<h3></h3>
	    <div class="panel panel-primary">

	    	<div class="panel-heading">${sessionScope.loginLibrarian.libid}님의 받은 메세지</div>
	    	<jsp:include page="./receiveMessege.jsp"></jsp:include>

	    </div>
    </div>
    
    <div id="mailbox" class="tab-pane fade">
    	<h3></h3>
	    <div class="panel panel-primary">
	    	<div class="panel-heading">${sessionScope.loginLibrarian.libid}님의 보낸 메세지</div>
	    	<jsp:include page="./sendMessege.jsp"></jsp:include>

	    </div>
    </div>
  </div>
</div>

</body>
</html>
 