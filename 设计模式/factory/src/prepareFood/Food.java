package prepareFood;

import materialtInterface.MainFood;
import materialtInterface.Meat;
import materialtInterface.Sauce;

public abstract class Food {
	private String name;
	protected Meat meat;
	protected Sauce sauce;
	protected MainFood rice;
	
	public abstract void prepare(); //׼��
	public abstract void make();   //����
	public abstract void cook();   //
	public abstract void delivery(); //����
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
