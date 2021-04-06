package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService_CRUD {
	
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 겹치는 부분 변수 생성
	private String uid = "newlec";
	private String pwd = "272452";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException { // DATABASE 불러 오는 함수
		
		int start = (10 * (page - 1) + 1); // 1, 11, 21, 31 ...
		int end = 10 * page; // 10, 20, 30, 40 ...
		
		String sql = "SELECT * FROM (" + // VIEW생성을 통해 단순화 가능
				"	 SELECT ROWNUM NUM, N.* FROM (" +
				"		 SELECT * FROM NOTICE ORDER BY REGDATE DESC " +
				"	 ) N " +
				") " +
				"WHERE " + field + " LIKE ? AND NUM BETWEEN ? AND ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + query + "%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) { // if : 데이터가 있으면 가져오고 없으면 아무일 없음, while : 처음부터 끝까지 데이터 가져오기
			
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(
								id,
								title,
								writerId,
								regDate,
								content,
								hit,
								files
							);
			
			list.add(notice);
			
		}
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException { // Scalar(단위값)을 얻어오는 함수 (게시글 수를 위한)
		
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE WHERE " + field + " LIKE ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + query + "%");
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt("COUNT");
		}
		
		rs.close();
		st.close();
		con.close();
		
		return count;
		
	}

	public int insert(Notice notice) throws SQLException, ClassNotFoundException { // DATABASE 입력하는 함수
		
		String title = notice.getTitle(); // notice에서 불러온 값을 사용
		String writer_id = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String sql = "INSERT INTO notice (" // Ctrl + f : 같은값들 찾아서 replace
				+ "    title,"
				+ "    writer_id,"
				+ "    content,"
				+ "    files"
				+ ") VALUES (?,?,?,?)"; // 들어갈 값을 물음표로 대체
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 들어갈 값을 준비시켜 줌
		st.setString(1, title); // 인덱스 값이 0이 아니라 1부터 시작
		st.setString(2, writer_id);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate(); // 업데이트가 완료되면 결과 개수를 반환 = 1
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int update(Notice notice) throws SQLException, ClassNotFoundException { // DATABASE 수정하는 함수
		
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String sql = "UPDATE NOTICE " // "UPDATE NOTICE " + "SET" : 띄어쓰기 유의
				+ "SET"
				+ "    TITLE=?,"
				+ "    CONTENT=?,"
				+ "    FILES=?"
				+ "WHERE ID=?";
				
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 들어갈 값을 준비시켜 줌
		st.setString(1, title); // 인덱스 값이 0이 아니라 1부터 시작
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate(); // 업데이트가 완료되면 결과 개수를 반환 = 1
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException { // DATABASE 삭제하는 함수
		
		String sql = "DELETE NOTICE WHERE ID=?";
				
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql); // 들어갈 값을 준비시켜 줌
		st.setInt(1, id);
		
		int result = st.executeUpdate(); // 업데이트가 완료되면 결과 개수를 반환 = 1
		
		st.close();
		con.close();
		
		return result;
	}

	
}
