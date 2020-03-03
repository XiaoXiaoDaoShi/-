package factory;

import materialtInterface.MainFood;
import materialtInterface.Meat;
import materialtInterface.Sauce;
import meterial.Rice;
import meterial.SanWeiGuoSauce;
import meterial.XiaHua;

public class ChongQingDeZhuangHuoGuoFactory implements SeasoningFactory{

	@Override
	public Meat createMeat(String type) {
		if(type.equals("xiahua")) {
			return new XiaHua();
		}
		return null;
	}

	@Override
	public Sauce createSauce(String type) {
		if(type.equals("sanweiguo")) {
			return new SanWeiGuoSauce();
		}
		return null;
	}

	@Override
	public MainFood createRice() {
		// TODO Auto-generated method stub
		return new Rice();
	}

	
	
}
