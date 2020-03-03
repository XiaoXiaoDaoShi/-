package bonus;

import People.Position;

public class TeamBonus extends BonusDecorator{
	Position position;

	public TeamBonus(Position positon) {
		this.position = positon;
	}

	@Override
	public String getDescription() {
		return position.getDescription() + " + TeamBonus";
	}

	@Override
	public double gain() {
		double temp = this.position.getTeamSaleCount() * 0.01;
		return this.position.gain() + temp;
	}
	
}
