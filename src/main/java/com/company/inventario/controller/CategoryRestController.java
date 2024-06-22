package com.company.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventario.response.CategoryResponseRest;
import com.company.inventario.services.ICategoryService;

@RestController
@RequestMapping("/api/v1") //url del api
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	@GetMapping("/categories")//obtiene la uri
	public ResponseEntity<CategoryResponseRest> mostrarCategorias(){
		ResponseEntity<CategoryResponseRest> response = service.mostrar();//igualamoscomo respuesta al metodo mostrar del servicio
		return response;
	}
}
