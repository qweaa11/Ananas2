package com.spring.bookmanage.library.Yjkservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.library.Yjkmodel.InterYjkDAO;
import com.spring.bookmanage.library.Yjkmodel.LibraryVO;
import com.spring.bookmanage.library.Yjkmodel.YjkVO;

//Service단 선언
@Service
public class YjkService implements InterYjkService {

	//==== 의존객체 주입하기(DI : Dependency Injection)  ====
	@Autowired
	private InterYjkDAO dao;
	
	//==== 양방향 암호화 알고리즘인 AES256을 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기(DI : Dependency Injection)  ====
	@Autowired
	private AES256 aes;

	// ==== 관리자 등록하기 ====
	@Override
	public int adminRegistEnd(YjkVO adminvo) {
		
		int n = dao.adminRegistEnd(adminvo);
		
		return n;
	}
	
	// ==== 아이디 중복체크 ====
	@Override
	public int idDuplicateCheck(String libid) {
		
		int n = dao.idDuplicateCheck(libid);
		
		return n;
	}

	// ==== 도서관 정보 가져오기 ==== //
	@Override
	public List<LibraryVO> getliblibrary() {
		
		List<LibraryVO> libInfo = dao.getliblibrary();
		
		return libInfo;
	}

}
