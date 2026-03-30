package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.edu.tint.wallpaper.util.DBConnection;
import model.Wallpaper;

public class WallpaperDAO {

	public List<Wallpaper> getWallpapers(int limit, int offset) {

		List<Wallpaper> list = new ArrayList<>();

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT * FROM HDwallpapers LIMIT ? OFFSET ?");

			ps.setInt(1, limit);
			ps.setInt(2, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Wallpaper w = new Wallpaper();

				w.setId(rs.getInt("id"));
				w.setTitle(rs.getString("title"));
				w.setImagePath(rs.getString("image_url"));

				list.add(w);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
}
