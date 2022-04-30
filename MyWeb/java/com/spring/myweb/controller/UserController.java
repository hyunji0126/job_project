package com.spring.myweb.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.MailSendService;

@RequestMapping("/user")
@Controller //빈등록하기위해서 붙여준 아노테이션
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private MailSendService mailService;
	
	//  주소 API
	//	devU01TX0FVVEgyMDIyMDIxNjExMzQ1NzExMjI1MTU=
	
	
	// 회원가입 페이지 이동
	@GetMapping("/userJoin")
	public void userJoin() {
		System.out.println("회원가입 페이지");
	}
	
	// 아이디 중복 확인(비동기)
	@ResponseBody //Rest Controller가 아닌 경우에는 아노테이션을 붙여야 통신이 가능
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String userid) { //@RequestBody json언어를 변환시키기 위해 필요함
		int result = service.idCheck(userid);
		if(result == 1) {
			return "dup";
		} else return "idPos";
	}
	
	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청이 들어옴");
		System.out.println("인증 이메일 : " + email);
		
		return mailService.joinEmail(email);
	}
	
	
	// 회원 가입 처리
	@PostMapping("/join")
	public String join(UserVO vo, RedirectAttributes ra) {
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess");
		return "redirect:/user/userLogin";
	}
		
	// 로그인 페이지 이동 요청
	@GetMapping("/userLogin")
	public void userLogin() {}
	
	// 로그인 요청
	/*
	 내 풀이
	@PostMapping("/login")
	public String login(UserVO vo) {
		service.login(vo.getUserId(), vo.getUserPw());
		return "redirect:/home";
	}
	*/
	
	/* 선생님 풀이 */
	@PostMapping("/login")
	public String login(String userId, String userPw, Model model) {
		UserVO vo = service.login(userId, userPw);
		model.addAttribute("user", vo);
		return "/user/userLogin"; // contorller에서 나가서 userLogin으로 갈때 interceptor가 가로채서 거를것이다 
	}
	
	@GetMapping("/userMyPage")
	public void userMyPage(HttpSession session, Model model) {
		System.out.println("usermypage");
		// 세션 데이터에서 id를 뽑아야 sql문을 돌릴 수 있겠죠?
		String id = ((UserVO)session.getAttribute("login")).getUserId();
		System.out.println(id);
		UserVO userInfo = service.getInfo(id);
		System.out.println(userInfo);
		model.addAttribute("userInfo", userInfo);
	}
	
	// 수정로직
	@PostMapping("/userUpdate")
	public String userUpdate(UserVO vo, RedirectAttributes ra) {
		System.out.println("update");
		System.out.println(vo);
		service.updateUser(vo);
		ra.addFlashAttribute("msg","수정이 완료되었습니다.");
		return "redirect:/";
	}
	
}
