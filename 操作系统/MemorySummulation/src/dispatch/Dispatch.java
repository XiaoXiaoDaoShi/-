package dispatch;

import java.util.ArrayList;

import memory.Page;
import memory.PageQueue;

public interface Dispatch {

	Page findPage(PageQueue pageQueue);
}	
