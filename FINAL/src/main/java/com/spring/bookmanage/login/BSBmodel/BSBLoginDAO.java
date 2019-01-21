package com.spring.bookmanage.login.BSBmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class BSBLoginDAO implements BSBInterLoginDAO{
	//=====#32.DAO 선언 =====


	
			// =====#33.의존객체 주입하기(DI : Dependency Injection) =====
			@Autowired
			private SqlSessionTemplate sqlsession;

			@Override
			public BSBMemberVO getLoginMember(HashMap<String, String> map) {
				BSBMemberVO loginuser = sqlsession.selectOne("BSB.getLoginMember", map);
				return loginuser;
			}

			
			

}
