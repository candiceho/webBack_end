package neu.edu.project.dao;

import java.util.Collection;

import neu.edu.project.domain.User;

public interface UserDao {
	public User getUser(Long id);
	public void addUser(User user);
	public Collection<User> listUser();
	public User findUserByUserName(String uName);
	public void deleteUser(User user);
}
