package prepareFood;

import factory.ChongQingDeZhuangHuoGuoFactory;
import factory.YunJiShaoEFactory;

public class SanWeiGuo extends Food{
	private ChongQingDeZhuangHuoGuoFactory chongQingDeZhuangHuoGuoFactory;

	public SanWeiGuo(ChongQingDeZhuangHuoGuoFactory chongQingDeZhuangHuoGuoFactory) {
		this.setName("ÈýÎ¶¹ø");
		this.chongQingDeZhuangHuoGuoFactory = chongQingDeZhuangHuoGuoFactory;
	}

	@Override
	public void prepare() {	
		this.sauce = chongQingDeZhuangHuoGuoFactory.createSauce("sanweiguo");	
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
