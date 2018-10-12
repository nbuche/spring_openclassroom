package org.example.demo.ticket.webapp.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("org.example.demo.ticket")
@ImportResource("classpath:/bootstrapContext.xml")
public class SpringConfiguration {

}
