package old;

public class OldImp implements Old {

	@Override
	public void write() {
		System.out.println("��¼��־");
	}

	@Override
	public void roll(int type) {
		System.out.println("����");
	}

	@Override
	public void delete(int type, int size) {
		System.out.println("����");
	}

	@Override
	public void concat() {
		System.out.println("����");
	}

}
