package prepareFood;

import factory.ChongQingDeZhuangHuoGuoFactory;

public class ShouGongXiaHua extends Food{
	private ChongQingDeZhuangHuoGuoFactory chongQingDeZhuangHuoGuoFactory;

	public ShouGongXiaHua(ChongQingDeZhuangHuoGuoFactory chongQingDeZhuangHuoGuoFactory) {
		this.setName(" ÷π§œ∫ª¨");
		this.chongQingDeZhuangHuoGuoFactory = chongQingDeZhuangHuoGuoFactory;
	}

	@Override
	public void prepare() {	
		this.sauce = chongQingDeZhuangHuoGuoFactory.createSauce("xiahua");	
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
