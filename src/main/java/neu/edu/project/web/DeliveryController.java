package neu.edu.project.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import neu.edu.project.domain.Order;
import neu.edu.project.domain.User;
import neu.edu.project.service.OrderService;
import neu.edu.project.service.UserService;

@Controller
@SessionAttributes("user")
@RequestMapping("/delivery")
public class DeliveryController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showOrders(Model model)  throws UsernameNotFoundException{
		UserDetails userDetails;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findUserByUserName(userDetails.getUsername());
		if(user == null){
			throw new UsernameNotFoundException("Not able to find user");
		}
		model.addAttribute("user", user);	
		model.addAttribute(new Order());
		model.addAttribute("unFinishList", orderService.listUnfinished());
		model.addAttribute("finishList", orderService.listFinished());
		return "delivery";
	}
	
	@RequestMapping(value = "/{orderId}/finish", method = RequestMethod.POST)
	public String finishOrder(@PathVariable Long orderId) {
		Order order= orderService.getOrder(orderId);
		order.setStatus(true);
		orderService.addOrder(order);
		return "redirect:/delivery";       	
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
}
