package com.jyore.spring.scope.route.example.beans;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jyore.spring.scope.route.RouteScope;

@Component(value="InterceptInspector")
public class InterceptInspector {

	private static final Logger log = LoggerFactory.getLogger(InterceptInspector.class);
	
	public void process(Exchange exchange) {
		
		log.info(
				"\nINTERCEPT REPORT\n\n" +
		        "\t\tExchange Id: " + exchange.getExchangeId() + "\n" +
				"\t\tRoute Id: " + exchange.getFromRouteId() + "\n" +
		        "\t\tFrom Id:" + exchange.getFromEndpoint().getEndpointUri() + "\n" +
		        "\t\tCorrelation Id: " + exchange.getProperty(Exchange.CORRELATION_ID) + "\n" +
		        "\t\tScope Id: " + exchange.getProperty(RouteScope.SCOPE_PROPERTY) + "\n" +
		        "END REPORT"
		);
	}
}
