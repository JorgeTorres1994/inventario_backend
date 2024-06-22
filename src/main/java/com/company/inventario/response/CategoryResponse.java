package com.company.inventario.response;

import java.util.List;

import com.company.inventario.model.Category;

import lombok.Data;
@Data
public class CategoryResponse {
	private List<Category> categoria;
}
