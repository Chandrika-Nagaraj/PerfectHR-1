package com.test;

public class MySingle {
	
private static MySingle obj=null;
	
	private MySingle() {
		
		
	}
	
	private static void getObject() {
		if(obj!=null) {
			obj= new  MySingle();
		}
		 
	}
	
	
}
