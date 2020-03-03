package pojo;

public class Dish {

	private String dishId;					//id
	private String dishName;				//名字
	private int dishPrice;					//价格
	private int dishCount;					//人气
	private String dishCookWay;				//做法
	private String dishFlavour;				//口味
	private String dishImage;				//图片路劲
	private int orderNumber=0;				//点菜数量
	
	public Dish(String dishId, String dishName, int dishPrice, int dishCount, String dishCookWay, String dishFlavour,
			String dishImage) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishCount = dishCount;
		this.dishCookWay = dishCookWay;
		this.dishFlavour = dishFlavour;
		this.dishImage = dishImage;
	}
	public Dish(String dishId, String dishName, int dishPrice, int dishCount, String dishCookWay, String dishFlavour) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishCount = dishCount;
		this.dishCookWay = dishCookWay;
		this.dishFlavour = dishFlavour;
	}
	
	
	public Dish(String dishName, int dishPrice, int dishCount, String dishCookWay, String dishFlavour, String dishImage) {
		super();
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishCount = dishCount;
		this.dishCookWay = dishCookWay;
		this.dishFlavour = dishFlavour;
		this.dishImage = dishImage;
	}

	
	
	public Dish(String dishName, int dishPrice, String dishCookWay, String dishFlavour, String dishImage) {
		super();
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishCookWay = dishCookWay;
		this.dishFlavour = dishFlavour;
		this.dishImage = dishImage;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public int getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(int dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public int getDishCount() {
		return dishCount;
	}
	public void setDishCount(int dishCount) {
		this.dishCount = dishCount;
	}
	public String getDishCookWay() {
		return dishCookWay;
	}
	public void setDishCookWay(String dishCookWay) {
		this.dishCookWay = dishCookWay;
	}
	public String getDishFlavour() {
		return dishFlavour;
	}
	public void setDishFlavour(String dishFlavour) {
		this.dishFlavour = dishFlavour;
	}


	public String getDishImage() {
		return dishImage;
	}


	

	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public void setDishImage(String dishImage) {
		this.dishImage = dishImage;
	}
	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishName=" + dishName + ", dishPrice=" + dishPrice + ", dishCount="
				+ dishCount + ", dishCookWay=" + dishCookWay + ", dishFlavour=" + dishFlavour + ", dishImage="
				+ dishImage + "]";
	}
	
	
	
	public int getTotalPrice() {						//获得总共的价格
		return this.dishPrice * this.orderNumber;
	}
	
	public void addCount() {				//	增加点餐量（人气）
		this.dishCount += this.orderNumber;
	}
	
}