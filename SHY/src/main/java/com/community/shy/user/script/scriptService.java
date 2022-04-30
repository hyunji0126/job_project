package com.community.shy.user.script;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.shy.board.command.CommentVO;
import com.community.shy.board.util.PageVO;
import com.community.shy.user.mapper.IscriptMapper;

@Service
public class scriptService implements IscriptService {

	@Autowired 
	private IscriptMapper mapper;
	
	// 스크랩추가
	@Override
	public int insertScript(scriptVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertScript(vo);
	}

	
//	게시물수 
	public int getSTotal(int bno_sc, String uses_Id) {
		return mapper.getSTotal(bno_sc, uses_Id);
	}
	
	@Override
	public List<scriptVO> myRecord(String uses_Id) {
		// TODO Auto-generated method stub
		return mapper.myRecord(uses_Id);
	}

	@Override
	public int cnxlScript(int scrap_no, int scrap_type) {
		// TODO Auto-generated method stub
		return mapper.cnxlScript(scrap_no,scrap_type);
	}

	

}
