package com.spring.bookmanage.JDSmodel;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bookmanage.JDSmodel.LibrarianVO;

//===== DAO 선언 =====
@Repository
public class LoginDAO implements InterLoginDAO{

			// ===== 의존객체 주입하기(DI : Dependency Injection) =====
			@Autowired
			private SqlSessionTemplate sqlsession;

			// ===== 사서 로그인 여부 알아보기 =====
			@Override
			public LibrarianVO getLoginLibrarian(HashMap<String, String> map) {

				LibrarianVO loginLibrarian = sqlsession.selectOne("JDS.getLoginLibrarian", map);
				
				return loginLibrarian;
			}
	
			// ===== 관리자 로그인 여부 알아보기 =====
			@Override
			public AdminVO getLoginAdmin(HashMap<String, String> map) {
				
				AdminVO loginAdmin = sqlsession.selectOne("JDS.getLoginAdmin", map);
				
				return loginAdmin;
			}

}
