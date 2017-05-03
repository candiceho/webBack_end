package neu.edu.project.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUser(Long id){
		return (User) sessionFactory.getCurrentSession().get(User.class, id);		
	}
	
	@Override
	public void addUser(User user){
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public User findUserByUserName(String username){
		return (User) sessionFactory.getCurrentSession().createQuery(
				"FROM User u WHERE u.userName = :username ")
			.setString("username", username).uniqueResult();
	}
	
	@Override
	public void deleteUser(User user){
		sessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	//@SuppressWarnings("unchecked")
	public Collection<User> listUser(){
		return sessionFactory.getCurrentSession().createQuery("From User").list(); //From User Order By id
	}
}
