package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentController {
	
	@Autowired
	DepartmentService depService;
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getAllDep(){
		List<Department> dep = depService.findAllDepartment();
		return new ResponseEntity<>(dep, HttpStatus.OK);
	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<Department> getDepById(@PathVariable("id") Long id){
		Department dep = depService.findDepartmentById(id);
		return new ResponseEntity<>(dep, HttpStatus.OK);
	}
	
	@PostMapping("/departments/addnew")
	public Department addDepartment(@RequestBody Department newdep) {
		Department dep = depService.addDepartment(newdep);
		return dep;
	}
	
	@PutMapping("/departments/edit/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updateDep){
		Department dep = depService.updateDepartment(id, updateDep);
		return ResponseEntity.ok(dep);
	}
	
	@DeleteMapping("/departments/delete/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
		depService.deleteDepartment(id);
		return ResponseEntity.ok("Department deleted successfully");
	}

}
