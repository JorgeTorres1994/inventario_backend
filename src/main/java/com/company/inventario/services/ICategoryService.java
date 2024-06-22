package com.company.inventario.services;

import org.springframework.http.ResponseEntity;

import com.company.inventario.response.CategoryResponseRest;

//Interfaz para declarar los servicios
public interface ICategoryService {
	public ResponseEntity<CategoryResponseRest> mostrar();//da estructura de respuesta http con un codigo y con una respuesta custom (CategoriaResponseRest)
	public ResponseEntity<CategoryResponseRest> buscarPorId(Long id);
	
}
