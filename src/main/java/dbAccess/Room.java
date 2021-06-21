package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Room {

	public static Connection conn;
	private String roomId;
	private String hostPlayerName;
	private int roomState;
	private String answerPlayerName;
	private int subjectId;
	private String answer;
	
	public Room(String roomId, String hostPlayerName, int roomState, String answerPlayerName, int subjectId, String answer) {
		this.roomId = roomId;
		this.hostPlayerName = hostPlayerName;
		this.roomState = roomState;
		this.answerPlayerName = answerPlayerName;
		this.subjectId = subjectId;
		this.answer = answer;
	}
	
	public Room(String roomId, String hostPlayerName) {
		this(roomId, hostPlayerName, 0, null, -1, null);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "insert into rooms (room_id, host_player_name) values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, roomId);
			pstmt.setString(2, hostPlayerName);

//			int num = pstmt.executeUpdate();
			pstmt.executeUpdate();
			
			pstmt.close();
						
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
			}
		}
	}
}
