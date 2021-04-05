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
		String sql = "INSERT INTO notice (" // Ctrl + f : �������� ã�Ƽ� replace
				+ "    title,"
				+ "    writer_id,"
				+ "    content,"
				+ "    files"
				+ ") VALUES (?,?,?,?)"; // �� ���� ����ǥ�� ��ü
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // �� ���� �غ���� ��
		st.setString(1, title); // �ε��� ���� 0�� �ƴ϶� 1���� ����
		st.setString(2, writer_id);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate(); // ������Ʈ�� �Ϸ�Ǹ� ��� ������ ��ȯ = 1
		
		System.out.println(result);
		
		st.close();
		con.close();

	}

}
