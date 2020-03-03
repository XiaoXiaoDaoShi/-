package queue;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import unit.PCB;
//import java.util.concurrent.LinkedBlockingQueue;
//����
public class ProcessQueue {
	private ArrayList<PCB> queue = new ArrayList<>();
	
	public void addElement(PCB pcb){
		queue.add(pcb);
	}
	
	public PCB getElement(){
		if(queue.size()>0) {
			PCB t_pcb = queue.get(0);
		queue.remove(0);
		return t_pcb;
		}
		return null;
	}

	public ArrayList<PCB> getQueue() {
		return queue;
	}
	
	public void stop() {							// ���н��̶�����
		for(int i=0 ;i<queue.size();i++) {
			queue.get(i).die();
		}
	}
	
	public PCB findRR(int ms) {				//�ҳ�����ʱ��Ƭ��ת��   ��ʱ��Ƭ����
		for(int i=0;i<queue.size();i++) {
			PCB temp = queue.get(i);
			if(temp.getPcbUsedTime()>=ms) {
				queue.remove(i);
				return temp;
			}
		}
		return null;
	}
	
	public void findFirst() {			//�ҳ����ȼ���ߵ�
		if(queue.size()>0) {
			PCB minPriority = queue.get(0);
		int min=0;
		for(int i=0;i<queue.size();i++) {
			PCB temp = queue.get(i);
			if(temp.getPcbPriority()>minPriority.getPcbPriority()) {
				minPriority = temp;
				min = i;
			}
		}
		queue.remove(min);					//�����ȼ���ߵķ��ھ������еĶ���
		queue.add(0, minPriority);			//�Ƶ�����
		}
	}
	
	
	public void reSetPriority() {					//������Ӧ���������ȼ�
		for(int i=0;i<queue.size();i++) {
			PCB temp = queue.get(i);
			int waitTime = temp.getPcbWatiTime();
			int needServiveTime = temp.getPcbNeedServiveTime();
			int newPriority = (waitTime + needServiveTime) / needServiveTime;
			temp.setPcbPriority(newPriority);
		}
	}
	
}
