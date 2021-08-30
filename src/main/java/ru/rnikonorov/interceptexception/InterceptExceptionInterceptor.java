package ru.rnikonorov.interceptexception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect
public class InterceptExceptionInterceptor {

    @Pointcut("@annotation(InterceptThis)")
    public void interceptor() {
    }

    @Around("interceptor()")
    public Object intercept(final ProceedingJoinPoint joinPoint) {
        final Object result;
        try {

            result = joinPoint.proceed();

            final ResponseEntity<DataTransferObjectDto> responseEntity = (ResponseEntity<DataTransferObjectDto>) result;
            addInterceptorInfo(Objects.requireNonNull(responseEntity.getBody()));
            return responseEntity;
        } catch (Throwable throwable) {
            final DataTransferObjectDto dto = new DataTransferObjectDto(null);
            dto.setExceptionInfo("Exception occurs");
            final ResponseEntity<DataTransferObjectDto> responseEntity = new ResponseEntity<>(
                    dto,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            addInterceptorInfo(Objects.requireNonNull(responseEntity.getBody()));
            return responseEntity;
        }
    }

    private void addInterceptorInfo(final DataTransferObjectDto dto){
        dto.setInterceptionInfo("Here is interceptor info");
    }

}
