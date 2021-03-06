<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<style>
ul.sideinfo {
	list-style: none;
	font-size: 8pt;
	font-family:'NanumGothic';
}
table{
	text-align: center;
}
th{ font-size:16pt;
	text-align:center;}

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
.BookInfo:hover{
	cursor:pointer;
	text-weight:bold;
	color:#ffaa00;
}


input[type="checkbox"].sideli{
	width:11pt;
	height:11pt;           
}
#btnFindBook{padding: .2em .4em;
	font-size:13pt;
    background-color: #0d55a2;
    color: #fff;
    border-radius: 3px;
    text-decoration: none;
    border: none;}

#section1{
	max-height: 1400px;
	overflow-y:scroll;
}  
	           
div.searchbar input[type=text] {
  padding: 10px;
  font-size: 13px;
  border: 1px solid grey;
  width: 180px;
  background: #f1f1f1;
}

div.searchbar button {
  width: 30px;
  padding: 10px;
  background: #2196F3;
  color: white;
  font-size: 13px;
  border: 1px solid grey;          
  border-left: none;
  cursor: pointer;
}

div.search button:hover {
  background: #0b7dda;
}


</style>

<script>
	$(document).ready(function(){
		         
		<%-- 검색창 엔터키 입력 --%>
		 $("#searchWord").keydown(function (key) {
	             if (key.keyCode == 13) {
	                $(".search").click();//엔터를 입력하면 검색버튼에 클릭 이벤트를 발생시킨다.
	             }
	         });
		
		<%-- 검색버튼(목록위) 클릭이벤트가 발생할때--%>
		$(".search").click(function(){
			
			if($("#searchWord").val().trim() == ""){
				
				alert("검색어를 입력해 주세요.");
				return;
			}
			$("input[type=checkbox]").prop('checked',false);//사이드바에 있는 체크된 조건들은 다 해제한다.
			var sort = $(".sort").val() 
			goBookBySearchBar(sort);//검색어 입력(목록위에있는거)을 이용한 목록 ajax 실행
			
		});
		
		<%-- 정렬조건을 바꿀시 --%>
		$(".sort").change(function(){
			
			$("#sortFrm").val($(this).val());
			
			if($("#searchWord").val().trim() ==""){
				//검색창에 글자가 없으면 사이드바 검색결과를 정렬.
				goBook();
			}else{								   
				//검색창에 글자가 있으면 검색창의 검색결과를 정렬
				goBookBySearchBar($(this).val());
			}
			
		});
		
		//처음 페이지에 왔을때 전체 목록을 보여주는 ajax 실행
		$("#sortFrm").val($(".sort").val());	
		goBook();
	});
			 
	<%-- 사이드바에서 해당 도서관을 클릭(체크박스 아님) 할경우 실행되는 메소드 --%>
	function findBookbyLibrary(libcode){
		$("#libraryFrm").val(libcode);
		$("#languageFrm").val("");
		$("#categoryFrm").val("");
		$("#fieldFrm").val("");
		$("#sortFrm").val($(".sort").val());
		$("#searchWord").val("");
		$("input[type=checkbox]").prop('checked',false);
		goBook();
		
	}
	
	<%-- 사이드바에서 해당 언어를 클릭(체크박스 아님) 할경우 실행되는 메소드 --%>
	function findBookbyLanguage(lcode){
		$("#libraryFrm").val("");
		$("#categoryFrm").val("");
		$("#fieldFrm").val("");
		$("#sortFrm").val($(".sort").val());
		$("#languageFrm").val(lcode);
		$("#searchWord").val("");
		$("input[type=checkbox]").prop('checked',false);
		goBook();
		
	}
	
	<%-- 사이드바에서 해당 종류을 클릭(체크박스 아님) 할경우 실행되는 메소드 --%>
	function findBookbyCategory(ccode){
		$("#libraryFrm").val("");
		$("#languageFrm").val("");
		$("#fieldFrm").val("");
		$("#sortFrm").val($(".sort").val());
		$("#categoryFrm").val(ccode);
		$("#searchWord").val("");
		$("input[type=checkbox]").prop('checked',false);
		goBook();
		
	}
	
	<%-- 사이드바에서 해당 분야을 클릭(체크박스 아님) 할경우 실행되는 메소드 --%>
	function findBookbyField(fcode){
		$("#libraryFrm").val("");
		$("#languageFrm").val("");
		$("#categoryFrm").val("");
		$("#sortFrm").val($(".sort").val());
		$("#fieldFrm").val(fcode);
		$("#searchWord").val("");
		$("input[type=checkbox]").prop('checked',false);
		goBook();
		
	}
	
	// 사이드바로 검색한 조건에 해당하는 책의 목록을 가져오는 메소드
	//가져올때 일련번호는 맨뒤의 각 권별 상세 번호는 가져오지 않는다.
	function findBookListBysidebar(){
		var library = "";
			$(".library").each(function(i){
				if($(this).prop("checked") == true){
					library += $(this).val()+"','";
				}
			});
			library = library.substring(0, library.length-3);
			
		var language = "";
		$(".language").each(function(i){
			if($(this).prop("checked") == true){
				language += $(this).val()+"','";
			}
		});
		language = language.substring(0, language.length-3);
		
		var category = "";
		$(".category").each(function(i){
			if($(this).prop("checked") == true){
				category += $(this).val()+"','";
			}
		});
		category = category.substring(0, category.length-3);
		
		var field = "";
		$(".field").each(function(i){
			if($(this).prop("checked") == true){
				field += $(this).val()+"','";
			}
		});
		field = field.substring(0, field.length-3);
		
		console.log("library ==>"+library);
		console.log("language ==>"+language);
		console.log("category ==>"+category);
		console.log("field ==>"+field);
		
		$("#libraryFrm").val(library);
		$("#languageFrm").val(language);
		$("#categoryFrm").val(category);
		$("#fieldFrm").val(field);
		$("#sortFrm").val($(".sort").val());
		$("#searchWord").val("");
		//ajax 실행 함수
		goBook();
		
		
		
	}
	
	//사이드바로 검색할때 사용되는 ajax 실행 함수
	function goBook(){
		
		var form_data= $("#sidebarFrm").serialize();//맨팉에 사이드바 검색용 폼을 serialize해온다.
		console.log($("#libraryFrm").val());
		$.ajax({
			
			url:"KKHfindBookBySidebar.ana",
			type:"GET",
			data:form_data,
			dataType:"JSON",
			success: function(json){
				
				var resultHTML = "";
				
				if(json.length <= 0){
					resultHTML+="<tr>"+
						"<td colspan='9'><h3  align='center'>조건에 맞는 상품이 없습니다</h3></td>"+
						"</tr>";
				}else{
					$.each(json,function(bookIndex,book){
						
						resultHTML += "<tr style='overflow-y:scroll; max-height:400px;' class='BookInfo' onClick='goBookDetail(\""+book.BOOKID+"\")'>"+
						"<td>"+(bookIndex+1)+"</td>"+
						"<td>"+book.BOOKID+"</td>"+
							"<td>"+book.TITLE+"</td>"+
							"<td>"+book.FNAME+"-"+book.GNAME+"</td>"+
							"<td>"+book.AUTHOR+"</td>"+       
							"<td>"+book.PUBNAME+"</td>"+
							"<td>"+book.LIBNAME+"</td>"+
							"<td>"+book.AGECODE+"</td>"+
							"<td>"+book.COUNT+"</td>"+
						"</tr>";
							
						});
				}
				
				//밑에 도서리스트를 보여주는 <tbody> 태그에 넣어준다.
				$("#displayBookList").empty().html(resultHTML);
				
			},error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }

		});
	}
	
	<%-- 검색창을 이용해 도서리스트를 보여줄때 실행되는 함수 --%>
	function goBookBySearchBar(sort){
		
		var searchType = $("#searchType").val();
		var searchWord = $("#searchWord").val();
		
		
		var form_data = {"searchType":searchType, //검색 타입 ex)도서명, 저자, 출판사
						 "searchWord":searchWord, //검색 어
						 "sort":sort};			  //정렬 조건 ex)도서명, 권수, 도서번호
		
		$.ajax({
			url:"KKHfindBookBySearchbar.ana",
			type:"GET",
			data:form_data,
			dataType:"JSON",
			success:function(json){
				
				var resultHTML = "";
				
				if(json.length <= 0){
					resultHTML+="<tr>"+
						"<td colspan='8'><h3  align='center'>조건에 맞는 상품이 없습니다</h3></td>"+
						"</tr>";
				}else{
					$.each(json,function(bookIndex,book){
						
						resultHTML += "<tr style='overflow-y:scroll; max-height:400px;' class='BookInfo' onClick='goBookDetail(\""+book.BOOKID+"\")'>"+
						"<td>"+(bookIndex+1)+"</td>"+
						"<td>"+book.BOOKID+"</td>"+
							"<td>"+book.TITLE+"</td>"+
							"<td>"+book.FNAME+"-"+book.GNAME+"</td>"+
							"<td>"+book.AUTHOR+"</td>"+       
							"<td>"+book.PUBNAME+"</td>"+
							"<td>"+book.LIBNAME+"</td>"+
							"<td>"+book.AGECODE+"</td>"+
							"<td>"+book.COUNT+"</td>"+
						"</tr>";
							
						});
				}
				
		
				$("#displayBookList").empty().html(resultHTML);
				
			},error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }

			
			
		});
		
	}
	
	<%-- 도서 상세페이지로 이동할때 실행되는 함수 --%>
	function goBookDetail(bookid){
		
		var frm = document.bookDetailFrm;
		frm.bookid.value = bookid; 
		frm.method="GET";
		frm.action="bookDetail.ana";
		frm.submit();
		
	}
	
	
