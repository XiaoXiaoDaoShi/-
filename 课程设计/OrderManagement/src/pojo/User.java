package pojo;

public  class User implements UserInterface {

	private String userId;
	private String userName;
	private String userPassword;
	private int userGender;
	private int userType;
	private String email;
	private String phone;
	
	public User(String userName, String userPassword, int userGender,String email, String phone) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.email = email;
		this.phone = phone;
	}
	
	
	
	public User(String userId, String userName, String userPassword, int userGender, String email,
			String phone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		
		this.email = email;
		this.phone = phone;
	}



	public User() {
		super();
	}


	public User(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void regiser() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public OrderForm orderDishes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void showPersonalInfo() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modifyPersonalInfo() {
		// TODO Auto-generated method stub
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserGender() {
		return userGender;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}



	public int getUserType() {
		return userType;
	}



	public void setUserType(int userType) {
		this.userType = userType;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userGender="
				+ userGender + ", userType=" + userType + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
	
}