package wechatObserve;

public class Customer implements Observer{
	private String id;
	private publicNumber pn;
	
	

	public Customer(publicNumber pn) {
		this.pn = pn;
		pn.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("id:"+this.id);
		
	}

	@Override
	public void update(String id) {
		this.id = id;
		display();
	}
	
}
