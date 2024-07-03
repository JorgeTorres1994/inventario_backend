package com.company.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventario.model.Category;
import com.company.inventario.response.CategoryResponseRest;
import com.company.inventario.services.ICategoryService;

@RestController
@RequestMapping("/api/v1") //url del api
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	//Obtener todas las categorias (get all categories)
	
	@GetMapping("/categories")//obtiene la uri
	public ResponseEntity<CategoryResponseRest> mostrar(){
		ResponseEntity<CategoryResponseRest> response = service.mostrar();//igualamoscomo respuesta al metodo mostrar del servicio
		return response;
	}
	
	//Obtener las categorias por id (get categories by id)
	
	@GetMapping("/categories/{id}")//obtiene la uri
	public ResponseEntity<CategoryResponseRest> buscarPorId(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.buscarPorId(id);//igualamoscomo respuesta al metodo mostrar del servicio
		return response;
	}
	
	//Guardar categorias con parametro Category
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> guardar(@RequestBody Category category){//Requestbody recupera lo que venga del objeto JSON y mapear a la Categoria
		ResponseEntity<CategoryResponseRest> response = service.guardar(category);//igualamoscomo respuesta al metodo guardar del servicio
		return response;
	}
	
	//Actualizar categoria con parametro Category y el id
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> actualizar(@RequestBody Category category, @PathVariable Long id){//Requestbody recupera lo que venga del objeto JSON y mapear a la Categoria y el id  a buscar
		ResponseEntity<CategoryResponseRest> response = service.actualizar(category, id);//igualamoscomo respuesta al metodo actualizar del servicio
		return response;
	}
	
	//Eliminar categtoria por id
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> eliminarPorId(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.eliminar(id);//igualamoscomo respuesta al metodo mostrar del servicio
		return response;
	}
	
}
