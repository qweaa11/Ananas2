package com.spring.bookmanage.member.JGHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberMapper {
	@Autowired private SqlSessionTemplate sqlsession;

	@Override
	public List<MemberVO> findAllMember() {
		List<MemberVO> memberList = sqlsession.selectList("jgh.findAllMember");

		return memberList;
	}// end of findAllMember

	@Override
	public List<MemberVO> findAllMemberBySearchWord(HashMap<String, String> parameterMap) {
		List<MemberVO> memberListBySearchWord = sqlsession.selectList("jgh.findAllMemberBySearchWord", parameterMap);

		return memberListBySearchWord;
	}// end of findAllMemberBySearchWord

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

	@Override
	public int countAllMemberWithSearchMap(HashMap<String, String> parameterMap) {
		int count = sqlsession.selectOne("jgh.countAllMemberWithSearchMap", parameterMap);

		return count;
	}// end of countAllMemberWithSearchMap

	@Override
	public int countAllMember() {
		int count = sqlsession.selectOne("jgh.countAllMember");

		return count;
	}// end of countAllMember

	@Override
	public List<MemberVO> findAllMemberWithPagination(HashMap<String, String> parameterMap) {
		List<MemberVO> memberList = sqlsession.selectList("jgh.findAllMemberWithPagination", parameterMap);

		return memberList;
	}// end of findAllMemberWithPagination
}