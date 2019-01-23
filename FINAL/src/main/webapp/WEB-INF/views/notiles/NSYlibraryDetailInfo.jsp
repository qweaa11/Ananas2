<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- DAUM MAP API -->
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89cb38301850de4bd9d2de6028e68d65&libraries=services"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		// ==== 지도에 표시할 좌표값 ====
		var y = ${libraryDetailInfo.y};
		var x = ${libraryDetailInfo.x};
		
		// ==== DAUM MAP API======================================================
	    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	    mapOption = {
	        center: new daum.maps.LatLng(y, x), // 지도의 중심좌표
	        level: 5 // 지도의 확대 레벨
	    };
	    //지도를 미리 생성
	    var map = new daum.maps.Map(mapContainer, mapOption);
	    //주소-좌표 변환 객체를 생성
	    var geocoder = new daum.maps.services.Geocoder();
	    //마커를 미리 생성
	    var marker = new daum.maps.Marker({
	        position: new daum.maps.LatLng(y, x),
	        map: map
	    });
	 	// ==== DAUM MAP API======================================================
		
	});
	
</script>
<style type="text/css">
	
</style>
<title>==== 도서관상세정보 ====</title>
</head>
<body class="w3-black">
<div class="w3-display-container">
	<img src="<%=request.getContextPath()%>/resources/img/img_lights.jpg"  style="width:100%;height:200px">
	<div class="w3-display-middle w3-large">
		<h1 class="w3-jumbo w3-text-white w3-wide"><b>${libraryDetailInfo.libname}</b></h1>
	</div>
	<div class="w3-display-bottomright w3-container">
		<p class="w3-text-white w3-large">도서관번호 : ${libraryDetailInfo.idx} / 도서관코드 : ${libraryDetailInfo.libcode}</p>
	</div>
</div>

<div class="w3-container w3-deep-orange w3-large w3-padding-8">
	<p><span class="w3-xlarge">.Tel.</span> ${libraryDetailInfo.tel}</p>
</div>

<div class="w3-container w3-deep-purple w3-large w3-padding-8" >
	<p><span class="w3-xlarge">.Address.</span> [${libraryDetailInfo.post}]${libraryDetailInfo.addr1}(${libraryDetailInfo.addr2})</p>
</div>
<br>

<div style="width:80%; margin-left:10%;">
	<div class="w3-container w3-blue w3-large w3-padding-8">
		<p><span class="w3-xlarge">도서관 사진</span></p>
	</div>
	<div class="w3-display-container">
		<img src="resources/NSYfiles/${libraryDetailInfo.fileName}" style="width:100%; height:300px;" >
	</div>
</div>
<br>

<div style="width:80%; margin-left:10%;">
	<div class="w3-container w3-green w3-large w3-padding-8">
		<p><span class="w3-xlarge">지도 표시</span></p>
	</div>
	<div class="w3-display-container">
		<div id="map" style="width:100%; height:300px; margin:auto; " style="align:center;"></div>		
	</div>
</div>
<br>

<div align="center" style="margin-bottom:10%; color:black;" >
	<button type="button" style="cursor:pointer;" onClick="self.close();" value="">닫기</button>
</div>

</body>
</html>