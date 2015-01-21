package bookOnline;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class Cart 
{
	private User user; //用户的标识
	private HashMap<BookInfo, Integer> books; //购物车中的物品
	public Cart(){
		books=new HashMap<BookInfo, Integer>();
	}
	public int getTotalNumber()
	{
		Set<Entry<BookInfo,Integer>> bookSet=books.entrySet();
		Iterator<Entry<BookInfo,Integer>> iter=bookSet.iterator();
		double totalNumber=0;
		Entry<BookInfo,Integer> entry;
		while(iter.hasNext())
		{
			entry=iter.next();
			totalNumber+=entry.getValue();
		}
		return (int) totalNumber;
	}
	public BookInfo[] toBookInfoArray()
	{
		Set<BookInfo> bookSet=books.keySet();
		Iterator<BookInfo> iter=bookSet.iterator();
		BookInfo[] bookList=new BookInfo[bookSet.size()];
		int i=0;
		while(iter.hasNext())
		{
			bookList[i]=iter.next();
			i++;
		}
		return bookList;
	}
	public double getTotalPrice()
	{
		Set<Entry<BookInfo,Integer>> bookSet=books.entrySet();
		Iterator<Entry<BookInfo,Integer>> iter=bookSet.iterator();
		double totalPrice=0;
		Entry<BookInfo,Integer> entry;
		while(iter.hasNext())
		{
			entry=iter.next();
			totalPrice+=entry.getKey().getBookprice()*entry.getValue();
		}
		return totalPrice;
	}
	public void addBook(BookInfo book,int quantity){
		books.put(book, new Integer(quantity));
	}
	public void removeBook(BookInfo book){
		books.remove(book);
	}
	public void updateBook(BookInfo book,int quantity){
		if(books.containsKey(book))
		{
			books.remove(book);
		}
		books.put(book, new Integer(quantity));
	}
	public HashMap getBooks(){
		return this.books;
	}
	public void setUser(User user){
		this.user=user;
	}
	public User getUser(){
		return this.user;
	}
	public void clear(){
		books.clear();
	}
}
