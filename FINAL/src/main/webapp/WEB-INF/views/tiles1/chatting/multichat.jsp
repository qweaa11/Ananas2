<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<style>
.background {
	border:1px solid #ccc;
	margin-left:100px;
	background-color: #d9d9d9;
	padding: 15px;
	width: 600px; }
	
.msgbox {
	width: auto;
	height: 600px; }
    
.msg {
	display: inline-block;
	width: 300px;
	height: 45px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857;
	color: #555555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-top-radius: 4px;
	border-bottom-radius: 4px; }

.send {
	background-color: #4d4d4d;
	display: inline-block;
	margin-bottom: 0;
	color: #ffffff;
	font-weight: normal;
	text-align: center;
	vertical-align: middle;
	border: 0px solid transparent;
	height: 45px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857;
	border-top-radius: 4px;
	border-bottom-radius: 4px; } 

.close {
	background-color: #4d4d4d;
	display: inline-block;
	margin-bottom: 0;
	color: #ffffff;
	font-weight: normal;
	text-align: center;
	vertical-align: middle;
	border: 0px solid transparent;
	height: 46px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857;
	border-top-radius: 4px;
	border-bottom-radius: 4px;
}
	 
.talk {
	/* display:block; */
	position: relative;
	padding: 10px;
	/* margin: 1em 0 1em; */
	color: black;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	width: auto; }

