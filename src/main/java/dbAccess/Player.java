package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Player {
	
	Connection conn;
	private static int player_idCount;
	private int player_id;
	private String player_name;
	private String room_id;
	private String session_id;
	private String hint;
	
	public Player(String player_name, String room_id, String session_id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);

			String sql = "insert into players (player_id, player_name, room_id, session_id, hint) values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, player_idCount);
			pstmt.setString(2, player_name);
			pstmt.setString(3, room_id);
			pstmt.setString(4, session_id);
			pstmt.setString(5, null);

			int num = pstmt.executeUpdate();
			
			pstmt.close();
			
			this.player_id = player_idCount++;
			this.player_name = player_name;
			this.room_id = room_id;
			this.session_id = session_id;
			this.hint = null;
			
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
