package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class boardService {
	
	@Autowired
	private BoardDao boardDao;

	public Map<String,Object> getList(int crtPage,String kwd) {
		
		//리스트 가져오기------------------------------------------------
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage > 0)? crtPage : (crtPage=1); //0보다크면 그냥 저리, 아니면 1페이지
		
		//시작글번호
		int startRnum= (crtPage-1) * listCnt;  //1페이지면0
		
		//끝 글 번호
		int endRnum = startRnum + listCnt;  //10
		
		List<BoardVo> list = boardDao.getList(startRnum,endRnum, kwd);
		
		
		//페이징계산(아래 번호)--------------------------------------------
		
		int totalCount = boardDao.totalCount(kwd);
		System.out.println(totalCount);
		
		//페이지당 버튼 갯수
		int pageBtncount = 5;
		
		//마지막 버튼 번호                //현재 1페이지면 1-5, 2~5페이지 1-5, 7페이지 6-10 
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtncount)*pageBtncount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtncount-1);
		
		//다음 화살표 유무
		boolean next = false;
		if( endPageBtnNo*listCnt < totalCount) { //10*10(10개씩 10페이지) < 107(개의 글)
			next = true;
		}else {    // 7페이지 밖에 없을때 8910안나오게 //73개 10페이지 -->7+1
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo !=1) {
			prev = true;
		}
		
		
		Map<String,Object> pmap = new HashMap<String,Object>();
		pmap.put("endPageBtnNo",endPageBtnNo);
		pmap.put("startPageBtnNo",startPageBtnNo);
		pmap.put("next",next);
		pmap.put("prev",prev);
		pmap.put("list",list);
		
		System.out.println(pmap.toString());
		
		return pmap;
	}

	public int insert(BoardVo boardvo) {
	
		return boardDao.insert(boardvo);
	}

	@Transactional
	public BoardVo readList(int no) {
		//조회수 1증가
		boardDao.updateHit(no);
		
		//해당글가져오기
		return boardDao.selectno(no);  //return boardVo
	}

	public int modify(BoardVo boardvo) {
		return boardDao.update(boardvo);
	}

	public int delete(int no) {
		return boardDao.delete(no);
	}
	
	public void insertinsert() {
		boardDao.insert70();
	}
	

	



}
