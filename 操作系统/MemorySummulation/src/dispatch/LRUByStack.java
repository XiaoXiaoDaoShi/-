package dispatch;

import java.util.ArrayList;

import memory.Page;
import memory.PageQueue;

public class LRUByStack implements Dispatch{
	
	
	
	@Override
	public Page findPage(PageQueue pageQueue) {			//LRU - Õ»
		Page temp = pageQueue.getTailElement();			// ÕÒ³öÕ»µ×µÄÒ³Ãæ		
		if(temp !=null) {
			return temp;
		}
		return null;
	}
	
}
