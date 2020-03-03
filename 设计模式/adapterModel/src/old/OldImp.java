package old;

public class OldImp implements Old {

	@Override
	public void write() {
		System.out.println("记录日志");
	}

	@Override
	public void roll(int type) {
		System.out.println("暂无");
	}

	@Override
	public void delete(int type, int size) {
		System.out.println("暂无");
	}

	@Override
	public void concat() {
		System.out.println("暂无");
	}

}
