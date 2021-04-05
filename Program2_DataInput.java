package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program2_DataInput {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST2";
		String writer_id = "newlec";
		String content = "hahaha";
		String files = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "INSERT INTO notice (" // Ctrl + f : 같은값들 찾아서 replace
				+ "    title,"
				+ "    writer_id,"
				+ "    content,"
				+ "    files"
				+ ") VALUES (?,?,?,?)"; // 들어갈 값을 물음표로 대체
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 들어갈 값을 준비시켜 줌
		st.setString(1, title); // 인덱스 값이 0이 아니라 1부터 시작
		st.setString(2, writer_id);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate(); // 업데이트가 완료되면 결과 개수를 반환 = 1
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
