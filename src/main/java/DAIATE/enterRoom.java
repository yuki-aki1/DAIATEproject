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

/**
 * Servlet implementation class enterRoom
 */
@WebServlet("/enterRoom")
public class enterRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public enterRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		request.setCharacterEncoding("UTF-8");

//		
//		
//		String name = request.getParameter("name"); // プレイヤー名の入力を取得
//		String roomId = request.getParameter("roomId"); // roomIｄの入力を取得
//		int numOfUser = 2; // とりあえず「1」
//
//		request.setAttribute("name", name);
//		request.setAttribute("roomId", roomId);
//		request.setAttribute("numOfUser", numOfUser);
//		
//		String[] errorText = {"エラー１","エラー２"};
//		request.setAttribute("errorText", errorText);
//
//		// プレイヤー名：aa、ルームID：00が入力されたときのみ「waitPlayer.jsp」へ
//		if (name.equals("aa") == true && roomId.equals("00") == true) {
//			request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);
//
//		} else if (name.equals("aa") == false && roomId.equals("00") == true) {
//			request.setAttribute("nameError", "err発生");
//			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
//
//		} else if (name.equals("aa") == true && roomId.equals("00") == false) {
//			request.setAttribute("roomIdError", "err発生");
//			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
//
//		} else {
//			request.setAttribute("nameError", "err発生");
//			request.setAttribute("roomIdError", "err発生");
//			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
//
//		}
		
		request.setCharacterEncoding("UTF-8");

		String playerName = request.getParameter("name");
		String roomId = request.getParameter("roomId");
		
		// もし名前やルームIDがnullならindex.jspに戻る
		if (playerName == null || roomId == null) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);			
			return;
		}
		
		HttpSession session = request.getSession(true);
		String sessionId = session.getId();
		List<String> errorTexts = new ArrayList<>();
		if (!Room.idExist(roomId)) {
			errorTexts.add("ルームID「" + roomId + "」は存在しません。");
		} else if (Player.existsPlayerName(roomId, playerName)) {
			errorTexts.add("ルームID「" + roomId + "」でプレイヤー名「" + playerName + "」はすでに使われています。");
		}
		
		
		if (errorTexts.size() != 0) {
			request.setAttribute("errorTexts", errorTexts.toArray(new String[errorTexts.size()]));
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
			return;
		}

		String hostPlayerName = Room.getHostName(roomId);
		
		session.setAttribute("roomId", roomId);
		session.setAttribute("hostPlayerName", hostPlayerName);
		session.setAttribute("playerName", playerName);
		
		new Player(playerName, roomId, sessionId);
	
		request.getRequestDispatcher("/waitRoom").forward(request, response);


	}

}
