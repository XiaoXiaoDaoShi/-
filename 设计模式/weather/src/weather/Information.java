package weather;

public class Information {
	private double temperature;
	private double humidity;
	private double pressure;
	
	private double maxTemp;
	private double minTemp;
	private double tempSum;
	private double numReadings;
	
	public Information(double temperature, double humidity, double pressure,  double maxTemp,
			double minTemp, double tempSum, double numReadings) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
		this.tempSum = tempSum;
		this.numReadings = numReadings;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	public double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
	public double getTempSum() {
		return tempSum;
	}
	public void setTempSum(double tempSum) {
		this.tempSum = tempSum;
	}
	public double getNumReadings() {
		return numReadings;
	}
	public void setNumReadings(double numReadings) {
		this.numReadings = numReadings;
	}
	
}
