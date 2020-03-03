package condiment;

import drink.Beverage;

public class Milk extends CondimentDecorator{
	Beverage beverage;

	public Milk(Beverage beverage) {
		
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDestription() + ", Milk";
	}
	
	public double cost() {
		return beverage.cost() + 9.99;
	}
}
