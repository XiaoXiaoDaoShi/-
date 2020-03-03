package util;

import java.util.Stack;

import command.CeilingFanOffCommand;
import command.Command;
import command.NoCommand;

public class RemoteControl {
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;
	Stack commandStack = new Stack();
	
	public RemoteControl() {
		super();
		
		onCommands = new Command[7];
		offCommands = new Command[7];
		Command noCommand = new NoCommand();
		for(int i=0;i<7;i++) {
			offCommands[i] = new NoCommand();
			onCommands[i] = new NoCommand();
			
		}
		undoCommand = noCommand;
	}
	
	public void setCommand(int slot, Command onCommand, Command offCommand) {
		offCommands[slot] = offCommand;
		onCommands[slot] = onCommand;
		
	}
	
	public void onButtonWasPushed(int slot) {
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
		commandStack.push(undoCommand);
	}
	
	public void offButtonWasPushed(int slot) {
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
		commandStack.push(undoCommand);
	}
	
	public void undoButtonWasPushed(){
		System.out.print("Undo:");
		undoCommand = (Command) commandStack.pop();
		undoCommand.undo();
		
	}
	
}
