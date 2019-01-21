<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<style>
ul.sideinfo {
	list-style: none;
	font-size: 10pt;
	font-family:'NanumGothic';
}

div.sideHeader {
	background-color: #d9d9d9;
	padding-left:5px;
	padding:3px;
	height:26px;
	font-family:'NanumGothicBold'
}

.sideText{ color:black;
			cursor:pointer;}
.sideText:hover {
	text-decoration: underline;
}

.multiselect:hover{
	color:navy;
}

input[type="checkbox"].sideli{
	width:14pt;
	height:14pt;           
}
#btnFindBook{padding: .2em .4em;
	font-size:13pt;
    background-color: #0d55a2;
    color: #fff;
    border-radius: 3px;
    text-decoration: none;
    border: none;}

</style>
<script>

	$(document).ready(function(){
		         
		$(".sideli").click(function(){
			
			if($(this).is('checked')){
				var property = $(this).val();
				console.log(property);
				if($(this).hasClass('library')){
					var librarycode = $("#library").val();
					librarycode = librarycode+","+property;
					$("#library").val(librarycode);
				}	
			}
			
			
		});
		
	});
	
	function findBookListbyLibrary(libcode){
		
	}
	
	function findBookListbyLanguage(lcode){
		
	}
	
	function findBookListbyCategory(ccode){
		
	}
	
	function findBookListbyField(fcode){
		
	}
</script>	

	<table class="table table-striped">
	<a style="width:50px;" href="<%=request.getContextPath() %>/logout.ana">로그아웃</a>
		<thead>
			<tr>
				<th>웃대의 몰락</th>
				<th>ABCD</th>
				<th>가나다라</th>
				<th>김국하</th>
				<th>웃긴대학</th>
				<th>2019-01-08</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>       
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
				<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			<tr>
				<td>제목1</td>
				<td>도서번호1</td>
				<td>ISBN1</td>
				<td>저자1</td>
				<td>출판사1</td>
				<td>등록날짜1</td>
			</tr>
			
		</tbody>
	</table>

	<div style="font-weight:bold; font-family: 'NanumGothicBold'; border: 0px solid red; color:#0088cc; font-size:15pt;">조회 조건 
		<button type="button" id="btnFindBook" style="margin-left:10px;" onClick="findBookListBysidebar();">검색</button>  </div>
	         
	<div style="float: left; border: 1px solid gray; margin-top:5pt;">        
		<div style="width: 230px;">
			<div>
				<div class="sideHeader" style="">도서관</div>
			</div>    
			<ul class="sideinfo">
				<li><input type="checkbox" name="library" id="library1" class="library sideli" value="100"/><a onClick="findBookListbyLibrary('도서관코드');"  class="sideText">종로(도서권수)</a></li>
				
				<li><input type="checkbox" name="library" id="library2" class="library sideli" value="200"/><a onClick="" class="sideText">마포</a></li>
				
				<li><input type="checkbox" name="library" id="library3" class="library sideli" value="300"/><a onClick="" class="sideText">구리</a></li>
				
				<li><input type="checkbox" name="library" id="library4" class="library sideli" value="400"/><a onClick="" class="sideText">화정</a></li>
				
				<li><input type="checkbox" name="library" id="library5" class="library sideli" value="500"/><a onClick="" class="sideText">수유</a></li>
			</ul>
			
		</div>
		<div>
			<div>
				<div class="sideHeader">언어</div>
			</div>
				<ul class="sideinfo">
				<li><input type="checkbox" name="language" id="language1" class="language sideli" value="A1"/><a onClick="findBookListbyLanguage('언어코드');" class="sideText">한국어(도서권수)</a></li>
				
				<li><input type="checkbox" name="language" id="language2" class="language sideli" value="A2"/><a onClick="" class="sideText">일본어</a></li>
				
				<li><input type="checkbox" name="language" id="language3" class="language sideli" value="B1"/><a onClick="" class="sideText">영어</a></li>
				
				<li><input type="checkbox" name="language" id="language4" class="language sideli" value="B2"/><a onClick="" class="sideText">중국어</a></li>
				
				<li><input type="checkbox" name="language" id="language5" class="language sideli" value="C3"/><a onClick="" class="sideText">프랑스어</a></li>
			</ul>
		</div>
		<div>
			<div>
				<div class="sideHeader">종류</div>
			</div>
				<ul class="sideinfo">
				<li><input type="checkbox" name="category" id="category1" class="category sideli" value="A01"/><a onClick="findBookListbyCategory('카테고리코드');" class="sideText">소설(도서권수)</a></li>
				
				<li><input type="checkbox" name="category" id="category2" class="category sideli" value="A02"/><a onClick="" class="sideText">수필</a></li>
				<li><input type="checkbox" name="category" id="category5" class="category sideli" value="A03"/><a onClick="" class="sideText">에세이</a></li>				
				<li><input type="checkbox" name="category" id="category3" class="category sideli" value="A04"/><a onClick="" class="sideText">시</a></li>
				
				<li><input type="checkbox" name="category" id="category4" class="category sideli" value="A05"/><a onClick="" class="sideText">사전/논문</a></li>
				
				<li><input type="checkbox" name="category" id="category5" class="category sideli" value="A06"/><a onClick="" class="sideText">동화책</a></li>
				<li><input type="checkbox" name="category" id="category5" class="category sideli" value="A07"/><a onClick="" class="sideText">잡지</a></li>
				<li><input type="checkbox" name="category" id="category5" class="category sideli" value="A08"/><a onClick="" class="sideText">만화책</a></li>
				<li><input type="checkbox" name="category" id="category5" class="category sideli" value="A09"/><a onClick="" class="sideText">문제집</a></li>
				
			</ul>
		</div>
		<div>
			<div>
				<div class="sideHeader">분야</div>
			</div>
				<ul class="sideinfo">
				<li><input type="checkbox" name="field" id="field1" class="field sideli" value="000"/><a onClick="findBookListbyField('분야 코드');" class="sideText">총류(사전)</a></li>
				
				<li><input type="checkbox" name="field" id="field2" class="field sideli" value="100"/><a onClick="" class="sideText">철학,심리학(도서권수)</a></li>
				
				<li><input type="checkbox" name="field" id="field3" class="field sideli" value="200"/><a onClick="" class="sideText">종교</a></li>
				
				<li><input type="checkbox" name="field" id="field4" class="field sideli" value="300"/><a onClick="" class="sideText">사회과학</a></li>
				
				<li><input type="checkbox" name="field" id="field5" class="field sideli" value="400"/><a onClick="" class="sideText">자연과학</a></li>
				<li><input type="checkbox" name="field" id="field5" class="field sideli" value="500"/><a onClick="" class="sideText">기술과학</a></li>
				<li><input type="checkbox" name="field" id="field5" class="field sideli" value="600"/><a onClick="" class="sideText">예술</a></li>
				<li><input type="checkbox" name="field" id="field5" class="field sideli" value="700"/><a onClick="" class="sideText">언어</a></li>
				<li><input type="checkbox" name="field" id="field5" class="field sideli" value="800"/><a onClick="" class="sideText">문학</a></li>
				<li><input type="checkbox" name="field" id="field5" class="field sideli" value="900"/><a onClick="" class="sideText">역사,지리</a></li>
			</ul>
		</div>

	</div>
</div>
</div>
	</div>

<form name="sidebar">
	<input type="hidden" name="library" id="library" value=""/>
	<input type="hidden" name="language" id="language" value=""/>
	<input type="hidden" name="category" id="category" value=""/>
	<input type="hidden" name="field" id="field" value=""/>
</form>
