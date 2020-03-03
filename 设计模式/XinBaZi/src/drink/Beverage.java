package drink;

public abstract class Beverage {
	public String destription = "Unkonwn Beverage";

	public String getDestription() {
		return destription;
	}

	public abstract double cost();
	
}
