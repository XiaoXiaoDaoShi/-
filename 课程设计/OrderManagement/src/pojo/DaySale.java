package pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DaySale {
	private Date date;
	private int sale;
	public DaySale(Date date, int sale) {
		super();
		this.date = date;
		this.sale = sale;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	
	public String getPraseDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(this.date);
	}
}
