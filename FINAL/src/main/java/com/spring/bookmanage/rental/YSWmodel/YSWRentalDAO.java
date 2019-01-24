package com.spring.bookmanage.rental.YSWmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YSWRentalDAO implements InterYSWRentalDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	/*
	// 대출 목록 페이지 보여주기
	@Override
	public List<YSWRentalVO> findRentalList() {
		
		List<YSWRentalVO> rentalList = sqlsession.selectList("YSW.findRentalList");
		return rentalList;
	}*/


	// 책 반납
	@Override
	public int returnBook(HashMap<String, Object> paraMap) {
		
		int result = 0;
		
		String bookInfo = (String)paraMap.get("bookid");
		String memberidInfo = (String)paraMap.get("memberid");
		String rentaldateInfo = (String)paraMap.get("rentaldate");
		
		String[] bookList = bookInfo.split(",");
		String[] memberidList = memberidInfo.split(",");
		String[] rentaldateList = rentaldateInfo.split(",");
		
		int size = bookList.length;
		
		//System.out.println("size1 : " + bookList.length);
		//System.out.println("size2 : " + memberidList.length);
		//System.out.println("size3 : " + rentaldateList.length);
		
		for(int i=0; i<size; i++) {
			
			HashMap<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("bookList", bookList[i]);
			infoMap.put("memberidList", memberidList[i]);
			infoMap.put("rentaldateList", rentaldateList[i]);
			
			//System.out.println("bookList : " + infoMap.get("bookList"));
			//System.out.println("memberidList : " + infoMap.get("memberidList"));
			//System.out.println("rentaldateList : " + infoMap.get("rentaldateList"));
			
			result += sqlsession.insert("YSW.returnBook", infoMap);
			infoMap.clear();
		}
		
		if(result == size) {
			result = 1;
		}
		else {
			result = 0;
		}
		
		return result;
	}
	
	
	// 도서 상태 변경
	@Override
	public int changeBookStat(HashMap<String, Object> paraMap) {
		

		int result = 0;
		
		String bookInfo = (String)paraMap.get("bookid");
				
		String[] bookList = bookInfo.split(",");
		
		int size = bookList.length;
		
		for(int i=0; i<size; i++) {
			
			HashMap<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("bookList", bookList[i]);
			
			result += sqlsession.update("YSW.changeBookStat", infoMap);
			infoMap.clear();
		}
		
		if(result == size) {
			result = 1;
		}
		else {
			result = 0;
		}
		
		return result;
		
	}
	
	
	// 책 반납 연체시 회원상태 변경
	@Override
	public int changeMemberStat(HashMap<String, Object> paraMap) {

		int result = 0;
		
		String delaydateInfo = (String)paraMap.get("delaydate");
		String memberidInfo = (String)paraMap.get("memberid");
				
		String[] delayInfoList = delaydateInfo.split(",");
		String[] memberidList = memberidInfo.split(",");
		
		int size = delayInfoList.length;
		
		for(int i=0; i<size; i++) {
			
			int delayDate = Integer.parseInt(delayInfoList[i]);
			
			//System.out.println("delayDate : " + delayDate);
			
			if(delayDate > 0) {
				
				HashMap<String, Object> infoMap = new HashMap<String, Object>();
				infoMap.put("delayDate", delayDate);
				infoMap.put("delayfee", delayDate*100);
				infoMap.put("memberidList", memberidList[i]);
				
				result += sqlsession.update("YSW.changeMemberStat", infoMap);
				
				infoMap.clear();
			}
			else {
				
				result += 1;
			}
			
		}
		
		if(result == size) {
			result = 1;
		}
		else {
			result = 0;
		}
		
		return result;
	}

	
	// 대여 리스트에서 삭제
	@Override
	public int deleteBook(HashMap<String, Object> paraMap) {
		

		int result = 0;
		
		String idxInfo = (String)paraMap.get("idx");
				
		String[] idxList = idxInfo.split(",");
		
		int size = idxList.length;
		
		for(int i=0; i<size; i++) {
			
			HashMap<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("idxList", idxList[i]);
			
			result += sqlsession.delete("YSW.deleteBook", infoMap);
			infoMap.clear();
		}
		
		if(result == size) {
			result = 1;
		}
		else {
			result = 0;
		}
		
		return result;
	}

	
	// 책 대여기간 연장
	@Override
	public int extendBook(HashMap<String, Object> paraMap) {

		int result = sqlsession.update("YSW.extendBook", paraMap);
		return result;
	}


	// 검색어에 따른 대여 목록 결과의 총 페이지 구하기
	@Override
	public int findTotalPageWith(HashMap<String, String> paraMap) {

		int page = sqlsession.selectOne("YSW.findTotalPageWith", paraMap);
		return page;
	}


	// 검색어가 없을 때 나오는 대여 목록의 총 페이지
	@Override
	public int findTotalPage() {

		int page = sqlsession.selectOne("YSW.findTotalPage");
		return page;
	}

	
	// 검색어에 따른 대여 목록 검색 
	@Override
	public List<YSWRentalVO> findRentalListWith(HashMap<String, Object> optionMap) {

		List<YSWRentalVO> rentalVOList = sqlsession.selectList("YSW.findRentalListWith", optionMap);
		return rentalVOList;
	}


	// 검색어 없이 대여 목록 검색
	@Override
	public List<YSWRentalVO> findRentalList(HashMap<String, Object> optionMap) {

		List<YSWRentalVO> rentalVOList = sqlsession.selectList("YSW.findRentalList", optionMap);
		return rentalVOList;
	}

	

}
