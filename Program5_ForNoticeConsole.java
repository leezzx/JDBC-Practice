package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5_ForNoticeConsole {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		NoticeConsole console = new NoticeConsole();
		
		EXIT: 
		while(true) { // 5 누를때 까지 반복, EXIT로 라벨
		
			console.printNoticeList();
			int menu = console.inputNoticeMenu();
			
			switch(menu) { // case일 경우 실행하는 스위치
			
			case 1: // 상세조회
				break;
			case 2: // 이전
				console.movePrevList();
				break;
			case 3: // 다음
				console.moveNextList();
				break;
			case 4: // 글쓰기
				break;
			case 5: // 검색
				console.inputSearchWord();
				break;
			case 6: // 종료
				System.out.println("Bye~~");
				break EXIT;
			default: // 1, 2, 3, 4, 5외 수를 입력할 경우
				System.out.println("<<사용방법>> 메뉴는 1~4까지만 입력할 수 있습니다.");
				break;
			
			}
		
		}

	}

}
