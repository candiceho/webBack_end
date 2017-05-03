package neu.edu.project.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.project.dao.OrderDao;
import neu.edu.project.domain.Order;
import neu.edu.project.domain.User;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	@Transactional
	public Order getOrder(Long id){
		return orderDao.getOrder(id);
	}
	
	@Override
	@Transactional
	public void addOrder(Order order){
		 orderDao.addOrder(order);
	}
	
	@Override
	@Transactional
	public List<Order> listOrder(){
    return orderDao.listOrder();
	}

	@Override
	@Transactional
	public List<Order> listFinished(){
    return orderDao.listFinished();
	}
	
	@Override
	@Transactional
	public List<Order> listUnfinished(){
		return orderDao.listUnfinished();
	}
	
	@Override
	@Transactional
	public List<Order> findOrderByUserId(Long userId){
		return orderDao.findOrderByUserId(userId);
	}
	
}
