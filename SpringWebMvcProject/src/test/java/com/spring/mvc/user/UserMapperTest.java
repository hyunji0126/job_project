package com.spring.mvc.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class UserMapperTest {

	//	Mapper 계층의 객체가 필요
	@Autowired
	private IUserMapper mapper;

	//회원 가입을 아무 아이디로 진행해 보세요.
	@Test
	public void RegistTest() {
		UserVO vo = new UserVO();
		vo.setAccount("abc111");
		vo.setPassword("abcd");
		vo.setName("홍길동");

		mapper.register(vo);
	}

	//위에서 회원 가입한 아이디로 중복 확인을 해서
	//COUNT(*)를 이용해서 1이 리턴이 되는지 확인하세요.
	@Test
	public void check() {
		System.out.println("중복확인");
		int result = mapper.checkId("abc111");
		if(result == 1) {
			System.out.println("아이디가 이미 존재함!");
		} else {
			System.out.println("아이디 사용 가능~!");
		}
	}

	//가입한 회원의 모든 정보를 얻어내서 출력해 보세요.
	@Test
	public void selectTest() {
		System.out.println(mapper.selectOne("abc111"));
	}

	//위에서 가입한 계정의 탈퇴를 진행해 보세요.
	//탈퇴가 성공했는지의 여부도 체크해 보세요.
	@Test
	public void deleteTest() {
		System.out.println("회원 탈퇴를 진행합니다.");
		mapper.delete("abc111");
		if(mapper.selectOne("abc111") == null){
			System.out.println("탈퇴 성공");
		} else {
			System.out.println("탈퇴 실패");
		}

	}
}
