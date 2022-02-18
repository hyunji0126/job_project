package com.spring.myweb.snsboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;

@Service
public class SnsBoardService implements ISnsBoardService {
	
	@Autowired
	private ISnsBoardMapper mapper; //서비스는 mapper와 의존관계
	
	@Override
	public void insert(SnsBoardVO vo) {
		mapper.insert(vo);
	}

	@Override
	public List<SnsBoardVO> getlist() {
		return null;
	}

	@Override
	public SnsBoardVO getDetail(int bno) {
		return null;
	}

	@Override
	public void delete(int bno) {

	}

}
