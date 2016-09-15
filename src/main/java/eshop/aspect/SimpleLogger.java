package eshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class SimpleLogger {
    public Object log (ProceedingJoinPoint call, int id) throws Throwable {
        try {
            return call.proceed();
        } finally {
            System.out.println("ASPECT.LOGGER: " + call.toShortString() + "called. args " +call.getArgs());
        }
    }
}
