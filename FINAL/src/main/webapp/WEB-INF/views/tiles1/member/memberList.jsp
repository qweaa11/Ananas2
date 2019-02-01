<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	#searchSelect {
		padding: .2em .4em;
		font-size:13pt;
	    border-radius: 3px;
	}
	
	#search {
		padding: .2em .4em;
		font-size: 13pt;
		background-color: #0d55a2;
		color: #fff;
		border-radius: 3px;
		text-decoration: none;
		border: none;
	}
	
	.pagination>li>a, .pagination>li>span {
		border-radius: 50% !important;
		margin: 0 5px;
	}

</style>

<script type="text/javascript">
	$(document).ready(function() {
		searchKeep();

		// 한 행을 클릭할 경우 체크박스 선택, 해제
		$(".td").click(function() {
			var flag = $(this).parent().find(".check").is(":checked");
			$(this).parent().find(".check").prop("checked",!flag);
		});// end of tr click

		// 엔터키 검색 설정
		$("#searchWord").keydown(function(event) {
	         if(event.keyCode == 13) {
	        	 search();
	         }// end of if
	    });// end of searchword keydown
	    
	    // 툴팁
		$('[data-toggle="tooltip"]').tooltip({
	        html:"true"
	    });
	    
	});// end of document ready
	
	function search() {// 검색
		var searchFrm = document.searchFrm;
		searchFrm.method = "GET";
		searchFrm.action = "memberList.ana";
		searchFrm.submit();
	}// end of search
	
	function searchKeep() {// 검색어 유지
		if(${searchWord != null && searchWord!= "" && searchWord != "null"}) {
			$("#colname").val("${colname}");
			$("#searchWord").val("${searchWord}");
		}// end of if
	}// end of searchKeep
	
	function allCheckFalse() {// 전체선택 해제
		var flag = $("input:checkbox[name=check]").prop("checked",false);
	}// end of allCheck

	function unlock(goBackURL) {// 계정 휴면해제 함수(활성화)
		var frm = searchFrm;
		frm.method = "POST";
		frm.action = "unlock.ana";

		var flag = false;
		var count = 0;
		$(".tr-row").each(function() {
			flag = $(this).find(".check").is(":checked");
			
			if(!flag) {
				$(this).find(".idx").attr("disabled", true);
			} else {
				count++;
			}// end of if~else
		});

		var choice = confirm(count+"명의 회원을 모두 활성화(휴면해제) 하시겠습니까?");
		if(count < 1) {
			alert("1명 이상의 회원을 선택해주세요.");
			$(".tr-row").find(".idx").attr("disabled", false);
			
			return ;
		}// end of if
		
		var status = "";
		if(choice) {
			var breakPoint = false;// continue or break
			$(".tr-row").each(function() {
				flag = $(this).find(".check").is(":checked");
				if(flag) {
					status = $(this).find(".status").text();
					if(status != "휴면") {
						console.log(status);
						breakPoint = true;
						return false;
					}// end of inner deep if
				}// end of if

			});

			if(breakPoint) {
				alert("선택하신 회원중 휴면상태가 아닌 회원이 존재합니다.");
			} else {
				frm.goBackURL.value = goBackURL;
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of unlock
	
	function recover(goBackURL) {// 계정 복원처리
		var frm = searchFrm;
		frm.method = "POST";
		frm.action = "recover.ana";
	
		var flag = false;
		var count = 0;
		$(".tr-row").each(function() {
			flag = $(this).find(".check").is(":checked");
			
			if(!flag) {
				$(this).find(".idx").attr("disabled", true);
			} else {
				count++;
			}// end of if~else
		});// end of each

		var choice = confirm(count+"명의 회원을 모두 복원하시겠습니까?");
		if(count < 1) {
			alert("1명 이상의 회원을 선택해주세요.");
			$(".tr-row").find(".idx").attr("disabled", false);
			
			return ;
		}// end of if
		
		var status = "";
		if(choice) {
			var breakPoint = false;// continue or break
			$(".tr-row").each(function() {
				flag = $(this).find(".check").is(":checked");
				if(flag) {
					status = $(this).find(".status").text();
					if(status == "일반" || status == "영구정지") {
						console.log(status);
						breakPoint = true;
						return false;
					}// end of inner deep if
				}// end of if

			});// end of each

			if(breakPoint) {
				alert("선택하신 회원중 복원불가능한 계정이 포함되어있습니다.(일반, 영구정지) ");
			} else {
				frm.goBackURL.value = goBackURL;
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of recover
	
	function remove(goBackURL) {// 계정 탈퇴처리
		var frm = searchFrm;
		frm.method = "POST";
		frm.action = "remove.ana";
	
		var flag = false;
		var count = 0;
		$(".tr-row").each(function() {
			flag = $(this).find(".check").is(":checked");
			
			if(!flag) {
				$(this).find(".idx").attr("disabled", true);
			} else {
				count++;
			}// end of if~else
		});// end of each

		var choice = confirm(count+"명의 회원을 모두 탈퇴처리 하시겠습니까?");
		if(count < 1) {
			alert("1명 이상의 회원을 선택해주세요.");
			$(".tr-row").find(".idx").attr("disabled", false);
			
			return ;
		}// end of if

		var status = "";
		if(choice) {
			var breakPoint = false;// continue or break
			$(".tr-row").each(function() {
				flag = $(this).find(".check").is(":checked");
				if(flag) {
					status = $(this).find(".status").text();
					if(status == "탈퇴") {
						console.log(status);
						breakPoint = true;
						return false;
					}// end of inner deep if
				}// end of if

			});// end of each

			if(breakPoint) {
				alert("선택하신 회원중 이미 탈퇴한 회원이 존재합니다.");
			} else {
				frm.goBackURL.value = goBackURL;
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of remove
	
	function ban(goBackURL) {// 계정 영구정지
		var frm = searchFrm;
		frm.method = "POST";
		frm.action = "ban.ana";
	
		var flag = false;
		var count = 0;
		$(".tr-row").each(function() {
			flag = $(this).find(".check").is(":checked");
			
			if(!flag) {
				$(this).find(".idx").attr("disabled", true);
			} else {
				count++;
			}// end of if~else
		});// end of each

		var choice = confirm(count+"명의 회원을 모두 영구정지 처리하시겠습니까?");
		if(count < 1) {
			alert("1명 이상의 회원을 선택해주세요.");
			$(".tr-row").find(".idx").attr("disabled", false);
			
			return ;
		}// end of if
		
		var status = "";
		if(choice) {
			var breakPoint = false;// continue or break
			$(".tr-row").each(function() {
				flag = $(this).find(".check").is(":checked");
				if(flag) {
					status = $(this).find(".status").text();
					if(status == "영구정지") {
						console.log(status);
						breakPoint = true;
						return false;
					}// end of inner deep if
				}// end of if

			});// end of each

			if(breakPoint) {
				alert("선택하신 회원중 이미 영구정지상태인 회원이 존재합니다.");
			} else {
				frm.goBackURL.value = goBackURL;
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of ban
	
	function latefeeReset(goBackURL) {// 연체료 납부(초기화)
		var frm = searchFrm;
		frm.method = "POST";
		frm.action = "latefeeReset.ana";
	
		var flag = false;
		var count = 0;
		$(".tr-row").each(function() {
			flag = $(this).find(".check").is(":checked");
			
			if(!flag) {
				$(this).find(".idx").attr("disabled", true);
			} else {
				count++;
			}// end of if~else
		});// end of each

		var choice = confirm(count+"명의 연체료를 모두 납부처리하시겠습니까?");
		if(count < 1) {
			alert("1명 이상의 회원을 선택해주세요.");
			$(".tr-row").find(".idx").attr("disabled", false);
			
			return ;
		}// end of if
		
		var latefee = 0;
		if(choice) {
			var breakPoint = false;// continue or break
			$(".tr-row").each(function() {
				flag = $(this).find(".check").is(":checked");
				if(flag) {
					latefee = $(this).find(".latefee").text();
					if(latefee == 0) {
						console.log(latefee);
						breakPoint = true;
						return false;
					}// end of inner deep if
				}// end of if

			});// end of each

			if(breakPoint) {
				alert("선택하신 회원중 이미 납부한 회원이 존재합니다.");
			} else {
				frm.goBackURL.value = goBackURL;
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of latefeeReset
	
</script>

<div class="container">
	<div class="row">

		<div class="container border" style="height: 50%; width: 100%; align-content: center;">

			<form name="searchFrm" onsubmit="return false">
				<div>
					<select id="colname" name="colname" style="height: 25px;" >
						<option value="name">이름</option>
						<option value="memberid">아이디</option>
					</select>

					<input type="text" id="searchWord" name="searchWord" />
					<input type="hidden" name="goBackURL" />
					<button type="button" class="btn btn-info" 
						data-toggle="tooltip" data-placement="top" title="검색" onclick="search();">
						<i class="glyphicon glyphicon-search"></i></button>
				</div>

				<div style="float: left;">
					<button type="button" id="lock"class="btn btn-dark" 
						data-toggle="tooltip" data-placement="top" title="전체선택 해제" onclick="allCheckFalse();">
						<i class="glyphicon glyphicon-remove"></i></button>
				</div>
				<div style="float: right;">
					<button type="button" class="btn btn-success"
						data-toggle="tooltip" data-placement="top" title="휴면해제" onclick="unlock('${goBackURL}');">
						<i class="glyphicon glyphicon-ok"></i></button>
					<button type="button" class="btn btn-dark" 
						 data-toggle="tooltip" data-placement="top" title="복원하기" onclick="recover('${goBackURL}');">
						 <i class="glyphicon glyphicon-refresh"></i></button>
					<button type="button" class="btn btn-warning" 
						 data-toggle="tooltip" data-placement="top" title="탈퇴" onclick="remove('${goBackURL}');">
						 <i class="glyphicon glyphicon-trash"></i></button>
					<button type="button" class="btn btn-danger" 
						 data-toggle="tooltip" data-placement="top" title="영구정지" onclick="ban('${goBackURL}');">
						 <i class="glyphicon glyphicon-ban-circle"></i></button>
					<button type="button" class="btn btn-info" 
					 	data-toggle="tooltip" data-placement="top" title="연체료 납부" onclick="latefeeReset('${goBackURL}')">
						<i class="glyphicon glyphicon-usd"></i></button>
				</div>
			
				<table id="table-member" class="table table-striped table-hover">
					<thead>
						<tr>
							<th style="text-align: center;">선택</th>
							<th style="text-align: center;">번호(RNO)</th>
							<th style="text-align: center;">이름</th>
							<th style="text-align: center;">아이디</th>
							<th style="text-align: center;">구분(연령대)</th>
							<th style="text-align: center;">성별</th>
							<th style="text-align: center;">이메일</th>
							<th style="text-align: center;">연락처</th>
							<th style="text-align: center;">회원상태</th>
							<th style="text-align: center;">가입일자</th>
							<th style="text-align: center;">연체료</th>
							<th style="text-align: center;">상세정보</th>
						</tr>
					</thead>
					<tbody align="center">
						<c:if test="${empty memberList}">
						<tr>
							<td colspan="12"><span style="color: #ff4d4d; font-weight: bold;">입력된 내용과 일치하는 회원이 존재하지 않습니다.</span></td>
						</tr>
						</c:if>
						
						<c:if test="${not empty memberList}">
							<c:forEach var="memberVO" items="${memberList}" varStatus="no">
						<tr class="tr-row">
							<td><input type="checkbox" class="check" name="check" /></td>
							<td class="td">${memberVO.rno}</td>
							<td class="td">${memberVO.name}</td>
							<td class="td">${memberVO.memberid}</td>
							<td class="td">${memberVO.ages}</td>
							<td class="td">${memberVO.gender}</td>
							<td class="td">${memberVO.email}</td>
							<td class="td">${memberVO.phone}</td>
							<td class="td status">${memberVO.status}</td>
							<td class="td">${memberVO.regDate}</td>
							<td class="td latefee">${memberVO.latefee}</td>
							<td class="detail">
								<input type="hidden" id="idx${no.count}" name="idx" class="idx" value="${memberVO.idx}"/>
								<button type="button" class="btn btn-dark"
									data-toggle="tooltip" data-placement="top" title="회원상세"
									onclick="javascript:location.href='memberDetail.ana?idx=${memberVO.idx}'">
									<i class="glyphicon glyphicon-user"></i></button>
							</td>
						</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</form>
			

		</div>

	</div>
	
	<div class="row" align="center">
		${pageBar}
	</div>

</div>