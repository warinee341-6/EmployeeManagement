package com.example.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Department;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientDepartmentService {
	
	@Autowired
	private final WebClient webClient;

	@Autowired
    WebClientDepartmentService(WebClient webClient) {
        this.webClient = webClient;
    }
	
	public Flux<Department> getAllDepartment(){
		return webClient.get().uri("/departments").retrieve().bodyToFlux(Department.class);
	}
	
	public Mono<Department> getDepById(Long id){
		return webClient.get().uri("/departments/"+id).retrieve().bodyToMono(Department.class);
	}
	
	public Mono<Department> addDepartment(Department dep){
		return webClient.post().uri("/departments/addnew").body(Mono.just(dep), Department.class).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Client error during addDepartment")))
				.onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Server error during addDepartment")))
				.bodyToMono(Department.class)
				.doOnNext(respone -> System.out.println("Respone receive: "+respone))
				.doOnError(error -> System.out.println("Error Occured: "+error.getMessage()));
	}
	
	public Mono<Department> updateDepartment(Long id,Department dep){
		return webClient.put().uri("/departments/edit/"+id).bodyValue(dep).retrieve().bodyToMono(Department.class);
	}

	public Mono<Void> deleteDepartment(Long id){
		return webClient.delete().uri("/departments/delete/"+id).retrieve().bodyToMono(Void.class);
	}
}
