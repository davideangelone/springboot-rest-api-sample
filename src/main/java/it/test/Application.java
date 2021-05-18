package it.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Test Rest Api Application", version = "1.0.0")
)
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
		logger.info(">>> Json specs at : /v3/api-docs <<<");
		logger.info(">>> Yaml specs at : /v3/api-docs.yaml <<<");
		logger.info(">>> Swagger at : /swagger-ui.html <<<");	
    }
}
