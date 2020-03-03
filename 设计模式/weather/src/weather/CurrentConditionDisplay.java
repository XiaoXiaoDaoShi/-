package weather;

public class CurrentConditionDisplay implements Observer, DisplayElement{
	private double temperature;
	private double humidity;
	private double pressure;
	
	
	public CurrentConditionDisplay(WeatherData weatherData) {
		super();
		weatherData.addObserver(this);
	}


	@Override
	public void display() {
		System.out.println("�¶ȣ�"+this.temperature + " ʪ�ȣ�"+ this.humidity + " ��ѹ��" + this.pressure);
		
	}


	@Override
	public void update(Information information) {
		this.temperature = information.getTemperature();
		this.humidity = information.getHumidity();
		this.pressure = information.getPressure();
	}
	
}
