package User;

import java.util.Date;
//
public class MyTimeThread extends Thread{
	private ClassRoom classRoom;
	private MyTime myTime;
	private int currentSerialNum=0;
	public MyTimeThread(ClassRoom classRoom, MyTime myTime) {
		super();
		this.classRoom = classRoom;
		this.myTime = myTime;
	}
	
	void getSerialNum() {    //»ñµÃ¿ÎÌÃ
		int temp=1;
		while(true){
			Date date = new Date();
//			temp = myTime.getSerialNumByTime(date);
			if(currentSerialNum != temp) {
				currentSerialNum=temp;
				classRoom.setSerialNum(currentSerialNum);
				classRoom.reLoad(currentSerialNum);	
			}					
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void run() { 
		getSerialNum();
	}
}
