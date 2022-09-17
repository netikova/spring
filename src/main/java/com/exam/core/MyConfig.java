package com.exam.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 설정내용을 담고 있는 클래스임을 표시
@ComponentScan(basePackages = {"com.exam.core"})//자바형식 //<context:component-scan base-package="com.exam.core"></context:component-scan>와 같음
public class MyConfig {
    //자바가 클래스를 스프링객체로 등록하려면 이런 형식으로 만들고 객체를 리턴한다.
//	@Bean("ma") //이 메서드에서 반환하는 객체를 스프링에 ma라는 이름으로 등록
	//이름을 생략하면, 메서드명을 이름으로 사용
	public MyApp ma() {
		 MyApp m = new MyApp();
		 m.setMyService(mse()); //mse() 메서드가 등록한 객체를 주입
		 return m;
	}
//	@Bean("mse") //이 메서드에서 반환하는 객체를 스프링에 mse라는 이름으로 등록
	public MyService mse() {
		return new MyServiceEn();
	}
//	@Bean("msk") //이 메서드에서 반환하는 객체를 스프링에 msk라는 이름으로 등록
	public MyApp msk() {
		return new MyApp();
	}
}
