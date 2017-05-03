package neu.edu.project.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.project.dao.UserDao;
import neu.edu.project.domain.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public User getUser(Long id){
		return userDao.getUser(id);
	}
	
	@Override
	@Transactional
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	@Override
	@Transactional
	public void deleteUser(User user){
		 userDao.deleteUser(user);
	}
	
	@Override
	@Transactional
	public Collection<User> listUser(){
		return userDao.listUser();
	}
	
	@Override
	@Transactional
	public User findUserByUserName(String uName){
return userDao.findUserByUserName(uName);
	}
	
//	@Override
//	@Transactional
//	public User findUserByUserId(Long userId){
//		return userDao.findUserByUserId(userId);
//	}
}
