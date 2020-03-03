package wechatObserve;

public interface Subjet {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObserver();
	
}
