package dispatch;

import queue.ProcessQueue;
import system.OperateSystem;
//高相应比优先 Highest Response Ratio Next
public class HRRN implements Dispatch{
	private ProcessQueue ReadyQueue;
	@Override
	public void dispatchFun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispatchFun(OperateSystem os) {
		ReadyQueue.reSetPriority();
		ReadyQueue.findFirst();
	}

	@Override
	public void setReadyQueue(ProcessQueue readyQueue) {
		this.ReadyQueue = readyQueue;	
	}

	@Override
	public void setRunQueue(ProcessQueue runQueue) {
		// TODO Auto-generated method stub
		
	}

}
