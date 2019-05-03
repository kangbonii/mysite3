package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class userService {
	
	@Autowired
	private UserDao userDao;
//	회원가입
	public int join(UserVo uservo) {
		return userDao.insert(uservo);
		
	}
//	회원정보 가져오기(email,password)
	public UserVo login(String email, String password) {
		return userDao.select(email,password);
		
	}
	
	public UserVo mofityform(int no) {
		return userDao.getUser(no);
		
	}
//	회원정보 수정
	public int modify(UserVo uservo) {
		return userDao.update(uservo);
	
	}
	
//	이메일 체크
	public boolean emailCheck(String email) {
		UserVo vo = userDao.select(email);
		//데이터가 없을때 true--->가입할 수 있음
		if(vo == null) {
			return true;
		}else {
			return false;
		}
	}

	
}
