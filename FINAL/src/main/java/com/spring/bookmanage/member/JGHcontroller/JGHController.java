package com.spring.bookmanage.member.JGHcontroller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.MyUtil;
import com.spring.bookmanage.member.JGHmodel.MemberVO;
import com.spring.bookmanage.member.JGHservice.JGHService;

@Controller
public class JGHController {
	@Autowired private JGHService service;
	@Autowired AES256 aes;

	/**
	 * 회원목록 조회 담당
	 * @param request
	 * @param response
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping(value = "memberList.ana", method = {RequestMethod.GET})
	public String list(HttpServletRequest request, HttpServletRequest response)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		List<MemberVO> memberList = null;

		String colname = request.getParameter("colname");
		String searchWord = request.getParameter("searchWord");

		HashMap<String, String> parameterMap = new HashMap<>();
		parameterMap.put("colname", colname);
		parameterMap.put("searchWord", searchWord);
		
		String str_currentPageNo = request.getParameter("currentPageNo");

		int countMember = 0;// 회원수
		int sizePerPage = 10;// 페이지당 레코드 수
		int currentPageNo = 1;// 현재 페이지번호

		int front;// 시작 행번호
		int rear;// 끝 행번호
		int blockSize = 10;// 블럭사이즈 = 페이지바에 나타낼 페이지 수
		
		if(searchWord != null && !searchWord.trim().equals("") && !searchWord.trim().equals("null")) {
			countMember = service.countMemberWithSearchOption(parameterMap);
		} else {
			countMember = service.countMemberWithOutSearchOption();
			colname = "";
			searchWord = "";
		}// end of if~else

		request.setAttribute("colname", colname);
		request.setAttribute("searchWord", searchWord);
		
		int totalPage = (int)Math.ceil((double)countMember/sizePerPage);
		
		if(str_currentPageNo == null) {// 게시판 초기화면의 경우
			currentPageNo = 1;
		} else {// 특정페이지를 조회한 경우
			try {
				currentPageNo = Integer.parseInt(str_currentPageNo);

				if(currentPageNo < 1 || currentPageNo > totalPage)// 사용자가 존재하지 않는 페이지번호를 입력하는 경우
					currentPageNo = 1;
			} catch(NumberFormatException e) {
				currentPageNo = 1;
			}// end of try catch
		}// end of if~else
		
		front = (currentPageNo-1)*sizePerPage+1;
		rear = front+sizePerPage-1;
		parameterMap.put("front", String.valueOf(front));
		parameterMap.put("rear", String.valueOf(rear));

		memberList = service.listServiceWithPagination(parameterMap);
		request.setAttribute("memberList", memberList);

		for(MemberVO memberVO : memberList) {
			memberVO.setEmail(aes.decrypt(memberVO.getEmail()));
			memberVO.setPhone(aes.decrypt(memberVO.getPhone()));
		}// end of for
		
		String pageBar = "<ul class='pagination'>"; 
		pageBar += MyUtil.createPageBar(sizePerPage, blockSize, totalPage, currentPageNo, colname, searchWord, "memberList.ana")
				+"</ul>";
		request.setAttribute("pageBar", pageBar);

		return "member/memberList.tiles1";
	}// end of list

	/**
	 * 선택한 회원목록에 대하여 계정 활성화
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "unlock.ana", method = {RequestMethod.POST})
	public String updateUnlockMember(HttpServletRequest request, HttpServletResponse response) {
		String[] idxArray = request.getParameterValues("idx");
		
		int row = service.unlockMember(idxArray);

		String msg = "";
		String loc = "";
		if(row != 1) {
			msg = "계정 활성화에 실패하였습니다.";
			loc = "javascript:histroy.back();";
		} else {
			msg = "계정 활성화에 성공하였습니다.";
			loc = "memberList.ana";
		}// end of if~else

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		return "msg";
	}// end of updateUnlockMember

	/**
	 * 선택한 회원목록에 대하여 영구정지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "ban.ana", method = {RequestMethod.POST})
	public String updateBanMember(HttpServletRequest request, HttpServletResponse response) {
		String[] idxArray = request.getParameterValues("idx");

		int row = service.banMember(idxArray);

		String msg = "";
		String loc = "";
		if(row != 1) {
			msg = "영구정지 일괄처리가 실패하였습니다.";
			loc = "javascript:history.back();";
		} else {
			msg = "영구정지 일괄처리가 성공하였습니다.";
			loc = "memberList.ana";
		}// end of if~else

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		return "msg";
	}// end of updateBanMember

	@RequestMapping(value = "remove.ana", method = {RequestMethod.POST})
	public String updateRemoveMember(HttpServletRequest request, HttpServletResponse response) {
		String[] idxArray = request.getParameterValues("idx");

		int row = service.removeMember(idxArray);

		String msg = "";
		String loc = "";
		if(row != 1) {
			msg = "회원탈퇴 일괄처리에 실패하였습니다.";
			loc = "javascript:history.back();";
		} else { 
			msg = "회원탈퇴 일괄처리가 성공하였습니다.";
			loc = "memberList.ana";
		}// end of if~else

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		return "msg";
	}// end of updateDeleteMember

	@RequestMapping(value = "recover.ana", method = {RequestMethod.POST})
	public String updateRecoverMember(HttpServletRequest request, HttpServletResponse response) {
		String[] idxArray = request.getParameterValues("idx");

		int row = service.recoverMember(idxArray);

		String msg = "";
		String loc = "";
		if(row != 1) {
			msg = "회원복구 일괄처리에 실패하였습니다.";
			loc = "javascript:history.back();";
		} else {
			msg = "회원복구 일괄처리가 성공하였습니다.";
			loc = "memberList.ana";
		}// end of if~else

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		return "msg";
	}// end of updateRecoverMember

}