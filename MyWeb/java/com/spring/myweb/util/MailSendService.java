package com.spring.myweb.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service //@Component 나 @Service를 쓰던 기능은 같다
public class MailSendService {

	@Autowired
	private JavaMailSender mailSender;
	private int authNum; 

	// 난수 발생 (여러분들 맘대로 하시면 됩니다.)
	public void makeRandomNumber() {
		// 난수의 범위 : 111111 ~ 999999
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("생성된 인증번호 : " + checkNum);
		authNum = checkNum;
	}

	// 회원가입시 사용할 이메일 양식
	public String joinEmail(String email) {
		makeRandomNumber();//joinEmail 부를떄마다 난수 생성
		String setFrom = "xxxx@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력.
		String toMail = email; // 수신받을 이메일
		String title = "회원가입인증 이메일 입니다."; // 이메일 제목
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + 
				"인증 번호는 " + authNum + "입니다." + "<br>" + 
				"해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNum);
	}

	// 비밀번호 찾기 할 떄 사용할 이메일 양식
	public void findPwEmail() {

	}
	
	// 기타등등의 이메일 양식
	
	// 이메일 전송 메서드
	public void mailSend(String setFrom, String toMail, String title, String Content) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// true 매개값을 전달하면 MultPart형식의 메세지 전달이 가능. 문자 인코딩 설정도 가능.
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(Content);
			// true전달 -> html형식으로 전송, 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(Content, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
