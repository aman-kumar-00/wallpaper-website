package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.edu.tint.wallpaper.util.DBConnection;
import model.User;

public class UserDAO {

	public boolean login(String email, String password) {

		boolean isAuthenticated = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isAuthenticated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isAuthenticated;
	}

	// signup method here

	public boolean signup(User user) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("INSERT INTO users(UserName,email,password) VALUES(?,?,?)");

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());

			int row = ps.executeUpdate();

			if (row > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

}