package dao;

import java.util.List;

import pojo.OrderForm;

public interface OrderFormDao {
	List<OrderForm> findAllOrderForm();
	OrderForm findOrderFormById(String id);
	int addOrderForm(OrderForm orderForm);
	int deleteOrderForm(String id);
	int modifyOrderForm(String id,OrderForm orderForm);
	
}
