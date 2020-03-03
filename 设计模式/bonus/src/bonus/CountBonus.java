package bonus;

import People.Position;

public class CountBonus extends BonusDecorator{
	Position position;

	public CountBonus(Position position) {
		
		this.position = position;
	}

	@Override
	public String getDescription() {
		return position.getDescription() + " + CountBonus";
	}

	@Override
	public double gain() {
		double temp = this.position.getPracticalBackMoney() * 0.001;
		return this.position.gain() + temp;
	}
	
	
	
}
