package com.spring.myweb.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.mapper.IReplyMapper;
import com.spring.myweb.util.PageVO;

@Service //이거뺴먹으면 null어쩌구가 뜬다
public class ReplyService implements IReplyService {

	@Autowired
	private IReplyMapper mapper;

	@Override
	public void replyRegist(ReplyVO vo) {
		mapper.replyRegist(vo);
	}

	@Override
	public List<ReplyVO> getList(PageVO vo, int bno) {
		Map<String, Object> data = new HashMap<>();
		data.put("paging", vo);
		data.put("bno", bno);
		
		return mapper.getList(data);
	}

	@Override
	public int getTotal(int bno) {
		return mapper.getTotal(bno);//내가 글번호줄께ㅓ 댓글이 몇개있는지 나한테 리턴해줭
	}

	@Override
	public int pwCheck(ReplyVO vo) {
		return mapper.pwCheck(vo);
	}

	@Override
	public void update(ReplyVO vo) {
		mapper.update(vo);
	}

	@Override
	public void delete(int rno) {
		mapper.delete(rno);
	}

}
