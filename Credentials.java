package Validation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Credentials {

	HashMap<String, String> hm = new HashMap<>();
	String user;
	String password;
	
	public Credentials(String user,String pass) {
		
		this.user=user;
		this.password=pass;
	}
	
	public boolean verifyCredentials() {
		
		boolean approve=false;
		if(hm.containsKey(user)) {
			String real=hm.get(user);
			if(real.equals(password)) {
				approve=true;
			}
		}
		
		return approve;
		
	}

	public HashMap<String, String> getCredentials() {

		try {

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ajay",
					"postgres", "Maddineni@123");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from credentials");

			while (rs.next()) {
				hm.put(rs.getString("userid"), rs.getString("password"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return hm;
	}

	public String generateKey() {	
		
		return "ajhsgteb123b3g42b";
	}
	
	public void updateTable(String key,String user) {
		
		try {
			
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ajay",
				"postgres", "Maddineni@123");

			PreparedStatement ps = con.prepareStatement("update credentials set key=? where userid=?");
			
			ps.setString(1, key);
			ps.setString(2, user);

			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
			
	}
	
	public boolean checkKey(String key,String user) {
		
		boolean access=false;
		
		try {
			
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ajay",
				"postgres", "Maddineni@123");

			Statement st = con.createStatement();
			String s="select key from credentials where userid="+user;
			ResultSet rs=st.executeQuery(s);

			if(rs.getString(3)!=null&&key.equals(rs.getString(3))) {
				access=true;
			}
			
			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return access;
	}
}
