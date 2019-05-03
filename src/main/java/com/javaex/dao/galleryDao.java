package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.galleryVo;

@Repository
public class galleryDao {
	@Autowired
	private SqlSession sqlsession;
	
	public int insertselectno(galleryVo vo) {
		int count= sqlsession.insert("gallery.insert",vo);
		return count;
		
	}

	public List<galleryVo> selectall(galleryVo galleryVo) {
		 List<galleryVo> list = sqlsession.selectList("gallery.selectList", galleryVo);
		return list;
	}

	public galleryVo selectOne(int no) {
		return sqlsession.selectOne("gallery.selectOne", no);
	}

	public int delete(galleryVo galleryVo) {
		return sqlsession.delete("gallery.delete", galleryVo);
	}
}
