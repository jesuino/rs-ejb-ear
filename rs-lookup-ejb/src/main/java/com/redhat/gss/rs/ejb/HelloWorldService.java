package com.redhat.gss.rs.ejb;

import javax.ejb.Remote;

@Remote
public interface HelloWorldService {
	
	public String sayHello(String name);

}
