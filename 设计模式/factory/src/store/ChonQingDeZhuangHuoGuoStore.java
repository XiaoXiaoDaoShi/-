package store;

import factory.ChongQingDeZhuangHuoGuoFactory;
import prepareFood.Food;
import prepareFood.SanWeiGuo;
import prepareFood.ShouGongXiaHua;

public class ChonQingDeZhuangHuoGuoStore extends FoodStore{
	ChongQingDeZhuangHuoGuoFactory factory = new ChongQingDeZhuangHuoGuoFactory();

	@Override
	public Food createFood(String type) {
		Food food = null;
		if(type.equals("sanweiguo")) {
			food = new SanWeiGuo(factory);
		}else if(type.equals("shougongxiahua")) {
			food = new ShouGongXiaHua(factory);
		}
		return food;
	}
	
}
