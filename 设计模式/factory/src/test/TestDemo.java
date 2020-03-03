package test;

import store.ChonQingDeZhuangHuoGuoStore;
import store.YunJiShaoEStore;

public class TestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChonQingDeZhuangHuoGuoStore cStore = new ChonQingDeZhuangHuoGuoStore();
		YunJiShaoEStore yStore = new YunJiShaoEStore();
		cStore.orderFood("sanweiguo");
		System.out.println();
		cStore.orderFood("shougongxiahua");
		System.out.println();
		yStore.orderFood("mizhichashaoe");
		System.out.println();
		yStore.orderFood("zhengzongshaoyafan");
		System.out.println();
		yStore.orderFood("meiguiguyoufan");
	}

}
