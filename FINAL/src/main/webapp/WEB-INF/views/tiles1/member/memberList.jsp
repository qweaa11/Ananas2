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

</style>

<script type="text/javascript">
	$(document).ready(function() {
		searchKeep();

		// 한 행을 클릭할 경우 체크박스 선택, 해제
		$(".td").click(function() {
			var flag = $(this).parent().find(".check").is(":checked");
			$(this).parent().find(".check").prop("checked",!flag);
		});// end of tr click

		$("#searchWord").keydown(function(event) {
	         if(event.keyCode == 13) {
	        	 search();
	         }// end of if
	    });// end of searchword keydown
	});// end of document ready
	
	function search() {
		var searchFrm = document.searchFrm;
		searchFrm.method = "GET";
		searchFrm.action = "memberList.ana";
		searchFrm.submit();
	}// end of search
	
	function searchKeep() {
		if(${searchWord != null && searchWord!= "" && searchWord != "null"}) {
			$("#colname").val("${colname}");
			$("#searchWord").val("${searchWord}");
		}// end of if
	}// end of searchKeep
	
	function allCheckFalse() {
		var flag = $("input:checkbox[name=check]").prop("checked",false);
	}// end of allCheck
	
	function unlock() {// 계정 휴면해제 함수(활성화)
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
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of unlock
	
	function recover() {// 계정 복원처리
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
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of recover
	
	function remove() {// 계정 탈퇴처리
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
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of remove
	
	function ban() {// 계정 영구정지
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
				frm.submit();
				return ;
			}// end of inner if~else

		}// end of outer if

		$(".tr-row").find(".idx").attr("disabled", false);
	}// end of ban
	
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
					<button type="button" class="btn btn-info" onclick="search();">검색</button>
				</div>

				<div style="float: left;">
					<button type="button" id="lock"class="btn btn-dark" onclick="allCheckFalse();"><i class="glyphicon glyphicon-remove"></i></button>
				</div>
				<div style="float: right;">
					<button type="button" class="btn btn-success" onclick="unlock();">휴면해제<i class="glyphicon glyphicon-ok"></i></button>
					<button type="button" class="btn btn-dark" onclick="recover();">복원하기<i class="glyphicon glyphicon-refresh"></i></button>
					<button type="button" class="btn btn-warning" onclick="ban();">영구정지<i class="glyphicon glyphicon-remove"></i></button>
					<button type="button" class="btn btn-danger" onclick="remove();">탈퇴처리<i class="glyphicon glyphicon-remove"></i></button>
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
							<th style="text-align: center;">상세정보</th>
						</tr>
					</thead>
					<tbody align="center">
						<c:if test="${empty memberList}">
						<tr>
							<td colspan="11"><span style="color: #ff4d4d; font-weight: bold;">입력된 내용과 일치하는 회원이 존재하지 않습니다.</span></td>
						</tr>
						</c:if>
						
						<c:if test="${not empty memberList}">
							<c:forEach var="memberVO" items="${memberList}" varStatus="no">
						<tr class="tr-row">
							<td><input type="checkbox" class="check" name="check" /></td>
							<td class="td">${no.count}</td>
							<td class="td">${memberVO.name}</td>
							<td class="td">${memberVO.memberid}</td>
							<td class="td">${memberVO.ages}</td>
							<td class="td">${memberVO.gender}</td>
							<td class="td">${memberVO.email}</td>
							<td class="td">${memberVO.phone}</td>
							<td class="td status">${memberVO.status}</td>
							<td class="td">${memberVO.regDate}</td>
							<td class="detail">
								<input type="text" id="idx${no.count}" name="idx" class="idx" value="${memberVO.idx}"/>
								<button type="button" class="btn btn-dark"
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

</div>