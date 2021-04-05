package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Practice3_DataUpdate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST3";
		String content = "hahaha3";
		String files = "";
		int id = 11;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "UPDATE NOTICE " // "UPDATE NOTICE " + "SET" : 띄어쓰기 유의
				+ "SET"
				+ "    TITLE=?,"
				+ "    CONTENT=?,"
				+ "    FILES=?"
				+ "WHERE ID=?";
				
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 들어갈 값을 준비시켜 줌
		st.setString(1, title); // 인덱스 값이 0이 아니라 1부터 시작
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate(); // 업데이트가 완료되면 결과 개수를 반환 = 1
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
