package com.kamilly.meson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class MesonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MesonApplication.class, args);
	}

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
