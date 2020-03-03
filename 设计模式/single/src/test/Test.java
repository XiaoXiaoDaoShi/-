package test;

import single.ChocolateBoilder;

public class Test {

	public static void main(String[] args) {
		for(int i=1; i<=5;i++) {
			new Worker(i).start();
		}
	}

}
