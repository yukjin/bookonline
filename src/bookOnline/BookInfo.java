package bookOnline;

import java.util.Date;

public class BookInfo
{
	private String bookid;
	private String bookname;
	private String bookconcern;
	private Date publishtime;
	private String bookauthor;
	private String authorintro;
	private double bookprice;
	private String bookpic;
	private String bookintro;
	private String booktype;
	private int booknum;
	private Rank rank;
	
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBookconcern() {
		return bookconcern;
	}
	public void setBookconcern(String bookconcern) {
		this.bookconcern = bookconcern;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getAuthorintro() {
		return authorintro;
	}
	public void setAuthorintro(String authorintro) {
		this.authorintro = authorintro;
	}
	public double getBookprice() {
		return bookprice;
	}
	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}
	public String getBookpic() {
		return bookpic;
	}
	public void setBookpic(String bookpic) {
		this.bookpic = bookpic;
	}
	public String getBookintro() {
		return bookintro;
	}
	public void setBookintro(String bookintro) {
		this.bookintro = bookintro;
	}
	public String getBooktype() {
		return booktype;
	}
	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	public Rank getRank() {
		return rank;
	}
	public int hashCode()
	{
		return bookid.hashCode();
	}
	public boolean equals(Object book)
	{
		return this.bookid.equals(((BookInfo)book).bookid);
	}
}
