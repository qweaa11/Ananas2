package com.spring.bookmanage.member.JGHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberMapper {
	@Autowired private SqlSessionTemplate sqlsession;

	/**
	 * 회원목록 전체조회
	 */
	@Override
	public List<MemberVO> findAllMember() {
		List<MemberVO> memberList = sqlsession.selectList("jgh.findAllMember");

		return memberList;
	}// end of findAllMember

	/**
	 * 검색설정에 따른 회원목록 조회
	 */
	@Override
	public List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> parameterMap) {
		List<MemberVO> memberListBySearchWord = sqlsession.selectList("jgh.findAllMemberBySearchWord", parameterMap);

		return memberListBySearchWord;
	}// end of findAllMemberBySearchWord

	/**
	 * 계정 활성화
	 */
	@Override
	public int unlockAllByStatus(String[] idxArray) {
		int row = 0;
		String idx = "";
		for(int i=0;i<idxArray.length;i++) {
			idx = idxArray[i];
			row = sqlsession.update("jgh.unlockAllByStatus", idx);
		}// end of for

		return row;
	}// end of unlockAllByStatus

	/**
	 * 계정 이용정지
	 */
	@Override
	public int banAllByStatus(String[] idxArray) {
		int row = 0;
		String idx = "";
		for(int i=0;i<idxArray.length;i++) {
			idx = idxArray[i];
			row = sqlsession.update("jgh.banAllByStatus", idx);
		}// end of for

		return row;
	}// end of banAllByStatus

	/**
	 * 계정 탈퇴
	 */
	@Override
	public int removeAllByStatus(String[] idxArray) {
		int row = 0;
		String idx = "";
		for(int i=0;i<idxArray.length;i++) {
			idx = idxArray[i];
			row = sqlsession.update("jgh.removeAllByStatus", idx);
		}// end of for

		return row;
	}// end of removeAllMemberByStatus

	/**
	 * 계정 복구
	 */
	@Override
	public int recoverAllByStatus(String[] idxArray) {
		int row = 0;
		String idx = "";
		for(int i=0;i<idxArray.length;i++) {
			idx = idxArray[i];
			row = sqlsession.update("jgh.recoverAllByStatus", idx);
		}// end of for

		return row;
	}// end of recoverAllByStatus

	/**
	 * 검색에 따른 회원수 조회
	 */
	@Override
	public int countAllMemberWithSearchMap(HashMap<String, String> parameterMap) {
		int count = sqlsession.selectOne("jgh.countAllMemberBySearchMap", parameterMap);

		return count;
	}// end of countAllMemberWithSearchMap

	/**
	 * 검색없이 회원수 조회(전체 회원수 조회)
	 */
	@Override
	public int countAllMember() {
		int count = sqlsession.selectOne("jgh.countAllMember");

		return count;
	}// end of countAllMember
}