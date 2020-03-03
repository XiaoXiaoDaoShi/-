package dispatch;

import java.util.ArrayList;

import memory.Page;
import memory.PageQueue;

public class LRUByStack implements Dispatch{
	
	
	
	@Override
	public Page findPage(PageQueue pageQueue) {			//LRU - ջ
		Page temp = pageQueue.getTailElement();			// �ҳ�ջ�׵�ҳ��		
		if(temp !=null) {
			return temp;
		}
		return null;
	}
	
}
