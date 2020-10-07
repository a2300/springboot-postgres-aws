package com.example.aws.springbootpostgresaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/aws-config.xml")
@EnableRdsInstance(databaseName = "${database-name:}",
		dbInstanceIdentifier = "${db-instance-identifier:}",
		username = "${rdsUserName:}", password = "${rdsPassword:}")
public class SpringbootPostgresAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPostgresAwsApplication.class, args);
	}

}
