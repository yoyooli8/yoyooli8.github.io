package com.ai.frame.test.springmvc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ControllerIntercepter2 {
	
	public void beforeAdvice(JoinPoint joinPoint){
		System.out.println("-----beforeAdvice().invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");  
        System.out.println(" 可通过joinPoint来获取所需要的内容");  
        System.out.println("-----End of beforeAdvice()------");  
	}
	
    public void afterAdvice(JoinPoint joinPoint) {    
        System.out.println("-----afterAdvice().invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");  
        System.out.println(" 可通过joinPoint来获取所需要的内容");  
        System.out.println("-----End of afterAdvice()------");  
    } 
	
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {    
        System.out.println("-----aroundAdvice().invoke-----");  
        System.out.println(" 此处可以做类似于Before Advice的事情");  
          
        //调用核心逻辑  
        Object retVal = pjp.proceed();  
        System.out.println(" 此处可以做类似于After Advice的事情");  
        System.out.println("-----End of aroundAdvice()------");  
        return retVal;  
    }
}
