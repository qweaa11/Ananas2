package com.spring.bookmanage.library.Yjkmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

//model단(DAO)의 인터페이스 선언
@Repository
public interface InterYjkDAO {

	int adminRegistEnd(YjkVO adminvo);// 관리자 등록하기

	int idDuplicateCheck(String libid);// 아이디 중복확인

	List<LibraryVO> getliblibrary();// 도서관 정보 가져오기

}
