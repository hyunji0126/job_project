package com.spring.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.db.model.BoardVO;
import com.spring.db.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private IBoardService service;

	//글 작성 화면을 열어주는 메서드
	@GetMapping("/write")
	public void write() {
		System.out.println("/board/write: GET");
	}

	//작성된 글 등록 처리 요청
	@PostMapping("/write")
	public String write(BoardVO vo) {
		System.out.println("/board/write: POST");
		service.insertArticle(vo);
		return "redirect:/board/list";
	}

	@GetMapping("/list")
	public void list(Model model) {
		System.out.println("/board/list: GET");
		model.addAttribute("articles", service.getArticles());
	}

	@GetMapping("/content")
	public void content(@RequestParam("boardNo") int board_no, Model model) {
		System.out.println("/board/content?boardNo = " + board_no);
		model.addAttribute("article",service.getArticle(board_no));
	}


	@GetMapping("/modify")
	public void modify(@RequestParam("boardNo") int board_no, Model model) {
		System.out.println("/board/modify?boardNo=" + board_no);
		model.addAttribute("article", service.getArticle(board_no));
	}

	@PostMapping("/modify")
	public String modify(BoardVO vo) {
		System.out.println("/board/modify : POST " + vo.getboard_no());
		service.updateArticle(vo);
		return "redirect:/board/content?boardNo=" + vo.getboard_no();
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("boardNo") int board_no) {
		System.out.println("/board/delete?boardNo=" + board_no);
		service.deleteArticle(board_no);
		return "redirect:/board/list";
	}

	//게시글 검색 처리 요청
	@GetMapping("/searchList")
	public String searchList(@RequestParam("keyword") String keyword, Model model) {
		model.addAttribute("articles", service.searchList(keyword));
		return "board/list";
	}

}







