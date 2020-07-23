package Java_Board;

class Article {
	
	int id;
	String title;
	String body;
	String regDate;
	int hit; //조회수
	
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
	
	String getPropertyByType(int type) {
		String rst ="";
		
		if(type ==1) {
			rst=this.title;
		}else {
			rst=this.body;
			
		}
		return rst;
	}
}