package neu.edu.project.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.Cart;
import neu.edu.project.domain.Order;
import neu.edu.project.domain.Products;
import neu.edu.project.domain.User;

@Repository
public class CartDaoImpl implements CartDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Cart getCart(Long id){
		return (Cart) sessionFactory.getCurrentSession().get(Cart.class, id);		
	}
	
	@Override
	public void addCart(Cart cart){
		if(cart.getId() == null){  //null
			sessionFactory.getCurrentSession().save(cart);
		}
		else{
			sessionFactory.getCurrentSession().merge(cart);
		}
		sessionFactory.getCurrentSession().flush();
	}

	
	@Override
	public Cart findCartByUserId(Long userId){		
		return (Cart) sessionFactory.getCurrentSession().createQuery("from Cart c where c.user.id=:userId").setLong("userId", userId).uniqueResult();
	}
	
	@Override
	public Long findUserByCartId(Long cartId){		
		String a= sessionFactory.getCurrentSession().createQuery("select user_ID from Cart c where c.id=:cartId").setLong("cartId", cartId)
		.uniqueResult().toString();
		return Long.parseLong(a);
	}
	
	@Override
	public void deletecartByUserId(Long userId){
		Cart c=(Cart) sessionFactory.getCurrentSession().createQuery("from Cart c where c.user.id=:userId").setLong("userId", userId).uniqueResult();
		if (c != null) {
			sessionFactory.getCurrentSession().delete(c);
			sessionFactory.getCurrentSession().flush();
		}		
	}
}
