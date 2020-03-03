package bonus;

import People.Position;

public class MouthBonus extends BonusDecorator{
	Position position;
	
	public MouthBonus(Position position) {
		this.position = position;
	}

	@Override
	public String getDescription() {
		return position.getDescription() + " + MouthBonus";
		
	}

	@Override
	public double gain() {
		double temp = this.position.getSaleCount() * 0.03;
		return this.position.gain() + temp;
	}
	
	
}
