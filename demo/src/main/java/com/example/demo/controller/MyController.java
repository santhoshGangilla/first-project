package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Employee;
import com.example.demo.service.MySerivce;

@RestController
public class MyController {
	
	private static final Logger Logger = LogManager.getLogger(MyController.class);
	
	@Autowired
	private MySerivce mySerivce;
	
	@GetMapping("/getAll")
	public List<Employee> getAllEmployees(@RequestParam("isCacheable") boolean isCacheable){
		List<Employee> list = mySerivce.getAllEmployees(isCacheable);
		Logger.debug("All the employees are ");
		return list;
		
	}
	
	@GetMapping("/getById/{id}")
	public Object getEmployeeId(@PathVariable Long id) {
		return mySerivce.getEmployeeById(id);
	}
	
	@PostMapping("/insert")
	public String insertEmployee(@RequestBody Employee emp) {
		//mySerivce.insertEmployee(emp);
		mySerivce.insertEmployee(emp);
		return "Employee inserted successfully k = ";
	}
	
	@PutMapping("/update/{id}")
	public String updateEmployee(@RequestBody Employee emp,@PathVariable Long id) {
		mySerivce.updateEmployee(emp, id);
		return "Employee updated successfully";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		mySerivce.deleteEmployee(id);
		return "Employee deleted successfully";
	}
	
	
}
