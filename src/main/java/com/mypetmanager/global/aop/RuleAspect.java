package com.mypetmanager.global.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RuleAspect {
	 // Repository 호출을 막기 위한 포인트컷 정의
    @Before("execution(* com.mypetmanager.application.controller.*.*(..)) && within(com.mypetmanager.integration.repositoryss.*)")
    public void beforeRepositoryCall() {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Repository call intercepted, throwing exception");
        throw new UnsupportedOperationException("Repository calls are not allowed from controllers");
    }
    
    @Before("execution(* com.mypetmanager.application.controller.*.*(..))")
    public void beforeTestRepositoryCall() {
    	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("beforeTestRepositoryCall");
        //throw new UnsupportedOperationException("Repository calls are not allowed from controllers");
    }
}
