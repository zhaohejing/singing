package com.efan.aspect;

import com.efan.core.page.ActionResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 45425 on 2017/5/4.
 */

@Aspect   //定义一个切面
@Configuration
public class ResultAspect {

    // 定义切点Pointcut
    @Pointcut("execution(* com.efan.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public ActionResult doAround(ProceedingJoinPoint pjp) throws Throwable {
        // result的值就是被拦截方法的返回值
        Object res = pjp.proceed();
        if(res instanceof ActionResult){
            return  (ActionResult)res;
        }
        ActionResult result=new ActionResult();
          if (res!=null){
              result.setSuccess(true);
              result.setResult(res);
          }else  {
              result.setSuccess(false);
              result.setResult(null );
          }
        return result;
    }
}
