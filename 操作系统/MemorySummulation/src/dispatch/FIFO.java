package dispatch;

import java.util.ArrayList;

import memory.Page;
import memory.PageQueue;

//先进先出算法
public class FIFO implements Dispatch{

	@Override
	public Page findPage(PageQueue pageQueue) {				//先来先服务
		ArrayList<Page> pageList = pageQueue.getQueue();
		if(pageList.size()>0) {
			return pageList.get(0);
		}
		return null;
	}
	
}
