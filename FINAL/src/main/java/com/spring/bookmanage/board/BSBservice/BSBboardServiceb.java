package com.spring.bookmanage.board.BSBservice;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.board.BSBmodel.BSBInterBoardDAO;
import com.spring.bookmanage.board.BSBmodel.BSBboardVO;
import com.spring.bookmanage.common.AES256;



//===== #31. Service 선언  =====
@Service
public class BSBboardServiceb implements BSBInterBoardService {
	
	//===== #34. 의존객체 주입하기(DI: Dependency Injection) =====
	@Autowired
	private BSBInterBoardDAO dao;
	
	//===== #45_2. 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한  클래스 의존객체 주입하기 (DI: Dependency Injection)=====
		@Autowired
		private AES256 aes;

		@Override
		public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
			int count = dao. getTotalCountWithSearch(paraMap);
			return count;
		}

		@Override
		public int getTotalCountNoSearch() {
			int count = dao.getTotalCountNoSearch();
			return count;
		}

		@Override
		public List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap) {
			List<BSBboardVO> boardList = dao.boardListPaging(paraMap);
			return boardList;
		}

		@Override
		public int add(BSBboardVO boardvo) {
		
			int n = dao.add(boardvo);
			
			return n;
		}

		@Override
		public BSBboardVO getView(String idx) {
			BSBboardVO boardvo = dao.getView(idx);
			return boardvo;
		}
		

	
	

	
	
}
