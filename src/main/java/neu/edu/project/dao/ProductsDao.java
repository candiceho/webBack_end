package neu.edu.project.dao;

import java.util.Collection;

import neu.edu.project.domain.Products;

public interface ProductsDao {
	public Products getProducts(Long id);
	public Collection<Products> listProducts();
	public void addProducts(Products product);
	public void deleteProducts(Long productId);
}
