package dispatch;

import queue.ProcessQueue;
import system.OperateSystem;

//优先级调度算法 priority-scheduling algorithm
public class PSA implements Dispatch{
	
	private ProcessQueue ReadyQueue;
	
	@Override
	public void dispatchFun() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispatchFun(OperateSystem os) {
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
