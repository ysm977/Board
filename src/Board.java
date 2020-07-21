import java.util.ArrayList;
import java.util.Scanner;

import util.Util;


public class Board{
	static ArrayList<Article> articles = new ArrayList<Article>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String cmd = "";
//		String store = null; // "" -> 없는 데이터, null

		int id = 4;
		//기본으로 생성
		Article article1 = new Article(); 
		article1.id=1;
		article1.title="테스트 데이터 제목1";
		article1.body="테스트 데이터 내용1";
		article1.regDate=Util.getCurrentDate();
		
		//생성자오버로딩으로 객체생성
		Article article2 = new Article(2,"제목2","내용2",Util.getCurrentDate());
		Article article3 = new Article(3,"제목3","내용3",Util.getCurrentDate());
		 //게시판에 테스트데이터 추가
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		while (true) {

			System.out.print("명령어를 입력해주세요 : ");
			cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("add : 게시물 저장");
				System.out.println("read : 게시물 조회");
				System.out.println("update : 게시물 수정");
				System.out.println("delete : 게시물 삭제");
			}

			if (cmd.equals("add")) {

				Article article = new Article();
				article.id = id;
				id++;

				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();
				article.title = title;
				
				article.regDate = Util.getCurrentDate(); //util패키지의 날짜구하기 불러오기
					

//				System.out.println("내용을 입력해주세요");
//				String body = sc.nextLine();
//				bodies.add(body);
//				article.body = body;
				
				articles.add(article);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (cmd.equals("read")) {
				System.out.println("======== 게시물 목록 ========");
				for (int i = 0; i < articles.size(); i++) {
					System.out.println("번호 : " + articles.get(i).id);
					System.out.println("제목 : " + articles.get(i).title); 
					
					String str=articles.get(i).regDate;
					String[]arr=str.split(" "); //공백기준으로 문자열 쪼개기
					
					System.out.println("작성일 : " + arr[0]);
					// System.out.println("내용 : " + bodies.get(i) + "\n");
				}
			}
			if (cmd.equals("update")) {
				System.out.println("어떤 게시물을 수정하시겠습니까? : ");
				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);
				
				if (targetArticle != null) {
					
					System.out.println("수정할 제목을 입력해주세요 : ");
					String updated_title = sc.nextLine();
					targetArticle.title = updated_title;
					
					System.out.println("수정이 완료되었습니다.");
				} else {
					System.out.println("없는 게시물 번호입니다.");
				}
				

			}
			if (cmd.equals("delete")) {
				System.out.println("어떤 게시물을 삭제하시겠습니까? :");

				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);

				if (targetArticle != null) {
					articles.remove(targetArticle);
					System.out.println("게시물이 삭제되었습니다.");
				} else {
					System.out.println("없는 게시물 번호입니다.");
				}
			}
		}
	}
	
	public static Article get_article_by_id(int id) {
		Article article = null;
		for (int i = 0; i < articles.size(); i++) {
			
			Article target = articles.get(i);
			
			if (target.id == id) {
				article = target;
				break;
			}
		}
		return article;
	}
	
}

// DataClass, DTO
class Article {
	
	int id;
	String title;
	String body;
	String regDate;
	
	//기본
	Article(){
		
	}
	//생성자 오버로딩
	Article(int id, String title, String body, String regDate){
		this.id=id;
		this.title=title;
		this.body=body;
		this.regDate=regDate;
		
	}
}