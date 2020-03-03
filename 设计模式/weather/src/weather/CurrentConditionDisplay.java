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
		System.out.println("ÎÂ¶È£º"+this.temperature + " Êª¶È£º"+ this.humidity + " ÆøÑ¹£º" + this.pressure);
		
	}


	@Override
	public void update(Information information) {
		this.temperature = information.getTemperature();
		this.humidity = information.getHumidity();
		this.pressure = information.getPressure();
	}
	
}
