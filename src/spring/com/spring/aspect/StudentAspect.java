package com.spring.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class StudentAspect {
	
	@Pointcut("execution(* com.spring.Student.lookBook(String))&&args(bookName)")
	public void readBook(String bookName){
		
	}
	
	@After("readBook(bookName)")
	public void  printReadBook(String bookName){
		System.out.println(bookName);
	}
	

}
