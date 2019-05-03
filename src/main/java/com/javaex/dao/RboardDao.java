package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	public List<RboardVo> getList(int startRnum,int endRnum, String kwd){
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("startRnum",startRnum);
		map.put("endRnum",endRnum);
		map.put("kwd", kwd );
		
		List<RboardVo> list = sqlsession.selectList("rboard.selectList", map);
		System.out.print("#####list#####");

		System.out.print(list);
		return list;
	}
	
	//전체글갯수
	public int totalCount(String kwd) {
		return sqlsession.selectOne("rboard.totalCount",kwd);
	}
	
	
	public int insert(RboardVo vo) {
		System.out.println("####insert####");

		System.out.println(vo);
		return sqlsession.insert("rboard.insert",vo);
	}
	
	public RboardVo selectno(int no) {
		System.out.print("#####");
		System.out.print(no);
		return sqlsession.selectOne("rboard.selectOne",no);
	}
	
	public int update(RboardVo boardvo) {
		return sqlsession.update("rboard.update",boardvo);
	}

	public int delete(int no) {
		return sqlsession.delete("rboard.delete",no);
	}

	public void updateHit(int no) {
		sqlsession.update("rboard.updatehit", no);	
	}

	public int update2(RboardVo boardvo) {
		// TODO Auto-generated method stub
		return sqlsession.update("rboard.update2",boardvo);
	}
	




	
}
