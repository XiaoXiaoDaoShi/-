package pojo;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class OrderForm {

	private List<Dish> dishList;
	private int orderId;
	private int orderNumber;
	private int orderPrice;
	private String orderGuestName;
	private String orderGuestPhone;
	private int orderGetWay;
	private Date orderDate;
	private String orderRemark;	
	private int orderState=0;

	


	public OrderForm(int orderNumber, int orderPrice, String orderGuestName, String orderGuestPhone, int orderGetWay,
			Date orderDate, String orderRemark) {
		super();
		this.orderNumber = orderNumber;
		this.orderPrice = orderPrice;
		this.orderGuestName = orderGuestName;
		this.orderGuestPhone = orderGuestPhone;
		this.orderGetWay = orderGetWay;
		this.orderDate = orderDate;
		this.orderRemark = orderRemark;
	}




	public OrderForm(int orderPrice, Date orderDate, String orderRemark, int orderState) {
		super();
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.orderRemark = orderRemark;
		this.orderState = orderState;
	}

	
	

	public OrderForm(int orderPrice, String orderGuestName, String orderGuestPhone, int orderGetWay, Date orderDate,
			String orderRemark) {
		super();
		this.orderPrice = orderPrice;
		this.orderGuestName = orderGuestName;
		this.orderGuestPhone = orderGuestPhone;
		this.orderGetWay = orderGetWay;
		this.orderDate = orderDate;
		this.orderRemark = orderRemark;
	}




	public OrderForm(int orderId, int orderNumber, int orderPrice, Date orderDate, String orderRemark, int orderState) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.orderRemark = orderRemark;
		this.orderState = orderState;
	}

	
	public OrderForm(int orderId, int orderNumber, int orderPrice, String orderGuestName, String orderGuestPhone,
			int orderGetWay, Date orderDate, String orderRemark, int orderState) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.orderPrice = orderPrice;
		this.orderGuestName = orderGuestName;
		this.orderGuestPhone = orderGuestPhone;
		this.orderGetWay = orderGetWay;
		this.orderDate = orderDate;
		this.orderRemark = orderRemark;
		this.orderState = orderState;
	}

	public List<Dish> getDishList() {
		return dishList;
	}




	public void setDishList(List<Dish> dishList) {
		this.dishList = dishList;
	}




	public int getOrderId() {
		return orderId;
	}




	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}




	public int getOrderNumber() {
		return orderNumber;
	}




	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}




	public int getorderPrice() {
		return orderPrice;
	}




	public void setorderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}




	public Date getDate() {
		return orderDate;
	}




	public void setDate(Date orderDate) {
		this.orderDate = orderDate;
	}




	public String getRemark() {
		return orderRemark;
	}




	public void setRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}




	public int getOrderState() {
		return orderState;
	}

	


	public int getOrderPrice() {
		return orderPrice;
	}




	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}




	public String getOrderGuestName() {
		return orderGuestName;
	}




	public void setOrderGuestName(String orderGuestName) {
		this.orderGuestName = orderGuestName;
	}




	public String getOrderGuestPhone() {
		return orderGuestPhone;
	}




	public void setOrderGuestPhone(String orderGuestPhone) {
		this.orderGuestPhone = orderGuestPhone;
	}




	public int getOrderGetWay() {
		return orderGetWay;
	}




	public void setOrderGetWay(int orderGetWay) {
		this.orderGetWay = orderGetWay;
	}




	public Date getOrderDate() {
		return orderDate;
	}




	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}




	public String getOrderRemark() {
		return orderRemark;
	}




	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}




	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public String getParseDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return df.format(orderDate);
	}


	public void showForm() {
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}