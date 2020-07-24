package Java_Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import util.Util;

public class BoardApp {
	
	ArrayList<Article> articles = new ArrayList<Article>();
	ArrayList<Reply> replies = new ArrayList<Reply>();
	int lastArticleId = 0; //게시물번호 관리용
	int lastReplyId = 0;	//댓글 번호 관리용
	
	void start() {
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		
		make_Test_Data();

		print_articles(articles);
		
		while (true) {

			System.out.print("명령어를 입력해주세요 : ");
			cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("add : 게시물 저장");
				System.out.println("list : 게시물 목록조회");
				System.out.println("detail : 게시물 상세조회");
				System.out.println("update : 게시물 수정");
				System.out.println("delete : 게시물 삭제");
				System.out.println("search : 게시물 검색");
				System.out.println("exit : 프로그램 종료");
			}

			if (cmd.equals("add")) {

				Article article = new Article();
				article.id = lastArticleId;
				lastArticleId++;
				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();
				article.title = title;
				
				article.regDate = Util.getCurrentDate(); //util패키지의 날짜구하기 불러오기
					

				System.out.println("내용을 입력해주세요");
				String body = sc.nextLine();
				article.body = body;
				
				articles.add(article);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (cmd.equals("list")) {
				print_articles(articles);
			}
			if (cmd.equals("update")) {
				System.out.println("어떤 게시물을 수정하시겠습니까? : ");
				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);
				
				if (targetArticle != null) {
					
					System.out.println("수정할 제목을 입력해주세요 : ");
					String updated_title = sc.nextLine();
					targetArticle.title = updated_title;
					System.out.println("수정할 내용을 입력해주세요 : ");
					String updated_body = sc.nextLine();
					targetArticle.body = updated_body;
					
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
			if(cmd.equals("search")) {
				System.out.println("검색항목을 선택해주세요: 1.제목, 2.내용");
				int searchFlag=Integer.parseInt(sc.nextLine());
				System.out.println("검색어를 입력해주세요");
				String keyword = sc.nextLine();				
				ArrayList<Article> searchedArticles = new ArrayList<>();
				
					for(int i=0; i<articles.size(); i++) {
						if(articles.get(i).getPropertyByType(searchFlag).contains(keyword)) { //getPropertyByType을 이용해 입력한숫자에따라 선택한 검색항목의 검색키워드 선별후 출력
							searchedArticles.add(articles.get(i));
						}
					}					
				print_articles(searchedArticles); //추가한게시물의 목록 조회
			}
			if(cmd.equals("detail")) {
				System.out.println("게시물 번호를 입력해주세요:");
				int articleId = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_id(articleId);
				
				if(article == null) {
					System.out.println("없는게시물입니다");
				}else {
					article.hit++;
					print_article(article);
					
					ArrayList<Reply> replies = get_replies_by_parent_id(articleId); //입력받은 번호에 해당하는 부모글의 댓글선별해서 replies에 대입
					print_replies(replies); //댓글리스트 출력
					
					while(true) {
						System.out.println("1.댓글, 2.좋아요, 3.수정, 4.삭제, 5.뒤로가기");
						int detailCmd= Integer.parseInt(sc.nextLine());
						if(detailCmd ==1) {
							int replyId = lastReplyId;
							lastReplyId++;
							
							System.out.println("댓글 내용을 입력해주세요:");
							String replyBody=sc.nextLine();
							String writer = "익명";
							String regDate = Util.getCurrentDate();
							
							Reply new_reply = new Reply(replyId, articleId, replyBody, writer,regDate);
							this.replies.add(new_reply); //댓글추가
							System.out.println("댓글이 성공적으로 등록되었습니다");
							
							print_article(article);
							ArrayList<Reply> replies2 =get_replies_by_parent_id(articleId);
							print_replies(replies2);
							
							
							
						}else if(detailCmd ==2) {
							System.out.println("1.좋아요 2.싫어요");
							int likeOrHate = Integer.parseInt(sc.nextLine());
							article.set_likes_and_hates("chacha1",likeOrHate);
							print_article(article);
							
							
							
						}else if(detailCmd ==5) {
							break;
						}
						
					}
				}
				
			}
		}
	}
	
	
	public ArrayList<Reply> get_replies_by_parent_id(int parent_id){ //부모글에 해당하는 댓글 선별
		ArrayList<Reply> result = new ArrayList<Reply>();
		
		for(int i=0; i<this.replies.size(); i++) { 
			if(this.replies.get(i).parent_id == parent_id) {
				result.add(this.replies.get(i));
				
			}
		}
		
		return result;
		
	}
	
	
	
	public void print_replies(ArrayList<Reply> replies) { //댓글리스트 받아서 출력하는 메서드
		System.out.println("=========댓글=========");
		for(int i=0; i <replies.size(); i++) {
			System.out.println("내용 : "+replies.get(i).body);
			System.out.println("작성자 : "+replies.get(i).writer);
			System.out.println("내용 : "+replies.get(i).regDate);
		}
	}
	
	
	
	
	public void print_article(Article article) { //게시물 상세보기 메소드(detail)
		System.out.println("--------게시물 상세--------");
		System.out.println("번호 : " + article.id);
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("조회수 : " + article.hit);
		
		HashMap<String, Integer> resultMap = article.get_likes_and_hates();		
		System.out.println("좋아요 : " + resultMap.get("like"));
		System.out.println("싫어요 : " + resultMap.get("hate"));
		
	}
	
	
	
	public void print_articles(ArrayList<Article> articles) { //게시물 목록 조회하기 메소드 (search,list 에서 사용)
		System.out.println("======== 게시물 목록 ========");
		for (int i = 0; i < articles.size(); i++) {
		System.out.println("번호 : " + articles.get(i).id);
		System.out.println("제목 : " + articles.get(i).title); 
		System.out.println("내용 : " + articles.get(i).body); 
		String str=articles.get(i).regDate;
		String[]arr=str.split(" "); //공백기준으로 문자열 쪼개기
		System.out.println("작성일 : " + arr[0]);
		System.out.println("조회수 :"+articles.get(i).hit);
		
	}
	}
	
	
	
	public Article get_article_by_id(int id) { //입력받은 번호의 게시물 리턴
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
	
	
	
	public void make_Test_Data() { //테스트 데이터 메서드
				HashMap<String, Integer> likes1 = new HashMap<>();
				likes1.put("chacha1", 1);
				likes1.put("chacha2", 2);
				likes1.put("chacha3", 1);
				
				HashMap<String, Integer> likes2 = new HashMap<>();
				likes2.put("chacha1", 2);
				likes2.put("chacha2", 2);
				likes2.put("chacha3", 2);
				
				HashMap<String, Integer> likes3 = new HashMap<>();
				likes3.put("chacha1", 1);
				likes3.put("chacha2", 1);
				likes3.put("chacha3", 1);
		
				Article article1 = new Article(); 
				article1.id=1;
				article1.title="테스트 데이터 제목1";
				article1.body="테스트 데이터 내용1";
				article1.regDate=Util.getCurrentDate();
				article1.hit=20;
				article1.likesAndHates = likes1;
				
				//생성자오버로딩으로 객체생성
				Article article2 = new Article(2,"제목2","내용2",Util.getCurrentDate(),30,likes2);
				Article article3 = new Article(3,"제목3","내용3",Util.getCurrentDate(),5,likes3);
				 //게시판에 테스트데이터 추가
				articles.add(article1);
				articles.add(article2);
				articles.add(article3);
				
				lastArticleId = 4;
				
				Reply r1 = new Reply(1, 1, "댓글1", "작성자1", Util.getCurrentDate());;
				Reply r2 = new Reply(2, 1, "댓글2", "작성자2", Util.getCurrentDate());;
				Reply r3 = new Reply(3, 2, "댓글3", "작성자3", Util.getCurrentDate());;
				
				
				
				replies.add(r1);
				replies.add(r2);
				replies.add(r3);
				
				lastReplyId = 4;
	}
	
	


	}


