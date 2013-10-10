package com.sample.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface EchoWebService {
	public String echo(@WebParam(name="fullName") String name);  
}
