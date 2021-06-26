package DAIATE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.Player;
import dbAccess.Room;
import dbAccess.Subject;

/**
 * Servlet implementation class sendHint
 */
@WebServlet("/sendHint")
public class sendHint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendHint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);			
			return ;
		}
		
		String hint = (String)request.getAttribute("hint");
		if (hint == null) {
			hint = "";
		}
		
		
		
		String roomId = (String) session.getAttribute("roomId");
		String playerName = (String) session.getAttribute("playerName");
		String hostPlayerName = (String) session.getAttribute("hostPlayerName");
		
		Room room = Room.getRoom(roomId);
		
		Player[] players = Player.getPlayersWithRoomId(roomId);

		List<String> playerNames = new ArrayList<>();
		int playerIndex = 0;
		int answerPlayerIndex = 0;
		for (int i = 0; i < players.length; i++) {
			String name = players[i].getPlayerName();
			if (name.equals(hostPlayerName)) {
				playerNames.add(0, name);
			} else {
				playerNames.add(name);
			}
			if (name.equals(playerName)) {
				playerIndex = i;
			}
			if (name.equals(room.getAnswerPlayerName())) {
				answerPlayerIndex = i;
			}
		}
		
		Subject subject = Subject.getSubject(room.getSubjectId());
		
		request.setAttribute("roomId", roomId);
		request.setAttribute("playerNames", playerNames.toArray(new String[playerNames.size()]));
		request.setAttribute("playerIndex", playerIndex);
		request.setAttribute("answerPlayerIndex", answerPlayerIndex);
		request.setAttribute("subject", subject.getSubjectName());
		
		if (room.getAnswerPlayerName().equals(playerName)) {
			Set<String> hints = new HashSet<>();
			for (Player player : players) {
				hints.add(player.getHint());
			}
			request.setAttribute("hints", hints.toArray(new String[hints.size()]));
			request.getRequestDispatcher("/WEB-INF/view/makeAnswer.jsp").forward(request, response);
		} else {
			String[] hints = new String[players.length];
			for (int i = 0; i < players.length; i++) {
				hints[i] = players[i].getHint();
			}
			request.setAttribute("hints", hints);
			request.getRequestDispatcher("/WEB-INF/view/waitAnswer.jsp").forward(request, response);						
		}
	}

}
