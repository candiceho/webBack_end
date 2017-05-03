package neu.edu.project.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.Order;
import neu.edu.project.domain.User;

@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Order getOrder(Long id){
		return (Order) sessionFactory.getCurrentSession().get(Order.class, id);	//load	
	}
	
@Override
public void addOrder(Order order){
	if(order.getId() == null){  //null
		sessionFactory.getCurrentSession().save(order);
	}
	else{
		sessionFactory.getCurrentSession().merge(order);
	}
	sessionFactory.getCurrentSession().flush();
}
	@Override
	public List<Order> listOrder(){
		return sessionFactory.getCurrentSession().createQuery("From Order").list();
	}
	
	@Override
	public List<Order> listFinished(){
		return sessionFactory.getCurrentSession().createQuery("From Order o Where o.status=true").list();
	}
	
	@Override
	public List<Order> listUnfinished(){
		return sessionFactory.getCurrentSession().createQuery("From Order o Where o.status=false").list();
	}
	
	@Override
	public List<Order> findOrderByUserId(Long userId){
		return sessionFactory.getCurrentSession().createQuery("from Order o where o.user.id="+userId).list();
	}
}
