package service;

import java.util.ArrayList;
import java.util.List;

import dao.DishDaoImp;
import pojo.Dish;

public class DishService {
	public List<Dish> findAllDish(){			//找出所有dish
		List<Dish> dishList;
		DishDaoImp dao = new DishDaoImp();
		dishList = dao.findAllDishes();
		return dishList;
	}
		
	public List<Dish> findTopDishesByLimit(int limit){		//人数最多的前limit个
		List<Dish> dishList;
		DishDaoImp dao = new DishDaoImp();
		dishList = dao.findTopDishesByLimit(limit);
		return dishList;
	}
	
	public int deleteDish(String did) {				//根据id 删除dish
		DishDaoImp dao = new DishDaoImp();
		int count = dao.deleteDish(did);
		return count;
	}
	
	public int addDish(Dish dish) {				//加入一个dish
		DishDaoImp dao = new DishDaoImp();
		int count = dao.addDish(dish);
		return count;
	}
	
	public int modifyDish(String did, Dish newDish) {			//修该dish
		DishDaoImp dao = new DishDaoImp();
		int count = dao.modifyDish(did, newDish);
		return count;
	}
	
	public Dish findDishById(int did) {				//根据Id找dish
		DishDaoImp dao = new DishDaoImp();
		Dish dish = dao.findDishById(did);
		return dish;
	}
}
