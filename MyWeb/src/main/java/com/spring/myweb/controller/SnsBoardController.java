package com.spring.myweb.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.command.UserVO;
import com.spring.myweb.snsboard.service.ISnsBoardService;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {

	@Autowired
	private ISnsBoardService service;//@Controller와 의존관계
	
	// 단순 페이지 이동 메서드
	@GetMapping("/snsList")
	public void snsList() {}
	
	@PostMapping("/upload")
	@ResponseBody // 비동기방식으로 받으니까
	public String upload(MultipartFile file, String content, //파일, 내용, 세션받으려고 매개변수 이렇게 씀
						 HttpSession session ) {//폼데이터 객체에 content로 담았으니 이름같게 맞춰줘야함
		try {
			
			String writer = ((UserVO)session.getAttribute("login")).getUserId();
			
			// 날짜별로 폴더를 생성해서 파일을 관리
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String fileLoca = sdf.format(date);
			String uploadPath = "C:\\test\\upload" +fileLoca;
			File folder = new File(uploadPath);
			if(!folder.exists()) {
				folder.mkdir();//폴더가 존재하지 않는다면 생성해라
			}
			
			String fileRealName = file.getOriginalFilename();
			//파일명을 고유한 랜ㄴ덤 문자로 생성
			UUID uuid = UUID.randomUUID();
			String uuids = uuid.toString().replaceAll("-", "");
			
			//확장자를 추출합니다.
			String fileExtentsion = fileRealName.substring(fileRealName.indexOf("."),fileRealName.length());
			
			System.out.println("저장할 폴더 경로 : " + uploadPath);
			System.out.println("실제 파일명 : " + fileRealName);
			System.out.println("폴더명 : " + fileLoca);
			System.out.println("확장자 : " + fileExtentsion);
			System.out.println("고유랜덤 문자 : " + uuids);
			
			String fileName = uuids + fileExtentsion;
			System.out.println("변경해서 저장할 파일명 : " + fileName);
			
			// 업로드한 파일을 서버 컴퓨터의 지정한 경로 내에 실제로 저장.
			File saveFile = new File(uploadPath + "\\" + fileName);
			file.transferTo(saveFile);
			
			// db에 insert작업을 진행
			SnsBoardVO snsVO = new SnsBoardVO(0,writer,uploadPath,fileLoca,fileName,fileRealName,content,null);
			service.insert(snsVO);
			
			return "success";
		} catch (Exception e) {
			System.out.println("업로드 중 에러 발생 : " + e.getMessage());
			return "fail";//에러가 났을 시에는실패 키워드를 반환
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
