package racingcar.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import racingcar.view.OutputView;

@Component
@Aspect
public class ExceptionAspect {

    @Around(value = "target(racingcar.controller.ConsoleRacingCarController)")
    public Object doPrintException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            OutputView.printException(e);
            return doPrintException(joinPoint);
        }
    }
}
