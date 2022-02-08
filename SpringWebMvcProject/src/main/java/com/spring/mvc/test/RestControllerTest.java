package com.spring.mvc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("/rest")
public class RestControllerTest {
	
	/*
	 @ResponseBody
	 - 리턴 데이터를 viewResolver에게 전달하지 않고
	 클라이언트에게 해당 데이터를 바로 응답하게 합니다.
	 비동기 통신에서 주로 많이 사용합니다.
	 - @RestController를 사용하면 모든 메서드에 
	 @ResponseBody를 붙인 결과와 같습니다.
	 */
	
	@GetMapping("/hello")
//	@ResponseBody // --> 이거 넣기 전에는 Hello World.jsp없다고 404에러떳었으나 저거 붙이니까 문자열이 리턴됨
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/hobby")
//	@ResponseBody
	public List<String> hobby(){
		List<String> hobby = Arrays.asList("축구", "수영", "영화감상");
		
		return hobby;
	}
	
	@GetMapping("/study")
	public Map<String, Object> study(){
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("spring", "spring framework5");
		
		return subject;
		
		//{"spring":"spring framework5","jsp":"java server pages","자바":"java"} 로 출력됨 순서가 거꾸로네?
	}
	
	@GetMapping("/person")
	public Person person() {
		Person p = new Person();//객체 생성
		p.setName("김철수");
		p.setAge(30);
		p.setHobbies(Arrays.asList("수영", "독서", "축구"));
		
		return p;
	}
	
//	@GetMapping("/getText")
	@GetMapping(value="/getText", produces="text/plain")// produces가 생략가능
	public String getText() {
		System.out.println("/getText 요청이 들어옴!");
		return "Hello World!";
	}
	
	@PostMapping("/getObject")
	public Person getObject(@RequestBody Person person) { //JSON 데이터가 날라올떄는 @Response의 반대인 @RequestBody로 받아야함 
		System.out.println("/getObject 요청이 들어옴");
		System.out.println("이름 : " + person.getName());
		System.out.println("나이 :"+ person.getAge());
		System.out.println("취미 : "+person.getHobbies());
		
		person.setAge(40);
		
		return person;
	}
	
	@GetMapping("/getPath/{id}/{cpp}/{page}")//{}안은 아무거나쓴거 의미없
	public Map<String, Object> getPath(@PathVariable int id, @PathVariable int cpp, @PathVariable int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("아이디", id);
		map.put("게시물 개수", cpp);
		map.put("페이지 번호", page);
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
