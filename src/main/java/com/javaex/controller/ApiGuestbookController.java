package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.guestService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/api/gb")
public class ApiGuestbookController {

	@Autowired
	private guestService guestService;

	// 리스트다가져오는거
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<GuestBookVo> list() {
		System.out.println("ajaxlist");
		
		List<GuestBookVo> guestbooklist = guestService.getList();
		
		System.out.println(guestbooklist.toString());
		return guestbooklist; // 기존방식과 비교해보기 데이터만보낼것임
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public GuestBookVo add(@RequestBody GuestBookVo guestvo) {  //@RequestBody 바디에 있다고. json형태로줌 
		System.out.println("add");
		
		return guestService.insertApi(guestvo); 
	}

	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@ModelAttribute GuestBookVo guestvo) {
		System.out.println("delete");
		int count =guestService.delete(guestvo);
		
		
		
		return count; 
	}
	
	
}
