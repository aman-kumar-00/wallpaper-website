package in.edu.tint.wallpaper.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallpaperdb", "root", "amankumar0007");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}