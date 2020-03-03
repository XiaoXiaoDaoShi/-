package prepareFood;

import factory.YunJiShaoEFactory;

public class ZhengZongShaoYaFan extends Food{
	private YunJiShaoEFactory yunJiShaoEFactory;
	
	public ZhengZongShaoYaFan(YunJiShaoEFactory yunJiShaoEFactory) {
		this.setName("Õý×ÚÉÕÑ¼·¹");
		this.yunJiShaoEFactory = yunJiShaoEFactory;
	}

	@Override
	public void prepare() {
		this.meat = yunJiShaoEFactory.createMeat("duck");
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
