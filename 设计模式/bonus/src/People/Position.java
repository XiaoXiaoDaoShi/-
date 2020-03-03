package People;

public abstract class Position{
	 String description = "Unknown";
	 double saleCount;
	 double practicalBackMoney;
	 double teamSaleCount;
	
	public String getDescription() {
		return description;
	}
	
	public abstract double gain();

	public double getSaleCount() {
		return saleCount;
	}


	public double getPracticalBackMoney() {
		return practicalBackMoney;
	}


	public double getTeamSaleCount() {
		return teamSaleCount;
	}

	
	
}
