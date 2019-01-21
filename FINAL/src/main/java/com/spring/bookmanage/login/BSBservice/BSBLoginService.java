package com.spring.bookmanage.login.BSBservice;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.login.BSBmodel.BSBInterLoginDAO;
import com.spring.bookmanage.login.BSBmodel.BSBMemberVO;

@Service
public class BSBLoginService implements BSBInterLoginService{
	
	// =====#31.Service 선언 =====

		
		// =====#34.의존객체 주입하기(DI : Dependency Injection) =====
		@Autowired
		private BSBInterLoginDAO dao;
		
		// =====#45. 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기(DI : Dependency Injection) =====
		@Autowired
		private AES256 aes;

		@Override
		public BSBMemberVO getLoginMember(HashMap<String, String> map) {
			BSBMemberVO loginuser = dao.getLoginMember(map);
			return loginuser;
		}

		
}
