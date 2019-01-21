package com.spring.bookmanage.member.YSWmodel;


import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmanage.library.Yjkmodel.YjkVO;


@Repository
public class YSWDAO implements InterYSWDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	
	//===== 아이디 중복 체크 요청. =====
	@Override
	public int idDuplicate(String memberid) {

		int result = sqlsession.selectOne("YSW.idDuplicate", memberid);
		return result;
	}

	
	//===== 회원등록 =====
	@Override
	public int memberRegistEnd(YSWMemberVO membervo) {

		int result = sqlsession.insert("YSW.memberRegistEnd", membervo);
		return result;
	}

	
	//===== 조건을 이용해서 사서 목록 가져오기. =====
	@Override
	public List<YSWLibrarianVO> findListWithOption(HashMap<String, String> paraMap) {
		
		List<YSWLibrarianVO> yswlibvoList = sqlsession.selectList("YSW.findListWithOption", paraMap);
		return yswlibvoList;
	}

	
	//===== 조건이 없을 때 사서 목록 가져오기. =====
	@Override
	public List<YSWLibrarianVO> findListNoneOption(HashMap<String, String> paraMap) {

		List<YSWLibrarianVO> yswlibvoList = sqlsession.selectList("YSW.findListNoneOption", paraMap);
		return yswlibvoList;
	}


	// ===== 조건을 입력했을 때 페이징처리(더보기)를 위한 totalCount =====
	@Override
	public int totalCounttWithOption(HashMap<String, String> paraMap) {

		int totalCount = sqlsession.selectOne("YSW.totalCounttWithOption", paraMap);
		return totalCount;
	}


	// ===== 조건이 없을 때 페이징처리(더보기)를 위한 totalCount =====
	@Override
	public int totalNoneOption() {

		int totalCount = sqlsession.selectOne("YSW.totalNoneOption");
		return totalCount;
	}


	// 사서 정보 수정
	@Override
	public int updatelibrarianInfo(HashMap<String, String> paraMap) {

		int result = sqlsession.update("YSW.updatelibrarianInfo", paraMap);
		return result;
	}


	// 사서 정보 삭제(Real Delete)
	@Override
	public int deleteLibrarian(String lIBRARIANIDX) {

		int result = sqlsession.delete("YSW.deleteLibrarian", lIBRARIANIDX);
		return result;
	}

}
