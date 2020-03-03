import condiment.Milk;
import condiment.Mocha;
import drink.Beverage;
import drink.Espresso;
import drink.HouseBlend;

public class Demo {

	public static void main(String[] args) {
		
		Beverage houseBLend = new HouseBlend();
		System.out.println(houseBLend.destription + " $" + houseBLend.cost());
		
		Beverage beverage = new Mocha(houseBLend);
		beverage = new Milk(beverage);
		System.out.println(beverage.destription + " $" + beverage.cost());
		
		
		Beverage Espresso = new Espresso();
		Beverage beverage2 = new Milk(Espresso);
		System.out.println(beverage2.getDestription() + " $" + beverage2.cost());
		
	} 

}
