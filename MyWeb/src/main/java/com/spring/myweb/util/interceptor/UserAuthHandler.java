package com.spring.myweb.util.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.myweb.command.UserVO;

public class UserAuthHandler implements HandlerInterceptor{
//	회원권한이 필요한 페이지 요청이 들어왔을때 요청을 가로채 확일할 인터셉터
//	글쓰기 화면과 마이페이지 화면 들어가는 요청을 가로채 검사하도록 합시다
//	권한이 없다면 로그인 페이지로 보내줍시다
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("회원권한이 필요한 페이지 요청 인터셉터");
		String writer = request.getParameter("wrtier");
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("login");

		if(vo==null) {
			response.sendRedirect( request.getContextPath() + "/user/userLogin");
			return false;
		}
		return true;
		
		
		
		
		
		
	}
	
	
	
	
	
}
