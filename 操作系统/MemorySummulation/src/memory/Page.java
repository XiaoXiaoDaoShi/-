package memory;

public class Page {
	private int id;				//id
	private int usedTime=0;			//使用的次数
		
	public Page(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}
	
	public void addUsedTime() {	
		this.usedTime += 1;
		
	}
	
}
