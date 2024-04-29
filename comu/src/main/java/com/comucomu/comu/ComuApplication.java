package com.comucomu.comu;

import com.comucomu.comu.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication()
public class ComuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComuApplication.class, args);
	}

}
