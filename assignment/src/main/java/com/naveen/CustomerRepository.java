package com.naveen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CustomerRepository {

	List<Customer> customers;
	Connection conn = null;
	
	private String instanceConnectionName = 
			"perpule-test-256018:us-central1:myinstance";
	
	private String databaseName = "customer";
	//private String IP_of_instance =  "35.222.84.36";
	private String username = "root";
	private String password = "12345";
	
	String jdbcUrl = String.format(
            "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
        + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
            databaseName,
            instanceConnectionName);         
	

	
	public CustomerRepository() {
		try {
			System.out.println("\n"+ jdbcUrl + "\n");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("\nConnection Established.....\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers(){
		List<Customer> customers = new ArrayList<>();
		String sql = "select * from customers";
		try {
			Statement st =  conn.createStatement();
			ResultSet rs =  st.executeQuery(sql);
			
			while(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt(1));
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setAddress(rs.getString(4));
				c.setCity(rs.getString(5));
				c.setPhone(rs.getInt(6));
				
				customers.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public Customer getCustomer(int id) {
		Customer c = new Customer();
		String sql = "select * from customers where id="+id;
		try {
			Statement st =  conn.createStatement();
			ResultSet rs =  st.executeQuery(sql);
			
			if (rs.next()) {
				c.setId(rs.getInt(1));
				c.setFirstName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setAddress(rs.getString(4));
				c.setCity(rs.getString(5));
				c.setPhone(rs.getInt(6));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public void createCustomer(Customer c) {
		String sql =  "insert into customers values(?,?,?,?,?,?)";
		try {
			PreparedStatement st =  conn.prepareStatement(sql);
			st.setInt(1, c.getId());
			st.setString(2, c.getFirstName());
			st.setString(3, c.getLastName());
			st.setString(4, c.getAddress());
			st.setString(5, c.getCity());
			st.setInt(6, c.getPhone());
			st.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Database not UPDATED");
			e.printStackTrace();
		}
	}
}
