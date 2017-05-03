package neu.edu.project.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import neu.edu.project.domain.Cart;
import neu.edu.project.domain.Order;
import neu.edu.project.domain.Products;
import neu.edu.project.domain.User;
import neu.edu.project.service.CartService;
import neu.edu.project.service.OrderService;
import neu.edu.project.service.ProductsService;
import neu.edu.project.service.UserService;

@Controller
@SessionAttributes("user")
@RequestMapping("/buyer")
public class BuyerController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired 
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showProducts(Model model) {
		UserDetails userDetails;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findUserByUserName(userDetails.getUsername());
		if(user == null){
			throw new UsernameNotFoundException("Not able to find user");
		}
		model.addAttribute("user", user);						
		model.addAttribute(new Products());
		model.addAttribute("productsList", productsService.listProducts());
		return "buyer";
	}

	@RequestMapping(value = "/{userId}/{productId}/addCart", method = RequestMethod.GET)
	public String addCart(@PathVariable Long productId, @PathVariable Long userId, @ModelAttribute("user") User user) {
	    Cart cart = new Cart();
	    if(cartService.findCartByUserId(userId) != null){
	    	cart= cartService.findCartByUserId(userId);
	    } 	
		Products product = productsService.getProducts(productId);
		cart.addProducts(product);
		cart.setTotal(cart.getTotal());
		cart.setUser(user);
		cartService.addCart(cart);
		return "redirect:/buyer";	
	}
	
	@RequestMapping(value = "/{cartId}/placeOrder", method = RequestMethod.POST)
	public String placeOrder( @PathVariable Long cartId,  @ModelAttribute("user") User user, Model model) {
	    Order order = new Order();	 
        long idd = user.getId(); 
	    Cart cart = cartService.findCartByUserId(idd);	
		if(cart.getProducts() != null){		   
		    for(Products p : cart.getProducts()){
		    	order.addProducts(p);
		    }
		}
		order.setStatus(false);
		order.setTotal(order.getTotal());
		order.setUser(user);
		orderService.addOrder(order);
		cartService.deletecartByUserId(idd);
		return "redirect:/buyer/" + idd + "/order";		
	}
	
	@RequestMapping(value = "/{userId}/order", method = RequestMethod.GET)
	public String viewMyOrder(@PathVariable Long userId, Model model, HttpServletResponse response)throws IOException {
		ArrayList<Order> myorderlist = 
		new ArrayList<Order>(orderService.findOrderByUserId(userId));
		model.addAttribute("orderlist", myorderlist);
		return "myorder";	
	}
	
	@RequestMapping(value = "/{cartId}/{productId}/deleteCart", method = RequestMethod.GET)
	public String deleteCart(@PathVariable Long productId, @PathVariable Long cartId, @ModelAttribute("user") User user) {
	    Cart cart = cartService.getCart(cartId);
	    Products product = productsService.getProducts(productId);
	    cart.deleteProducts(product);
	    cart.setTotal(cart.getTotal());
		cart.setUser(user);
		Long userId = user.getId();
		cartService.addCart(cart);
	    return "redirect:/buyer/" + userId + "/cart";	
	}
	
	@RequestMapping(value = "/{userId}/cart", method = RequestMethod.GET)
	public String viewCart(@PathVariable Long userId, @Valid User user,BindingResult result,Model model){
		if(result.hasErrors()){
			model.addAttribute("error", "emptyy");
			return "buyer";
		}
	    Cart cart = cartService.findCartByUserId(userId);
		model.addAttribute("cart",cart);
	    cartService.addCart(cart);
		return "mycart";	
	}
	
	@RequestMapping(value = "/user/{userId}/photo", method = RequestMethod.GET)
	public String venderPhoto(@PathVariable Long userId, HttpServletResponse response)throws IOException {
		User user = userService.getUser(userId); 
		byte[] photoBytes = user.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(user.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + user.getPhotoFilename() + "\"");
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";	
	}
	
	@RequestMapping(value = "/products/{productId}/photo", method = RequestMethod.GET)
	public String productsPhoto(@PathVariable Long productId, HttpServletResponse response)throws IOException {
		Products product = productsService.getProducts(productId);
		byte[] photoBytes = product.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(product.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + product.getPhotoFilename() + "\"");
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";	
	}
}
