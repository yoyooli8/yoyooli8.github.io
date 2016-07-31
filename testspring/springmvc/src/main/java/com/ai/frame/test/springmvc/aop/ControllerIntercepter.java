package com.ai.frame.test.springmvc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ControllerIntercepter {
	@Pointcut("execution(public com.ai.frame.test.springmvc.controller.TestMvcController.login* (..)) and args(param)")
	protected void aspectjPointcut(){}
	
	@Before(value="aspectjPointcut(param)",argNames = "param")
	public void beforeAdvice(JoinPoint joinPoint,String param){
		System.out.println("-----beforeAdvice().invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");  
        System.out.println(" 可通过joinPoint来获取所需要的内容");  
        System.out.println("-----End of beforeAdvice()------");  
	}
	
	@After(value = "aspectjMethod(param)",argNames = "param")    
    public void afterAdvice(JoinPoint joinPoint,String param) {    
        System.out.println("-----afterAdvice().invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");  
        System.out.println(" 可通过joinPoint来获取所需要的内容");  
        System.out.println("-----End of afterAdvice()------");  
    } 
	
	@Around(value = "aspectjMethod(param)",argNames = "param")    
    public Object aroundAdvice(ProceedingJoinPoint pjp,String param) throws Throwable {    
        System.out.println("-----aroundAdvice().invoke-----");  
        System.out.println(" 此处可以做类似于Before Advice的事情");  
          
        //调用核心逻辑  
        Object retVal = pjp.proceed();  
        System.out.println(" 此处可以做类似于After Advice的事情");  
        System.out.println("-----End of aroundAdvice()------");  
        return retVal;  
    }
	
//	@AfterReturning(value = "aspectjMethod()", returning = "retVal")    
//    public void afterReturningAdvice(JoinPoint joinPoint, String retVal) {    
//        System.out.println("-----afterReturningAdvice().invoke-----");  
//        System.out.println("Return Value: " + retVal);   
//        System.out.println(" 此处可以对返回值做进一步处理");  
//        System.out.println(" 可通过joinPoint来获取所需要的内容");  
//        System.out.println("-----End of afterReturningAdvice()------");  
//    }
//	
//	@AfterThrowing(value = "aspectjMethod()", throwing = "ex")    
//    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {    
//        System.out.println("-----afterThrowingAdvice().invoke-----");  
//        System.out.println(" 错误信息："+ex.getMessage());  
//        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");  
//        System.out.println(" 可通过joinPoint来获取所需要的内容");  
//        System.out.println("-----End of afterThrowingAdvice()------");    
//    }   
}
