package com.example.demo.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.exception.EmployeeAlreadyExistsException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.NoEmployeeExists;
import com.example.demo.model.Employee;
import com.example.demo.repository.MyRepository;

@Service
public class MySerivce {
	
	private static final Logger logger = LogManager.getLogger(MySerivce.class);
	
	@Autowired
	private MyRepository myRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Cacheable(cacheNames = "employeeList",condition = "#isCacheable")
	public List<Employee> getAllEmployees(boolean isCacheable){
		List<Employee> employeesList = (List<Employee>) myRepository.findAll();
		if(employeesList.isEmpty())
			throw new NoEmployeeExists("NO Employee Exists");
		logger.info("fetched employees list from database");
		return employeesList;
		//return myRepository.getAll();
	/*	Query query = entityManager.createNativeQuery("select * from employees");
		return query.getResultList(); */
	
	}

	@Cacheable(cacheNames  = "tenSecondCache",key = "#id")
	public Employee getEmployeeById(Long id) {
		
		logger.info("fetched employee from database");
		return myRepository.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException("No Employee Exists with given Id "+id));
		//return myRepository.getById(id);
	/*	return entityManager.createNativeQuery("select * from employees where id=?1")
						  .setParameter(1, id)
						  .getSingleResult();	*/
	}
	
	@Transactional
	public void insertEmployee(Employee emp) {
		Employee employee = myRepository.findById(emp.getId()).orElse(null);
		if(employee != null)
			throw new EmployeeAlreadyExistsException("Employee already exists");
		else {
			logger.info("saved employee to database");
			 myRepository.save(emp);
		}
		//myRepository.insert(emp.getId(), emp.getEmployeeName(), emp.getDepartment());
		/* return entityManager.createNativeQuery("insert into employees values (?1,?2,?3)")
			   .setParameter(1, emp.getId())
			   .setParameter(2, emp.getEmployeeName())
			   .setParameter(3, emp.getDepartment())
			   .executeUpdate(); */
	}
	
	@Transactional
	@Modifying
	public void updateEmployee(Employee emp,Long id)
	{
		Employee existingEmployee = myRepository.findById(id).orElse(null);
		if(existingEmployee != null) {
			logger.info("updated employee in the database");
			myRepository.save(emp);
		}
		else 
			throw new EmployeeNotFoundException("No Employee Exists with given id");
		//myRepository.update(emp.getId(), emp.getEmployeeName(), emp.getDepartment());
	/*	return entityManager.createNativeQuery("update employees set employeename=?2, department=?3 where id=?1)")
				   .setParameter(1, emp.getId())
				   .setParameter(2, emp.getEmployeeName())
				   .setParameter(3, emp.getDepartment())
				   .executeUpdate();  */
	}
	
	@Transactional
	public void deleteEmployee(Long id) {
		Employee existingEmployee = myRepository.findById(id).orElse(null);
		if(existingEmployee != null) {
			myRepository.deleteById(id);
			logger.info("deleted employee from database");
		}
		else 
			throw new EmployeeNotFoundException("No Employee Exists with given id");
	
		//myRepository.delete(id);
	/*	return entityManager.createNativeQuery("delete from  employees where id=?1)")
				   .setParameter(1, id)
				   .executeUpdate();  */
	}
	
	
}
