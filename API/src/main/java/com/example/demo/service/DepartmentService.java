package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository repo;
	
	public List<Department> findAllDepartment(){
		return (List<Department>) repo.findAll();
	}
	
	public Department findDepartmentById(Long id) {
		return repo.findById(id).orElseThrow(()->new DepartmentFoundException(id));
	}
	
	public void save(Department d) {
		repo.save(d);
	}
	
	public Department addDepartment(Department d) {
		repo.save(d);
		return d;
	}
	
	public Department updateDepartment(Long id,Department department) {
		Department existingDep = repo.findById(id).orElseThrow(()-> new DepartmentFoundException(id));
		existingDep.setName(department.getName());
		existingDep.setEmployees(department.getEmployees());
		return repo.save(existingDep);
	}
	
	public void deleteDepartment(Long id) {
		repo.deleteById(id);
	}

}
