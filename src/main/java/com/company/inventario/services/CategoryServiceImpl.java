package com.company.inventario.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventario.dao.ICategoryDao;
import com.company.inventario.model.Category;
import com.company.inventario.response.CategoryResponseRest;

//Implementacion de los servicios declarados en ICategoriaService

@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private ICategoryDao categoriaDao;//Hacemos la inyeccion del ICategoriaDao con Autowired
	
	@Override
	@Transactional(readOnly = true)//Es un metodo transaccional
	public ResponseEntity<CategoryResponseRest> mostrar() {
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> categoria = (List<Category>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta invalida", "-1", "Ocurrió un error");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}
	
}
