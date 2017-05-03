package neu.edu.project.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import neu.edu.project.domain.Order;
import neu.edu.project.domain.Products;
import neu.edu.project.domain.User;
import neu.edu.project.service.OrderService;
import neu.edu.project.service.ProductsService;
import neu.edu.project.service.UserService;

@Controller
@SessionAttributes("user")
@RequestMapping("/vender")
public class VenderController {
	@Autowired
	private UserService userService;	
	@Autowired
	private OrderService orderService;	
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String initManagePage(Model model) {  //throws UsernameNotFoundException
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
		return "vender";
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrders(Model model){
		ArrayList<Order> orders = new ArrayList<Order>(orderService.listOrder());	
		model.addAttribute("orderList", orders);
		return "orders";
	}
	
	@RequestMapping(value = "/{productId}/delete", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable Long productId, HttpServletResponse response)throws IOException {
		productsService.deleteProducts(productId);
		return "redirect:/vender";	
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.GET)
	public String addProduct(Model model)throws IOException {
		model.addAttribute(new Products());
		return "createProduct";	
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String invalidatorUser(Model model, @Valid Products product, BindingResult bindResult){
		if(bindResult.hasErrors()){
			return "/vender/add";
		}
		productsService.addProducts(product);
		return "redirect:/vender"; 
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
	
	@RequestMapping(value = "/products/{productId}/photo" , method = RequestMethod.GET)
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
