package newType;

public class NewTypeImp implements NewType {

	@Override
	public void roll(int type) {
		if(type == 1) {
			System.out.println("����־��С����");
		}else if(type==2) {
			System.out.println("���ڹ���");
		}else if(type==3) {
			System.out.println("ǿ�ƹر���־����");
		}
	}

	@Override
	public void delete(int type, int size) {
		if(type==1) {
			System.out.println("ɾ����СΪ" + size + "������");
		}else if(type==2) {
			System.out.println("ɾ��" + size + "�����ڵĹ�������");
		}
	}

	@Override
	public void concat() {
		System.out.println("�ļ��ϲ�");
	}

	@Override
	public void write() {
		System.out.println("��¼��־");
	}

}
