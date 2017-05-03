package neu.edu.project.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neu.edu.project.domain.User;
import neu.edu.project.service.UserService;
import neu.edu.project.validator.LoginValidator;

@Controller
public class AccountController {
	@Autowired
	private UserService userService;
	
	@Autowired 
	private LoginValidator loginValidator;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model){
		model.addAttribute(new User());
		return "/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String invalidatorUser(Model model, @Valid User user, BindingResult bindResult){
		loginValidator.validate(user,bindResult);
		if(bindResult.hasErrors()){
			return "/register";
		}
		PasswordEncoder passwordEncode = new StandardPasswordEncoder();
		user.setPassword(passwordEncode.encode(user.getPassword()));
		userService.addUser(user);
		return "redirect:/"; 
	}
}
