package wechatObserve;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		publicNumber pn = new publicNumber();
		Customer c = new Customer(pn);
	
		pn.setId("123456");
		pn.notifyObserver();
	}

}
