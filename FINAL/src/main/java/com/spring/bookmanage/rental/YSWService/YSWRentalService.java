package com.spring.bookmanage.rental.YSWService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
