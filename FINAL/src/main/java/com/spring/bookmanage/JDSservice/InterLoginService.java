package com.spring.bookmanage.JDSservice;

import java.util.HashMap;

import com.spring.bookmanage.JDSmodel.AdminVO;
import com.spring.bookmanage.JDSmodel.LibrarianVO;

public interface InterLoginService {


	LibrarianVO getLoginLibrarian(HashMap<String, String> map);// 사서 로그인 여부 알아보기 

	AdminVO getLoginAdmin(HashMap<String, String> map);// 관리자 로그인 여부 알아보기 
	
}
