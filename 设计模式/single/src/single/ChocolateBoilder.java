package single;

public class ChocolateBoilder {
	private boolean empty;
	private boolean boiled;
	private volatile static ChocolateBoilder chocolateBoilder;
	
	public ChocolateBoilder() {
		this.empty=true;
		this.boiled=false;
	}
	
	public static ChocolateBoilder getInstance() {
		if(chocolateBoilder == null) {
			synchronized (ChocolateBoilder.class) {
				if(chocolateBoilder == null) {
					chocolateBoilder = new ChocolateBoilder();
				}
			}
		}
		return chocolateBoilder;
	}
	
	public synchronized void fill() {
		if(isEmpty()) {
			empty=false;
			boiled=false;
			System.out.println("加料");
		}
	}
	
	public synchronized void boil() {
		if(!isEmpty() && !isBoiled()) {
			boiled = true;
			System.out.println("煮");
		}
	}
	
	public synchronized void drain() {
		if(!isEmpty() && isBoiled()) {
			empty = true;
			System.out.println("取出");
		}

	}
	
	
	 public synchronized boolean isEmpty() {
		return empty;
	}
	
	public synchronized boolean isBoiled() {
		return boiled;
	}
	

}
