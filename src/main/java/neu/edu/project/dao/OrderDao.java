package neu.edu.project.dao;

import java.util.Collection;
import java.util.List;

import neu.edu.project.domain.Order;
import neu.edu.project.domain.User;

public interface OrderDao {
	public Order getOrder(Long id);
	public void addOrder(Order order);
	public  List<Order> listOrder();
	public List<Order> listFinished();
	public  List<Order> listUnfinished();
	public List<Order> findOrderByUserId(Long userId);
}
