package com.spring.mvc.user;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	
	@PostMapping("/checkId")
	public String checkId(@RequestBody String account){ //login-modal.jsp에서 전달받을 값을 'text'로해서 String 값이 리턴 타입 dataType : 'text'
		System.out.println("/user/checkId : POST");
		System.out.println("param : " + account);
		 
		int checkNum =  service.checkId(account); //checkNum여기에 1아니면 0이 올것
		
		if(checkNum == 1) {
			System.out.println("아이디가 중복됨!");
			return "duplicated";
		} else {
			System.out.println("이이디 사용 가능~!");
			return "available";
		}
	}
	
	//회원가입 요청 처리
	@PostMapping("/")
	public String register(@RequestBody UserVO vo) {
		System.out.println("/user/: POST");
		service.register(vo);
		return "joinSucess";
	}
	
	//로그인 요청 처리
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO vo, 
							/* HttpServletRequest request */
							HttpSession session, HttpServletResponse response) {
		System.out.println("/user/loginCheck : POST");
		System.out.println("param : " + vo);
		
		// 서버에서 세션 객체를 얻는 방법
		// 1. HttpServletRequest 객체 사용
//		HttpSession session = request.getSession();
		
		// 2. 매개값으로 HttpSession 객체 받아서 사용
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserVO dbData = service.selectOne(vo.getAccount());
		
		if(dbData != null) {
			if(encoder.matches(vo.getPassword(), dbData.getPassword())) {
				//로그인 성공 회원을 대상으로 세션 정보를 생성
				session.setAttribute("login", dbData);
				
				long limitTime = 60 * 60 * 24 * 90; //3달가량 시간 지정
				
				// 자동 로긘 체크시 처리해야 할 내용
				if(vo.isAutoLogin()) {
					// 자동 로긘을 희망하는 경우
					// 쿠키를 이용하여 자동 로긘 정보를 저장
					System.out.println("자동 로긘 쿠키 굽는중");
					// 세션 아이디를 가지고 와서 쿠키에 저장(고유한 값이 필요해서)
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					loginCookie.setPath("/"); // 쿠키가 동작할 수 있는 유효한 url
					loginCookie.setMaxAge((int) limitTime);
					response.addCookie(loginCookie);
					
					// 자동 로긘 유지 시간을 날짜 객체로 변환 (db에 삽입하기 위해, 밀리초)
					long expiredDate = System.currentTimeMillis() + (limitTime * 1000);
					// Date 객체의 생성자에 매개값으로 밀리초의 정수를 전달하면 날짜 형태로 변경해 줍니다.
					Date limitDate = new Date(expiredDate);
					System.out.println("자동 로긘 만료 시간 : " + limitDate);
					service.keepLogin(session.getId(), limitDate, vo.getAccount());
				}
				
				return "loginSuccess";
			}
			return "pwFail";
		} else {
			return "idFail";
		}
	}
	
	// 로그아웃처리
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session, RedirectAttributes ra,
							   HttpServletRequest request, HttpServletResponse response) {
		System.out.println("/user/logout : GET");
		
		UserVO user = (UserVO) session.getAttribute("login"); // 세션삭제 전에 아이디꺼내놓기
		
//		session.invalidate(); //세션자체를 무효화
		
		session.removeAttribute("login");
		
		/*
		 자동 로그인 쿠키가 있는지를 확인(없는 사람도 있으니까)
		 쿠키가 존재한다면 쿠키의 수명을 0
		 쿠키를 지울때도 setPath를 동일하게 지정해줘야합니다. 
		 db의 내용도 바꿔줘야함
		 세션 ID:'none',만료시간 : 지금이시간 ->	
		 */
		Cookie loginCookie = WebUtils.getCookie(request,"loginCookie");
		
		if(loginCookie != null) {
			System.out.println("쿠키가 없다!");
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			service.keepLogin("none", new Date(), user.getAccount());
		}
		
		
		ra.addFlashAttribute("msg", "logout");
		
		/*
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", "value");
		mv.setViewName("redirect:/");
		
		return mv;
		*/
		// ㄴ> 위의 내용 조금 더 짧게
		return new ModelAndView("redirect:/");
	}
	
	
	
	
	
}
