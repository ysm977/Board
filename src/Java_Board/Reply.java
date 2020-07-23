package Java_Board;

public class Reply {
	
	int id; //댓글 번호
	int parent_id; //부모글 번호
	String body; //내용
	String writer; //작성자
	String regDate; //작성일
	
	
	public Reply(int id, int parent_id, String body, String writer, String regDate) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
	}
	

}
