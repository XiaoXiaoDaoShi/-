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
		System.out.println("��ǰ�¶�:" + this.temp + " ����¶�:" + this.maxTemp + " ��С�¶�:" + this.minTemp + " �¶��ܺ�:" + this.tempSum
				+ " �����Ĵ���:" + this.numReadings);
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
