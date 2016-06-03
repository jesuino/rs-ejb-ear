package com.redhat.gss.rs.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(HelloWorldService.class)
public class HelloWorldServiceBean implements HelloWorldService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name + ", EJB from a JAX-RS resource.";
	}

}
