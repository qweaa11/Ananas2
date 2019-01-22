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

		int currentPageNo = 1;// 현재 페이지번호
		int sizePerPage = 5;// 페이지당 레코드 수
		
		int front;// 시작행번호
		int rear;// 끝행번호
		
		int blockSize = 10;
		
		if(searchWord != null && !searchWord.trim().isEmpty()) {
			memberList = service.searchList(parameterMap);
			request.setAttribute("colname", colname);
			request.setAttribute("searchWord", searchWord);
		} else {
			memberList = service.noSearchList();
		}// end of if~else

		for(MemberVO memberVO : memberList) {
			memberVO.setEmail(aes.decrypt(memberVO.getEmail()));
			memberVO.setPhone(aes.decrypt(memberVO.getPhone()));
		}// end of for

		request.setAttribute("memberList", memberList);

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