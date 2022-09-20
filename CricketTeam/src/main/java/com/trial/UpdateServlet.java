package com.trial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Update Player</h1>");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		Player player = PlayerDao.getPlayerById(id);

		out.print("<div class=\"new\">\n" + "  <form action='SaveUpdateServlet' method='post'>\n" + "\n"
				+ "<label hidden for=\"matches\">Player id</label><br>\n"
				+ "  <input hidden type=\"number\" id=\"playerId\" name=\"id\" value='" + player.getId() + "'><br>\n"
				+ "  \n" + "  <br>\n" + "    <label for=\"name\">Enter Player Name</label><br>\n"
				+ "    <input type=\"text\" id=\"name\" name=\"name\"  value='" + player.getName() + "'><br>\n"
				+ "    \n" + "\n" + "   <label for=\"matches\">Enter number of matches played</label><br>\n"
				+ "  <input type=\"number\" id=\"matches\" name=\"matches\"  value='" + player.getMatches() + "'><br>\n"
				+ "  \n" + "  <label for=\"score\">Enter total runs scored</label><br>\n"
				+ "    <input type=\"number\" id=\"score\" name=\"totalScore\"  value='" + player.getTotalScore() + "'><br>\n"
				+ "    \n" + "    <label for=\"wickets\">Enter number of Wickets taken</label><br>\n"
				+ "    <input type=\"number\" id=\"wickets\" name=\"wickets\"  value='" + player.getWickets()
				+ "'><br>\n" + "    \n" + "    <label for=\"ducks\">Enter number of ducks</label><br>\n"
				+ "    <input type=\"number\" id=\"ducks\" name=\"ducks\"  value='" + player.getDucks() + "'><br>\n"
				+ "   \n" + "   <label for=\"players\">Player Type</label><br>\n"
				+ "    <select id=\"playerType\" name = \"playerType\" value='" + player.getPlayerType()+"'"
				+ "				>\n"
				+ "					<option value=\"Batsman\">Batsman</option>\n"
				+ "					<option value=\"Bowler\">Bowler</option>\n"
				+ "					<option value=\"Wicket-Keeper\">Wicket-Keeper</option>\n"
				+ "					<option value=\"All-Rounder\">All-Rounder</option>\n"
				+ "				</select><br>\n" + "\n" + "<br>\n" + "    <input type=\"submit\" value=\"Update\">\n" + "  </form>\n"
				+ "</div>");
		out.close();
	}
}
