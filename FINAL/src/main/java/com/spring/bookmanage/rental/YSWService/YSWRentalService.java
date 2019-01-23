package com.spring.bookmanage.rental.YSWService;

import java.util.ArrayList;
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
	
	
	// 대출 목록 페이지 보여주기
	@Override
	public List<YSWRentalVO> findRentalList() {

		List<YSWRentalVO> rentalList = dao.findRentalList();
		return rentalList;
	}

	
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

}
