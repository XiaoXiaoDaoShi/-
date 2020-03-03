package test;

import adapter.NewTypeAdapter;
import newType.NewTypeImp;
import old.Old;
import old.OldImp;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewTypeImp adapter = new NewTypeImp();
//		OldImp adapter = new OldImp();
		Old newType = new NewTypeAdapter(adapter);
		newType.write();
		newType.roll(1);
		newType.delete(1, 10086);
		newType.concat();
	}

}
