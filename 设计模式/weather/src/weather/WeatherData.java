package weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{

	private Information information;
	private List<Observer> observerList = new ArrayList<>();
	private boolean flag = false;
	
	
	public WeatherData(Information information) {
		super();
		this.information = information;
	}

	@Override
	public void addObserver(Observer o) {
		observerList.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		int i = observerList.indexOf(o);
		observerList.remove(i);	
	}

	@Override
	public void notifyObserver() {
		if(flag) {
			for(int i=0; i<observerList.size(); i++) {
				observerList.get(i).update(this.information);
			}
		}
		
	}

	@Override
	public void setChange() {
		this.flag = true;
		
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}
	
	
	
}
