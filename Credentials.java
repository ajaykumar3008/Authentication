package autharization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Credentials {

	HashMap<String, String> hm = new HashMap<>();

	public HashMap<String, String> getCredentials() {

		try {

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
					"plf_training_admin", "pff123");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from ajayEmp");

			while (rs.next()) {
				hm.put(rs.getString("user"), rs.getString("password"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return hm;
	}
}
