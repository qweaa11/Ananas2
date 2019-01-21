package com.spring.bookmanage.JDSservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookmanage.JDSmodel.AdminVO;
import com.spring.bookmanage.JDSmodel.InterLoginDAO;
import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.common.AES256;

// ===== Service 선언 =====
@Service
public class LoginService implements InterLoginService{

		// ===== 의존객체 주입하기(DI : Dependency Injection) =====
		@Autowired
		private InterLoginDAO dao;
		
		// ===== 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기(DI : Dependency Injection) =====
		@Autowired
		private AES256 aes;

		// ===== 사서 로그인 여부 알아보기  =====
		@Override
		public LibrarianVO getLoginLibrarian(HashMap<String, String> map) {
			
			LibrarianVO loginLibrarian = dao.getLoginLibrarian(map);
			
			return loginLibrarian;
		}

		// ===== 관리자 로그인 여부 알아보기  =====
		@Override
		public AdminVO getLoginAdmin(HashMap<String, String> map) {
			
			AdminVO loginAdmin = dao.getLoginAdmin(map);
			
			return loginAdmin;
		}
}
