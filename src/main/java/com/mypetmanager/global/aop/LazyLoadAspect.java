package com.mypetmanager.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//@Aspect
//@Component
public class LazyLoadAspect {
	
	//@Pointcut("execution(* com.mypetmanager.business.domain.owner.OwnerDomain.getPetList(..))")
	//@Around("@annotation(com.mypetmanager.global.annotation.domain.LazyLoad)")
	//@Around("execution(* com.mypetmanager..*(..))")
	public Object lazyLoad(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("aspect StartStartStartStart");
		long begin = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		System.out.println(System.currentTimeMillis() - begin);
		return retVal;
	}
	
}