.talk.other {
	/* display:block;	 */
	margin-left: 30px;
	border: 5px solid #fff;
	background: #fff; }

.talk.me {
	text-align:right;
	/* display:block; */
	margin-right: 30px;
	border: 5px solid #ffec42;
	background-color: #ffec42; }

.talk:before {
	content: "";
	position: absolute;
	bottom: -20px; 
	left: 40px;
	border-width: 20px 20px 0;
	border-style: solid;
	border-color: #5a8f00 transparent;
	/* reduce the damage in FF3.0 */
	display: block; 
	width: 0; }

/* creates the smaller  triangle */
.talk:after {
	content: "";
	position: absolute;
	bottom: -13px; 
	left: 47px;
	border-width: 13px 13px 0;
	border-style: solid;
	border-color: #fff transparent;
	/* reduce the damage in FF3.0 */
	display:block;
	width: 0; }

/* creates the larger triangle */
.talk.other:before {
	top: 10px;
	bottom: auto;
	left: -30px;
	border-width: 15px 30px 15px 0;
	border-color: transparent #fff; }

/* creates the smaller  triangle */
.talk.other:after {
	top: 16px;
	bottom: auto;
	left: -21px;
	border-width: 9px 21px 9px 0;
	border-color: transparent #fff; }

.talk.me:before {
	top: 10px;
	/* controls vertical position */
	bottom: auto;
	left: auto;
	right: -30px;
	/* value = - border-left-width - border-right-width */
	border-width: 15px 0 15px 30px;
	border-color: transparent #ffec42; }

.talk.me:after {
	top: 16px;
	bottom: auto;
	left: auto;
	right: -21px;
	border-width: 9px 0 9px 21px;
	border-color: transparent #ffec42; }
	
</style>

<script type="text/javascript" src="resources/js/json2.js"></script>
<!-- JSON.stringify() 는 값을 그 값을 나타내는 JSON 표기법의 문자열로 변환해주는 것인데 이것을 사용하기 위해서는 json2.js 가 필요하다. -->

<script type="text/javascript" >

    // === !!! WebSocket 통신은 스크립트로 작성하는 것이다. !!! === //
    $(document).ready(function(){
        
        var url = window.location.host; // 웹브라우저의 주소창의 포트까지 가져옴 #<websocket:mapping path="/chatting/multichatstart.action" handler="echoHandler" />
    //  alert("url : " + url);
    //  결과값  url: 192.168.50.53:9090 #자기 IP주소
    	    
    	var pathname = window.location.pathname; // '/'부터 오른쪽에 있는 모든 경로
    // 	alert("pathname : " + pathname);
    //  결과값  pathname : /board/chatting/multichat.action
    	 	
    	var appCtx = pathname.substring(0, pathname.lastIndexOf("/"));  // "전체 문자열".lastIndexOf("검사할 문자"); #마지막 슬래시인 15의 앞까지만 읽어온다.
    // 	alert("appCtx : " + appCtx);
    //  결과값  appCtx : /board/chatting
    	 	
    	var root = url+appCtx;
    // 	alert("root : " + root);
    //  결과값   root : 192.168.50.53:9090/board/chatting
   	
    	var wsUrl = "ws://"+root+"/multichatstart.ana"; // #웹소켓 통신은 ws, 웹은 http이다. -> websocketContext.xml에서 <websocket:mapping path="/chatting/multichatstart.action" handler="echoHandler" />을 복사해서 /multichatstart.action을 가져온다.
       	var websocket = new WebSocket(wsUrl);  //  /WEB-INF/web.xml 에 가서 appServlet 의 contextConfigLocation 을 수정한다. 
     // var websocket = new WebSocket("ws://192.168.50.53:9090/board/chatting/multichatstart.action");
        
     // alert(wsUrl);
    	
    	// >>>> === Javascript WebSocket 이벤트 정리 === <<<< 
	    //      	onopen    ==>  WebSocket 연결
	    //      	onmessage ==>  메시지 수신
	    //      	onerror   ==>  전송 에러 발생
	    //      	onclose   ==>  WebSocket 연결 해제
    	
    	var messageObj = {}; // 자바스크립트에서 객체 생성함.
    	
	    // === 웹소켓에 최초로 연결이 되었을 경우에 실행되어지는 콜백함수 ===
    	websocket.onopen = function() {
    	//	alert("웹소켓 연결됨!!");
    		$("#chatStatus").text("정보: 채팅방에 접속 되었습니다.");
   			var userList = $("#userList").val();

    	/*	
            messageObj = {};  // 초기화
            messageObj.message = "채팅방에 <span style='color: red;'>입장</span>했습니다";
            messageObj.type = "all";
            messageObj.to = "all";
        */    
            // 또는
            messageObj = { message : "채팅방에 <span style='color: red;'>입장</span>했습니다"
        		     	 , type : "all"
        		     	 , to : "all" };  // 자바스크립트에서 객체의 데이터값 초기화
   			
        		     	 
            websocket.send(JSON.stringify(messageObj));
            
            // JSON.stringify() 는 값을 그 값을 나타내는 JSON 표기법의 문자열(String)로 변환한다 #객체가 아니라 문자열로 바꿈                   
            /*
				JSON.stringify({});                  // '{}'
				JSON.stringify(true);                // 'true'
				JSON.stringify('foo');               // '"foo"'
				JSON.stringify([1, 'false', false]); // '[1,"false",false]'
				JSON.stringify({ x: 5 });            // '{"x":5}'
             */
        };
    	
    	// === 메시지 수신 콜백함수
        websocket.onmessage = function(evt) {
            $("#chatMessage").append(evt.data);
            $("#chatMessage").append("<br/>");
            $("#chatMessage").scrollTop(99999999);
        };
        
        // === 웹소캣 연결 해제 콜백함수
        websocket.onclose = function() {
            // websocket.send("채팅을 종료합니다.");
        }
         
        
        $("#message").keydown(function (key) {
             if (key.keyCode == 13) {
                $("#sendMessage").click();
             }
          });

        
        
        $("#sendMessage").click(function() {
            if( $("#message").val() != "") { // #내용물이 있으면
            	
            	// ==== 자바스크립트에서 replace를 replaceAll 처럼 사용하기 ====
        		// 자바스크립트에서 replaceAll 은 없다.
        		// 정규식을 이용하여 대상 문자열에서 모든 부분을 수정해 줄 수 있다.
        		// 수정할 부분의 앞뒤에 슬래시를 하고 뒤에 gi 를 붙이면 replaceAll 과 같은 결과를 볼 수 있다.
                var message = $("#message").val();
                message = message.replace(/<script/gi, "&lt;script"); 
            	
                messageObj = {}; // #만들었던 객체를 다시 초기화해온 것.
                messageObj.message = message;
                messageObj.type = "all";
                messageObj.to = "all";
                 
                var to = $("#to").val();
                if ( to != "" ) { // #귓속말을 읽어와서 비어있지 않으면
                    messageObj.type = "one";
                    messageObj.to = to;
                }
                 
                websocket.send(JSON.stringify(messageObj));
                // JSON.stringify() 는 값을 그 값을 나타내는 JSON 표기법의 문자열로 변환한다
                
                $("#chatMessage").append("<p class='talk me'>[나]: " + message + "</p>");
                $("#chatMessage").append("<br/>");
                $("#chatMessage").scrollTop(99999999);
                $("#message").val("");
            }
        });
    });
    
</script>
</head>
<body>
<div class="col-sm-8 background" >
	<div id="chatStatus"></div><br/>
	<div class="msgbox" id="chatMessage" style="overFlow: auto; max-height: 500px;"></div> <!-- style="overFlow: auto; max-height: 500px;" -->
	<input type="text" id="to" style="margin-bottom:10px; border:1px solid #ccc;" placeholder="귓속말대상"/><br/>
	<input class="msg" type="text" id="message" placeholder="메시지 내용"/>
    <input class="send" type="button" id="sendMessage" value="보내기" /> 
    <input class="close" type="button" onClick="javascript:location.href='<%=request.getContextPath() %>/index.ana'" value="채팅방나가기" />
</div>

</body>
</html>