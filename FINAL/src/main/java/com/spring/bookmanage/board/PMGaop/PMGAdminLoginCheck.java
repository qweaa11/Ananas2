package com.spring.bookmanage.board.PMGaop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PMGAdminLoginCheck {

	@Pointcut("execution(public * com.spring..*Controller.requireLogin_*(..))")
	public void requireLogin() { }
	
	@Before("requireLogin()")
	public void before(JoinPoint joinPoint) {
		HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[1];
		HttpSession session = request.getSession();
		
		HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
		
	}
	
}
