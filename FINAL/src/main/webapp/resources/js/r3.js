$(document).ready(function(e){
		
		// 대여 회원 검색 카테고리
	    $('.search-member').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-member span#search_concept').text(concept);
			$('.input-member #membercategory').val(param);
		});
		
		
	 	// 예약 회원 검색 카테고리
	    $('.search-member2').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-member2 span#search_concept').text(concept);
			$('.input-member2 #membercategory2').val(param);
		});
		
	    
		// 책 검색 카테고리
	    $('.search-book').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-book span#search_concept').text(concept);
			$('.input-book #bookcategory').val(param);
		});
		
	 	// 대여 검색 카테고리
	    $('.search-rental').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-rental span#search_concept').text(concept);
			$('.input-rental #rentalcategory').val(param);
		});
	 	
	 	
	 	// 예약을 위한 대여 검색 카테고리
	    $('.search-rental2').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-rental2 span#search_concept').text(concept);
			$('.input-rental2 #rentalcategory2').val(param);
		});
	 	
	 	
	 	// 반납 정렬 카테고리
	    $('.sort-rental').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.sort-rental span#search_concept').text(concept);
			$('.input-rental-sort #sortrental').val(param);
		});
	 	
	 	// 예약 정렬 카테고리
	    $('.sort-rental2').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.sort-rental2 span#search_concept').text(concept);
			$('.input-rental-sort2 #sortrental2').val(param);
		});
	    
	    // 회원 목록 스타일 부여
	    $(document).on("mouseover", ".hover", function () { 
	    	$(this).addClass("hoverStyle");
		});
	    
	    $(document).on("mouseout", ".hover", function () {
	    	$(this).removeClass("hoverStyle");
		});
	    
	    // 엔터 쳤을 시 대여 회원 검색
	    $("#search_member").keydown(function(event) {
			if(event.keyCode == 13) {
				searchMember('1');
			}
		});
	    
	    // 엔터 쳤을 시 예약 회원 검색
	    $("#search_member2").keydown(function(event) {
			if(event.keyCode == 13) {
				searchMember('2');
			}
		});
	    
	    // 엔터 쳤을 시 도서 검색
	    $("#search_book").keydown(function(event) {
			if(event.keyCode == 13) {
				searchBook();
			}
		});
	    
	 	// 엔터 쳤을 시 대여 검색
	    $("#search_rental").keydown(function(event) {
			if(event.keyCode == 13) {
				searchRental('1');
			}
		});
	 	
	 	
	 	// 엔터 쳤을 시 예약을 위한 대여 검색
	    $("#search_rental2").keydown(function(event) {
			if(event.keyCode == 13) {
				searchRental('2');
			}
		});
	 	
	    
	    // 대여 회원 상세 정보 표시
	    $(document).on("click", ".memberselect", function () {
	    	
	    	var memberid = $(this).find(".memberid").text();
	    	
	    	var data_form = {"memberid":memberid}
	    	
	    	$.ajax({
				
				url:"r3findOneMember.ana",
				type:"GET",
				data:data_form,
				dataType:"json",
				success:function(json) {
					
					$("#memberid").text(json.MEMBERID);
					$("#name").text(json.NAME);
					$("#ages").text(json.AGES);
					$("#addr1").text(json.ADDR1);
					$("#addr2").text(json.ADDR2);
					$("#phone").text(json.PHONE);
					
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
				
			});// end of $.ajax()-------------------------
	    	
		});// end of $(document).on()--------------------------------------
		
		
		// 예약 회원 상세 정보 표시
	    $(document).on("click", ".memberselect2", function () {
	    	
	    	var memberid = $(this).find(".memberid2").text();
	    	
	    	var data_form = {"memberid":memberid}
	    	
	    	$.ajax({
				
				url:"r3findOneMember.ana",
				type:"GET",
				data:data_form,
				dataType:"json",
				success:function(json) {
					
					$("#rmemberid").text(json.MEMBERID);
					$("#rname").text(json.NAME);
					$("#rages").text(json.AGES);
					$("#raddr1").text(json.ADDR1);
					$("#raddr2").text(json.ADDR2);
					$("#rphone").text(json.PHONE);
					
				},
				error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
				
			});// end of $.ajax()-------------------------
	    	
		});// end of $(document).on()--------------------------------------
		
		
		// 대여 대기 창으로 옮기기
		$(document).on("click", ".bookselect", function () {

	    	var memberid = $("#memberid").text();
	    	
	    	if(memberid == null || memberid == "") {
	    		alert("회원을 선택해주세요");
	    		return;
	    	}
	    	
	    	var bookid = $(this).find(".bookid").text();
	    	
	    	var title = $(this).find(".bookid").next().text();
	    	
	    	var flag = false;
	    	
	    	$(".bookval").each(function () {
				if($(this).val() == bookid){
					flag = true;
					return false;
				}
			});
	    	
	    	if(flag) {
	    		alert("이미 대여대기 목록에 들어있는 책 입니다.");
	    		return;
	    	}
	    	
	    	title = title.length > 15?title.substring(0, 15) + "...":title; 
	    	
	    	var name = $("#name").text();
	    	
	    	var today = new Date();
	    	var deadline = new Date();
	    	deadline.setDate(today.getDate() + 14);
	    	
	    	var dd = deadline.getDate();
	    	var mm = deadline.getMonth()+1; //January is 0!
	    	var yyyy = deadline.getFullYear();
	    	
	    	html = 	"<li class=\"list-group-item hover rentalredy\">\n" + 
					"    <div class=\"row\">\n" + 
					"        <div class=\"col-xs-2 memberidval text-left\">" + memberid + "</div>\n" + 
					"        <div class=\"col-xs-2 nameval\">" + name + "</div>\n" + 
					"        <div class=\"col-xs-3 \">" + title + "</div>\n" + 
					"        <div class=\"col-xs-2 \">14일</div>\n" + 
					"        <div class=\"col-xs-3 deadlineval\">" + yyyy + "/" + mm + "/" + dd + "</div>\n" +
					"		 <input type='hidden' class='bookval' value='" + bookid + "'/>" +
					"    </div>\n" + 
					"</li>";
					
			$(".rentalList").append(html);
	    	
		});// end of $(document).on()--------------------------------------
		
		
		// 대여 대기창에서 지우기 
		$(document).on("click", ".rentalredy", function () {
			$(this).empty().hide();
		});// end of $(document).on()----------------------------
		
	    
		// 반납을 위한 대여목록 정렬 클릭시 정렬을 위해 함수 호출
		$(".btnRentalSort").click(function () {
			searchRental('1');
		});
		
		
		// 예약을 위한 대여목록 정렬 클릭시 정렬을 위해 함수 호출
		$(".btnRentalSort2").click(function () {
			searchRental('2');
		});
		
		
		// 반납 대기 창으로 옮기기
		$(document).on("click", ".rentalselect", function () {

	    	var memberid = $(this).find(".rentalMemberid").text();
	    	var name = $(this).find(".rentalName").text();
	    	var bookid = $(this).find(".rentalBookid").text();
	    	var title = $(this).find(".rentalTitle").text();
	    	var rentalDate = $(this).find(".rentalRentaldate").text();
	    	var deadline = $(this).find(".rentalDeadline").text();
	    	var renew = $(this).find(".rentalRenew").text();
	    	var deadlinecut = $(this).find(".rentalDeadlinecut").text() + "";
	    	
	    	var flag = false;
	    	
	    	$(".rentalBookval").each(function () {
				if($(this).val() == bookid){
					flag = true;
					return false;
				}
			});
	    	
	    	if(flag) {
	    		alert("이미 반납대기 목록에 들어있는 책 입니다.");
	    		return;
	    	}
	    	
	    	title = title.length > 15?title.substring(0, 15) + "...":title; 
	    	
	    	var today = new Date();
	    	
	    	var dd = today.getDate();
	    	var mm = today.getMonth()+1; //January is 0!
	    	var yyyy = today.getFullYear();
	    	
	    	html = 	"<li class=\"list-group-item hover returnredy\">\n" + 
					"    <div class=\"row\">\n" + 
					"		<div class=\"col-xs-1 text-left\" style=\" \">" + memberid + "</div>\n" + 
					"		<div class=\"col-xs-1\" style=\"\">" + name + "</div>\n" + 
					"		<div class=\"col-xs-3\" style=\"\">" + title + "</div>\n" + 
					"		<div class=\"col-xs-2\" style=\"\">" + bookid + "</div>\n" + 
					"		<div class=\"col-xs-2\" style=\"\">" + rentalDate + "</div>\n" + 
					"		<div class=\"col-xs-2\" style=\"\">" + deadline + "</div>\n" + 
					"		<div class=\"col-xs-1 text-center\" style=\"\">" + (renew < 3?(3-renew) + "회 남음":"연장불가") + "</div>\n" +
					"		<input type='hidden' class='rentalBookval' value='" + bookid + "'/>" +
					"		<input type='hidden' class='rentalMemberval' value='" + memberid + "'/>" +
					"		<div class='rentalRenewval' style='display: none;'>" + renew + "</div>" +
					"		<div class='rentalDeadlinecutval' style='display: none;'>" + deadlinecut + "</div>" +
					"    </div>\n" + 
					"</li>";
					
			$(".returnList").append(html);
	    	
		});// end of $(document).on()--------------------------------------
		
		
		// 반납 대기창에서 지우기 
		$(document).on("click", ".returnredy", function () {
			$(this).empty().hide();
		});// end of $(document).on()----------------------------
		
		
		// 예약 대기 창으로 옮기기
		$(document).on("click", ".rentalselect2", function () {

	    	var memberid = $("#rmemberid").text();
	    	var name = $("#rname").text();
	    	var bookid = $(this).find(".rentalBookid").text();
	    	var title = $(this).find(".rentalTitle").text();
	    	var deadline = $(this).find(".rentalDeadline").text();
	    	
	    	if(memberid == null || memberid == "") {
	    		alert("회원을 선택해주세요");
	    		return;
	    	}
	    	
	    	
	    	
	    	var flag = false;
	    	
	    	$(".reserveBookval").each(function () {
				if($(this).text() == bookid){
					flag = true;
					return false;
				}
			});
	    	
	    	if(flag) {
	    		alert("이미 예약대기 목록에 들어있는 책 입니다.");
	    		return;
	    	}
	    	
	    	title = title.length > 15?title.substring(0, 15) + "...":title; 
	    	
	    	deadline = deadline.split("-");
	    	
	    	var dead = new Date(deadline[0], deadline[1], deadline[2]);
	    	var reservedate = new Date();
	    	reservedate.setDate(dead.getDate() + 1);
	    	
	    	var dd = reservedate.getDate();
	    	var mm = reservedate.getMonth()+1; //January is 0!
	    	var yyyy = reservedate.getFullYear();
	    	
	    	html = 	"<li class=\"list-group-item hover reserveredy\">\n" + 
					"    <div class=\"row\">\n" + 
					"        <div class=\"col-xs-2 reserveMemberidval text-left\">" + memberid + "</div>\n" + 
					"        <div class=\"col-xs-2\">" + name + "</div>\n" + 
					"        <div class=\"col-xs-3 \">" + title + "</div>\n" + 
					"        <div class=\"col-xs-3 reserveBookval\">" + bookid + "</div>\n" + 
					"        <div class=\"col-xs-2 reservedateval\">" + yyyy + "/" + mm + "/" + dd + "</div>\n" +
					"    </div>\n" + 
					"</li>";
					
			$(".reservationList").append(html);
	    	
		});// end of $(document).on()--------------------------------------
		
		// 예약 대기창에서 지우기 
		$(document).on("click", ".reserveredy", function () {
			$(this).empty().hide();
		});// end of $(document).on()----------------------------
		
	});// end of ready()------------------
	
	
	// 회원목록 표시
	function searchMember(type) {

		var cateogry = "";
		var searchWord = "";
		
		if(type == "1") {
			cateogry = $("#membercategory").val();
			searchWord = $("#search_member").val();
		}
		else {
			cateogry = $("#membercategory2").val();
			searchWord = $("#search_member2").val();
		}
		
		var data_form = {"searchWord":searchWord, "cateogry":cateogry}
		
		$.ajax({
			
			url:"r3searchMember.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				var html = "";
				
				if(json.length > 0){
					
					$.each(json, function (entryIndex, entry) {
					 	
						if(type == "1") {
							html += "<li class=\"list-group-item hover memberselect\">\n" + 
									"	<div class=\"row\">\n" + 
									"		<div class=\"col-xs-6 memberid text-left\" style=\" \">" + entry.MEMBERID + "</div>\n" + 
									"		<div class=\"col-xs-6\" style=\"\">" + entry.NAME + "</div>\n" + 
									"	</div>\n" + 
									"</li>";
						}
						else {
							html += "<li class=\"list-group-item hover memberselect2\">\n" + 
									"	<div class=\"row\">\n" + 
									"		<div class=\"col-xs-6 memberid2 text-left\" style=\" \">" + entry.MEMBERID + "</div>\n" + 
									"		<div class=\"col-xs-6\" style=\"\">" + entry.NAME + "</div>\n" + 
									"	</div>\n" + 
									"</li>";
						}
						
						
						
					});// end of $.each()---------------------------
					
				}
				else {
					html += "<li class=\"list-group-item\">\n" + 
					"	<div class=\"row\">\n" + 
					"		<div class=\"col-xs-12 memberid text-left\" style=\"text-align: center;\">검색 결과가 없습니다.</div>\n" + 
					"	</div>\n" + 
					"</li>";
				}
				
				if(type == "1") {
					$(".memberList").html(html);
				}
				else {
					$(".memberList2").html(html);
				}
				
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()---------------------
		
	}
	
	// 대여를 위한 도서목록 표시
	function searchBook() {
		
		var cateogry = $("#bookcategory").val();
		var searchWord = $("#search_book").val();
		
		var data_form = {"searchWord":searchWord, "cateogry":cateogry}
		
		$.ajax({
			
			url:"r3searchBook.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				var html = "";
				
				if(json.length > 0){
					
					$.each(json, function (entryIndex, entry) {
					 	 
						html += "<li class=\"list-group-item hover bookselect\">\n" + 
								"    <div class=\"row\">\n" + 
								"        <div class=\"col-xs-6 bookid text-left\" style=\" \">" + entry.BOOKID + "</div>\n" + 
								"        <div class=\"col-xs-6\" style=\"\">" + entry.TITLE + "</div>\n" + 
								"    </div>\n" + 
								"</li>";
						
					});// end of $.each()---------------------------
					
				}
				else {
					html += "<li class=\"list-group-item\">\n" + 
							"	<div class=\"row\">\n" + 
							"		<div class=\"col-xs-12 memberid text-left\" style=\"text-align: center;\">검색 결과가 없습니다.</div>\n" + 
							"	</div>\n" + 
							"</li>";
				}
				
				
				$(".bookList").html(html);
				
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()---------------------
		
	}// end of searchBook()---------------------
	
	
	// 책 대여 기능
	function rental() {
		
		var bookids = "";
		
		$(".bookval").each(function () {
			bookids += $(this).val() + ","; 
		});
		
		bookids = bookids.substring(0, bookids.length-1);
		
		var memberids = "";
		
		$(".memberidval").each(function () {
			memberids += $(this).text() + ",";
		});
		
		memberids = memberids.substring(0, memberids.length-1);
		
		if(bookids.trim() == "" || memberids.trim() == "") {
			alert("목록에 등록해주세요");
			return;
		}
		
		var names = "";
		
		$(".nameval").each(function () {
			names += $(this).text() + ","; 
		});
		
		names = names.substring(0, names.length-1);
		
		var deadlines = "";
		
		$(".deadlineval").each(function () {
			deadlines += $(this).text() + ","; 
		});
		
		deadlines = deadlines.substring(0, deadlines.length-1);
		
		var data_form = {"bookids":bookids, "memberids":memberids, "names":names, "deadlines":deadlines}
		
		
		$.ajax({
			url:"rentalInsert.ana",
			type:"POST",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				if(json.RESULT == "1"){
					alert("대여가 되었습니다.");
					$(".rentalList").empty();
					$(".membersearch").click();
					$(".booksearch").click();
				}
				else {
					alert(json.MSG);
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()-------------------------
		
		
	}// end of rental
	
	
	// 대여 대기목록 리셋
	function rentalReset() {
		$(".rentalList").empty();
	}// end of rentalReset()-------------------
	
	
	// 대여된 목록을 불러오기
	function searchRental(type) {
		
		if(type == "1"){
			var category = $("#rentalcategory").val();
			var searchWord = $("#search_rental").val();
			var sort = $("#sortrental").val();
		}
		else {
			var category = $("#rentalcategory2").val();
			var searchWord = $("#search_rental2").val();
			var sort = $("#sortrental2").val();
		}
		
		
		 var data_form = {"searchWord":searchWord, "category":category, "sort":sort}
		
		$.ajax({
			
			url:"r3searchRental.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				var html = "";
				
				if(json.length > 0) {
					
					$.each(json, function (entryIndex, entry) {
						html += "<li class=\"list-group-item hover " + (type == 1? "rentalselect":"rentalselect2") + 
								"\" " + (entry.DEADLINECUT > 0?"style='background-color: red; color: white;'":"") + ">\n" + 
								"	<div class=\"row\">\n" + 
								"		<div class=\"col-xs-1 rentalMemberid text-left\" style=\" \">" + entry.MEMBERID + "</div>\n" + 
								"		<div class=\"col-xs-1 rentalName\" style=\"\">" + entry.NAME + "</div>\n" + 
								"		<div class=\"col-xs-3 rentalTitle\" style=\"\">" + entry.TITLE + "</div>\n" + 
								"		<div class=\"col-xs-2 rentalBookid\" style=\"\">" + entry.BOOKID + "</div>\n" + 
								"		<div class=\"col-xs-2 rentalRentaldate\" style=\"\">" + entry.RENTALDATE + "</div>\n" + 
								"		<div class=\"col-xs-2 rentalDeadline\" style=\"\">" + entry.DEADLINE + "</div>\n" + 
								"		<div class=\"col-xs-1 text-center\" style=\"\">" + (entry.DEADLINECUT < 1 && entry.RENEW < 3?(3 - entry.RENEW) + "회 남음":"연장불가") + "</div>\n" +
								"		<div class='rentalDeadlinecut' style='display: none;'>" + entry.DEADLINECUT + "</div>" + 
								"		<div class='rentalRenew' style='display: none;'>" + entry.RENEW + "</div>" + 
								"	</div>\n" + 
								"</li>";
					});// end of each()----------------------------- 
					
				}
				else {
					html += "<li class=\"list-group-item\">\n" + 
							"	<div class=\"row\">\n" + 
							"		<div class=\"col-xs-12 text-left\" style=\"text-align: center;\">검색 결과가 없습니다.</div>\n" + 
							"	</div>\n" + 
							"</li>";
				}
				
				if(type == "1"){
					$(".rentalSearchList").html(html);
				}
				else {
					$(".rentalSearchList2").html(html);
				}
				
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()---------------------
		
	}// end of searchRental()--------------------- 
	
	
	// 반납 대기목록 리셋
	function returnReset() {
		$(".returnList").empty();
	}// end of rentalReset()------------------
	
	
	// 도서 반납 페이지
	function returned() {
		
		var bookids = "";
		
		$(".rentalBookval").each(function () {
			bookids += $(this).val() + ","; 
		});
		
		bookids = bookids.substring(0, bookids.length-1);
		
		var memberids = "";
		
		$(".rentalMemberval").each(function () {
			memberids += $(this).val() + ",";
		});
		
		memberids = memberids.substring(0, memberids.length-1);
		
		if(bookids.trim() == "" || memberids.trim() == "") {
			alert("목록에 등록해주세요");
			return;
		}
		
		var deadlinecuts = "";
		
		$(".rentalDeadlinecutval").each(function () {
			deadlinecuts += $(this).text() + ","; 
		});
		
		deadlinecuts = deadlinecuts.substring(0, deadlinecuts.length-1);
		
		var data_form = {"bookids":bookids, "memberids":memberids, "deadlinecuts":deadlinecuts};
		
		$.ajax({
			url:"r3bookReturn.ana",
			type:"POST",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				if(json.RESULT == "1"){
					alert("반납이 되었습니다.");
					$(".returnList").empty();
					$(".rentalsearch").click();
					$(".rentalsearch2").click();
				}
				else {
					alert(json.MSG);
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()-------------------------
		
	}// end of returned()-------------------
	
	
	// 도서 대여 연장
	function reservation() {
		
		var bookids = "";
		
		$(".rentalBookval").each(function () {
			bookids += $(this).val() + ","; 
		});
		
		bookids = bookids.substring(0, bookids.length-1);
		
		var flag = false;
		
		$(".rentalRenewval").each(function () {
			
			if($(this).text() > 2){
				flag = true;
				return false;
			}
		});
		
		if(flag) {
			alert("연장 횟수를 초과한 책이 있어 연장이 불가능 합니다.");
			return;
		}
		
		if(bookids.trim() == "") {
			alert("목록에 등록해주세요");
			return;
		}
		
		flag = false;
		
		$(".rentalDeadlinecutval").each(function () {
			
			if($(this).text() > 0){
				flag = true;
				return false;
			}
		});
		
		if(flag) {
			alert("연체되어 있는 책이 있어 연장이 불가능 합니다.");
			return;
		}
		
		var data_form = {"bookids":bookids};
		
		$.ajax({
			url:"r3bookRenew.ana",
			type:"POST",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				if(json.RESULT == "1"){
					alert("연장이 되었습니다.");
					$(".returnList").empty();
					$(".rentalsearch").click();
					$(".rentalsearch2").click();
				}
				else {
					alert(json.MSG);
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()-------------------------
		
	}// end of reservation()------------------------
	
	
	// 예약 대기 리셋
	function reservationReset() {
		$(".reservationList").empty();
	}// end of rentalReset()------------------
	
	
	// 도서 예약
	function reservation() {
		
		var bookids = "";
		
		$(".reserveBookval").each(function () {
			bookids += $(this).text() + ","; 
		});
		
		bookids = bookids.substring(0, bookids.length-1);
		
		var memberids = "";
		
		$(".reserveMemberidval").each(function () {
			memberids += $(this).text() + ","; 
		});
		
		memberids = memberids.substring(0, memberids.length-1);
		
		if(bookids.trim() == "" || memberids.trim() == "") {
			alert("목록에 등록해주세요");
			return;
		}
		
		var reservedates = "";
		
		$(".reservedateval").each(function () {
			reservedates += $(this).text() + ","; 
		});
		
		reservedates = reservedates.substring(0, reservedates.length-1);
		
		var data_form = {"bookids":bookids, "memberids":memberids, "reservedates":reservedates};
		
		console.log(bookids + " " + memberids + " " +reservedates);
		
		$.ajax({
			url:"r3bookReservation.ana",
			type:"POST",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				if(json.RESULT == "1"){
					alert("예약이 되었습니다.");
					$(".reservationList").empty();
					$(".rentalsearch").click();
					$(".rentalsearch2").click();
				}
				else {
					alert(json.MSG);
				}
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
			
		});// end of $.ajax()-------------------------
		
	}// end of reservation()----------------------