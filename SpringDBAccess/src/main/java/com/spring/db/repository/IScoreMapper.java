package com.spring.db.repository;

import java.util.List;

import com.spring.db.model.ScoreVO;

public interface IScoreMapper {
//마이바티스는 클래스가 없기때문에 인터페이스가 없으면 안됨
	//점수 등록 기능
	void insertScore(ScoreVO score);
	
	//점수 전체 조회 기능
	List<ScoreVO> selectAllScores();
	
	//점수 삭제 기능
	void deleteScore(int num);
	
	//점수 개별 조회 기능
	ScoreVO selectOne(int num);

	
}
