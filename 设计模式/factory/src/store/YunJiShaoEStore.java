package store;

import factory.SeasoningFactory;
import factory.YunJiShaoEFactory;
import prepareFood.Food;
import prepareFood.MiZhiChaShaoE;
import prepareFood.RoseOidRice;
import prepareFood.ZhengZongShaoYaFan;

public class YunJiShaoEStore extends FoodStore{
	
	YunJiShaoEFactory factory = new YunJiShaoEFactory();
	
	@Override
	public Food createFood(String type) {
		Food food = null;
		if(type.equals("mizhichashaoe")) {
			food = new MiZhiChaShaoE(factory);
		}else if(type.equals("zhengzongshaoyafan")) {
			food = new ZhengZongShaoYaFan(factory);
		}else if(type.equals("meiguiguyoufan")) {
			food = new RoseOidRice(factory);
		}
		return food;
	}
	
}
