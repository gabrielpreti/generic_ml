package com.ml.core.impl.accesslog.integ;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

public class AccessLogEventsProducer extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		CsvDataFormat csv = new CsvDataFormat("\t");

		from("file:/tmp/load/?delete=true&preMove=.run").//
				id("eventsProducer").//
				autoStartup(true).//
				split(body().tokenize("\n")).//
				streaming().//
                threads(10).//
				unmarshal(csv).//
				process(new AccessLogEventProcessor()).//
				beanRef("httpEventsRepository", "addEvent").log("result is ${body}");
		
		
		from("file:/tmp/analysis?delete=true&preMove=.run").//
		autoStartup(true).//
		beanRef("httpEventsModel", "runRepositoryAnalysis");
	}

}
