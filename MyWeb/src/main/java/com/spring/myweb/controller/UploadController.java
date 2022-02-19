package com.spring.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.myweb.command.MultiUploadVO;
import com.spring.myweb.command.UploadVO;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

	//upload.jsp 페이지 이동 요청
	@GetMapping("upload")
	public void form() {}

	@PostMapping("/upload_ok")
	public String upload(@RequestParam("file") MultipartFile file) {

		try {
			String fileRealName= file.getOriginalFilename();//파일명
			long size =file.getSize();//파일의 크기를 가져올수 있음
			System.out.println("파일명은 " + fileRealName);
			System.out.println("파일사이즈는 " + size);

			//서버에 저장할 파일 이름
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
			String uploadFolder = "C:\\test\\upload";

			/*
			 파일 업로드 시 파일명이 동일한 파일이 이미 존재할 수도 있고, 
			 사용자가 업로드하는 파일명이 영어 이외의 언어로 되어있을 수 있습니다.
			 타 언어를 지원하지 않는 환경에서는 정상 동작이 되지안핫브니다.(리눅스)
			 고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일 명을 새롭게 만들어 줍니다.
			 */
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");
			String uniqueName = uuids[0];
			System.out.println("생성된 고유 문자열 : " + uniqueName);
			System.out.println("확장자명 : " + fileExtension);
			
			File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension);

			// 실제 파일 저장 메서드 (fileWriter 작업을 손쉽게 한방에 처리해 줍니다.)
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fileupload/upload_ok";
	}


	@PostMapping("/upload_ok2")
	public String upload2(MultipartHttpServletRequest files) {
		
		// 서버에서 저장할 파일 경로
		String uploadFolder = "C:\\test\\upload";
		
		List<MultipartFile> list = files.getFiles("files");
		/*
		for(int i=0; i<list.size(); i++) { //향상for문으로도 작성이 가능하다
	
			String fileRealName = list.get(i).getOriginalFilename();
			long size = list.get(i).getSize();//사이즈 얻어내기
			
			System.out.println("파일명 :" + fileRealName);
			System.out.println("사이즈 :" + size);
			
			File saveFile = new File(uploadFolder + "\\" + fileRealName);
			
			try {
				list.get(i).transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		for(MultipartFile m : list) {
			String fileRealName = m.getOriginalFilename();
			long size = m.getSize();
			
			System.out.println("파일명 :" + fileRealName);
			System.out.println("사이즈 :" + size);
			
			File saveFile = new File(uploadFolder + "\\" + fileRealName);
			try {
				m.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "fileupload/upload_ok";
	}
	
	
	
	@PostMapping("/upload_ok3")
	public String upload3(@RequestParam("file") List<MultipartFile> list) {
		
		// 서버에서 저장할 파일 경로
				String uploadFolder = "C:\\test\\upload";

		for(MultipartFile m : list) {
			String fileRealName = m.getOriginalFilename();
			long size = m.getSize();
			
			System.out.println("파일명 :" + fileRealName);
			System.out.println("사이즈 :" + size);
			
			File saveFile = new File(uploadFolder + "\\" + fileRealName);
			try {
				m.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "fileupload/upload_ok";
	}
	
	
	
	@PostMapping("/upload_ok4")
	public String upload4(MultiUploadVO vo) { //MultiUploadVO는 list가지고있음
		
		System.out.println(vo);

		// 서버에서 저장할 파일 경로
		String uploadFolder = "C:\\test\\upload";
		List<UploadVO> list = vo.getList();
		
		try {
			for(UploadVO uvo : list) {
				String fileRealName = uvo.getFile().getOriginalFilename();
				long size = uvo.getFile().getSize();
				
				File saveFile = new File(uploadFolder + "\\" + fileRealName);
				uvo.getFile().transferTo(saveFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fileupload/upload_ok";
	}

}
