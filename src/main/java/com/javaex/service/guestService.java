package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class guestService {
	@Autowired
	private GuestBookDao dao;
	
	public List<GuestBookVo> getList() {
		return dao.getList();
	}
	
	public int insert(GuestBookVo guestvo) {
		return dao.insert(guestvo);
		
	}
	
	public int delete(GuestBookVo guestvo) {
		return dao.delete(guestvo);
	}
	

	
	
}
