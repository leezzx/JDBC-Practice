package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5_ForNoticeConsole {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		NoticeConsole console = new NoticeConsole();
		
		EXIT: 
		while(true) { // 5 ������ ���� �ݺ�, EXIT�� ��
		
			console.printNoticeList();
			int menu = console.inputNoticeMenu();
			
			switch(menu) { // case�� ��� �����ϴ� ����ġ
			
			case 1: // ����ȸ
				break;
			case 2: // ����
				console.movePrevList();
				break;
			case 3: // ����
				console.moveNextList();
				break;
			case 4: // �۾���
				break;
			case 5: // �˻�
				console.inputSearchWord();
				break;
			case 6: // ����
				System.out.println("Bye~~");
				break EXIT;
			default: // 1, 2, 3, 4, 5�� ���� �Է��� ���
				System.out.println("<<�����>> �޴��� 1~4������ �Է��� �� �ֽ��ϴ�.");
				break;
			
			}
		
		}

	}

}
