package util;

public class CeilingFan {
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	String location;
	int speed;
	public CeilingFan(String location) {
		super();
		this.location = location;
		this.speed = OFF;
	}
	
	public void high() {
		this.speed = HIGH;
		System.out.println("High Speed");
		//设置高转速
	}
	
	public void medium() {
		this.speed = MEDIUM;
		System.out.println("Medium Speed");
		//设置中转速
	}
	
	public void low() {
		this.speed = LOW;
		System.out.println("Low Speed");
		//设置低转速
	}
	
	public void off() {
		this.speed = OFF;
		System.out.println("OFF");
		//关闭吊扇
	}
	
	public int getSpeed() {
		return this.speed;
	}
}
