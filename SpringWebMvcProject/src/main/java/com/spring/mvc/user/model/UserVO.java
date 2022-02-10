package com.spring.mvc.user.model;
/*
CREATE TABLE mvc_user(
    account VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(100) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    reg_date DATE DEFAULT sysdate
);
 */

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
	
	private String account;
	private String password;
	private String name;
	private Timestamp regDate;
	private String sessionId;
	private Timestamp limitTime;
	
	// 자동 로긘 체크 여부
	private boolean autoLogin; //db에없는
	
	
	
}
