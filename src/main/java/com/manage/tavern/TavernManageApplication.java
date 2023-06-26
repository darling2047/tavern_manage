package com.manage.tavern;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.manage.tavern"})
@EnableTransactionManagement
@MapperScan("com.manage.tavern.mapper")
public class TavernManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TavernManageApplication.class, args);
    }

}
