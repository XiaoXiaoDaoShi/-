package memory;

import java.util.ArrayList;

public class PageQueue {
	ArrayList<Page> queue = new ArrayList<>();
	
	public void addElement(Page page) {
		queue.add(page);
	}
	
	public Page getElement() {
		if(queue.size()>0) {
			Page t_page;
			t_page = queue.get(0);
			queue.remove(0);
			return t_page;
		}
		return null;
	}
	
	public ArrayList<Page> getQueue(){
		return queue;
	}
	
	public Page getTailElement() {
		return queue.get(queue.size()-1);
	}
	
	public void putFirst(int index) {			//把指定的Page移到队首
		Page temp = this.getElementByIndex(index);
		if(temp!=null) {
			queue.add(0, temp);
		}
	}
	
	public Page getElementByIndex(int index) {		//
		Page temp;
		temp = queue.get(index);
		if(temp!=null) {
			queue.remove(temp);
			return temp;
		}
		return null;
	}
	
}
