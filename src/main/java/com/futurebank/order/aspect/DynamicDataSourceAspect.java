

package com.futurebank.order.aspect;


import com.futurebank.order.config.DS;
import com.futurebank.order.config.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {
    private static final Logger log;
    private final String DEFAULT_DATA_SOURCE = "master";

    @Pointcut("execution(public * com.futurebank.order.mapper.*.*(..))")
    public void dataSourcePoint() {
    }

    @Before("dataSourcePoint()")
    public void before(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String methodName = methodSignature.getName();
        final Class[] parameterTypes = methodSignature.getParameterTypes();
        try {
            final Method method = target.getClass().getInterfaces()[0].getMethod(methodName, (Class<?>[]) parameterTypes);
            String dataSource = "master";
            if (null != method && method.isAnnotationPresent((Class<? extends Annotation>) DS.class)) {
                final DS DS = method.getAnnotation(DS.class);
                dataSource = DS.value();
            }
            DynamicDataSourceHolder.setDataSource(dataSource);
        } catch (Exception e) {
            DynamicDataSourceAspect.log.info("error", (Throwable) e);
        }
    }

    @After("dataSourcePoint()")
    public void after(final JoinPoint joinPoint) {
        DynamicDataSourceHolder.clear();
    }

    static {
        log = LoggerFactory.getLogger((Class) DynamicDataSourceAspect.class);
    }

}
