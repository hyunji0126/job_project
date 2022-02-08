package com.spring.mvc.board;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentTest {

	@Test
	public void testUriComp() {
	
//		Uri를 쉽게 작성할 수 있도록 도와주는 유틸 클래서 
//		UriComponentBuilder 사용하기
		UriComponents ucp =  UriComponentsBuilder.newInstance().path("/board/list") //newInstance()를 통해서 객체 받아오기, path("/)경로작성, UriComponents가 리턴타입
										.queryParam("page", 3)
										.queryParam("countPerPage", 20)
										.queryParam("keyword", "메롱")
										.queryParam("condition", "title")
										.build();//build를 통해서 완성된 객체를 받는다
		
		System.out.println(ucp.toUriString());
		
	}
	
	
}
