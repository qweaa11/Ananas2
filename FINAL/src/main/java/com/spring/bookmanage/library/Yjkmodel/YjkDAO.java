package com.spring.bookmanage.library.Yjkmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//model단(DAO)의 선언
@Repository
public class YjkDAO implements InterYjkDAO {

	//==== 의존객체 주입하기(DI : Dependency Injection) ====
	@Autowired
	private SqlSessionTemplate sqlsession;

	// ==== 관리자 등록하기 ==== //
	@Override
	public int adminRegistEnd(YjkVO adminvo) {
		
		int n = sqlsession.insert("bookmanage.adminRegistEnd", adminvo);
		
		return n;
	}

	// ==== 아이디 중복확인 ==== //
	@Override
	public int idDuplicateCheck(String libid) {
		int n = sqlsession.selectOne("bookmanage.idDuplicateCheck", libid);
		
		return n;
	}

	// ==== 도서관 정보 가져오기 ==== //
	@Override
	public List<LibraryVO> getliblibrary() {
		List<LibraryVO> libInfo = sqlsession.selectList("bookmanage.getliblibrary");
		
		return libInfo;
	}

}
