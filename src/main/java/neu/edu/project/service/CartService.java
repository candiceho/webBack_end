package neu.edu.project.service;

import java.util.List;

import neu.edu.project.domain.Cart;
import neu.edu.project.domain.User;

public interface CartService {
	public Cart getCart(Long id);
	public Cart findCartByUserId(Long userId);
	public void addCart(Cart cart);
	public Long findUserByCartId(Long cartId);
	public void deletecartByUserId(Long userId);
}
