package bookOnline;

public class Rank 
{
	private int bookRankId;
	private int bookRank;
	private int pepopleNumber;
	public Rank(int bookRank,int peopleNumber)
	{
		this.bookRank=bookRank;
		this.pepopleNumber=peopleNumber;
	}
	public int getBookRankId() {
		return bookRankId;
	}
	public void setBookRankId(int bookRankId) {
		this.bookRankId = bookRankId;
	}
	public int getBookRank() {
		return bookRank;
	}
	public void setBookRank(int bookRank) {
		this.bookRank = bookRank;
	}
	public int getPepopleNumber() {
		return pepopleNumber;
	}
	public void setPepopleNumber(int pepopleNumber) {
		this.pepopleNumber = pepopleNumber;
	}
	
}
