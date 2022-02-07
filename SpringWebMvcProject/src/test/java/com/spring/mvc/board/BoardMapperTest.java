package com.spring.mvc.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;

@RunWith(SpringJUnit4ClassRunner.class) //SpringJUnit4ClassRunner 객체를 이용해서 BoardMapperTest의 테스트 가상환경을 구축하겠다
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class BoardMapperTest {

	@Autowired
	private IBoardMapper mapper; //IBoardMapper 인터페이스를 mapper에 주입
	//@ContextConfiguration 이걸 위에서 선언해놔서 @Autowired를 사용할 수 있음 xml설정을 끌고왔기 때문에

	//	게시글 등록 단위 테스트
	@Test //이걸붙여야 main만들지 않아도 됨... main뭔데?????
	public void insertTest() {
		for(int i=1; i<=300; i++) {
			BoardVO article  = new BoardVO();
			article.setTitle("테스트 제목입니다." + i);
			article.setWriter("김테스트" + i);
			article.setContent("테스트 중이니까 조용히 하세요." + i);
			mapper.insert(article);
		}
	}
	/*
	//게시글 목록 전체 조회 테스트
	//게시물 개수 몇개인지 출력하시고, 게시글 모든 내용을 toString()으로 출력하세요.
	@Test
	public void getListTest() {
		List<BoardVO> list = mapper.getArticleList();
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
	}
	 */

	//게시글 단일 조회 테스트
	//44번글을 조회해서 글 상세 내용을 출력해 주세요.
	@Test
	public void getArticle() {
		BoardVO vo = mapper.getArticle(44);
		System.out.println(vo);
	}
	//게시글 수정 테스트
	//BoardVO 객체를 하나 생성하시고, VO의 setter를 사용하여
	//수정 내용(글 제목, 글 내용)을 입력하고 수정을 진행해 보세요. (1번글의 제목, 내용)
	@Test
	public void update() {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(1);
		vo.setTitle("제목수정");
		vo.setContent("내용수정");
		mapper.update(vo);
		System.out.println("수정 후 정보 : " + mapper.getArticle(1));
	}

	//게시글 삭제 테스트
	//3번글을 삭제하세요. 삭제 후 상세보기를 통해 3번글을 가져왔을 때 null이 리턴되는지
	//조건문으로 확인해서 결과를 출력하세요. (null이 왔다는것 -> 삭제 성공! 게시글 없음!)
	@Test
	public void deleteTest() {
		mapper.delete(3);
		if(mapper.getArticle(3) == null) {
			System.out.println("delete success");
		} else {
			System.out.println("delete fail");
		}
	}

}
