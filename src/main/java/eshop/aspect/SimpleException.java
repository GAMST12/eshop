package eshop.aspect;

import org.aspectj.lang.JoinPoint;

public class SimpleException {
    public void logException (JoinPoint joinPoint, Throwable t)  {
            System.out.println("ASPECT.EXCEPTION-LOGGER: " + t.getMessage());
    }

}
