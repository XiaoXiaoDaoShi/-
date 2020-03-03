package Test;

import command.CeilingFanHighCommand;
import command.CeilingFanLowCommand;
import command.CeilingFanMediumCommand;
import command.CeilingFanOffCommand;
import util.CeilingFan;
import util.RemoteControl;

public class Test {

	public static void main(String[] args) {
		RemoteControl rc = new RemoteControl();
		CeilingFan ceilingFan = new CeilingFan("Ò»¸öµçÉÈ");
		CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);
		CeilingFanLowCommand ceilingFanLowCommand = new CeilingFanLowCommand(ceilingFan);
		CeilingFanHighCommand ceilingFanHighCommand = new CeilingFanHighCommand(ceilingFan);
		CeilingFanMediumCommand ceilingFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
		
		rc.setCommand(0, ceilingFanLowCommand, ceilingFanOffCommand);
		rc.setCommand(1, ceilingFanMediumCommand, ceilingFanOffCommand);
		rc.setCommand(2, ceilingFanHighCommand, ceilingFanOffCommand);
		
		rc.onButtonWasPushed(0);
		
		rc.offButtonWasPushed(0);
		rc.undoButtonWasPushed();
		rc.onButtonWasPushed(1);
		
		rc.onButtonWasPushed(2);
		rc.undoButtonWasPushed();
		rc.undoButtonWasPushed();
	}

}
