package com.spring.bookmanage.member.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.Yjkmodel.YjkVO;
import com.spring.bookmanage.member.YSWmodel.YSWLibrarianVO;
import com.spring.bookmanage.member.YSWmodel.YSWMemberVO;
import com.spring.bookmanage.member.YSWmodel.YSWRentalVO;

@Service
public interface InterYSWService {

	//===== 아이디 중복 체크 요청. =====
	int idDuplicate(String memberid);

	//===== 회원등록 =====
	int memberRegistEnd(YSWMemberVO membervo);

	//===== 조건을 이용해서 사서 목록 가져오기. =====
	List<YSWLibrarianVO> findListWithOption(HashMap<String, String> paraMap);

	//===== 조건이 없을 때 사서 목록 가져오기. =====
	List<YSWLibrarianVO> findListNoneOption(HashMap<String, String> paraMap);

	// ===== 조건이 있을때 페이징 처리(더보기)를 위한 totalCount =====
	int totalCounttWithOption(HashMap<String, String> paraMap);

	// ===== 조건이 없을때 페이징 처리(더보기)를 위한 totalCount =====
	int totalNoneOption();

	// 사서 정보 수정
	int updatelibrarianInfo(HashMap<String, String> paraMap);

	// 사서 정보 삭제(Real Delete)
	int deleteLibrarian(String lIBRARIANIDX);

	// 대출 목록 페이지 보여주기
	List<YSWRentalVO> findRentalList();
	
}
