package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.edu.tint.wallpaper.util.DBConnection;
import model.Wallpaper;

public class WallpaperDAO {

	public List<Wallpaper> getWallpapers(String tagstr, int categoryId, int limit, int offset) {

		List<Wallpaper> list = new ArrayList<>();

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps;

			/* If NO search */

			if (tagstr == null || tagstr.trim().isEmpty()) {

				ps = con.prepareStatement(

						"SELECT * FROM HDwallpapers " + "WHERE category_id=? " + "ORDER BY id DESC LIMIT ? OFFSET ?"

				);

				ps.setInt(1, categoryId);
				ps.setInt(2, limit);
				ps.setInt(3, offset);

			}

			/* If SEARCH exists */

			else {

				ps = con.prepareStatement(

						"SELECT * FROM HDwallpapers " + "WHERE category_id=? " + "AND (tags LIKE ? "
								+ "OR image_url LIKE ?) " + "ORDER BY id DESC LIMIT ? OFFSET ?"

				);

				ps.setInt(1, categoryId);

				ps.setString(2, "%" + tagstr + "%");

				ps.setString(3, "%" + tagstr + "%");

				ps.setInt(4, limit);
				ps.setInt(5, offset);

			}

			/* Execute */

			ResultSet rs = ps.executeQuery();

			/* Read results */

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