package prepareFood;

import factory.YunJiShaoEFactory;

public class RoseOidRice extends Food{
private YunJiShaoEFactory yunJiShaoEFactory;
	
	public RoseOidRice(YunJiShaoEFactory yunJiShaoEFactory) {
		this.setName("Ãµ¹å¹ÄÓÍ·¹");
		this.yunJiShaoEFactory = yunJiShaoEFactory;
	}

	@Override
	public void prepare() {
		this.sauce = yunJiShaoEFactory.createSauce("roseoil");
		this.rice = yunJiShaoEFactory.createRice();
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
