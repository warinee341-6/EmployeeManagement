package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.service.WebClientDepartmentService;
import com.example.demo.model.service.WebClientEmployeeService;

import reactor.core.publisher.Mono;

@Controller
public class EmployeeController {

	@Autowired
	WebClientEmployeeService empService;
	
	@Autowired
	WebClientDepartmentService depService;
	
	@GetMapping("/web/employees")
	public String getAllEmployee(Model model) {
	    model.addAttribute("emp", empService.getAllEmployee().collectList().block());
	    return "employeeListCRUD";
	}

	
	@GetMapping("/web/employees/view/{id}")
	public String getEmpById(@PathVariable Long id, Model model) {
		Mono<Employee> monoEmp = empService.getEmpById(id);
		Employee emp = monoEmp.block();
		model.addAttribute("emp", emp);
		return "employeeDetail";
	}
	
	@GetMapping("/web/employees/form")
	public String createEmployee(Model model) {
	    List<Department> department = depService.getAllDepartment().collectList().block();
	    model.addAttribute("department", department);
	    Employee emp = new Employee();
	    model.addAttribute("emp", emp); 
	    return "addEmployee"; 
	}

	@PostMapping("/web/employees/addnew")
	public String addEmployee(@ModelAttribute Employee empRequest, Model model) {
		Mono<Employee> emp = empService.addEmployee(empRequest);
		model.addAttribute("emp", emp.block());
	    return "redirect:/web/employees";
	}

	@GetMapping("/web/employees/edit/{id}")
	public String editEmployee(@PathVariable Long id, Model model) {
		List<Department> department = depService.getAllDepartment().collectList().block();
	    model.addAttribute("department", department);
		Mono<Employee> monoEmp = empService.getEmpById(id);
		Employee emp = monoEmp.blockOptional().get();
		model.addAttribute("emp", emp);
		return "editEmployeeForm";
	}
	
	@PostMapping("/web/employees/update/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee empRequest, Model model) {
		Mono<Employee> monoEmp = empService.updateEmployee(id, empRequest);
		model.addAttribute("emp", monoEmp.block());
		return "redirect:/web/employees/view/{id}";
	}


}
