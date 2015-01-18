package com.ml.v2.integ;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.ml.v2.flow.event.impl.HttpEvent;

public class AccessLogEventProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		@SuppressWarnings("unchecked")
		List<List<String>> body = exchange.getIn().getBody(List.class);
		List<String> register = body.get(0);

		Date date = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss").parse(register.get(2));
		exchange.getIn().setBody(new HttpEvent(date, register.get(4), register.get(6)));

	}

}