package command;

import util.CeilingFan;

public class CeilingFanMediumCommand implements Command {
	CeilingFan ceilingFan;
	int prevSpeed;
	public CeilingFanMediumCommand(CeilingFan ceilingFan) {
		super();
		this.ceilingFan = ceilingFan;
	}

	@Override
	public void execute() {
		this.prevSpeed = ceilingFan.getSpeed();
		ceilingFan.medium();
		
	}

	@Override
	public void undo() {
		if(this.prevSpeed == ceilingFan.HIGH) {
			ceilingFan.high();
		}
		else if(this.prevSpeed == ceilingFan.LOW) {
			ceilingFan.low();
		}
		else if(this.prevSpeed == ceilingFan.MEDIUM) {
			ceilingFan.medium();
		}
		else if(this.prevSpeed == ceilingFan.OFF) {
			ceilingFan.off();
		}
		
	}
}
