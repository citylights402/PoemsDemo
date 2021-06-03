package com.zhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhou.mapper")//mybatis plus专属扫描mapper包
public class PoemsDemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(PoemsDemo01Application.class, args);
	}

}
