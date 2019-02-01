package com.spring.bookmanage.member.JGHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.schedule.JGHscheduler.ScheduleMapper;

/**
 * MemberDAO
 * @author implements(nine9sh)
 *
 */
@Repository
public class MemberDAO implements MemberMapper, ScheduleMapper {
	@Autowired private SqlSessionTemplate sqlsession;

	/* MemberManagement part */
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
	public int clearLatefee(String[] idxArray) {
		int row = 0;

		String idx = "";
		for(int i=0;i<idxArray.length;i++) {
			idx = idxArray[i];
			row = sqlsession.update("jgh.clearLatefee", idx);
		}// end of for

		return row;
	}// end of clearLatefee

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

	/* Scheduler part 
	@Override
	public void schedulingAllMemberByRevokeDate() {
		sqlsession.update("jghSchedule.schedulingAllMemberByRevokeDate");
	}// end of scheduilingAllMemberByRevokeDate */
}