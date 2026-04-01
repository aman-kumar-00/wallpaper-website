package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import in.edu.tint.wallpaper.util.DBConnection;

@WebServlet("/UploadWallpaper")
@MultipartConfig

public class UploadWallpaper extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Get category */

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		/* Optional title prefix */

		String prefix = request.getParameter("titlePrefix");
		String tags = request.getParameter("tags");

		/* Upload folder */

		String uploadPath = getServletContext().getRealPath("/Image");

		File folder = new File(uploadPath);

		if (!folder.exists()) {

			folder.mkdir();

		}

		/* Get multiple files */

		Collection<Part> parts = request.getParts();

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(

					"INSERT INTO HDwallpapers(title,image_url,category_id,tags) VALUES(?,?,?,?)"

			);

			for (Part part : parts) {

				if (part.getName().equals("images") && part.getSize() > 0) {

					String fileName = part.getSubmittedFileName();

					/* Save file */

					part.write(uploadPath + File.separator + fileName);

					/* Auto title from filename */

					String title;

					if (prefix != null && !prefix.isEmpty()) {

						title = prefix + " " + fileName;

					} else {

						title = fileName;

					}

					/* DB path */

					String imagePath = "Image/" + fileName;

					/* Insert */

					ps.setString(1, title);
					ps.setString(2, imagePath);
					ps.setInt(3, categoryId);
					ps.setString(4, tags); // save filename as tag

					ps.executeUpdate();

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		response.sendRedirect("home.html");

	}

}