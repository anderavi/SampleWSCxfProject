package com.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jws.WebService;

import com.sample.service.EchoWebService;

@WebService(endpointInterface = "com.sample.service.EchoWebService")
public class EchoWebServiceImpl implements EchoWebService {
	
	 @Override
	 public String echo(String name) {  
         if (name == null || name.trim().length() == 0) {  
             return "echo back: -please provide a name-";  
         }  
         SimpleDateFormat dtfmt = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");  
         return "echo back: name " + name + " received on " + dtfmt.format(Calendar.getInstance().getTime());  
     }  
}