package DAIATE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class waitAnswer
 */
@WebServlet("/waitAnswer")
public class waitAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public waitAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);			
			return ;
		}
		
		String roomId = (String) session.getAttribute("roomId");
		String playerName = (String) session.getAttribute("playerName");
		String hostPlayerName = (String) session.getAttribute("hostPlayerName");
		
		Room room = Room.getRoom(roomId);
		int roomState = room.getRoomState();
		
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

		List<String> hints = new ArrayList<>();
		for (int i = 0; i < players.length; i++) {
			hints.add(players[i].getHint());
		}
		request.setAttribute("hints", hints.toArray(new String[hints.size()]));

		
		if (roomState == 2) {
			request.getRequestDispatcher("/WEB-INF/view/waitAnswer.jsp").forward(request, response);
			return ;
		} 
		
		if (roomState == 3) {
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
