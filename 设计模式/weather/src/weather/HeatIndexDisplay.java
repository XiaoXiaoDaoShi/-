package weather;

public class HeatIndexDisplay implements Observer, DisplayElement{
	private double t;
	private double rh;
	private double heat;
	
	
	
	
	public HeatIndexDisplay(WeatherData weatherData) {
		super();
		weatherData.addObserver(this);
	}
	@Override
	public void display() {
		this.heat = ((16.923+(0.185212*t)+(5.37941*rh)-(0.100254*t*rh)+
                (0.00941695*t*t)+(0.00728898*rh*rh)+(0.000345372*t*t*rh)-
                (0.000814971*t*rh*rh)+(0.0000102102*t*t*rh*rh)-(0.000038646*t*t*t)+
                (0.0000291583*(rh * rh * rh)) +(0.00000142721 * (t * t * t * rh)) + 
                (0.000000197438 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
                0.000000000843296 * (t * t * rh * rh * rh)) - (0.0000000000481975 * 
                (t * t * t * rh * rh * rh)));
		System.out.println("当前热值:" + this.heat);
		
	}
	@Override
	public void update(Information information) {
		this.t = information.getTemperature();
		this.rh = information.getHumidity();
		
	}

	
	
}
