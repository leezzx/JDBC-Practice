package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException { // ����ó��
		
		// �ش� project ��Ŭ�� -> Build Path -> Configure Build Path -> Libraries -> Add External JARs... -> ojdbc8.jar Apply
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // localhost �ڸ��� ���� ��Ǫ���� ����Ƽ �ֱ� ����
		String sql = "SELECT * FROM NOTICE WHERE HIT>=0";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) { // if : �����Ͱ� ������ �������� ������ �ƹ��� ����, while : ó������ ������ ������ ��������
			
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			
			System.out.printf(" id :%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n", id, title, writerId, regDate, content, hit);
//			id :1, title:JDBC�� �����ΰ�, writerId:newlec, regDate:2021-04-03, content:aaa, hit:0
//			id :2, title:JDBC2�� �����ΰ�, writerId:newlec, regDate:2021-04-03, content:aaa, hit:0
//			id :3, title:JDBC3�� �����ΰ�, writerId:newlec, regDate:2021-04-03, content:aaa, hit:0
			
		}
		
		rs.close();
		st.close();
		con.close();
		
	}

}
