package com.labs.catalog.aspect;

import com.labs.catalog.dto.BookDetailResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.labs.catalog.web.*.*(..))")
    public void restAPI(){}

    @Pointcut("within(com.labs.catalog.web.*)")
    public void withinPointcutExample(){}

    @Pointcut("args(com.labs.catalog.dto.PublisherCreateRequestDTO)")
    public void argsPointcutExample(){}

    @Pointcut("@args(com.labs.catalog.annotation.LogThisArgs)")
    private void argsAnnotationPointcutExample(){}

    @Pointcut("@annotation(com.labs.catalog.annotation.LogThisMethod)")
    private void annotationPointcutExample(){}

    @Before("annotationPointcutExample()")
    public void beforeExecutedLogging(){
        log.info("This is log from aspect before method executed");
    }

    @After("annotationPointcutExample()")
    public void afterExecutedLogging(){
        log.info("This is log from aspect after method executed");
    }

    @AfterReturning("annotationPointcutExample()")
    public void afterReturnExecutedLogging(){
        log.info("This is log from aspect after returning method executed");
    }

    @AfterThrowing("annotationPointcutExample()")
    public void afterThrowingExecutedLogging(){
        log.info("This is log from aspect after throwing method executed");
    }

    @Around("restAPI()")
    public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            log.info("Start {}. {} ", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            return joinPoint.proceed();
        } finally {
            stopWatch.start();
            log.info("Finish {}. {} excution time = {}",
                    joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(),
                    stopWatch.getTotalTimeMillis()
            );
        }
    }
}
