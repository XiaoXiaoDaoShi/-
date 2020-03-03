package memory;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dispatch.CreatePage;
import dispatch.Dispatch;
import dispatch.FIFO;

public class Memory {
	private PageQueue stayQueue = new PageQueue();		
	ArrayList<Page> pageList = stayQueue.getQueue();		//驻留集类
	private int maxLength;
	private PageQueue pageQueue = new PageQueue();

	private Dispatch model;
	
	private float totalExchange=0;
	private float hitTimes=0;
	private float failTimes=0;
	private JTextArea weedOutTextArea = new JTextArea();
	
	public Memory(int maxLength) {
		super();
		this.maxLength = maxLength;
	}

	public void randomUseOnePage() {				//随机运行一个页面
		int pageLength = pageList.size();
		if(pageLength > 0) {
			double temp = Math.random() * pageLength;
		int choosed = (int) temp;
		pageList.get(choosed).addUsedTime();
		stayQueue.putFirst(choosed); 					//把该Page放到栈顶
		}
		
	}
	
	public void createOnePage() {					//创建一个页面
		int id;
		double temp = Math.random()*10;
		id = (int)temp;
		pageQueue.addElement(new Page(id));
		System.out.println("create " + id);
	}
	
	public void addOnePage() {						//添加一个页面进入驻留集
		
		Page temp = pageQueue.getElement();
		if(temp!=null){
				if(this.isContain(temp)){				//如果驻留集中亦存在该页面
					System.out.println("命中");
					this.hitTimes += 1;	
				}
				else {
					this.failTimes += 1;
					if(pageList.size()<maxLength){   //如果驻留集未满,就之间添加到驻留集
					pageList.add(temp);
					}
				
				else{
					Page delete = model.findPage(stayQueue);			//找出要从驻留集中删除的页面
					
					pageList.remove(delete);				//删除
					
					pageList.add(temp);						//把新的页面加入
					
					weedOutTextArea.append("ID:"+delete.getId()+"\n");
					weedOutTextArea.append(" 使用次数:"+delete.getUsedTime()+"\n");
					}		
				}			
		}
			this.totalExchange += 1;	
	}
	
	private void show(Page page, JTextArea textArea) {
		textArea.append("ID:" + page.getId() + "\n");
		textArea.append("  使用次数:"+page.getUsedTime()+"\n");
	}
	
	public void listen(JTextArea backQueueTextArea, JTextArea stayTextArea, JTextField hitTextField, JTextField lackTextField){			//监听内存变化
		ArrayList<Page> backList = pageQueue.getQueue();
		backQueueTextArea.setText(null);
		stayTextArea.setText(null);
		for(int i=0; i<backList.size();i++){			//打印后背队列
			show(backList.get(i), backQueueTextArea);
		}
		
		for(int i=0;i<pageList.size();i++){				//打印驻留集
			show(pageList.get(i),stayTextArea);
		}
		hitTextField.setText(this.calculateHitPercent());
		
		lackTextField.setText(this.calculateLackPercent());
	}
	
	
	public String calculateHitPercent(){
		if(this.totalExchange>0){
			DecimalFormat decimalFormat = new DecimalFormat("##.00%");
			float temp = this.hitTimes / this.totalExchange;
			return decimalFormat.format(temp);
		}
		return "0";
	}
	
	public String calculateLackPercent() {
		if(this.totalExchange>0) {
			DecimalFormat decimalFormat = new DecimalFormat("##.00%");
			float temp = this.failTimes / this.totalExchange;
			return decimalFormat.format(temp);
		}
		return "0";
	}
	
	public void setModel(Dispatch model) {
		this.model = model;
	}
	
	public boolean isContain(Page page) {
		int id=page.getId();
		for(int i=0;i<pageList.size();i++) {
			if(id==pageList.get(i).getId()) {
				return true;
			}
		}
		return false;
	}
	
	public void setWeedOutTextArea(JTextArea weedOutTextArea) {
		this.weedOutTextArea = weedOutTextArea;
	}
}
