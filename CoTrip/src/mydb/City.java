package mydb;

public class City {

	protected String cityname;
	
	public City(){}
	public City(String cityname) {
		this.cityname = cityname;
	}
	
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCityname() {
		return cityname;
	}
	
}
