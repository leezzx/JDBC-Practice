package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService_CRUD;

public class NoticeConsole {

	private NoticeService_CRUD service;
	private int page; // 페이지 이전, 다음을 위한 변수
	private String searchField; // 검색을 위한 변수
	private String searchWord; // 검색을 위한 변수
	
	public NoticeConsole() {
		
		service = new NoticeService_CRUD();
		page = 1; // 처음 페이지 지정
		searchField = "TITLE"; // 기본 필드 지정
		searchWord = "";
		
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount(searchField, searchWord); // 게시글 수를 구하기 위한 변수, List가져올 때마다 바뀌어야 하니 지역변수
		int lastPage = count / 10; // List가져올 때마다 바뀌어야 하니 지역변수
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		
		System.out.println("────────────────────────────────────");
		System.out.printf("<공지사항> 총 %d 게시글\n", count);
		System.out.println("────────────────────────────────────");
		
		for(Notice n : list) {
		System.out.printf("%d. %s / %s / %s\n", // printf 에서 %d : 숫자, %s : 문자
							n.getId(), 
							n.getTitle(), 
							n.getWriterId(),
							n.getRegDate());
		}
		System.out.println("────────────────────────────────────");
		System.out.printf("          %d/%d pages\n", page, lastPage);
		
	}

	public int inputNoticeMenu() {
		
		Scanner scan = new Scanner(System.in); // console을 위한 Scanner : 정수, 실수, 문자열을 읽어오는 class
		
		System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.종료 >");
		String menu_ = scan.nextLine(); // nextLine() : 띄어쓰기 및 정수 문자열 가능
		int menu = Integer.parseInt(menu_);
		
		return menu;
		
	}

	public void movePrevList() {
		
		if(page == 1) {
			System.out.println("======================");
			System.out.println("[ 이전 페이지가 없습니다.]");
			System.out.println("======================");
			return;
		}
		
		page--; // 1씩 감소
		
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		
		int count = service.getCount(searchField, searchWord); // 게시글 수를 구하기 위한 변수, List가져올 때마다 바뀌어야 하니 지역변수
		int lastPage = count / 10; // List가져올 때마다 바뀌어야 하니 지역변수
		lastPage = count % 10 > 0 ? lastPage + 1 : lastPage;
		
		if(page == lastPage) {
			System.out.println("======================");
			System.out.println("[ 다음 페이지가 없습니다.]");
			System.out.println("======================");
			return;
		}
		
		page++; // 1씩 증가
		
	}

	public void inputSearchWord() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요");
		System.out.println(">");
		searchField = scan.nextLine();
		
		System.out.println("검색어 >");
		searchWord = scan.nextLine();
		
	}

}
