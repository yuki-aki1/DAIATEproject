package subjectDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbAccess.DatabaseInfo;

public class MakeSubjectDB {
	public static void main(String[] args) {
		addSubjectList();
	}
	public static Connection conn;
	
	public static void addSubjectList() {
		List<String> subjectList = new ArrayList<>();
		
		try {
			//テキストファイルに書かれたお題一覧をリストに格納
			FileReader fr = new FileReader("testSubject.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			
			while(line != null) {
				line = br.readLine();
				subjectList.add(line);
			}
			fr.close();
			
			//お題が入ったリストからお題を取り出し、DBにいれる
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DatabaseInfo.DB_URL, DatabaseInfo.USER, DatabaseInfo.PASSWORD);			
			
			String sql = "insert into subjects (subject_name) values (?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			

			for (int i = 0; i < subjectList.size(); i++) {
				pstmt.setString(1, subjectList.get(i));
				pstmt.executeUpdate();
			}
			
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
