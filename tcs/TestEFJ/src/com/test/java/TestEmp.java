package com.test.java;

import java.util.Date;

public class TestEmp extends Employee {

	public static void main (String[] args) throws CloneNotSupportedException {
		Employee e = new Employee();
		Employee e1 = (Employee) e.clone();
		
		Date d = new Date();
		d.clone();
		
		
		System.out.println("test passed");
	}
	
	@Override
	public Integer test () {
		System.out.println("test");
	//	id = new Date();
         return null;
	}

}