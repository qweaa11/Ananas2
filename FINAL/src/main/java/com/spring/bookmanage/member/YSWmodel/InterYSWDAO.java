package com.spring.bookmanage.member.YSWmodel;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.bookmanage.library.Yjkmodel.YjkVO;
import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Repository
public interface InterYSWDAO {
	
	// === 회원 가입시 등록 할 수 있는 도서관 리스트 요청
	//List<HashMap<String, String>> findLibraryList();

	//===== 아이디 중복 체크 요청. =====
	int idDuplicate(String memberid);

	//===== 회원등록 =====
	int memberRegistEnd(YSWMemberVO membervo);

	//===== 조건을 이용해서 사서 목록 가져오기. =====
	List<YSWLibrarianVO> findListWithOption(HashMap<String, String> paraMap);

	//===== 조건이 없을 때 사서 목록 가져오기. =====
	List<YSWLibrarianVO> findListNoneOption(HashMap<String, String> paraMap);

	// ===== 더보기를 위한 totalCount =====
	int totalCounttWithOption(HashMap<String, String> paraMap);

	int totalNoneOption(HashMap<String, String> paraMap);

	// 사서 정보 수정
	int updatelibrarianInfo(HashMap<String, String> paraMap);

	// 사서 정보 삭제(Real Delete)
	int deleteLibrarian(String lIBRARIANIDX);

	


	
	

}
