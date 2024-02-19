package com.mohamedcode.mailpulse;

import com.mohamedcode.mailpulse.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MailPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailPulseApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
////		AuthFilter authFilter = new AuthFilter();
//		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//
//		registrationBean.setFilter(new AuthFilter());
////		registrationBean.addUrlPatterns("/home", "/home/*");
////		registrationBean.addUrlPatterns("/home/getinbox");
//
//		return registrationBean;
//	}
}
