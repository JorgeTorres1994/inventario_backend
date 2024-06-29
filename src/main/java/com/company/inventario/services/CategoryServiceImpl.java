package com.company.inventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventario.dao.ICategoryDao;
import com.company.inventario.model.Category;
import com.company.inventario.response.CategoryResponseRest;

//Implementacion de los servicios declarados en ICategoriaService

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryDao categoriaDao;// Hacemos la inyeccion del ICategoriaDao con Autowired

	@Override
	@Transactional(readOnly = true) // Es un metodo transaccional
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

	@Override
	@Transactional(readOnly = true) // Es un metodo transaccional
	public ResponseEntity<CategoryResponseRest> buscarPorId(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> lista = new ArrayList<>();
		try {
			Optional<Category> category = categoriaDao.findById(id);
			if(category.isPresent()) {//si la categoria esta presente
				lista.add(category.get());
				response.getCategoriaResponse().setCategoria(lista);
				response.setMetadata("Respuesta ok", "00", "Categoría encontrada");
			}else {
				response.setMetadata("Respuesta invalida", "-1", "No se encontró la categoría");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta invalida", "-1", "Ocurrió un error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> guardar(Category category) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> lista = new ArrayList<>();
		try {
			Category categorySaved = categoriaDao.save(category);
			if(categorySaved != null) {
				lista.add(categorySaved);
				response.getCategoriaResponse().setCategoria(lista);
				response.setMetadata("Respuesta ok", "00", "Categoría guardada");
			}else {
				response.setMetadata("Respuesta invalida", "-1", "No se guardó la categoría");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta invalida", "-1", "Ocurrió un error al guardar la categoria");
			e.getStackTrace();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
