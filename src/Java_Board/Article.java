package Java_Board;

import java.util.HashMap;

class Article {
	
	int id;
	String title;
	String body;
	String regDate;
	int hit; //조회수
	HashMap<String , Integer> likesAndHates; //1. 좋아요 2. 싫어요
	
	
	//기본
	Article(){
		
	}
	//생성자 오버로딩
	Article(int id, String title, String body, String regDate, int hit, HashMap<String, Integer> likesAndHates){
		this.id=id;
		this.title=title;
		this.body=body;
		this.regDate=regDate;
		this.hit=hit; 
		this.likesAndHates = likesAndHates;
		
	}
	
	void set_likes_and_hates(String userId, int likeOrHate) {
		if(likesAndHates == null) {
			likesAndHates=new HashMap<String, Integer>();			
		}
//		if(likesAndHates.get(userId)==null) {
//			
//		}
		if(likesAndHates.containsKey(userId)) {
			if(likesAndHates.get(userId) == likeOrHate) {
				likesAndHates.remove(userId);
			}else {
				likesAndHates.put(userId, likeOrHate);
			}
		}else {
			likesAndHates.put(userId, likeOrHate);
		}
		
	}
	
	HashMap<String, Integer>get_likes_and_hates(){
		int likeCnt = 0;
		int hateCnt = 0;
		
		for(int value : likesAndHates.values()) {
			if(value == 1) {
				likeCnt++;
			}
			
		}
		hateCnt= likesAndHates.size() - likeCnt;
		
		HashMap<String, Integer> resultMap =new HashMap<>();
		resultMap.put("like", likeCnt);
		resultMap.put("hate", hateCnt);
		
		return resultMap;
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