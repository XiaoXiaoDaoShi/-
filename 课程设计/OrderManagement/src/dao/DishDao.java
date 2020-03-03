package dao;

import java.util.List;

import pojo.*;

public interface DishDao {
	
	/**
	 * 
	 * @param dish
	 */
	int addDish(Dish dish);

	/**
	 * 
	 * @param dish
	 * @param newDish
	 */
	int modifyDish(String did, Dish newDish);

	/**
	 * 
	 * @param did
	 */
	int deleteDish(String did);

	List<Dish> findAllDishes();

}