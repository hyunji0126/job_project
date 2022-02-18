package com.spring.myweb.command;
/*
 * --SNS게시판
 
	CREATE TABLE snsboard(
	    bno  NUMBER(10,0) PRIMARY KEY,
	    writer VARCHAR2(50) NOT NULL,
	    uploadpath VARCHAR2(100) NOT NULL,
	    fileloca VARCHAR2(100) NOT NULL,
	    filename VARCHAR2(50) NOT NULL,
	    filerealname VARCHAR2(50) NOT NULL,
	    content VARCHAR2(2000),
	    regdate DATE DEFAULT sysdate
	);

	CREATE SEQUENCE snsboard_seq
	    START WITH 1
	    INCREMENT BY 1
	    MAXVALUE 1000
	    NOCYCLE
	    NOCACHE;
 */

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor // 아무값도 받징낳느니 생성자생성 기본생성자 기본값을 아무것도 받지않음
@AllArgsConstructor// 모든값을 매개값으로 받는 생성자
@ToString
public class SnsBoardVO {

	private int bno;
	private String writer;
	private String uploadpath;
	private String fileloca;
	private String filename;
	private String filerealname;
	private String content;
	private Timestamp regdate;
	
	
	
	
	
	
	
}
