
public class Borrows {
	
	private Member m;
	private Book n;

	Borrows(Member m, Book n){
		this.m = m;
		this.n = n;
	}
	
	Member getMember() {
		return this.m;
	}
	
	Book getBorrowed() {
		return this.n;
	}
}
