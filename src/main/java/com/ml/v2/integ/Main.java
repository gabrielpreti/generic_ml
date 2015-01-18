package com.ml.v2.integ;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		SpringCamelContext sprintContext = new SpringCamelContext(new ClassPathXmlApplicationContext("applicationContext.xml"));
		sprintContext.start();
		sprintContext.startAllRoutes();

		System.out.println("Started context");

		org.apache.camel.main.Main main = new org.apache.camel.main.Main();
		main.enableHangupSupport();
		main.run();

	}

}
