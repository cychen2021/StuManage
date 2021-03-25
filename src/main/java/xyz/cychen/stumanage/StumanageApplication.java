package xyz.cychen.stumanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class StumanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StumanageApplication.class, args);
	}

}
