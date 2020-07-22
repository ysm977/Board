package Java_Board;

class Article {
	
	int id;
	String title;
	String body;
	String regDate;
	int hit;
	
	//기본
	Article(){
		
	}
	//생성자 오버로딩
	Article(int id, String title, String body, String regDate, int hit){
		this.id=id;
		this.title=title;
		this.body=body;
		this.regDate=regDate;
		this.hit=hit;
		
	}
}