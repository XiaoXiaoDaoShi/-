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
	ArrayList<Page> pageList = stayQueue.getQueue();		//פ������
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

	public void randomUseOnePage() {				//�������һ��ҳ��
		int pageLength = pageList.size();
		if(pageLength > 0) {
			double temp = Math.random() * pageLength;
		int choosed = (int) temp;
		pageList.get(choosed).addUsedTime();
		stayQueue.putFirst(choosed); 					//�Ѹ�Page�ŵ�ջ��
		}
		
	}
	
	public void createOnePage() {					//����һ��ҳ��
		int id;
		double temp = Math.random()*10;
		id = (int)temp;
		pageQueue.addElement(new Page(id));
		System.out.println("create " + id);
	}
	
	public void addOnePage() {						//���һ��ҳ�����פ����
		
		Page temp = pageQueue.getElement();
		if(temp!=null){
				if(this.isContain(temp)){				//���פ����������ڸ�ҳ��
					System.out.println("����");
					this.hitTimes += 1;	
				}
				else {
					this.failTimes += 1;
					if(pageList.size()<maxLength){   //���פ����δ��,��֮����ӵ�פ����
					pageList.add(temp);
					}
				
				else{
					Page delete = model.findPage(stayQueue);			//�ҳ�Ҫ��פ������ɾ����ҳ��
					
					pageList.remove(delete);				//ɾ��
					
					pageList.add(temp);						//���µ�ҳ�����
					
					weedOutTextArea.append("ID:"+delete.getId()+"\n");
					weedOutTextArea.append(" ʹ�ô���:"+delete.getUsedTime()+"\n");
					}		
				}			
		}
			this.totalExchange += 1;	
	}
	
	private void show(Page page, JTextArea textArea) {
		textArea.append("ID:" + page.getId() + "\n");
		textArea.append("  ʹ�ô���:"+page.getUsedTime()+"\n");
	}
	
	public void listen(JTextArea backQueueTextArea, JTextArea stayTextArea, JTextField hitTextField, JTextField lackTextField){			//�����ڴ�仯
		ArrayList<Page> backList = pageQueue.getQueue();
		backQueueTextArea.setText(null);
		stayTextArea.setText(null);
		for(int i=0; i<backList.size();i++){			//��ӡ�󱳶���
			show(backList.get(i), backQueueTextArea);
		}
		
		for(int i=0;i<pageList.size();i++){				//��ӡפ����
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
