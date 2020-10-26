package jp.co.axa.apidemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @EnableCaching  Text is for Enabling Cahching
 * ***/
@EnableSwagger2
@SpringBootApplication
@EnableCaching
public class ApiDemoApplication {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ApiDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
		 LOGGER.error("Application Started ApiDemoApplication ");
	}

}
