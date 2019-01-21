package com.spring.bookmanage.JDSmodel;

import java.util.HashMap;

import com.spring.bookmanage.JDSmodel.LibrarianVO;

//model단(DAO)의 인터페이스 선언

public interface InterLoginDAO {

			LibrarianVO getLoginLibrarian(HashMap<String, String> map);//사서 로그인 여부 알아보기

			AdminVO getLoginAdmin(HashMap<String, String> map);//관리자 로그인 여부 알아보기

}
