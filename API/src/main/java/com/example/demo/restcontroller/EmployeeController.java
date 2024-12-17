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

import com.example.demo.model.Employee;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@Autowired
	DepartmentService depService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmp(){
		List<Employee> emp = empService.findAllEmployee();
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") Long id){
		Employee emp = empService.findEmployeeById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@PostMapping("/employees/addnew/{id}")
	public Employee addEmployee(@RequestBody Employee newEmp,@PathVariable Long id){
		Employee emp = empService.addEmployee(newEmp, id);
		return emp;
	}
	
	@PutMapping("/employees/edit/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updateEmp, @PathVariable Long id){
		Employee emp = empService.updateEmployee(id, updateEmp);
		return ResponseEntity.ok(emp);
	}
	
	@DeleteMapping("/employees/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		empService.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted successfully");
	}
}
