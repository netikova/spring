package com.exam.core;

import org.springframework.stereotype.Component;

//@Component("msk")
public class MyServiceKo implements MyService {

	@Override
	public String getHelloMsg() {
		
		return "안녕하세요";
	}

	@Override
	public String getByeMsg() {
		
		return "안녕하가세요";
	}

}
