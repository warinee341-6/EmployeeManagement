package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientEmployeeService {

	@Autowired
	private final WebClient webClient;

	@Autowired
	WebClientDepartmentService departmentService;

	@Autowired
	WebClientEmployeeService(WebClient webClient) {
		this.webClient = webClient;
	}

	public Flux<Employee> getAllEmployee() {

		return webClient.get().uri("/employees").retrieve().bodyToFlux(Employee.class);
	}

	public Mono<Employee> getEmpById(Long id) {
		return webClient.get().uri("/employees/" + id).retrieve().bodyToMono(Employee.class);
	}

	public Mono<Employee> addEmployee(Employee emp) {
		return webClient.post().uri("/employees/addnew/" + emp.getDepartment().getId()) // URL สำหรับ RESTful API ของคุณ
				.bodyValue(emp) // ส่งข้อมูล Employee
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError,
						clientResponse -> Mono
								.error(new RuntimeException("เกิดข้อผิดพลาดจากฝั่งลูกค้าในการเพิ่มพนักงาน"))) // จัดการข้อผิดพลาด																												// 4xx
				.onStatus(HttpStatusCode::is5xxServerError,
						clientResponse -> Mono
								.error(new RuntimeException("เกิดข้อผิดพลาดจากฝั่งเซิร์ฟเวอร์ในการเพิ่มพนักงาน"))) // จัดการข้อผิดพลาด																													// 5xx
				.bodyToMono(Employee.class) // แปลงการตอบกลับเป็นวัตถุ Employee
				.doOnNext(response -> System.out.println("ได้รับการตอบกลับ: " + response)) // บันทึกการตอบกลับ
				.doOnError(error -> System.out.println("เกิดข้อผิดพลาด: " + error.getMessage())); // จัดการข้อผิดพลาด
	}

	public Mono<Employee> updateEmployee(Long id, Employee emp) {
		return webClient.put().uri("/employees/edit/" + id).bodyValue(emp).retrieve().bodyToMono(Employee.class);
	}

	public Mono<Void> deleteEmployee(Long id) {
		return webClient.delete().uri("/employees/delete/" + id).retrieve().bodyToMono(Void.class);
	}

}
