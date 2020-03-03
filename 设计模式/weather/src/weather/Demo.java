package weather;

public class Demo {

	public static void main(String[] args) {
		
		Information information = new Information(1.0, 12.3, 13.5, 45, 12.02, 12.0, 55);
		WeatherData weatherData = new WeatherData(information);
		CurrentConditionDisplay currentCoditonDisplay = new CurrentConditionDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
		
		weatherData.setChange();
		weatherData.notifyObserver();
		currentCoditonDisplay.display();
		statisticsDisplay.display();
		forecastDisplay.display();
		heatIndexDisplay.display();
		
	}

}
