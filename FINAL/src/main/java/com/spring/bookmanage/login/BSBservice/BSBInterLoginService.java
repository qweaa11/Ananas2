package com.spring.bookmanage.login.BSBservice;

import java.util.HashMap;
import java.util.List;

import com.spring.bookmanage.login.BSBmodel.BSBMemberVO;

public interface BSBInterLoginService {

	BSBMemberVO getLoginMember(HashMap<String, String> map);

	
	
}
