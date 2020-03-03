package dispatch;

import java.util.ArrayList;

import memory.Page;
import memory.PageQueue;

//�Ƚ��ȳ��㷨
public class FIFO implements Dispatch{

	@Override
	public Page findPage(PageQueue pageQueue) {				//�����ȷ���
		ArrayList<Page> pageList = pageQueue.getQueue();
		if(pageList.size()>0) {
			return pageList.get(0);
		}
		return null;
	}
	
}
