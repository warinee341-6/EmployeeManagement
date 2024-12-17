package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Department;
import com.example.demo.model.service.WebClientDepartmentService;

import reactor.core.publisher.Mono;

@Controller
public class DepartmentController {

	@Autowired
	WebClientDepartmentService depService;
	
	@GetMapping("/web/departments")
	public String getAllDepartment(Model model) {
		model.addAttribute("department", depService.getAllDepartment().collectList().block());
		return "departmentListCRUD";
	}
	
	@GetMapping("/web/departments/{id}")
	public String getDepById(@PathVariable Long id, Model model) {
		Mono<Department> monoDep = depService.getDepById(id);
		Department dep = monoDep.block();
		model.addAttribute("dep", dep);
		return "departmentDetail";
	}
	
}
