package bookOnline;

import java.sql.SQLException;

import store.DBService;

public class Pagination 
{
	public String bookType;
	public int pageSize;
	public int currPage;
	public DBService dbService;
	public Pagination()
	{
		
	}
	public Pagination(int pageSize,int currPage)
	{
		this.pageSize=pageSize;
		this.currPage=currPage;
	}
	public Pagination(String bookType,int pageSize,int currPage)
	{
		this.bookType=bookType;
		this.pageSize=pageSize;
		this.currPage=currPage;
	}
	public BookInfo[] getBookInfoByType() throws SQLException
	{
		return dbService.queryBookInfoByPageAndType(currPage, bookType, pageSize);
	}
	public BookInfo[] getBookInfoByNew() throws SQLException
	{
		return dbService.getNewBooks(currPage, pageSize);
	}
	public BookInfo[] getBookInfoByHot() throws SQLException
	{
		return dbService.getHotBooks(currPage, pageSize);
	}
	public int getPageNumByCategory() throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByCategory()/pageSize);
	}
	private int getTotalNumberByCategory() throws SQLException
	{
		return dbService.getTotalNumberByCategory(bookType);
	}
	/**新书页数*/
	public int getPageNumByNewBooks() throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByNewBooks()/pageSize);
	}
	/**获得一天以来的新书*/
	private int getTotalNumberByNewBooks()throws SQLException
	{
		return dbService.getTotalNumberByNewBooksInOneDay();
	}
	
	/**热门图书共有的页数*/
	public int getPageNumByHotBooks() throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByHotBooks()/pageSize);
	}
	/**获得所有热门图书*/
	private int getTotalNumberByHotBooks()throws SQLException
	{
		return dbService.getTotalNumberByHot();
	}
	/**搜索结果图书共有的页数*/
	public int getPageNumByAutoSearch(String bookname) throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByAutoSearch(bookname)/pageSize);
	}
	/**获得搜索结果数量*/
	private int getTotalNumberByAutoSearch(String bookname)throws SQLException
	{
		return dbService.getTotalNumberByAutoSearch(bookname);
	}
}
