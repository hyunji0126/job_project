package com.spring.mvc.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.mvc.board.commons.PageVo;
import com.spring.mvc.board.commons.SearchVO;
import com.spring.mvc.board.model.BoardVO;

public interface IBoardService {
	// 게시름 등록 가능
	void insert(BoardVO article);

	/*
	 - MyBatis로 DB연동을 진행할 떄 파라미터 값이 2개 이상이라면
	 1. @Param을 이용해서 작성
	 2. Map으로 포장해서 전송하는 법
	 3. 객체 하나를 매개값을 보내는 법
	 들을 적절하게 상황에 맞게 선택


	//	@Param을 이용한 매개값 전달 방식
	//	@Param("xml파일 내에서 사용할 이름") 매개변수
	// 게시글 전체 조회 기능
	List<BoardVO> getArticleList(@Param("paging") PageVo paging, 
								 @Param("keyword") String keyword,
								 @Param("condition") String condition);
	 */

	//	여러개의 파라미터들을 Map으로 포장해서 보내는 방식
	//	<key, value> : key : xml파일에서 사용할 이름, value : 값 (Object(모든 클래스의 부모) 타입)
	//	List<BoardVO> getArticleList(Map<String, Object> datas);
	List<BoardVO> getArticleList(SearchVO search);

	// 게시글 상세 조회 기능
	BoardVO getArticle(int boardNo);

	// 게시글 수정 기능
	void update(BoardVO article);

	// 게시글 삭제 기능
	void delete(int boardNo);

	//	게시물 수 조회 기능
	int countArticles(SearchVO search);
}
