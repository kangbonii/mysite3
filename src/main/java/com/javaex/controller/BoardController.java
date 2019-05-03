package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.interceptor.Auth;
import com.javaex.service.boardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private boardService boardService;
	
	
	/* 리스트 기본 */
	/*
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String addlist(@RequestParam(value="crtPage" , required = false, defaultValue = "1") int crtPage, Model model) {
		System.out.println("list");
		//List<BoardVo> list = boardService.getList(crtPage);
		Map<String,Object> pMap =  boardService.getList(crtPage);
		System.out.println(pMap.toString());
		//model.addAttribute("list", list);
		model.addAttribute("pMap", pMap);
		return "board/list";
		
	}
	*/
	
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String addlist(@RequestParam(value="crtPage" , required = false, defaultValue = "1") int crtPage, 
						  @RequestParam(value="kwd", required=false, defaultValue="") String kwd,  
						  Model model) {
		System.out.println("list");
		
		Map<String,Object> pMap =  boardService.getList(crtPage,kwd);
		System.out.println(pMap.toString());
		
		model.addAttribute("pMap", pMap);
		return "board/list";
		
	}
	
	@Auth
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
	public String modify(@ModelAttribute BoardVo boardvo,
			@RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
			@RequestParam( value="kwd", required=false, defaultValue="") String kwd,
			Model model) {
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
	
	
	
	
	/*
	@RequestMapping(value = "/insert70", method = RequestMethod.GET)
	public String ininin() {
		boardService.insertinsert();
		return "redirect:/main";
	}
	*/
	
	
	
}
