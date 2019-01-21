package com.spring.bookmanage.JDSaop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.spring.bookmanage.common.MyUtil;


//===== #52. 공통관심사 클래스(Aspect 클래스) 생성하기. =====
@Aspect
@Component
public class LoginCheck {
	
	// ===== Pointcut 을 생성한다. =====
	@Pointcut("execution(public * com.spring..*Controller.requireLogin_*(..))")
	public void requireLogin() {}
		
	// === Before Advice 선언 및 Before Advice 내용 구현하기
	@Before("requireLogin()")
	public void before(JoinPoint joinPoint) {
		// JoinPoint joinPoint는 포인트멋되어진 주업무의 메소드이다.
		
		// 로그인 유무를 확인하기 위해서 request 를 통해 session을 얻어온다.
		HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[0];
		HttpSession session = request.getSession();
		
		HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
		
		if(session.getAttribute("loginLibrarian") == null) {
			// 해당 요청자가 로그인을 하지 않은 상태이라면 로그인 하는 페이지로 이동시키겠다.
			try {
				String msg ="먼저 로그인 하세요~~!";
				String loc = request.getContextPath()+"/FINAL/login.ana";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				// >>> 로그인 성공 후 로그인 하기전 페이지로 돌아가는 작업하기 <<<
				// ===>>> 현재 페이지 주소(URL) 알아내기 <<<===
				String url = MyUtil.getCurrentURL(request);
				
				session.setAttribute("gobackURL", url);
				// 세션에 url 값을 저장해준다.
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/msg.jsp");
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(session.getAttribute("loginAdmin") == null) {
			// 해당 요청자가 로그인을 하지 않은 상태이라면 로그인 하는 페이지로 이동시키겠다.
			try {
				String msg ="먼저 로그인 하세요~~!";
				String loc = request.getContextPath()+"/FINAL/login.ana";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				// >>> 로그인 성공 후 로그인 하기전 페이지로 돌아가는 작업하기 <<<
				// ===>>> 현재 페이지 주소(URL) 알아내기 <<<===
				String url = MyUtil.getCurrentURL(request);
				
				session.setAttribute("gobackURL", url);
				// 세션에 url 값을 저장해준다.
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/msg.jsp");
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}//end of before(JoinPoint joinPoint)
		
	

}
