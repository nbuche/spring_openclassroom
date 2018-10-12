package org.example.demo.ticket.batch;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example.demo.ticket")
@ImportResource("classpath:/bootstrapContext.xml")
@PropertySource(ignoreResourceNotFound = false, value=  
	  "file:${application.home}/conf/config.properties"
	)
public class SpringConfiguration {

}
