package weather;

public class ForecastDisplay implements Observer, DisplayElement{
	private double temperature1;
	private double humidity1;
	private double pressure1;
	
	private double temperature2=0;
	private double humidity2=0;
	private double pressure2=0;
	
	
	
	
	public ForecastDisplay(WeatherData weatherData) {
		super();
		weatherData.addObserver(this);
	}
	@Override
	public void display() {
		double forecastT = Math.abs(this.temperature1 - this.temperature2)/this.temperature2;
		double forecastH = Math.abs(this.humidity1 - this.humidity2) / this.humidity2;
		double forecastP = Math.abs(this.pressure1 - this.pressure2) / this.pressure2;
		System.out.println(" 预报温度:" + forecastT + " 预报湿度:" + forecastH + " 预报压力:" + forecastP);
	}
	@Override
	public void update(Information information) {
		this.temperature1 = this.temperature2;
		this.humidity1 = this.humidity2;
		this.pressure1 = this.pressure2;
		
		this.temperature2 = information.getTemperature();
		this.humidity2 = information.getHumidity();
		this.pressure2 = information.getPressure();
		
	}


	
}
