package User;

import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyTime {
	
	private Map<Integer, int []> tableTime = new HashMap<>();
	private int lastTime = 45;
	
	public int [] parseTime(Time date) {   //格式化时间
		SimpleDateFormat ft = new SimpleDateFormat ("HH:mm");
		String s = ft.format(date);
		String[] t = s.split(":");
		int[] time = new int[2];
		for(int i=0;i<t.length;i++) {
			time[i] = Integer.parseInt(t[i]);
		}
		return time;
	}
	
	public int [] parseTime(Date date) {   //格式化时间
		SimpleDateFormat ft = new SimpleDateFormat ("HH:mm");
		String s = ft.format(date);
		String[] t = s.split(":");
		int[] time = new int[3];
		for(int i=0;i<t.length;i++) {
			time[i] = Integer.parseInt(t[i]);
		}
		return time;
	}
	
	public int[] getLocalTime() {  //获得当前时间
		Date date = new Date();
		int[] time = parseTime(date);
		return time;
	}
	
	public void readTableTime(Map<Integer, Time> map) {  //读取时间表
		int[] t = new int[2];
		for(int i=1;i<=map.size();i++) {
			Time date = (Time) map.get(i);
			t = parseTime(date);
			tableTime.put(i, t);
		}
	}	
	
	int[] timeMinusThirty(int [] time) {  //把时间减去30分钟
		int m;
		int h;
		m = time[1] - 30;
		h = time[0];
		if(m<0) {
			m = 60+m;
			h=h-1;
		}
		int[] cTime = {h,m};
		return cTime;
	}
	
	boolean timeCompare(int[] timeT, int[] timeC) {  //1.计划中的时间，  2.当前的时间
		int[] newTimeT = timeMinusThirty(timeT);
		if(timeC[0] > newTimeT[0])   //当前时间小时 大于计划中的
			return true;
		else if(timeC[0] == newTimeT[0]) {   //如果时刻 相等， 比较分时刻
			if(timeC[1] > newTimeT[1]){      //当前分时刻大于计划中的
				return true;
			}
		}
		return false;
	}
	
	public int getSerialNumByTime(Date date) {
		int serialNum = 0;
		int [] time = parseTime(date);
		for(int i=tableTime.size();i>0;i--) {         //从后遍历 
			int[] tempTime = tableTime.get(i);
			if(timeCompare(tempTime, time)) {
				serialNum= i;
				break;
			}
				
		}
		return serialNum;		
	}	
}
