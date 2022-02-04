package com.spring.mvc.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private IBoardService service;

	//	게시글 목록 불러오기 요청
	@GetMapping("/list")
	public String list(Model model) { //model 매개변수 필요 list를 담을
		System.out.println("/board/list : GET");
		model.addAttribute("articles",service.getArticleList());

		return "board/list";
	}

	//	글쓰기 페이지로 이동 요청

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
	@GetMapping("/content")
	public String content(@RequestParam("boardNo") int boardNo, Model model) {
		System.out.println("/board/content: GET");
		System.out.println("요청된 글 번호: " + boardNo);
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/content";
	}

	// 게시글 수정
	/* 내 풀이 */
	@PostMapping("/modify")
	public String modify(BoardVO article) {
		
		System.out.println("/board/modify : POST");
		service.update(article);

		return "redirect:/board/content?boardNo=" + article.getBoardNo();
	}
	

	// 게시글 삭제
	/* 내 풀이 */
	@PostMapping("/delete")
	public String delete(@RequestParam("boardNo") int boardNo) {
		System.out.println("/board/delete : POST");
		service.delete(boardNo);
		
		return "redirect:/board/list";
	}











}
