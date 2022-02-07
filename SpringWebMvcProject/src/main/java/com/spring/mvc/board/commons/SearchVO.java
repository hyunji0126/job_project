package com.spring.mvc.board.commons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO extends PageVo {
	
	private String keyword;
	private String condition;
	
	
	
	
}
