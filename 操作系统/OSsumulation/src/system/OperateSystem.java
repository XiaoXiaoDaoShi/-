package system;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextArea;

import dispatch.Dispatch;
import dispatch.FCFS;
import queue.ProcessQueue;
import unit.CreateProcess;
import unit.PCB;
//默认为先进先出
public class OperateSystem extends Thread{
	private ProcessQueue ReadyQueue = new ProcessQueue();
	private ProcessQueue RunQueue = new ProcessQueue();
	private ProcessQueue WaitQueue = new ProcessQueue();
	private CreateProcess createProcess = new CreateProcess();
	
	private static Dispatch model ;
	private int core=5;
	private String modelName;
	
	private JTextArea readyTextArea;
	private JTextArea runTextArea;
	private JTextArea waitTextArea;
	private JTextArea finishedTextArea;
	
	private boolean start=false;
	
	private volatile static OperateSystem os;
	
	public OperateSystem(Dispatch model) {
		super();
		this.model = model;
		this.modelName = model.getClass().getName().substring(9);
		model.setReadyQueue(ReadyQueue);
		model.setRunQueue(RunQueue);
	}
	
	public static OperateSystem getInstace(Dispatch model) {
		if(os == null) {
			synchronized (OperateSystem.class) {
				if(os == null) {
					os = new OperateSystem(model);				
				}		
			}
		}
		return os;
	}
	
	public void setReadyTextArea(JTextArea readyTextArea) {
		this.readyTextArea = readyTextArea;
	}

	public void setRunTextArea(JTextArea runTextArea) {
		this.runTextArea = runTextArea;
	}

	public void setWaitTextArea(JTextArea waitTextArea) {
		this.waitTextArea = waitTextArea;
	}

	public void setFinishedTextArea(JTextArea finishedTextArea) {
		this.finishedTextArea = finishedTextArea;
	}
	
	public synchronized void addCore() {
		this.core += 1;
	}
	
	public synchronized void useCore() {
		this.core -= 1;
	}

	public void createOneProcess() {       //创建一个进程
		String name="random name";
		double number = Math.random()*10;
		name +=String.valueOf((int)number);
		PCB temp = createProcess.create(name);
		ReadyQueue.addElement(temp);
		temp.start();
		System.out.println("create one process and add it to ReadyQueue");
	}
	
	public void createAnyProcess(int n, boolean randomName) {			//创建指定数量的进程
		if(randomName) {
			for(int i=1;i<=n;i++) {
				String baseName = "randomName";
				baseName += String.valueOf(i);
				PCB temp = createProcess.create(baseName);
				ReadyQueue.addElement(temp);
				temp.start();
				System.out.println("create " + baseName + " and add it to ReadyQueue");
			}
		}
	}
	
	private PCB dispatchProcess(ProcessQueue queue_1, ProcessQueue queue_2) {
		PCB pcb_1 = queue_1.getElement();
		if(pcb_1!=null) {
			queue_2 .addElement(pcb_1);
			return pcb_1;
		}
		return null;
	}
	
	public void readyToRun() {										//就绪队列到运行队列	
		if(this.core>0) {
			PCB temp = this.dispatchProcess(ReadyQueue, RunQueue);
			if(temp!=null) {
					this.useCore();
					if(temp.getPause()) {
						temp.resumeThread();
					}
					temp.setPcbState(1);
					temp.clearWaitTime();
			}	
		}
	}
	
	public  void runToWait() {									//运行队列到阻塞队列
		PCB temp = this.dispatchProcess(RunQueue, WaitQueue);
		if(temp!=null) {
			temp.pauseThread();
			temp.setPcbState(2);
			this.addCore();
		}
		
	}
	
	public void waitToReady() {
		PCB temp = this.dispatchProcess(WaitQueue, ReadyQueue);
		if(temp!=null) {
			temp.setPcbState(0);
		}
	}
	
	public void askForIO() {			//发送I/O请求  运行队列到阻塞队列
		this.runToWait();
	}
	
	public void ioIsOver() {			//I/O结束    阻塞队列到就绪队列
		this.waitToReady();
	}
	
	private void show(PCB pcb, String type, JTextArea textArea) {
		String state=null;
		int t_state = pcb.getPcbState();
		if(t_state==0) {
			state = "就绪";
		}
		else if(t_state==1) {
			state = "运行";
		}
		else if(t_state==2) {
			state ="阻塞";
		}
		textArea.append(pcb.getPcbId() +": \n");
		textArea.append("    Name:"+pcb.getPcbName()+"\n");
		textArea.append("    Need Time:"+pcb.getPcbNeededTime()+"\n");
		textArea.append("    State:"+state+"\n");
		if(type.equals("PSA") || type.equals("HRRN") ) {
			textArea.append("    Priority:"+pcb.getPcbPriority()+"\n");
		}
	}
	
	private void finishedText(PCB pcb, JTextArea textArea) {
		textArea.append(pcb.getPcbId() + ": \n");
		textArea.append("    Name:"+pcb.getName()+ "  Arrival Time:" + pcb.getPcbArrialTime() +"  Finished Time:"+pcb.getAlivalTime());
		textArea.append("    周转时间:" + String.valueOf(pcb.getAlivalTime() - pcb.getPcbArrialTime()) +"\n");
	}
	
	public void listen() {	//监听
		ArrayList<PCB> runList = RunQueue.getQueue();
		ArrayList<PCB> readyList = ReadyQueue.getQueue();
		ArrayList<PCB> waitList = WaitQueue.getQueue();
		readyTextArea.setText(null);
		runTextArea.setText(null);
		waitTextArea.setText(null);
		//ready:
		for(int i=0; i< readyList.size();i++) {		//打印就绪队列
			this.show(readyList.get(i), this.modelName, readyTextArea);
		}
		//run
		for(int i=0; i< runList.size();i++) {
			PCB temp = runList.get(i);
			if(temp !=null) {
					if(temp.getPcbNeededTime() <=0) {	//如果结束
						runList.remove(i);				//从运行队列取出
						this.finishedText(temp, finishedTextArea);
						this.addCore();
			}
			else {														
				this.show(temp, this.modelName, runTextArea);	//打印运行队列
			}
		}
			
		}
		
		//wait
		for(int i=0; i< waitList.size();i++) {		//打印阻塞队列
			this.show(waitList.get(i), this.modelName, waitTextArea);
		}
	}
	
	
	
	public void run() {
		while(start) {
			this.listen();						//监听
			model.dispatchFun(this);				//调度算法运行
			this.readyToRun();					//把就绪队列放进运行队列
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void open() {
		this.start=true;
	}
	
	public void close() {
		ReadyQueue.stop();
		RunQueue.stop();
		WaitQueue.stop();
		this.start=false;
	}
}
