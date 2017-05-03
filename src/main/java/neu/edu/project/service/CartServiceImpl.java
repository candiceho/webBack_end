package neu.edu.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.project.dao.CartDao;
import neu.edu.project.dao.OrderDao;
import neu.edu.project.domain.Cart;
import neu.edu.project.domain.User;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDao cartDao;
	
	@Override
	@Transactional
	public Cart getCart(Long id){
		return cartDao.getCart(id);
	}
	
	@Override
	@Transactional
	public void addCart(Cart cart){
		 cartDao.addCart(cart);
	}
	@Override
	@Transactional
	public Cart findCartByUserId(Long userId){
		return (Cart) cartDao.findCartByUserId(userId);
	}
	
	@Override
	@Transactional
	public Long findUserByCartId(Long cartId){
		return (Long) cartDao.findUserByCartId(cartId);
	}
	
	@Override
	@Transactional
	public void deletecartByUserId(Long userId){
		cartDao.deletecartByUserId(userId);
	}
}
