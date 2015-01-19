package com.ml.v2.integ;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessLogEventsProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        CsvDataFormat csv = new CsvDataFormat("\t");
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        from("file:/tmp/load/?delete=true&preMove=.run").//
                id("eventsProducer").//
                autoStartup(true).//
                split(body().tokenize("\n")).//
                executorService(threadPool).//
                streaming().//
                unmarshal(csv).//
                process(new AccessLogEventProcessor()).//
                beanRef("httpEventsRepository", "addEvent").//
                log("result is ${body}").//
                end().//
                log("Done");
    }

}
