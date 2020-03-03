package newType;

public class NewTypeImp implements NewType {

	@Override
	public void roll(int type) {
		if(type == 1) {
			System.out.println("按日志大小滚动");
		}else if(type==2) {
			System.out.println("定期滚动");
		}else if(type==3) {
			System.out.println("强制关闭日志滚动");
		}
	}

	@Override
	public void delete(int type, int size) {
		if(type==1) {
			System.out.println("删除大小为" + size + "的数据");
		}else if(type==2) {
			System.out.println("删除" + size + "个周期的过期数据");
		}
	}

	@Override
	public void concat() {
		System.out.println("文件合并");
	}

	@Override
	public void write() {
		System.out.println("记录日志");
	}

}
