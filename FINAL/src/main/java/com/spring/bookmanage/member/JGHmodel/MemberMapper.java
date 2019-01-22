package com.spring.bookmanage.member.JGHmodel;

import java.util.HashMap;
import java.util.List;

public interface MemberMapper {
	/**
	 * 회원목록 전체조회
	 */
	List<MemberVO> findAllMember();

	/**
	 * 검색설정에 따른 회원목록 조회
	 * @param parameterMap
	 * @return
	 */
	List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> parameterMap);

	/**
	 * 선택한 계정 활성화(휴면해제)
	 * @param idxArray
	 * @return
	 */
	int unlockAllByStatus(String[] idxArray);

	/**
	 * 선택한 계정목록 영구정지
	 * @param idxArray
	 * @return
	 */
	int banAllByStatus(String[] idxArray);

	/**
	 * 계정 탈퇴처리
	 * @param idxArray
	 * @return
	 */
	int removeAllByStatus(String[] idxArray);

	/**
	 * 계정 복구처리
	 * @param idxArray
	 * @return
	 */
	int recoverAllByStatus(String[] idxArray);

	/**
	 * 검색에 따른 회원수 조회
	 * @param parameterMap
	 * @return
	 */
	int countAllMemberWithSearchMap(HashMap<String, String> parameterMap);

	/**
	 * 전체 회원수 조회(검색없이 회원수 조회)
	 * @return
	 */
	int countAllMember();
}