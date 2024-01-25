package com.mypetmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.mypetmanager.global.config.JpaConfig;

@ConfigurationPropertiesScan
@Import(JpaConfig.class)
@SpringBootApplication()
@EnableAspectJAutoProxy
public class MyPetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPetApplication.class, args);
	}


}
