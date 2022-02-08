package com.spring.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.commons.PageCreator;
import com.spring.mvc.board.commons.PageVo;
import com.spring.mvc.board.commons.SearchVO;
import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private IBoardService service;

	// 검색 처리 이후 게시글 목록 불러오기 요청
	@GetMapping("/list")
	public String list(SearchVO search , Model model) {
		System.out.println("/board/list : GET");
		System.out.println("페이지 번호 : " + search.getPage());
		System.out.println("검색어 : " + search.getKeyword());
		System.out.println("검색 조건 : " +  search.getCondition());
		
//		if(search.getKeyword()==null) {
//			search.setKeyword("");
//			search.setCondition("");
//		} 
//		ㄴ> 이거 없어도 됨 동적 sql문 작생했기 떄문에
		
		List<BoardVO> list = service.getArticleList(search);
		
		PageCreator pc = new PageCreator();
		pc.setPaging(search);
		pc.setArticleTotalCount(service.countArticles(search));
		
		model.addAttribute("articles", list);
		model.addAttribute("pc", pc);
		
		return "board/list";
	}
	/*
	//	게시글 목록 불러오기 요청
	@GetMapping("/list")
	public String list(Model model) { //model 매개변수 필요 list를 담을
		System.out.println("/board/list : GET");
		model.addAttribute("articles",service.getArticleList());

		return "board/list";
	}
	//	페이징 처리 이후 게시글 목록 불러오기 요청
	@GetMapping("/list")
	public String list(PageVo paging, Model model) {
		System.out.println("/board/list : GET");
		System.out.println("페이지 번호 : " + paging.getPage());
		
		List<BoardVO> list = service.getArticleList(paging);
		System.out.println("페이징 처리 후 게시물의 수 : " + list.size());
		
		PageCreator pc = new PageCreator();
		pc.setPaging(paging);
		pc.setArticleTotalCount(service.countArticles());
		
		model.addAttribute("articles", list);
		model.addAttribute("pc", pc);
		
		
		return "board/list";
	}
	 */


	//	글쓰기 페이지로 이동 요청
	@GetMapping("/write")
	public void write() {
		System.out.println("/board/write : GET");
	}


	//	게시글 DB 등록요청
	@PostMapping("/write")
	public String wrtie(BoardVO article) {
		System.out.println("/board/write : POST");
		service.insert(article);

		return "redirect:/board/list";
	}

	//게시글 상세보기 요청
	/* 내가 쓴 풀이
	@GetMapping("/content")
	public String content(Model model) {
		System.out.println("/board/content : GET");
		model.addAttribute("content", service.getArticle(42));

		return "board/content";
	}
	 */
	//	선생님 풀이
	/*
	  @PathVariable은 URL 경로에 변수를 포함시켜 주는방식
	  null이나 공백이 들어갈 수 있는 파라미터라면 적용하지 않는 것을 추천
	  파라미터값에 .이 포함되어 있으면 . 뒤의 값은 잘립니다
	  {} 안에 변수명을 지어주시고, @PathVariable 괄호안에 영역을 지목해서 값을 받아옵니다.
	 */
	//	@GetMapping("/content")
	@GetMapping("/content/{boardNo}") //${}안에는 아무말이나 써도됨 지목하기 위해서 이름을 달음, list.jsp에서 ?boardNo= 를 생략해서
	public String content(@PathVariable int boardNo, Model model, 
						  @ModelAttribute("p") SearchVO paging ) {
		//@RequestParam("boardNo") int boardNo --> boardNo 변수명이 동일하므로 @RequestParam("boardNo") 파라미터 변수명은 생략가능
		//@PathVariable("boardNo") 붙인건 /content/${boardNo}이 값을 뜯어오기위해 붙인 어노테이션 @RequestParam처럼 변수명이 동일하면 ()괄호안의 값 생략가능
		System.out.println("/board/content: GET");
		System.out.println("요청된 글 번호: " + boardNo);
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/content";
	}

	// 게시글 수정 화면 요청
	@GetMapping("/modify")
	public void modify(int boardNo, Model model, 
						@ModelAttribute("p") PageVo paging) {
		System.out.println("/board/modfiy : GET");
		model.addAttribute("article", service.getArticle(boardNo));
	}


	// 게시글 수정 처리 요청
	/* 내 풀이 */
	@PostMapping("/modify")
	public String modify(BoardVO article) {

		System.out.println("/board/modify : POST");
		service.update(article);

		return "redirect:/board/content/" + article.getBoardNo();
	}


	// 게시글 삭제
	/* 내 풀이 */
	@PostMapping("/delete")
	public String delete(int boardNo, RedirectAttributes ra) {
		System.out.println("/board/delete : POST");
		service.delete(boardNo);
		ra.addFlashAttribute("msg", "del success");
		return "redirect:/board/list";
	}











}
