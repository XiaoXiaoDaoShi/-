package adapter;

import newType.NewType;
import newType.NewTypeImp;
import old.Old;

public class NewTypeAdapter implements Old{

	NewTypeImp newTypeImp;
	
	
	
	public NewTypeAdapter(NewTypeImp newTypeImp) {
		super();
		this.newTypeImp = newTypeImp;
	}

	@Override
	public void roll(int type) {
		newTypeImp.roll(type);
	}

	@Override
	public void delete(int type, int size) {
		newTypeImp.delete(type, size);
	}

	@Override
	public void concat() {
		newTypeImp.concat();
	}

	@Override
	public void write() {
		newTypeImp.write();
	}

	

}
