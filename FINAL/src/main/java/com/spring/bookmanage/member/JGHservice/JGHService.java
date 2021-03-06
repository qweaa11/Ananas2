package com.spring.bookmanage.member.JGHservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.member.JGHmodel.MemberMapper;
import com.spring.bookmanage.member.JGHmodel.MemberVO;

@Service
public class JGHService {
	@Autowired private MemberMapper mapper;

	/**
	 * 회원 전체목록 불러오기
	 * @return
	 */
	public List<MemberVO> listService() {
		List<MemberVO> memberList = mapper.findAllMember();

		return memberList;
	}// end of listService

	/**
	 * 검색설정에 따른 회원목록 불러오기
	 * @param parameterMap
	 * @return
	 */
	public List<MemberVO> searchList(HashMap<String, String> parameterMap) {
		List<MemberVO> memberListBySearchWord = mapper.findAllMemberBySearchWord(parameterMap);

		return memberListBySearchWord;
	}// end of listService

	/**
	 * 회원 전체목록 불러오기
	 * @param parameterMap
	 * @return
	 */
	public List<MemberVO> noSearchList() {
		List<MemberVO> memberListByNoSearchWord = mapper.findAllMember();

		return memberListByNoSearchWord;
	}// end of noSearchList

	/**
	 * 회원 휴면상태 해제
	 * @param idxArray
	 * @return
	 */
	public int unlockMember(String[] idxArray) {
		int row = mapper.unlockAllByStatus(idxArray);

		return row;
	}// end of unlockMember

	/**
	 * 회원 영구정지
	 * @param idxArray
	 * @return
	 */
	public int banMember(String[] idxArray) {
		int row = mapper.banAllByStatus(idxArray);

		return row;
	}// end of banMember

	/**
	 * 회원 탈퇴처리
	 * @param idxArray
	 * @return
	 */
	public int removeMember(String[] idxArray) {
		int row = mapper.removeAllByStatus(idxArray);

		return row;
	}// end of removeMember

	/**
	 * 회원 복구처리
	 * @param idxArray
	 * @return
	 */
	public int recoverMember(String[] idxArray) {
		int row = mapper.recoverAllByStatus(idxArray);

		return row;
	}// end of recoverMember

	/**
	 * 회원 연체료
	 * @param idxArray
	 * @return
	 */
	public int latefeeClearMember(String[] idxArray) {
		int row = mapper.clearLatefee(idxArray);

		return row;
	}// end of latefeeClearMember

	/**
	 * 검색설정에 따른 회원수 불러오기
	 * @param parameterMap
	 * @return
	 */
	public int countMemberWithSearchOption(HashMap<String, String> parameterMap) {
		int count = mapper.countAllMemberWithSearchMap(parameterMap);

		return count;
	}// end of countMemberListWithSearchWord

	/**
	 * 검색없이 회원수 불러오기(전체회원수)
	 * @return
	 */
	public int countMemberWithOutSearchOption() {
		int count = mapper.countAllMember();

		return count;
	}// end of countMemberWithOutSearchOption

	/**
	 * 검색조건에 따른 회원목록 불러오기(페이지네이션 구현)
	 * @param parameterMap
	 * @return
	 */
	public List<MemberVO> listServiceWithPagination(HashMap<String, String> parameterMap) {
		List<MemberVO> memberList = mapper.findAllMemberWithPagination(parameterMap);

		return memberList;
	}// end of listServiceWithPagination
}