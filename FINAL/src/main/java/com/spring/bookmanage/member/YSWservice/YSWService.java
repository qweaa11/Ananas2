package com.spring.bookmanage.member.YSWservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.library.Yjkmodel.YjkVO;
import com.spring.bookmanage.member.YSWmodel.InterYSWDAO;
import com.spring.bookmanage.member.YSWmodel.YSWLibrarianVO;
import com.spring.bookmanage.member.YSWmodel.YSWMemberVO;
import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Service
public class YSWService implements InterYSWService {
	
	@Autowired
	private InterYSWDAO dao;
	
	
	// === 회원 가입시 등록 할 수 있는 도서관 리스트 요청
	/*@Override
	public List<HashMap<String, String>> findLibraryList() {

		List<HashMap<String, String>> libraryMap  = dao.findLibraryList();
		return libraryMap;
	}*/

	

	//===== 아이디 중복 체크 요청. =====
	@Override
	public int idDuplicate(String memberid) {

		int result = dao.idDuplicate(memberid);
		return result;
	}

	
	//===== 회원등록 =====
	@Override
	public int memberRegistEnd(YSWMemberVO membervo) {

		int result = dao.memberRegistEnd(membervo);
		return result;
	}

	
	//===== 조건을 이용해서 사서 목록 가져오기. =====
	@Override
	public List<YSWLibrarianVO> findListWithOption(HashMap<String, String> paraMap) {

		List<YSWLibrarianVO> yswlibvoList = dao.findListWithOption(paraMap);
		return yswlibvoList;
	}

	
	//===== 조건이 없을 때 사서 목록 가져오기. =====
	@Override
	public List<YSWLibrarianVO> findListNoneOption(HashMap<String, String> paraMap) {

		List<YSWLibrarianVO> yswlibvoList = dao.findListNoneOption(paraMap);
		return yswlibvoList;
	}


	// ===== 조건이 있을때 페이징 처리(더보기)를 위한 totalCount =====
	@Override
	public int totalCounttWithOption(HashMap<String, String> paraMap) {
		
		int totalCount = dao.totalCounttWithOption(paraMap);
		return totalCount;
	}


	// ===== 조건이 없을때 페이징 처리(더보기)를 위한 totalCount =====
	@Override
	public int totalNoneOption(HashMap<String, String> paraMap) {

		int totalCount = dao.totalNoneOption(paraMap);
		return totalCount;
	}


	// 사서 정보 수정
	@Override
	public int updatelibrarianInfo(HashMap<String, String> paraMap) {

		int result = dao.updatelibrarianInfo(paraMap);
		return result;
	}


	// 사서 정보 삭제(Real Delete)
	@Override
	public int deleteLibrarian(String lIBRARIANIDX) {
		
		int result = dao.deleteLibrarian(lIBRARIANIDX);
		return result;
	}


}
