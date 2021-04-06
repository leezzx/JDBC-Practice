package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService_CRUD;

public class NoticeConsole {

	private NoticeService_CRUD service;
	private int page; // ������ ����, ������ ���� ����
	private String searchField; // �˻��� ���� ����
	private String searchWord; // �˻��� ���� ����
	
	public NoticeConsole() {
		
		service = new NoticeService_CRUD();
		page = 1; // ó�� ������ ����
		searchField = "TITLE"; // �⺻ �ʵ� ����
		searchWord = "";
		
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount(searchField, searchWord); // �Խñ� ���� ���ϱ� ���� ����, List������ ������ �ٲ��� �ϴ� ��������
		int lastPage = count / 10; // List������ ������ �ٲ��� �ϴ� ��������
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		
		System.out.println("������������������������������������������������������������������������");
		System.out.printf("<��������> �� %d �Խñ�\n", count);
		System.out.println("������������������������������������������������������������������������");
		
		for(Notice n : list) {
		System.out.printf("%d. %s / %s / %s\n", // printf ���� %d : ����, %s : ����
							n.getId(), 
							n.getTitle(), 
							n.getWriterId(),
							n.getRegDate());
		}
		System.out.println("������������������������������������������������������������������������");
		System.out.printf("          %d/%d pages\n", page, lastPage);
		
	}

	public int inputNoticeMenu() {
		
		Scanner scan = new Scanner(System.in); // console�� ���� Scanner : ����, �Ǽ�, ���ڿ��� �о���� class
		
		System.out.printf("1.����ȸ/ 2.����/ 3.����/ 4.�۾���/ 5.�˻�/ 6.���� >");
		String menu_ = scan.nextLine(); // nextLine() : ���� �� ���� ���ڿ� ����
		int menu = Integer.parseInt(menu_);
		
		return menu;
		
	}

	public void movePrevList() {
		
		if(page == 1) {
			System.out.println("======================");
			System.out.println("[ ���� �������� �����ϴ�.]");
			System.out.println("======================");
			return;
		}
		
		page--; // 1�� ����
		
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		
		int count = service.getCount(searchField, searchWord); // �Խñ� ���� ���ϱ� ���� ����, List������ ������ �ٲ��� �ϴ� ��������
		int lastPage = count / 10; // List������ ������ �ٲ��� �ϴ� ��������
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		
		if(page == lastPage) {
			System.out.println("======================");
			System.out.println("[ ���� �������� �����ϴ�.]");
			System.out.println("======================");
			return;
		}
		
		page++; // 1�� ����
		
	}

	public void inputSearchWord() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("�˻� ����(title/content/writerId)�߿� �ϳ��� �Է��ϼ���");
		System.out.println(">");
		searchField = scan.nextLine();
		
		System.out.println("�˻��� >");
		searchWord = scan.nextLine();
		
	}

}
