package unit;

import java.util.Date;

public class PCB extends Thread{	
	private String pcbName;
	private String pcbId;
	private int pcbPriority;			//优先级
	
	private int pcbArrialTime;			//到达时间，
	private int pcbNeededTime;
	private int pcbUsedTime=0;			//CPU使用时间
	private int pcbWatiTime=0;
	private int pcbAlivalTime;			//PCB的存活时间
	private int pcbState=0;
	private int pcbReasonOfBlock=0;
	private int pcbNeedServiveTime;     //需要服务的时间
	
	private final Object lock = new Object();
	private boolean pause = false;
	
	//PCB
	public PCB(String pcbName, String pcbId, int pcbPriority, int pcbArrialTime, int pcbNeededTime) {
		super();
		this.pcbName = pcbName;
		this.pcbId = pcbId;
		this.pcbPriority = pcbPriority;
		this.pcbArrialTime = pcbArrialTime;
		this.pcbNeededTime = pcbNeededTime;
		this.pcbAlivalTime = pcbArrialTime;
		this.pcbNeedServiveTime = pcbNeededTime;
	}
	
	public void pauseThread() { 			//暂停线程
        this.pause = true;
        this.setPcbState(2);
    }
	
	 public void resumeThread() {
         this.pause = false;
         synchronized (lock) {
             //唤醒线程
             lock.notify();
             this.setPcbState(0);
         }
     }
	 
	 void onPause() {
         synchronized (lock) {
             try {
                 //线程 等待/阻塞
                 lock.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
	
	public int getPcbNeedServiveTime() {
		return this.pcbNeedServiveTime;
	}
	 
	public int getAlivalTime() {
		return this.pcbAlivalTime;
	}
	 
	public int getPcbWatiTime() {
		return pcbWatiTime;
	}

	public void setPcbWatiTime(int pcbWatiTime) {
		this.pcbWatiTime = pcbWatiTime;
	}

	public String getPcbName() {
		return pcbName;
	}

	public void setPcbName(String pcbName) {
		this.pcbName = pcbName;
	}

	public String getPcbId() {
		return pcbId;
	}

	public void setPcbId(String pcbId) {
		this.pcbId = pcbId;
	}

	public int getPcbPriority() {
		return pcbPriority;
	}

	public void setPcbPriority(int pcbPriority) {
		this.pcbPriority = pcbPriority;
	}

	public int getPcbArrialTime() {
		return pcbArrialTime;
	}

	public void setPcbArrialTime(int pcbArrialTime) {
		this.pcbArrialTime = pcbArrialTime;
	}

	public int getPcbNeededTime() {
		return pcbNeededTime;
	}

	public void setPcbNeededTime(int pcbNeededTime) {
		this.pcbNeededTime = pcbNeededTime;
	}

	public int getPcbUsedTime() {
		return pcbUsedTime;
	}

	public void setPcbUsedTime(int pcbUsedTime) {
		this.pcbUsedTime = pcbUsedTime;
	}

	public int getPcbState() {
		return pcbState;
	}

	public void setPcbState(int pcbState) {
		this.pcbState = pcbState;
	}

	public int getPcbReasonOfBlock() {
		return pcbReasonOfBlock;
	}

	public void setPcbReasonOfBlock(int pcbReasonOfBlock) {
		this.pcbReasonOfBlock = pcbReasonOfBlock;
	}
	
	public boolean getPause() {
		return this.pause;
	}
	
	private void addAlivalTime(int ms) {
		this.pcbAlivalTime += ms;
	}
	
	private void working(int ms) {
		this.pcbNeededTime -= ms;
	}
	
	private void addUseTime(int ms) {				//增加运行时间
		this.pcbUsedTime += ms;
	}
	
	private void addWaitTime(int ms) {
		this.pcbWatiTime += ms;
	}
	
	public void die() {					//结束
		this.pcbNeededTime = 0;
	}
	
	public void clearWaitTime() {
		this.pcbWatiTime = 0;
	}
	
	@Override
	public void run() {
		super.run();
		int time = 1;
		while(this.pcbNeededTime > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.addAlivalTime(time);
			
			if(this.getPcbState()==1) {  //如果运行状态 就运行
				if(pause) {
				onPause();
				//线程 阻塞/等待
			}
				this.working(time);			//运行
				this.addUseTime(time);		//增加运行时间
			}
			
			else if(this.getPcbState()==0) {  //如果就绪状态 就增加等待时间
					this.addWaitTime(time);	
			}		
		}
	}

	@Override
	public String toString() {
		return "PCB [pcbName=" + pcbName + ", pcbId=" + pcbId + ", pcbPriority=" + pcbPriority + ", pcbArrialTime="
				+ pcbArrialTime + ", pcbNeededTime=" + pcbNeededTime + ", pcbUsedTime=" + pcbUsedTime + ", pcbState="
				+ pcbState + ", pcbReasonOfBlock=" + pcbReasonOfBlock + ", lock=" + lock + ", pause=" + pause + "]";
	}
	
}
