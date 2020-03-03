package dispatch;

import java.util.ArrayList;

import queue.ProcessQueue;
import system.OperateSystem;
import unit.PCB;

public class RR implements Dispatch{
	
	private int userTime;
	private ProcessQueue ReadyQueue;
	private ProcessQueue RunQueue;
//	private OperateSystem os;
//
//	public void setOs(OperateSystem os) {
//		this.os = os;
//	}

	public RR(int i) {
		this.userTime = i;
	}



	public void setReadyQueue(ProcessQueue readyQueue) {
		ReadyQueue = readyQueue;
	}



	public void setRunQueue(ProcessQueue runQueue) {
		RunQueue = runQueue;
	}

	@Override
	public void dispatchFun(OperateSystem os) {			//��ͣ ���ѽ��̷ŵ��������У� ����ʹ��ʱ����0
		
		PCB temp = RunQueue.findRR(userTime);   		//�ҳ�ʱ��Ƭ���ڵ�
		if(temp!=null) {
			temp.pauseThread();							//PCB������ͣ
			temp.setPcbUsedTime(0);
			ReadyQueue.addElement(temp);				//��PCB���þ�������
			os.addCore();								
		}
	}

	@Override
	public void dispatchFun() {
		// TODO Auto-generated method stub
		
	}

	
}
