package com.community.shy.board.JobBoard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.community.shy.board.command.BoardVO;
import com.community.shy.board.util.PageVO;


public interface IBoardService {
//	게시판 구분 
	List<BoardVO> getJBoard(int board_type ,PageVO paging);
//관리자 게시판 
	List<BoardVO> getjBoard(int board_no);
//	글등록
	void BoardJRegist(BoardVO vo);
//	삭제
	void JBoardDelete(int board_no);
//	상세보기
	BoardVO JBoardDetail(int board_no, int board_type, String user_id);
	
	//로그인 없이 상세보기
	BoardVO jBoardDetail(int board_no, int board_type);
	
//	총게시물수 
	int getJTotal(PageVO vo, int board_type);
//	게시물을 만들때 타입을 전달
	List<BoardVO>  getBoard(int board_type);
//	게시물 수정
	void JBoardUpdate(BoardVO vo);
//  메인용 리스트
	List<BoardVO> mainBoard(BoardVO vo);

	List<BoardVO> getJBoardForMain(ArrayList board_types);
	
	//내가 쓴글 불러오기
	List<BoardVO> myRecord(String writer);
	
	//베스트 글 불러오기
	List<BoardVO> getBest();
	
//  조회수	
	boolean updateHit(int board_no);
//  좋아요,싫어요
	void insertBoardCnt(Map<String, Object> param);
	
	//어드민용 메인페이지
		List<BoardVO> getAdminList();
		//어드민용 유저게시판 정보 
		List<BoardVO> getUserBoardList(String board_writer);
		//전체 페이지
		List<BoardVO> getAllboard();
		//어드민 데이터보여주긔 
		List<Integer> getAllTotal();
		//어드민 날자 조회 
		List<HashMap<String, Object>> admin_findDate(String date1 , String date2);
}
