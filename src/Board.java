import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		//Stirng store = null; // "" -> 없는 데이터, null
		//자바배열->데이터타입, 길이
		
		ArrayList<Article> articles = new ArrayList<Article>();
		int id=1;
		
		while(true) {
			
			System.out.print("명령어를 입력해주세요 : ");
			cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			if(cmd.equals("help")) {
				System.out.println("add : 데이터 저장");
				System.out.println("read : 데이터 조회");
				System.out.println("update : 데이터 수정");
				System.out.println("delete : 데이터 삭제");
			}
			if(cmd.equals("add")) {
				
				Article article = new Article();
				article.id=id;
				id++;
				
				
				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();
				titles.add(title);				
				article.title=title;
				
 				System.out.println("내용을 입력해주세요");
				String body=sc.nextLine();
				bodies.add(body);
				article.body=body;
				System.out.println("작성자를 입력해주세요");
				String writer=sc.nextLine();
				writers.add(writer);
				
				System.out.println("게시물이 등록되었습니다");
			}
			if(cmd.equals("read")) {//배열에 있는 게시물들 목록화
				System.out.println("========게시물 목록========");
				for(int i=0; i<titles.size(); i++) { //배열.length ->lastIndex로 변경
					System.out.println("번호 : "+ids.get(i));
					System.out.println("제목 : "+titles.get(i));
					System.out.println("내용 : "+bodies.get(i)+"\n");
				}
			}
			if(cmd.equals("update")) {
				System.out.println("어떤 게시물을 수정하시겠습니까? : ");
				int targetIndex = sc.nextInt();
				sc.nextLine();//줄바꿈
				System.out.println("수정할 제목을 입력해주세요 : ");
				String updated_title = sc.nextLine();
				titles.set(targetIndex-1, updated_title);
			}
			if(cmd.equals("delete")) {//입력받은 번호에 해당하는 게시물 인덱스 찾기
				
					System.out.println("어떤 게시물을 삭제하시겠습니까? :");
					int targetNo = Integer.parseInt(sc.nextLine());
				int targetIndex = -1; //index가 0부터 시작하기때문에
				
				for(int i=0;  i< articles.size(); i++) {
					if(articles.get(i)==targetNo) {
						targetIndex=i;
						break;
						
					}
				}
				
				if(targetIndex != -1) {
				
				ids.remove(targetIndex);
				titles.remove(targetIndex);
				bodies.remove(targetIndex);
				writers.remove(targetIndex);
				}else {
					System.out.println("올바른 게시물 번호가 아닙니다");
				}
			}
		}
		
	}

}
//DataClass. DTO
class Article{
	int id;
	String title;
	String body;
	String writer;
	
	
}