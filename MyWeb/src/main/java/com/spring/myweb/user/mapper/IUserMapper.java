package com.spring.myweb.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.myweb.command.UserVO;

public interface IUserMapper {
	// 아이디 중복확인
	int idCheck(String id);
	
	// 회원 가입
	void join(UserVO vo);
	
	// 로그인
	UserVO login(@Param("id") String id, @Param("pw") String pw);
	
	// 회원 정보 얻어오기 -- 마이페이지에 들어가 회원정보 얻어오기
	UserVO getInfo(String id);
	
	// 회원 정보 수정
	void updateUser(UserVO vo);
	
	// 회워 정보 삭제
	void deleteUser(@Param("id") String id, @Param("pw") String pw);//삭제전에 pw받고 삭제할꺼면 매개변수에 id pw 두개 받고 로긘됐으면 그냥 삭제해줄꺼다하면 id만 매개변수로 받으면 된다.
	
}
