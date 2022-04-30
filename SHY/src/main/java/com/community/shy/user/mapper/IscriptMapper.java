package com.community.shy.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.community.shy.user.script.scriptVO;

public interface IscriptMapper {
	// 스크랩하기 ( 추가 )
	public int insertScript(scriptVO vo);
	
	// 이미 찜한 게시물인지 조회
	public int getSTotal( @Param("bno_sc") int bno_sc, @Param("uses_Id") String uses_Id);
	// 스크랩 목록 1개 삭제
	public int cnxlScript(@Param("scrap_no") int scrap_no, @Param("scrap_type") int scrap_type);
	
	List<scriptVO> myRecord(String uses_Id);
	
}
