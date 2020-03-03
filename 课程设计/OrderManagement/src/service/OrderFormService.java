package service;

import java.util.List;

import dao.OrderFormDaoImp;
import pojo.OrderForm;

public class OrderFormService {
	public List<OrderForm> findAllOrderForm() {			//找到所有order
		OrderFormDaoImp dao = new OrderFormDaoImp();
		List<OrderForm> orderFormList = dao.findAllOrderForm();
		return orderFormList;
	}
	
	public int addOrderForm(OrderForm orderForm) {				//添加order
		OrderFormDaoImp dao = new OrderFormDaoImp();
		int count = dao.addOrderForm(orderForm);
		return count;
	}
	
	public int finishOrderForm(String id) {			//完成订单
		OrderFormDaoImp dao = new OrderFormDaoImp();
		int count = dao.finishOrderForm(id);
		return count;
	}
	
	public int deleteOrderForm(String id) {			//根据id删除
		OrderFormDaoImp dao = new OrderFormDaoImp();
		int count = dao.deleteOrderForm(id);
		return count;
	}
}
