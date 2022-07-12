package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface MyRepository extends CrudRepository<Employee, Long> {
	
@Query(value = "select * from employees",nativeQuery = true)
public List<Employee> getAll();

@Query(value = "select * from employees where id=?1",nativeQuery = true)
public Employee getById(Long id);

@Modifying
@Transactional
@Query(value = "insert into employees values (?1,?2,?3)",nativeQuery = true)
public void insert(Long id, String name, String department);

@Modifying
@Transactional
@Query(value = "update employees set employeename=?2, department=?3 where id=?1",nativeQuery = true)
public void update(Long id, String name, String department);

@Modifying
@Transactional
@Query(value = "delete from employees where id=?1", nativeQuery = true)
public void delete(Long id);
}
