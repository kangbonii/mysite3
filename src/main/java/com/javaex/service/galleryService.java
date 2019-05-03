package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.galleryDao;
import com.javaex.vo.galleryVo;

@Service
public class galleryService {
	@Autowired
	private galleryDao dao;
	
	public int restore(MultipartFile file, galleryVo galleryVo) {
		
		String savaDir = "/home/bituser/upload";
		
		//오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		//저장할 파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		System.out.println(saveName);	
		
		//파일패스
		String filePath = savaDir+"/"+saveName;
		System.out.println(filePath);	
		
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);	
		
			
		//서버에 파일복사
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);

		System.out.println(galleryVo.toString());
		
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(savaDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);

			if (bout != null) {
				bout.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int count = dao.insertselectno(galleryVo);
		//filevo DB저장
		
		//화면에 등록한 이미지띄우기
		return count;
	}

	public List<galleryVo> readList(galleryVo galleryVo) {
		return dao.selectall(galleryVo);
	}

	public galleryVo selectNo(galleryVo galleryVo) {
		return dao.selectOne(galleryVo.getNo());
	}

	public int delete(galleryVo galleryVo) {
		return dao.delete(galleryVo);
	}



}
