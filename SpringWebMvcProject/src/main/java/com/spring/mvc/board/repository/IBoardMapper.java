package com.spring.mvc.board.repository;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;

// 게시관 고나련 CRUD 추상 메서드 선언.(Create Regist Update Delete)
public interface IBoardMapper {
	// 게시름 등록 가능
	void insert(BoardVO article);
	
	// 게시글 전체 조회 기능
	List<BoardVO> getArticleList();//매개변수 지금 당장은 필요없는듯하여 작성하지않음
	
	// 게시글 상세 조회 기능
	BoardVO getArticle(int boardNo);
	
	// 게시글 수정 기능
	void update(BoardVO article);
	
	// 게시글 삭제 기능
	void delete(int boardNo);
}
