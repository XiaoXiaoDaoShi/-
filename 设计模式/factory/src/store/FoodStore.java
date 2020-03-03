package store;

import prepareFood.Food;

public abstract class FoodStore {
	public Food orderFood(String type) {
		Food food = createFood(type);
		food.prepare();
		food.make();
		food.cook();
		food.delivery();
		return food;
	}

	public abstract Food createFood(String type);
}
