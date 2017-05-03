package neu.edu.project.service;

import java.util.Collection;

import neu.edu.project.domain.User;

public interface UserService {
	 User getUser(Long id);
	 void addUser(User user);
	 Collection<User> listUser();
	 User findUserByUserName(String uName);
	 void deleteUser(User user);
	// User findUserByUserId(Long userId);
}
