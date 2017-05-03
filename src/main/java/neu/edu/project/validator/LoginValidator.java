package neu.edu.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import neu.edu.project.dao.UserDao;
import neu.edu.project.domain.User;

@Component
public class LoginValidator implements Validator{
	
	@Autowired 
	private UserDao userDao;
	
	@Override
	public boolean supports(Class<?> clazz) {     
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		User newUser = userDao.findUserByUserName(user.getUserName());
		if(newUser != null){
			errors.rejectValue("userName", "userName.alreadyExist", "User Name existed!");
		}
	    //	User user = (User)target;
		//  User targetUser = userDao.findUserByUserName(user.getUserName());
			
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "UserName is invalid");
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.alreadyExist", "User Already Exist");
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Password Is Incorrect");
	//errors.rejectValue("userName", "userName.alreadyExist", "User name is not available, try something else!");
	}
}


	
			