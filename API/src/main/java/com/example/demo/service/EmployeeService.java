package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;

	@Autowired
	DepartmentRepository depRepo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Employee> findAllEmployee(){
		return (List<Employee>) repo.findAll();
	}
	
	public Employee findEmployeeById(Long id) {
		return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
//	public void save(Employee e) {
//		repo.save(e);
//	}
	
	@Transactional
	public Employee addEmployee(Employee emp,Long id) {
		Department dep = depRepo.findById(id).orElseThrow(()->new DepartmentFoundException(id));
		emp.setDepartment(dep);
		entityManager.persist(emp);
		return emp;
	}
	
	public Employee updateEmployee(Long id, Employee e) {
		Employee existingEmp = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		existingEmp.setName(e.getName());
		existingEmp.setSalary(e.getSalary());
		existingEmp.setDepartment(e.getDepartment());
		return repo.save(existingEmp);
	}
	
	public void deleteEmployee(Long id) {
		repo.deleteById(id);
	}
}
