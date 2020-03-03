package weather;

public interface Subject {
	
	public void notifyObserver();
	public void setChange();
	public void addObserver(Observer o);
	public void deleteObserver(Observer o);
	
}
