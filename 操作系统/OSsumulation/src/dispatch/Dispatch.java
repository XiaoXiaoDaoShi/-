package dispatch;

import queue.ProcessQueue;
import system.OperateSystem;
import unit.PCB;

public interface Dispatch {
	void dispatchFun();

	void dispatchFun(OperateSystem os);
	void setReadyQueue(ProcessQueue readyQueue);
	void setRunQueue(ProcessQueue runQueue);
}
