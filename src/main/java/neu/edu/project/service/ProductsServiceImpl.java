package neu.edu.project.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.project.dao.ProductsDao;
import neu.edu.project.dao.UserDao;
import neu.edu.project.domain.Products;

@Service
public class ProductsServiceImpl implements ProductsService{

	@Autowired
	private ProductsDao productsDao;
	
	@Override
	@Transactional
	public Products getProducts(Long id){
		return productsDao.getProducts(id);		
	}
	
	@Override
	@Transactional
	public Collection<Products> listProducts(){
		return productsDao.listProducts();
	}
	
	@Override
	@Transactional
	public void addProducts(Products product){
		productsDao.addProducts(product);
	}
	
	@Override
	@Transactional
	public void deleteProducts(Long productId){
		productsDao.deleteProducts(productId);
	}
}
