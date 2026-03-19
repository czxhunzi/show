package com.example.springboot.aop;

import com.example.springboot.entity.OperateLog;
import com.example.springboot.mapper.OperateLogMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Aspect //切面是可模块的点
public class MyAspect {

    @Resource
    private OperateLogMapper operateLogMapper;

    //定义切入点
    //第一个*表示任意返回值，AdminService后面的*表示里面所有的类，(..)表示任意个数任意类型的参数，(*)表示任意层级任意类型的一个参数
    //如果要匹配的类名字不方便用通配符表示，还可以考虑使用注解@annotation
    @Pointcut("execution(* com.example.springboot.service.impl.AdminService.list(..))")
    private void pointCut(){}


//    @Around("pointCut()")
    @Around("@annotation(com.example.springboot.anno.Log)") //这种写法需要自定义注解然后加到需要处理类上
    public Object aop(ProceedingJoinPoint joinPoint) throws Throwable {//joinPoint可以或取方法执行时的相关信息
        System.out.println("方法执行前");
        OperateLog operateLog = new OperateLog();
        //获取目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();
        //不执行的话方法消失了
        Object proceed = joinPoint.proceed();
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setReturnValue(proceed.toString());
        operateLogMapper.save(operateLog);
        System.out.println("方法执行后");
        return proceed;
    }
}
