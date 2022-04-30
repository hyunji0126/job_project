package com.community.shy.user.script;

import java.util.List;

public interface IscriptService {
	
	// 스크랩하기(추가)
	public int insertScript(scriptVO vo);
	
	// 이미 찜한 게시물인지 조회
	public int getSTotal( int bno_sc,String uses_Id);
	
	List<scriptVO> myRecord(String uses_Id);
	
	//스크랩 취소 리스트에서 삭제
	public int cnxlScript(int scrap_no , int scrap_type );
	
}
