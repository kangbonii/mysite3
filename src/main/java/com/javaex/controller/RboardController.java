package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.rboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {

	@Autowired
	private rboardService rboardService;
	
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
		
		Map<String,Object> pMap =  rboardService.getList(crtPage,kwd);
		System.out.println("######pMap#####");

		System.out.println(pMap.toString());
		
		model.addAttribute("pMap", pMap);
		return "rboard/list";
		
	}
	
	
	@RequestMapping(value = "/writeform", method = RequestMethod.GET)
	public String writeform() {
		System.out.println("writeform");
		return "rboard/writeform";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute RboardVo boardvo, HttpSession session) {		
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		
		boardvo.setTitle(boardvo.getTitle());
		boardvo.setContent(boardvo.getContent());
		boardvo.setUser_no(uservo.getNo());
		
		int count = rboardService.insert(boardvo);
		System.out.println(count);
		return "redirect:/rboard/list";
	
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("read");
		System.out.println(no);

		RboardVo boardvo = rboardService.readList(no);
		
		model.addAttribute("boardvo",boardvo);

		return "rboard/read";
	}
	
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		System.out.println("modifyform");
		
		
		RboardVo boardvo = rboardService.readList(no);
		model.addAttribute("boardvo",boardvo);		
		
		return "rboard/modifyform";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute RboardVo boardvo,
			@RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
			@RequestParam( value="kwd", required=false, defaultValue="") String kwd,
			Model model) {
		System.out.println("modify");
		
		rboardService.modify(boardvo);
		
		return "redirect:/rboard/read?no=" + boardvo.getNo();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no, Model model) {
		System.out.println("delete");
		
		int count =rboardService.delete(no);
		

		return "redirect:/rboard/list";
	}
	
	@RequestMapping(value = "/commentform", method = RequestMethod.GET)
	public String commentform() {
		
		System.out.println("commentform");
		return "rboard/commentform";
	}
	
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String comment(@ModelAttribute RboardVo boardvo, HttpSession session) {		
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		System.out.println("#####comment#####");
		System.out.println(boardvo.getTitle());
		System.out.println(boardvo.getGroup_no());
		System.out.println(boardvo.getOrder_no());

		System.out.println(uservo.getNo());

		boardvo.setTitle(boardvo.getTitle());
		boardvo.setContent(boardvo.getContent());
		boardvo.setUser_no(uservo.getNo());
		boardvo.setGroup_no(boardvo.getGroup_no());
		boardvo.setOrder_no(boardvo.getOrder_no()+1);
		int count = rboardService.insert(boardvo);
		System.out.println(count);
		return "redirect:/rboard/list";
	}

	/*
	@RequestMapping(value = "/insert70", method = RequestMethod.GET)
	public String ininin() {
		boardService.insertinsert();
		return "redirect:/main";
	}
	*/
	
	
	
}
