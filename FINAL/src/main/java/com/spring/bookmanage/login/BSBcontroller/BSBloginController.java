package com.spring.bookmanage.login.BSBcontroller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.SHA256;
import com.spring.bookmanage.login.BSBmodel.BSBMemberVO;
import com.spring.bookmanage.login.BSBservice.BSBInterLoginService;

@Controller
public class BSBloginController {
	
	// =====#35.의존객체 주입하기(DI : Dependency Injection) =====
			@Autowired
			private BSBInterLoginService service;
			
			
	
	@RequestMapping(value="/memberLogin.ana",method= {RequestMethod.GET})
	public String adminLogin(HttpServletRequest req) {

		return "memberLoginform.notiles";
	}
	
	@RequestMapping(value="/memberLoginEnd.ana",method= {RequestMethod.POST})
	public String adminLoginEnd(HttpServletRequest req) {
		
			String memberid = req.getParameter("memberid");
			String pwd = req.getParameter("pwd");
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("MEMBERID", memberid);
			map.put("PWD", SHA256.encrypt(pwd));
			
			BSBMemberVO loginuser = service.getLoginMember(map);
			////////////////////////////////////////////////////
			
			HttpSession session = req.getSession();
			
			if(loginuser == null) {
				String msg = "아이디 또는 암호가 틀립니다.";
				String loc = "javascript:history.back()";
				
				req.setAttribute("msg", msg);
				req.setAttribute("loc", loc);
				
				return "msg";
			
			}
			else {
				// 아무런 이상없이 로그인 하는 경우
				session.setAttribute("loginuser", loginuser);
				
				if(session.getAttribute("gobackURL") != null) { // 세션에 저장된 돌아갈 페이지의 주소(gobackURL)
					String gobackURL =  (String)session.getAttribute("gobackURL");
					
					req.setAttribute("gobackURL", gobackURL);
					
					session.removeAttribute("gobackURL");
				}
				
				
		
			return "memberLoginEnd.notiles";
		}
	
	}
	
	// ===== #50. 로그아웃 완료 요청. =====
			@RequestMapping(value="/memberLogout.ana", method={RequestMethod.GET})
			public String logout(HttpServletRequest req, HttpSession session) {
				
				 session.invalidate();
			  	
				 String msg = "로그아웃 되었습니다."; 
				 String ctxPath = req.getContextPath();
				 String loc = ctxPath+"/memberLogin.ana";
					
				 req.setAttribute("msg", msg);
				 req.setAttribute("loc", loc);
					
				 return "msg";
			}
	
	
}
