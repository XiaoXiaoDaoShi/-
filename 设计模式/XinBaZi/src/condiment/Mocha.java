package condiment;
import drink.Beverage;

public class Mocha extends CondimentDecorator{
	Beverage beverage;
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDestription() + ", Mocha";
	}
	
	public double cost() {
		return 1 + beverage.cost();
	}
}
