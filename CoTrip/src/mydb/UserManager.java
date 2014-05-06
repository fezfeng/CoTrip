package mydb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createUserSql = "INSERT INTO USER (USERNAME, PASSWORD, CITY) VALUES (?, ?, ?);";
	
	String readAllUsersSql = "SELECT * FROM USER;";
	String readUser = "SELECT * FROM USER WHERE USERNAME=?;";
	String readUserForId = "SELECT * FROM USER WHERE ID=?;";
	
	String getUserForNameSql = "SELECT * FROM USER WHERE USERNAME=?";
	
	String updateUserSql = "UPDATE USER SET PASSWORD=? CITY=? WHERE USERNAME=?;";
	String deleteUserSql = "DELETE FROM USER WHERE USERNAME=?";
	
	String getUserPasswordSql = "SELECT * FROM USER WHERE USERNAME=?;";
	
	
	public void createUser(User newUser) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createUserSql);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getCity());
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
	
	

	public List<User> readAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
			while(results.next()) {
				User user = new User(results.getInt("id"), 
						results.getString("username"), results.getString("password"), 
						results.getString("city"));
				users.add(user);
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
		return users;
	}
	
	
	public List<String> readAllUsernames(){
		List<String> res = new ArrayList<String>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllUsersSql);
			results = statement.executeQuery();
			while(results.next()) {
				
				String tmp = results.getString("username");
				res.add(tmp);
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
		return res;
	}
	

	public User getUserForName (String username) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(getUserForNameSql);
			statement.setString(1, username);
			results = statement.executeQuery();
			if(results.next()) {
				User user = new User(results.getInt("id"), 
						results.getString("username"), results.getString("password"), 
						results.getString("city"));
				return user;
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
		return null;
	}
	
	
	public User getUserForId (int user_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUserForId);
			statement.setInt(1, user_id);
			results = statement.executeQuery();
			if(results.next()) {
				User user = new User(results.getInt("id"), 
						results.getString("username"), results.getString("password"), 
						results.getString("city"));
				return user;
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
		return null;
	}
	
	public void updateUser(String username, User user) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(updateUserSql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getCity());
			statement.setString(3, username);
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
	
	
	public String getPassword(String username){
		String tmp = "";
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(getUserPasswordSql);
			statement.setString(1, username);
			results = statement.executeQuery();
			while(results.next()) {
				
				tmp = results.getString("password");
				
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
		
		return tmp;
	}
	
	public void deleteUser(String username) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteUserSql);
			statement.setString(1, username);
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
	public UserManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/app");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
