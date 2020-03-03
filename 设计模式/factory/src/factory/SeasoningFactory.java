package factory;

import materialtInterface.MainFood;
import materialtInterface.Meat;
import materialtInterface.Sauce;

public interface SeasoningFactory {
	Meat createMeat(String type);
	Sauce createSauce(String type);
	MainFood createRice();
	
}
