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
	/**����ҳ��*/
	public int getPageNumByNewBooks() throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByNewBooks()/pageSize);
	}
	/**���һ������������*/
	private int getTotalNumberByNewBooks()throws SQLException
	{
		return dbService.getTotalNumberByNewBooksInOneDay();
	}
	
	/**����ͼ�鹲�е�ҳ��*/
	public int getPageNumByHotBooks() throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByHotBooks()/pageSize);
	}
	/**�����������ͼ��*/
	private int getTotalNumberByHotBooks()throws SQLException
	{
		return dbService.getTotalNumberByHot();
	}
	/**�������ͼ�鹲�е�ҳ��*/
	public int getPageNumByAutoSearch(String bookname) throws SQLException
	{
		return (int) Math.ceil((double)getTotalNumberByAutoSearch(bookname)/pageSize);
	}
	/**��������������*/
	private int getTotalNumberByAutoSearch(String bookname)throws SQLException
	{
		return dbService.getTotalNumberByAutoSearch(bookname);
	}
}
