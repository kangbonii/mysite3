package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestBookVo;

@Service
public class boardService {
	
	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		return boardDao.getList();
	}

	public int insert(BoardVo boardvo) {
		return boardDao.insert(boardvo);
	}

	public BoardVo readList(int no) {
		return boardDao.selectno(no);
	}

	public int modify(BoardVo boardvo) {
		return boardDao.update(boardvo);
	}

	public int delete(int no) {
		return boardDao.delete(no);
	}
	

	



}
