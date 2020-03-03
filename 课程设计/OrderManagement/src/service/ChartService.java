package service;

import java.util.List;

import dao.ChartDaoImp;
import dao.DishDaoImp;
import pojo.DaySale;
import pojo.Dish;

public class ChartService {
	public List<DaySale> getDaySale(){			//获得每日销售额
		ChartDaoImp dao = new ChartDaoImp();
		List<DaySale> daySaleList = dao.getDaySale();
		return daySaleList;
	}
	
	public List<Dish> findAllDishes() {				//查找所有菜品 主要是销售量
		DishDaoImp dao = new DishDaoImp();
		List<Dish> dishList = dao.findAllDishes();
		return dishList;
	}
	public List<Dish> findTopDishesByLimit(int limit){			//查找人数最多的前limit个
		DishDaoImp dao = new DishDaoImp();
		List<Dish> dishList = dao.findTopDishesByLimit(limit);
		return dishList;
	}
}
