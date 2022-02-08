package com.spring.mvc.board.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardVO {

	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Timestamp regDate;
	private int viewCnt;

	//newmark부착 여부 논리 타입 필드
	private boolean newMark;
}
