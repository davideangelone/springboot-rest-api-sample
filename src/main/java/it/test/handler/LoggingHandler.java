package it.test.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingHandler {

    private static final Logger log = LoggerFactory.getLogger(LoggingHandler.class);

    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void repository() {
    	//Match any repository
    }
    
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    	//Match any controller
    }
    
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    	//Match any service
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    	//Match any method execution
    }
    
    private String serialize(Object ojb) {
    	try {
    		return new ObjectMapper().writeValueAsString(ojb);
    	} catch (Exception e) {
    		return String.valueOf(ojb);
    	}
    }
    
    private String getDate() {
    	return new SimpleDateFormat("dd-MM-yyyy HH24:mm:ss").format(new Date());
    }
    
    @Around("(repository() || controller() || service()) && allMethod()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
    	
    	Object result = null;
    	
    	try {
	        log.info(">>>> {} - [{}.{}] - Method [{}] - Args {}",
	        		getDate(),
	        		joinPoint.getSignature().getDeclaringType().getSimpleName(),
	        		joinPoint.getSignature().getName(),
	        		joinPoint.getSignature(), 
	        		serialize(joinPoint.getArgs()));
	        
	        result = joinPoint.proceed();
	        
	        log.info("<<<< {} - [{}.{}] - Method [{}] - Result [{}]",
	        		getDate(),
	        		joinPoint.getSignature().getDeclaringType().getSimpleName(),
	        		joinPoint.getSignature().getName(),
	        		joinPoint.getSignature(),
	        		serialize(result));
	        
    	} catch (Throwable t) {
	        log.info("<<<< {} - [{}.{}] - Method [{}] - Result [{}]",
	        		getDate(),
	        		joinPoint.getSignature().getDeclaringType().getSimpleName(),
	        		joinPoint.getSignature().getName(),
	        		joinPoint.getSignature(),
	        		serialize(t.toString()));
	        
	        throw t;
    	}
    	
    	return result;
    }
    
}