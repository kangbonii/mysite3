package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestBookDao;
import com.javaex.service.guestService;
import com.javaex.vo.GuestBookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	@Autowired
	private guestService guestService;
	
	@RequestMapping(value = "/addlist", method = RequestMethod.GET)
	public String addlist(Model model) {
		System.out.println("addlist");
		List<GuestBookVo> list = guestService.getList();

		model.addAttribute("list", list);
		return "guestbook/addlist";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo guestvo) {

		System.out.println(guestvo.toString());
		int count = guestService.insert(guestvo);
		System.out.println(count);
		return "redirect:/guestbook/addlist";
	}
	

	@RequestMapping(value = "/dform", method = RequestMethod.GET)
	public String dform() {
		System.out.println("dform");
		return "guestbook/deleteform";
	}

	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo guestvo) {
		System.out.println("delete");

		int count =guestService.delete(guestvo);
		System.out.println(count);
		
		return "redirect:/guestbook/addlist";

	}


}
