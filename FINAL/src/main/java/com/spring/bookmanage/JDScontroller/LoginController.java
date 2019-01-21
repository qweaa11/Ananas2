package com.spring.bookmanage.JDScontroller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.JDSmodel.AdminVO;
import com.spring.bookmanage.JDSmodel.LibrarianVO; 
import com.spring.bookmanage.JDSservice.InterLoginService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.SHA256;

	//===== 컨트롤러 선언  =====
	@Controller
	@Component
	/* XML에서 빈을 만드는 대신에 클래스명 앞에 @Component 어노테이션을 적어주면 해당 클래스는 bean으로 자동 등록된다. 
	  그리고 bean의 이름(첫글자는 소문자)은 해당 클래스명이 된다. */
	public class LoginController {
		
		// ===== 의존객체 주입하기(DI : Dependency Injection) =====
		@Autowired
		private InterLoginService service;
		
		// ===== 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기(DI : Dependency Injection) =====
		@Autowired
		private AES256 aes;


		
		// ===== 로그인 폼 페이지 요청 =====/
		@RequestMapping(value="/login.ana", method= {RequestMethod.GET})
		public String login(HttpServletRequest req) {
			
			String cookie_key = "";
			String cookie_value = "";
			
			boolean flag1 = false;
			boolean flag2 = false;
			
			Cookie[] cookieArr = req.getCookies();
			if(cookieArr != null) {
				// 사용자 클라이언트 컴퓨터에서 보내온 쿠키의 정보가 있다라면
			
				for(Cookie c : cookieArr) {
					cookie_key = c.getName(); // 쿠키의 이름(키값)을 꺼내오는 메소드.
					
					if("saveid1".equals(cookie_key)) {
						cookie_value = c.getValue();
						flag1 = true;
						req.setAttribute("saveid1", cookie_value);
						req.setAttribute("flag1", flag1);
						break;
					}
				}

				for(Cookie c : cookieArr) {
					cookie_key = c.getName(); // 쿠키의 이름(키값)을 꺼내오는 메소드.
					
					if("saveid2".equals(cookie_key)) {
						cookie_value = c.getValue(); // 쿠키의 value 값을 꺼내오는 메소드. 
						flag2 = true;
						req.setAttribute("saveid2", cookie_value);
						req.setAttribute("flag2", flag2);
						break; // for 탈출
					}
					
				}// end of for-------------------
				
			}else {
				req.removeAttribute("saveid1");
				req.removeAttribute("saveid2");
			}

			return "loginform.notiles";
			
		}
	
		// ===== 사서 로그인 여부 알아오기 
		@RequestMapping(value="/loginEnd1.ana", method= {RequestMethod.POST})
		public String loginEnd(HttpServletRequest req, HttpServletResponse res) {
			
			String libid = req.getParameter("libid");
			String pwd = req.getParameter("pwd");
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("LIBID", libid);
			map.put("PWD", SHA256.encrypt(pwd));
			
			LibrarianVO loginLibrarian = service.getLoginLibrarian(map);
			
			HttpSession session = req.getSession();
			
			if(loginLibrarian == null) {
				String msg = "아이디 또는 암호가 틀립니다.";
				String loc = "javascript:history.back()";
				
				req.setAttribute("msg", msg);
				req.setAttribute("loc", loc);
				
				return "msg";
			}
			
			else {
				// 아무런 이상없이 로그인 하는 경우
				session.setAttribute("loginLibrarian", loginLibrarian);
			
				if(session.getAttribute("gobackURL") != null) {
					
					String gobackURL = (String)session.getAttribute("gobackURL");
					req.setAttribute("gobackURL", gobackURL);
					
					session.removeAttribute("gobackURL");
				}

				String saveid1 = req.getParameter("saveid1");
				
				Cookie cookie1 = new Cookie("saveid1", loginLibrarian.getLibid());

				if(saveid1 != null) {
				
					cookie1.setMaxAge(7*24*60*60); 
				
				}
				else {
					
					cookie1.setMaxAge(0); 

				}
				
				cookie1.setPath("/");

				res.addCookie(cookie1);
				
				return "Login/loginEnd1.tiles1";
				
			}
		}//end of public String loginEnd1(HttpServletRequest req)----------------
	
		
		// ===== 관리자 로그인 여부 알아오기 
		@RequestMapping(value="/loginEnd2.ana", method= {RequestMethod.POST})
		public String loginEnd1(HttpServletRequest req, HttpServletResponse res) {
			
			String adminid = req.getParameter("adminid");
			String pwd = req.getParameter("pwd");
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ADMINID", adminid);
			map.put("PWD", pwd);
			
			AdminVO loginAdmin = service.getLoginAdmin(map);
			
			HttpSession session = req.getSession();
			
			if(loginAdmin == null) {
				String msg = "아이디 또는 암호가 틀립니다.";
				String loc = "javascript:history.back()";
				
				req.setAttribute("msg", msg);
				req.setAttribute("loc", loc);
				
				return "msg";
			}
			
			else {
				// 아무런 이상없이 로그인 하는 경우
				session.setAttribute("loginAdmin", loginAdmin);
			
				if(session.getAttribute("gobackURL") != null) {
					
					String gobackURL = (String)session.getAttribute("gobackURL");
					req.setAttribute("gobackURL", gobackURL);
					
					session.removeAttribute("gobackURL");
				}
				
				
				String saveid2 = req.getParameter("saveid2");
				
				Cookie cookie2 = new Cookie("saveid2", loginAdmin.getAdminid());

				if(saveid2 != null) {
				
					cookie2.setMaxAge(7*24*60*60); 
				
				}
				else {
					
					cookie2.setMaxAge(0); 

				}
				
				cookie2.setPath("/");

				res.addCookie(cookie2);

				return "Login/loginEnd2.tiles1";
				
			}
		}//end of public String loginEnd2(HttpServletRequest req)----------------
		

		// ===== 로그아웃 완료 요청 =====
		@RequestMapping(value="/logout.ana", method={RequestMethod.GET})
		public String logout(HttpServletRequest req, HttpSession session) {
			
			 session.invalidate();
		  	
			 String msg = "로그아웃 되었습니다."; 
			 String ctxPath = req.getContextPath();
			 String loc = "login.ana";
				
			 req.setAttribute("msg", msg);
			 req.setAttribute("loc", loc);
				
			 return "msg";
		}
		
	
	
		
		
		
		
		
			
	}
