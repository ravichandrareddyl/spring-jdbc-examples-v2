package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.dao.ExampleDAO;
import com.example.demo.model.Customer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		ExampleDAO dao= context.getBean(ExampleDAO.class);
		System.out.println("printing list of users");
		List<Customer> custs= dao.getListOfUsers();
	 	System.out.println("printing size"+ custs.size());
	 	int [] count = dao.batchUpdate(custs);
	 	System.out.println("prinintg row count"+ count.length);
	}
}
