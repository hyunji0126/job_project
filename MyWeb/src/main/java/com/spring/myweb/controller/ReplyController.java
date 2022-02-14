package com.spring.myweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.service.IReplyService;
import com.spring.myweb.util.PageVO;

@RestController//비동기방식 사용할꺼라서 restcontroller
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	// 댓글등록
	@PostMapping("/replyRegist")
	public String replyRegist(@RequestBody ReplyVO vo) {
		System.out.println("댓글 등록 요청이 들어옴");
		System.out.println(vo);
		service.replyRegist(vo);
		
		
		return "regSuccess";
	}
	
	// 페이징이 추가된 댓글 목록
	@GetMapping("/getList/{bno}/{pageNum}")
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum ) {
		//	1. getList 메서드가 (글번호, 페이지번호)를 매개값으로 받습니다.
		
		//	2. Mapper 인터페이스에 각각 다른 값을 전달하기 위해 Map을 쓰던지, @Param을 사용
		
		//	3. replyMapper.xml sql문을 페이징 쿼리로 작성합니다.
		
		//	4. 레스트 방식은 화면에 필요한 값을 여러개 보낼 떄, 리턴에 Map이나 VO형식으로 필요한 데이터를 한번에 담아서 처리
		//	ㄴ>더보기눌러서 목록더보기식이면 페이지 크리에이터는 필요없음
		
		//	댓글 목록 리스트와 전체 대슥르 개수를 함꼐 전달할 예정 => Map으로 포장해서 데이터로 전달할 예정
		
//		System.out.println("받니");
		
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum); // 화면에서 전달된 페이지 번호
		vo.setCountPerPage(5); // 댓글은 한 화면에서 5개씩 보여주겠다
		
		List<ReplyVO> list = service.getList(vo, bno); // 댓글 목록 데이터
		int total = service.getTotal(bno); // 전체 댓글 개수.		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("total", total);
		
		return map;
 	}
	
	
	@PostMapping("/update")
	public String update(@RequestBody ReplyVO vo) { //@RequestBody json으로 값이 날라올떄는 rewustebody를 사용한다
		System.out.println("controller-update");
		
		// 비밀번호 확인
		int result = service.pwCheck(vo);
		
		if(result == 1) { //pw가 맞는경우
			service.update(vo);
			return "modSuccess";
		} else {
			return "pwFail";
		}
	}
	
	@PostMapping("/delete")
	public String delete(@RequestBody ReplyVO vo) {
		int result = service.pwCheck(vo);
		
		if(result == 1) {
			service.delete(vo.getRno());
			return "delSuccess";
		} else {
			System.out.println("비번이 틀렸음");
			return "pwFail";
		}
	}
	
	
	
	
}
