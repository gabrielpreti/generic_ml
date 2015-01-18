package com.ml.core.impl.accesslog.integ;

import org.apache.camel.builder.RouteBuilder;

public class PrintSlots extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:/tmp/slots").beanRef("httpEventsRepository", "printSlotsReport");

	}

}
