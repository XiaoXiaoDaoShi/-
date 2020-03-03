package prepareFood;

import factory.YunJiShaoEFactory;

public class MiZhiChaShaoE extends Food{
	private YunJiShaoEFactory yunJiShaoEFactory;
	
	
	
	public MiZhiChaShaoE(YunJiShaoEFactory yunJiShaoEFactory) {
		this.setName("√€÷≠≤Ê…’∂Ï");
		this.yunJiShaoEFactory = yunJiShaoEFactory;
	}

	@Override
	public void prepare() {
		this.meat = yunJiShaoEFactory.createMeat("goose");
		this.sauce = yunJiShaoEFactory.createSauce("mizhi");
		
	}

	@Override
	public void make() {
		System.out.println(this.getName() + "is making");
		
	}

	@Override
	public void cook() {
		System.out.println(this.getName() + "is cooking");
		
	}

	@Override
	public void delivery() {
		System.out.println(this.getName() + "is deliverying");
	}	
}
