package com.trial;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String name = request.getParameter("name");
		int matches = Integer.parseInt(request.getParameter("matches"));
		int totalScore = Integer.parseInt(request.getParameter("totalScore"));
		int wickets = Integer.parseInt(request.getParameter("wickets"));
		int ducks = Integer.parseInt(request.getParameter("ducks"));
		String playerType = request.getParameter("playerType");

		Player player1 = new Player();
		player1.setName(name);
		player1.setMatches(matches);
		player1.setTotalScore(totalScore);
		player1.setWickets(wickets);
		player1.setDucks(ducks);
		player1.setPlayerType(playerType);

		PlayerDao playerdao = new PlayerDao();
		playerdao.savePlayer(player1);
		
		response.sendRedirect("index.html");
	}

}
