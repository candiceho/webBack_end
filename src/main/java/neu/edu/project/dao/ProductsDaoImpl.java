package neu.edu.project.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import neu.edu.project.domain.Order;
import neu.edu.project.domain.Products;
import neu.edu.project.domain.User;

@Repository
public class ProductsDaoImpl implements ProductsDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Products getProducts(Long id){
		return (Products) sessionFactory.getCurrentSession().get(Products.class, id);		
	}
	
	@Override
	public Collection<Products> listProducts(){
		return sessionFactory.getCurrentSession().createQuery("From Products Order By id").list();
	}
	
	@Override
	public void addProducts(Products product){
		if(product.getId() == null){  //null
			sessionFactory.getCurrentSession().save(product);
		}
		else{
			sessionFactory.getCurrentSession().update(product);
		}
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void deleteProducts(Long productId){
		Products products = getProducts(productId);
		if (products != null) {
			sessionFactory.getCurrentSession().delete(products);
			sessionFactory.getCurrentSession().flush();
		}
	}
	
}
