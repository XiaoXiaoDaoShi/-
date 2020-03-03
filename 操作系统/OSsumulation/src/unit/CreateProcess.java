package unit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CreateProcess {
	private Set<String> idSet = new HashSet<>();
	
	private String creareId(){						//创建id
		String id="0";
		for(int i=0;i<5;i++){
			double temp = Math.random()*10;
			id +=String.valueOf((int)temp);
		}
		if(idSet.contains(id)){
			id = null;
		}
		else{
			idSet.add(id);
		}
		return id;
	}
	
	private int createPriority() {				//创建优先级
		double temp = Math.random()*10;
		return (int)temp;
	}
	
	private int createdNeededTime() {			//创建需要时间
		double temp = Math.random()*10 + 1;
		
		return (int)temp;
	}
	
	private int createArrivalTime() {			//创建到达时间
		return 0;
	}
	
	
	public PCB create(String name){				//创建PCB
		String id=null;
		do{
			id = this.creareId();
			
		}while(id==null);
		
		int priority = this.createPriority();
		int arrivalTime = this.createArrivalTime();
		int neededTime = this.createdNeededTime();
		
		return new PCB(name, id, priority, arrivalTime, neededTime);
		
	}
	
	
	
}
