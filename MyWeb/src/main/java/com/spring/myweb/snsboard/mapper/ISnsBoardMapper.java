package com.spring.myweb.snsboard.mapper;

import java.util.List;

import com.spring.myweb.command.SnsBoardVO;

public interface ISnsBoardMapper {

	// 등록
	void insert(SnsBoardVO vo);
	
	// 목록
	List<SnsBoardVO> getlist();
	
	// 상세
	SnsBoardVO getDetail(int bno);
	
	// 삭제
	void delete(int bno);
	
}
