package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sample.controller.GlobalFilter;

@SpringBootApplication
public class Sample1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sample1Application.class, args);
	}

	@Bean
	FilterRegistrationBean<GlobalFilter> globalfilter(){
		final FilterRegistrationBean<GlobalFilter> global=new FilterRegistrationBean<>();
		global.setFilter(new GlobalFilter());
		global.addUrlPatterns("/privatechat","/chatroom");
		return global;
	}
}
