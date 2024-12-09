package com.amatta.scheduler.amatta_back;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class AmattaBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmattaBackApplication.class, args);
    }

}
