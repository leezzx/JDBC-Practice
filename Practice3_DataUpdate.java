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
		String sql = "UPDATE NOTICE " // "UPDATE NOTICE " + "SET" : ���� ����
				+ "SET"
				+ "    TITLE=?,"
				+ "    CONTENT=?,"
				+ "    FILES=?"
				+ "WHERE ID=?";
				
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // �� ���� �غ���� ��
		st.setString(1, title); // �ε��� ���� 0�� �ƴ϶� 1���� ����
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate(); // ������Ʈ�� �Ϸ�Ǹ� ��� ������ ��ȯ = 1
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