</script>	
<div class="container-fluid" style="padding-left:200px;">      <%--  container-fluid 로 하면 사용자 정의로 영역을 잡을수 있다. --%>
<div class="row">
	<div class="col-lg-12 col-sm-12 ">
				<span style="font-weight:bold; font-size: 24pt; margin-bottom:15px;">도서관리<i class="glyphicon glyphicon-book" style="font-size:22px;"></i> ></span><span style="font-weight:bold; font-size: 21pt; margin-bottom:15px;"> 도서목록</span>
				<i class="	glyphicon glyphicon-th-list" style="font-size: 19px;"></i>
	</div>
		<%-- 도서 검색, 정렬 시작 --%>
		<div class="col-lg-9 col-sm-9">
			<div class="searchbar col-lg-12" style="  margin-left: 20px; margin-top: 5px; float: left;">
				<select id="searchType"
					style="padding: .2em .4em; font-size: 13pt; background-color: #2196F3; height: 29pt; color: #fff; border-radius: 3px;">
					<option value="title">도서명</option>
					<option value="author">저자</option>
					<option value="pubname">출판사</option> 
				</select> <input id="searchWord" type="text" placeholder="Search.." name="search2">
				<button type="button" class="search">
					<i class="fa fa-search"></i>
				</button>
				<select style="float: right; height: 15pt; margin-top:15px;" class="sort">
				<option value="title">도서명</option>
				<option value="count">권수</option>
				<option value="bookid">도서번호</option>
			</select>
			</div>
			
	
		</div>
		<%-- 도서 검색,정렬 끝 --%>
		
		<%-- 도서 리스트 출력화면 시작 --%>
		<div class="col-lg-9 col-sm-9"  style="overflow-y:scroll; max-height:700px; margin-top:15px;">       <%-- 도서리스트를 overflow-y 축으로 값을 줘서 일정 밑으로 내려가지 않게 한다. --%> 
			<table class="table table-striped" id="section1">     
				<thead>
					<tr><%--각각 너비(width) 값을 넣어서 능동형 웹 구조에 맞게 테이블을 조정한다. --%>
						<th width="7%">번호</th>
						<th width="12%">도서 코드</th>
						<th width="20%">도서명</th>
						<th width="11%">분류</th>      
						<th width="12%">저자/역자</th>
						<th width="8%">출판사</th>
						<th width="11%">위치</th>
						<th width="11%">도서 연령</th>    
						<th width="8%">권수</th> 
					</tr>      
				</thead>       
				<tbody id="displayBookList" >
					
				</tbody>
			</table>
		</div>
		<%-- 도서 리스트 출력화면 끝 --%>
		
		<%-- 도서 조건검색 사이드바 시작 --%>
