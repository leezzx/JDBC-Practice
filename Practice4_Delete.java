package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Practice4_Delete {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int id = 11;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "DELETE NOTICE WHERE ID=?";
				
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 들어갈 값을 준비시켜 줌
		st.setInt(1, id);
		
		int result = st.executeUpdate(); // 업데이트가 완료되면 결과 개수를 반환 = 1
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
