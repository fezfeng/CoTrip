package mydb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CityManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createCitySql = "INSERT INTO CITY (CITYNAME) VALUES (?);";
	String readAllCitysSql = "SELECT * FROM CITY;";
	String deleteCitySql = "DELETE FROM CITY WHERE CITYNAME=?";

	public void createCity(String cityname) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createCitySql);
			statement.setString(1, cityname);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<City> readAllCitys() {
		List<City> citys = new ArrayList<City>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllCitysSql);
			results = statement.executeQuery();
			while(results.next()) {
				City city = new City(results.getString("cityname"));
				citys.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return citys;
	}

	
	public void deleteCity(String cityname) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteCitySql);
			statement.setString(1, cityname);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	DataSource ds;
	public CityManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/app");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}