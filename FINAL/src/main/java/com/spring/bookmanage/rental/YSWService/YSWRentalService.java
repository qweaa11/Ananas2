package com.spring.bookmanage.rental.YSWService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.rental.YSWmodel.InterYSWRentalDAO;
import com.spring.bookmanage.rental.YSWmodel.YSWRentalVO;

@Service
public class YSWRentalService implements InterYSWRentalService {
	
	@Autowired
	private InterYSWRentalDAO dao;
	
	
	/*// 대출 목록 페이지 보여주기
	@Override
	public List<YSWRentalVO> findRentalList() {

		List<YSWRentalVO> rentalList = dao.findRentalList();
		return rentalList;
	}*/

	
	// 대여 책자를 반환 시키고 대여 목록에서 삭제 시키기
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})  
	public int returnAndDelete(HashMap<String, Object> paraMap) {

		int a = 0;
		int b = 0;
		
		// 책 반납
		int n = dao.returnBook(paraMap);
		
		if(n == 1) {
			
			// 도서 상태 변경
			a = dao.changeBookStat(paraMap);
			
			// 책 반납 연체시 회원상태 변경
			b = dao.changeMemberStat(paraMap);
			
			// 대여 리스트에서 삭제
			b = dao.deleteBook(paraMap);
		
		}
		
		return (a+b);
	}

	
	// 반납 기간을 연장 시키기
	@Override
	public int extendBook(HashMap<String, Object> paraMap) {

		// 책 대여기간 연장
		int result = dao.extendBook(paraMap);
		
		return result;
	}


	// 검색어에 따른 대여 목록 결과의 총 페이지 구하기
	@Override
	public int findTotalPageWith(HashMap<String, String> paraMap) {

		int page = dao.findTotalPageWith(paraMap);
		return page;
	}


	// 검색어가 없을 때 나오는 대여 목록의 총 페이지
	@Override
	public int findTotalPage() {

		int page = dao.findTotalPage();
		return page;
	}


	// 검색어에 따른 대여 목록 검색 
	@Override
	public List<YSWRentalVO> findRentalListWith(HashMap<String, Object> optionMap) {

		List<YSWRentalVO> rentalVOList = dao.findRentalListWith(optionMap);
		return rentalVOList;
	}


	// 검색어 없이 대여 목록 검색
	@Override
	public List<YSWRentalVO> findRentalList(HashMap<String, Object> optionMap) {

		List<YSWRentalVO> rentalVOList = dao.findRentalList(optionMap);
		return rentalVOList;
	}

}
