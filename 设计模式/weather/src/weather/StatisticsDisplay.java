package weather;

public class StatisticsDisplay implements Observer, DisplayElement{
	private double temp;  //
	private double maxTemp;
	private double minTemp;
	private double tempSum;
	private double numReadings;
	
	
	
	public StatisticsDisplay(WeatherData weatherData) {
		super();
		weatherData.addObserver(this);
	}
	@Override
	public void display() {
		System.out.println("当前温度:" + this.temp + " 最大温度:" + this.maxTemp + " 最小温度:" + this.minTemp + " 温度总和:" + this.tempSum
				+ " 波动的次数:" + this.numReadings);
	}
	@Override
	public void update(Information information) {
		this.temp = information.getTemperature();
		this.maxTemp = information.getMaxTemp();
		this.minTemp = information.getMinTemp();
		this.tempSum = information.getTempSum();
		this.numReadings = information.getNumReadings();
	}

	
	
}
