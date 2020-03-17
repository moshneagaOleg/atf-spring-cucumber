package io.tpd.springbootcucumber;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

@ExtendWith(SpringExtension.class)
@SpringBootApplication(scanBasePackages = {"io.tpd.springbootcucumber"})
public class SpringBootCucumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCucumberApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker(Locale.US);
    }

}
