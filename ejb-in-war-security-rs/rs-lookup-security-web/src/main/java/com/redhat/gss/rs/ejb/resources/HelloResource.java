package com.redhat.gss.rs.ejb.resources;

import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.redhat.gss.rs.ejb.HelloWorldService;

@Stateless
@Path("hello")
public class HelloResource {

	Logger logger = Logger.getLogger(HelloResource.class.getName());

	@EJB
	HelloWorldService service;

	@GET
	@PermitAll
	@Path("public")
	public String hello(@QueryParam("name") String name) {
		logger.warning("public hello");
		return sayHello(name);
	}

	@GET
	@Path("role1")
	@RolesAllowed("role1")
	public String helloRole1(@QueryParam("name") String name) {
		logger.warning("hello for role1 only");
		return sayHello(name);
	}

	@GET
	@Path("role2")
	@RolesAllowed("role2")
	public String helloRole2(@QueryParam("name") String name) {
		logger.warning("hello for role2 only");
		return sayHello(name);
	}

	private String sayHello(String name) {
		String hellGreeting = service.sayHello(name);
		logger.info(hellGreeting);
		return hellGreeting;
	}

}
