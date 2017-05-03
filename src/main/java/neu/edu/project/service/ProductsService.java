package neu.edu.project.service;

import java.util.Collection;

import neu.edu.project.domain.Products;

public interface ProductsService {
	public Products getProducts(Long id);
	public Collection<Products> listProducts();
	public void addProducts(Products product);
	public void deleteProducts(Long productId);
}
