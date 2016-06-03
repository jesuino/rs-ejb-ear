package com.redhat.gss.rs.ejb.resources;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.redhat.gss.rs.ejb.HelloWorldService;

@Path("hello")
@Stateless
public class HelloResource {

	Logger logger = Logger.getLogger(HelloResource.class.getName());
	
	@EJB
	HelloWorldService service;
	
	@GET
	public String hello(@QueryParam("name") String name) {
		String hellGreeting = service.sayHello(name);
		logger.info(hellGreeting);
		return hellGreeting;
	}
	
}
