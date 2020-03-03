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
	public void dispatchFun(OperateSystem os) {			//暂停 并把进程放到就绪队列， 并把使用时间置0
		
		PCB temp = RunQueue.findRR(userTime);   		//找出时间片到期的
		if(temp!=null) {
			temp.pauseThread();							//PCB运行暂停
			temp.setPcbUsedTime(0);
			ReadyQueue.addElement(temp);				//把PCB放用就绪队列
			os.addCore();								
		}
	}

	@Override
	public void dispatchFun() {
		// TODO Auto-generated method stub
		
	}

	
}
