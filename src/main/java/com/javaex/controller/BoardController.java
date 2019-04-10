package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.boardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private boardService boardService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String addlist(Model model) {
		System.out.println("list");
		List<BoardVo> list = boardService.getList();
		System.out.println(list.toString());
		model.addAttribute("list", list);
		return "board/list";
		
	}
	
	@RequestMapping(value = "/writeform", method = RequestMethod.GET)
	public String writeform() {
		System.out.println("writeform");
		return "board/writeform";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardvo, HttpSession session) {		
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		
		boardvo.setTitle(boardvo.getTitle());
		boardvo.setContent(boardvo.getContent());
		boardvo.setUser_no(uservo.getNo());
		
		int count = boardService.insert(boardvo);
		System.out.println(count);
		return "redirect:/board/list";
	
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("read");
		
		BoardVo boardvo = boardService.readList(no);
		model.addAttribute("boardvo",boardvo);
		return "board/read";
	}
	
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		System.out.println("modifyform");
		
		BoardVo boardvo = boardService.readList(no);
		model.addAttribute("boardvo",boardvo);		
		
		return "board/modifyform";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardvo, Model model) {
		System.out.println("modify");
		
		boardService.modify(boardvo);
		
		return "redirect:/board/read?no=" + boardvo.getNo();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no, Model model) {
		System.out.println("delete");
		
		int count =boardService.delete(no);
		

		return "redirect:/board/list";
	}
	
	
	
	
	
}
