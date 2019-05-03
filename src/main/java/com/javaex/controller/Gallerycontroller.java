package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.galleryService;
import com.javaex.vo.UserVo;
import com.javaex.vo.galleryVo;

@Controller
@RequestMapping(value = "/gallery")


public class Gallerycontroller {
	
	@Autowired
	galleryService galleryService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@ModelAttribute galleryVo galleryVo, Model model) {
		List<galleryVo> galleryvo = galleryService.readList(galleryVo);
		model.addAttribute("galleryvo",galleryvo);
		return "gallery/list";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, @ModelAttribute galleryVo galleryVo,  HttpSession session) {
		System.out.println(file.getOriginalFilename());
		UserVo vo = (UserVo)session.getAttribute("authUser");
		galleryVo.setUser_no(vo.getNo());
		int count = galleryService.restore(file,galleryVo);
		System.out.println(count);
		return "redirect:/gallery/form";
	}
	
	@ResponseBody
	@RequestMapping(value = "/viewimg", method = RequestMethod.POST)
	public galleryVo add(@ModelAttribute galleryVo galleryVo) {  
		System.out.println("add");
		
		return galleryService.selectNo(galleryVo); 
	
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@ModelAttribute galleryVo guestvo) {
		System.out.println("delete");
		String formatName = guestvo.getSaveName();
		int count =galleryService.delete(guestvo);
		
		return count; 
	}
	
	
}