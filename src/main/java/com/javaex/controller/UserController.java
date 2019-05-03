package com.javaex.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.userService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private userService userService;

	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "user/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo uservo) {
		System.out.println(uservo.toString());
		int count = userService.join(uservo);
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		return "user/loginform";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
//			HttpSession session) {
//		
//		UserVo authUser = userService.login(email, password);
//		if (authUser != null) { // 로그인 성공
//			session.setAttribute("authUser", authUser);
//			return "redirect:/main";
//		} else {
//			return "redirect:/user/loginform?result=fail";
//		}
//	}

	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo vo = (UserVo)session.getAttribute("authUser");
		UserVo uservo = userService.mofityform(vo.getNo());
		System.out.println("uservo" + uservo.toString());
		
		model.addAttribute("uservo", uservo);
		
	
		return "user/modifyform";
		
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute UserVo uservo, HttpSession session) {
		int count = userService.modify(uservo);
		System.out.println(count);
		
		UserVo vo = (UserVo)session.getAttribute("authUser");
		vo.setNo(uservo.getNo());
		vo.setName(uservo.getName());
		return "redirect:/main";

	}
	
	@ResponseBody //jsp을 안찾음. 기존방식으로 x
	@RequestMapping(value="/emailcheck", method = RequestMethod.POST)
	public boolean emailCheck(@RequestParam("email") String email) {
		System.out.println(email);
		boolean result = userService.emailCheck(email);
		return result;
	}

}
