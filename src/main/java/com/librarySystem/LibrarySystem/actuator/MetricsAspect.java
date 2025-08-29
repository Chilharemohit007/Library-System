package com.librarySystem.LibrarySystem.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricsAspect {

    private final MeterRegistry meterRegistry;

    public MetricsAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Before("execution(* com.librarySystem.LibrarySystem.service.BookService.*(..))")
    public void countServiceMethodCalls() {
        meterRegistry.counter("BookService.method.calls").increment();
    }
}

