package wechatObserve;

import java.util.ArrayList;
import java.util.List;

public class publicNumber implements Subjet{
	private List<Customer> customers = new ArrayList<>();
	private String id;
	
	@Override
	public void addObserver(Observer o) {
		if(o instanceof Customer) {
			customers.add((Customer) o);
		}
		
	}

	@Override
	public void removeObserver(Observer o) {
		int i = customers.indexOf(o);
		if(i>=0) {
			customers.remove(o);
		}
		
	}

	@Override
	public void notifyObserver() {
		for(int i=0;i<customers.size();i++) {
			Customer c = customers.get(i);
			c.update(this.id);
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