<div class=" col-lg-3  col-sm-3" style="">
	<div style="font-weight:bold; font-family: 'NanumGothicBold'; border: 0px solid red; color:#0088cc; font-size: 12pt;">조회 조건 
		<button type="button" id="btnFindBook" style="font-size:10pt;" onClick="findBookListBysidebar();">검색</button></div>
	          
	<div style="float: left; border: 1px solid gray;" class="sidebar">
		<c:if test="${sessionScope.loginAdmin != null }"> <%-- 총관리자로 접속했을때만 도서관별 장서수를 보여주게 한다. --%>              
		<div>
			<div>
				<div class="sideHeader" style="">도서관(총 장서수)</div>       
			</div>    
			<ul class="sideinfo">
				<c:forEach var="library" items="${libraryList }">
				   
					<li><input type="checkbox"  class="library sideli" value="${library.LIBCODE }"/><a onClick="findBookbyLibrary('${library.LIBCODE}');"  class="sideText">${library.LIBNAME }(${library.COUNT })</a></li>
					
				</c:forEach>    
			</ul>
			
		</div>
		</c:if>
		<div>
			<div>
				<div class="sideHeader">언어</div>
			</div>
				<ul class="sideinfo">
				<c:forEach var="language" items="${languageList }">
					<li><input type="checkbox"  class="language sideli" value="${language.LCODE }"/><a onClick="findBookbyLanguage('${language.LCODE}');" class="sideText">${language.LNAME }(${language.COUNT })</a></li>
				</c:forEach>
				
				
				<!-- <li><input type="checkbox"  class="language sideli" value="JP"/><a onClick="findBookbyLanguage('JP');" class="sideText">일본어</a></li>
				
				<li><input type="checkbox"  class="language sideli" value="EN"/><a onClick="findBookbyLanguage('EN');" class="sideText">영어</a></li>
				
				<li><input type="checkbox"  class="language sideli" value="CH"/><a onClick="findBookbyLanguage('CH');" class="sideText">중국어</a></li>
				
				<li><input type="checkbox"  class="language sideli" value="FR"/><a onClick="findBookbyLanguage('FR');" class="sideText">프랑스어</a></li>
				<li><input type="checkbox"  class="language sideli" value="GM"/><a onClick="findBookbyLanguage('GM');" class="sideText">독일어</a></li> -->
			</ul>
		</div>
		<div>
			<div>
				<div class="sideHeader">종류</div>
			</div>
				<ul class="sideinfo">
				<c:forEach var="category" items="${categoryList }">
					<li><input type="checkbox"  class="category sideli" value="${category.CCODE }"/><a onClick="findBookbyCategory('${category.CCODE}');" class="sideText">${category.CNAME }(${category.COUNT })</a></li>
				</c:forEach>
				
				<!-- <li><input type="checkbox"  class="category sideli" value="E01"/><a onClick="findBookbyCategory('E01');" class="sideText">수필</a></li>
				<li><input type="checkbox"  class="category sideli" value="E02"/><a onClick="findBookbyCategory('E02');" class="sideText">에세이</a></li>				
				<li><input type="checkbox"  class="category sideli" value="P01"/><a onClick="findBookbyCategory('P01');" class="sideText">시</a></li>
				<li><input type="checkbox" class="category sideli" value="D01"/><a onClick="findBookbyCategory('D01');" class="sideText">사전/논문</a></li>
				<li><input type="checkbox"  class="category sideli" value="F01"/><a onClick="findBookbyCategory('F01');" class="sideText">동화책</a></li>
				<li><input type="checkbox"  class="category sideli" value="M01"/><a onClick="findBookbyCategory('M01');" class="sideText">잡지</a></li>
				<li><input type="checkbox"  class="category sideli" value="C01"/><a onClick="findBookbyCategory('C01');" class="sideText">만화책</a></li>
				<li><input type="checkbox"  class="category sideli" value="S01"/><a onClick="findBookbyCategory('S01');" class="sideText">문제집</a></li> -->
				
			</ul>
		</div>
		<div>
			<div>
				<div class="sideHeader">분야</div>
			</div>
				<ul class="sideinfo">
				<c:forEach var="field" items="${fieldList }">                 
					<li><input type="checkbox"  class="field sideli" value="${field.FCODE}"/><a onClick="findBookbyField('${field.FCODE}');" class="sideText">${field.FNAME }(${field.COUNT })</a></li>
				</c:forEach>
				
				<!-- <li><input type="checkbox"  class="field sideli" value="1"/><a onClick="findBookbyField('1');" class="sideText">철학,심리학(도서권수)</a></li>
				<li><input type="checkbox"  class="field sideli" value="2"/><a onClick="findBookbyField('2');" class="sideText">종교</a></li>
				<li><input type="checkbox"  class="field sideli" value="3"/><a onClick="findBookbyField('3');" class="sideText">사회과학</a></li>
				<li><input type="checkbox"  class="field sideli" value="4"/><a onClick="findBookbyField('4');" class="sideText">자연과학</a></li>
				<li><input type="checkbox" class="field sideli" value="5"/><a onClick="findBookbyField('5');" class="sideText">기술과학</a></li>
				<li><input type="checkbox"  class="field sideli" value="6"/><a onClick="findBookbyField('6');" class="sideText">예술</a></li>
				<li><input type="checkbox"  class="field sideli" value="7"/><a onClick="findBookbyField('7');" class="sideText">언어</a></li>
				<li><input type="checkbox" class="field sideli" value="8"/><a onClick="findBookbyField('8');" class="sideText">문학</a></li>
				<li><input type="checkbox"  class="field sideli" value="9"/><a onClick="findBookbyField('9');" class="sideText">역사,지리</a></li> -->
			</ul>
		</div>

	</div>
</div>
<%-- 도서 조건검색 사이드바 끝 --%>
</div>
	</div>

<%-- 조건검색 사이드바로 검색할때 넘기는 값을 담는 폼 --%>
<form name="sidebarFrm" id="sidebarFrm">
	<input type="hidden" name="sort" id="sortFrm" value=""/>
	<input type="hidden" name="library" id="libraryFrm" value=""/>
	<input type="hidden" name="language" id="languageFrm" value=""/>
	<input type="hidden" name="category" id="categoryFrm" value=""/>
	<input type="hidden" name="field" id="fieldFrm" value=""/>
</form>

<%-- 도서 상세로 갈때 넘기는 bookid 를 담는 폼 --%>
<form name="bookDetailFrm">
	<input type="hidden" name="bookid"/>
</form>
