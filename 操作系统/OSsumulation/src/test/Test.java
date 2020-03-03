package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import UI.MainUI;
import dispatch.Dispatch;
import dispatch.FCFS;
import dispatch.HRRN;
import dispatch.PSA;
import dispatch.RR;
import system.OperateSystem;
import unit.CreateProcess;
//0.ready 1.run 2.wait 3.guaqi 4.finish
public class Test {

	public static void main(String[] args) {	
		MainUI frame = new MainUI();
		frame.setVisible(true);
	}

}
