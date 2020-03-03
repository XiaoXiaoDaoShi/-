package dispatch;

import java.util.ArrayList;

import memory.Page;
import memory.PageQueue;

public class LRUByCount implements Dispatch {

	@Override
	public Page findPage(PageQueue pageQueue) {				//LRU - ������
		ArrayList<Page> pageList = pageQueue.getQueue();
		if(pageList.size()>0) {
			Page min = pageList.get(0);
			for(int i=0;i<pageList.size();i++) {			//���� �ҵ�ʹ����С����ֵ
			Page temp = pageList.get(i);
			if(min.getUsedTime()>temp.getUsedTime() ){
				min = temp;
				}
			}
			return min;
		}
		
		return null;
	}

}
