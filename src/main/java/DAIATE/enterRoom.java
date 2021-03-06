package DAIATE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name"); // プレイヤー名の入力を取得
		String roomId = request.getParameter("roomId"); // roomIｄの入力を取得
		int numOfUser = 2;
		name ="aaaaa";
		roomId="000000";
		

		request.setAttribute("name", name);
		request.setAttribute("roomId", roomId);
		request.setAttribute("numOfUser", numOfUser);

		boolean hasTheGameStarted = false; // ゲームが始まっているか(とりあえず、常に始まっていない状態にしている)

		if (hasTheGameStarted) {
			request.getRequestDispatcher("/WEB-INF/view/makeHint.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response); //ゲームが始まっていない場合
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name"); // プレイヤー名の入力を取得
		String roomId = request.getParameter("roomId"); // roomIｄの入力を取得
		int numOfUser = 2; // とりあえず「1」

		request.setAttribute("name", name);
		request.setAttribute("roomId", roomId);
		request.setAttribute("numOfUser", numOfUser);
		
		String[] errorText = {"エラー１","エラー２"};
		request.setAttribute("errorText", errorText);

		// プレイヤー名：aa、ルームID：00が入力されたときのみ「waitPlayer.jsp」へ
		if (name.equals("aa") == true && roomId.equals("00") == true) {
			request.getRequestDispatcher("/WEB-INF/view/waitPlayer.jsp").forward(request, response);

		} else if (name.equals("aa") == false && roomId.equals("00") == true) {
			request.setAttribute("nameError", "err発生");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

		} else if (name.equals("aa") == true && roomId.equals("00") == false) {
			request.setAttribute("roomIdError", "err発生");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

		} else {
			request.setAttribute("nameError", "err発生");
			request.setAttribute("roomIdError", "err発生");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);

		}

	}

}
