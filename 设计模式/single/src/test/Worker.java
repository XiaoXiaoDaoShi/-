package test;

import single.ChocolateBoilder;

public class Worker extends Thread{
	private String workerName;

	public Worker(int i) {
		super();
		this.workerName = "π§»À" + String.valueOf(i);
	}
	
	 public void work() {
		ChocolateBoilder ch = ChocolateBoilder.getInstance();

		
		ch.fill();
		ch.boil();
		ch.drain();

	}
	
	public void run() {
		work();
	}
}
