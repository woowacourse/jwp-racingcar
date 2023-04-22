package racingcar.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* racingcar.controller..*(..))")
    private void controllerPackage() {

    }

    @Pointcut("execution(* *..*Controller.*(..))")
    private void controllerClasses() {

    }

    @AfterThrowing(value = "controllerClasses() && controllerClasses()", throwing = "e")
    public void logException(RuntimeException e) {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("{} : {}", e.getClass(), e.getMessage(), e);
    }
}
