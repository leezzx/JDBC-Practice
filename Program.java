package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException { // 예외처리
		
		// 해당 project 우클릭 -> Build Path -> Configure Build Path -> Libraries -> Add External JARs... -> ojdbc8.jar Apply
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // localhost 자리에 원격 컴푸터의 아이티 넣기 가능
		String sql = "SELECT * FROM NOTICE WHERE HIT>=0";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "newlec", "272452");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) { // if : 데이터가 있으면 가져오고 없으면 아무일 없음, while : 처음부터 끝까지 데이터 가져오기
			
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			
			System.out.printf(" id :%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n", id, title, writerId, regDate, content, hit);
//			id :1, title:JDBC란 무엇인가, writerId:newlec, regDate:2021-04-03, content:aaa, hit:0
//			id :2, title:JDBC2란 무엇인가, writerId:newlec, regDate:2021-04-03, content:aaa, hit:0
//			id :3, title:JDBC3란 무엇인가, writerId:newlec, regDate:2021-04-03, content:aaa, hit:0
			
		}
		
		rs.close();
		st.close();
		con.close();
		
	}

}
