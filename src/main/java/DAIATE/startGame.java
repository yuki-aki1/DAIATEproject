package DAIATE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.Player;
import dbAccess.Room;
import dbAccess.Subject;
import utility.RandomGenerate;

/**
 * Servlet implementation class startGame
 */
@WebServlet("/startGame")
public class startGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public startGame() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int roomState = 1;

		HttpSession session = request.getSession(false);

		if (session == null) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
			return;
		}

		String roomId = (String) session.getAttribute("roomId");
		String playerName = (String) session.getAttribute("playerName");

		Room.updateRoomState(roomId, roomState);

		Player[] players = Player.getPlayersWithRoomId(roomId);

		int answerPlayerIndex = RandomGenerate.getRandomIndex(players.length);
		Room.updateAnswerPlayerName(roomId, players[answerPlayerIndex].getPlayerName());

		String[] playerNames = new String[Player.getPlayersWithRoomId(roomId).length];
		for (int i = 0; i < Player.getPlayersWithRoomId(roomId).length; i++) {
			playerNames[i] = Player.getPlayersWithRoomId(roomId)[i].getPlayerName();
		}

		int playerIndex = 0;
		for (int i = 0; i < players.length; i++) {
			String name = players[i].getPlayerName();
			if (name.equals(playerName)) {
				playerIndex = i;
			}
		}

		Subject subject = Subject.getSubject();
		Room.updateSubjectId(roomId,subject.getSubjectId());

		request.setAttribute("roomId", roomId);
		request.setAttribute("playerNames", playerNames);
		request.setAttribute("playerIndex", playerIndex);
		request.setAttribute("answerPlayerIndex", answerPlayerIndex);
		request.setAttribute("subject", subject.getSubjectName());

		request.getRequestDispatcher("/WEB-INF/view/makeHint.jsp").forward(request, response);
	}

}
