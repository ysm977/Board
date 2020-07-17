import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	static ArrayList<Article> articles = new ArrayList<Article>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String cmd = "";
//		String store = null; // "" -> 없는 데이터, null

		int id = 1;

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

//				System.out.println("내용을 입력해주세요");
//				String body = sc.nextLine();
//				bodies.add(body);
//				article.body = body;
//				
//				System.out.println("작성자를 입력해주세요");
//				String writer = sc.nextLine();
//				writers.add(writer);
//				article.writer = writer;
				
				articles.add(article);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (cmd.equals("read")) {
				System.out.println("======== 게시물 목록 ========");
				for (int i = 0; i < articles.size(); i++) {
					System.out.println("번호 : " + articles.get(i).id);
					System.out.println("제목 : " + articles.get(i).title);
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
	String writer;
}