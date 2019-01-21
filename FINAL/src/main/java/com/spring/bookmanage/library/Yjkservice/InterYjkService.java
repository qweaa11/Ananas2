package com.spring.bookmanage.library.Yjkservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.Yjkmodel.LibraryVO;
import com.spring.bookmanage.library.Yjkmodel.YjkVO;

//Service단 인터페이스 선언

@Service
public interface InterYjkService {

	int adminRegistEnd(YjkVO adminvo);// 관리자 등록하기

	int idDuplicateCheck(String libid);// 아이디 중복체크

	List<LibraryVO> getliblibrary();// 도서관 정보 가져오기	

}
