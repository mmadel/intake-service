package com.cob.salesforce;

import com.cob.salesforce.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class CobSalesForceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobSalesForceServiceApplication.class, args);
	}

}
