package com.exam.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //AOP 설정을 담고 있는 클래스임을 의미
@Component
public class MyAdvice {
//     @Before(value = "execution(* com.exam.core.*.*(..))") //이메서드를 실행하기 전에 밑에 메서드를 실행
	 public void bm(JoinPoint jp) {	       
	        //실행될 타겟메서드가 속한 객체의 클래스명
	        String className = jp.getTarget().getClass().getSimpleName(); //객체클래스 이름
	        String methodName = jp.getSignature().getName(); //객체 메서드 이름(실행될 타겟 메서드명)	        
	        System.out.println("MyAdvice bm 실행 : " + className + "." + methodName);
	    }
//	 @After("pc()") //이메서드를 실행 후에 밑에 메서드를 실행
	 public void am(JoinPoint jp) {	         
	        //실행될 타겟메서드가 속한 객체의 클래스명
	        String className = jp.getTarget().getClass().getSimpleName(); //객체클래스 이름
	        String methodName = jp.getSignature().getName(); //객체 메서드 이름(실행될 타겟 메서드명)	        
	        System.out.println("MyAdvice am 실행 : " + className + "." + methodName);
	    }
	 @Around("pc()")
	 public Object rm(ProceedingJoinPoint pjp) throws Throwable {
	        System.out.println("MyAdvice:rm start.");
	        long time1 = System.currentTimeMillis();
	        Object retVal = pjp.proceed();  //**타겟 메서드 실행**
	 
	        System.out.println("ProceedingJoinPoint executed. return value is [" + retVal + "]");
	 
	        retVal = retVal + "(modified)";
	        System.out.println("return value modified to [" + retVal + "]");
	 
	        long time2 = System.currentTimeMillis();
	        System.out.println("MyAdvice:rm end. Time(" + (time2 - time1) + ")");
	        return retVal;
    }
	 
	 //반복적으로 사용하는 포인트컷을 별도로 정의하고, ("pc()")와 같이 사용 가능
	 @Pointcut("execution(* com.exam.core.*.*(..))")
	 private void pc() {  //반환타입은 void, 메서드 내용 불필요
		 
	 }
}
