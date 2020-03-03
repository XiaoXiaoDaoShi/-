package factory;

import materialtInterface.MainFood;
import materialtInterface.Meat;
import materialtInterface.Sauce;
import meterial.Duck;
import meterial.Goose;
import meterial.MiZhiSauce;
import meterial.Rice;
import meterial.RoseOil;
import prepareFood.MiZhiChaShaoE;

public class YunJiShaoEFactory implements SeasoningFactory{

	public Meat createMeat(String type) {
		// TODO Auto-generated method stub
		if(type.equals("duck")) {
			return new Duck();
		}else if(type.equals("goose")){
			return new Goose();
		}
		return null;
	}

	
	
	public Sauce createSauce(String type) {
		if(type.equals("mizhi")) {
			return new MiZhiSauce();
		}else if(type.equals("roseoil")) {
			return new RoseOil();
			
		}
		return null;
	}

	@Override
	public MainFood createRice() {
		// TODO Auto-generated method stub
		return new Rice();
	}

	
	
}
