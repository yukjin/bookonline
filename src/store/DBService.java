package store;

import java.sql.SQLException;
import java.sql.Statement;

import bookOnline.BookInfo;

public interface DBService 
{
	/**获得Statement对象*/
	public Statement getStatement()throws SQLException;
	/**关闭Statement对象以及与之关联的Connection对象*/
	public void closeStatement(Statement stmt);
	/**根据书名查找并返回代表一本书的bean*/
	public BookInfo queryByBookName(String bookname)throws SQLException;
	/**查找指定种类下指定页的图书信息*/
	public BookInfo[] queryBookInfoByPageAndType(int page, String type,int pageSize)throws SQLException;
	/**获得指定种类图书的数量*/
	public int getTotalNumberByCategory(String category)throws SQLException;
	/**获得相关图书*/
	public BookInfo[] getRelatedBook(String bookname,String booktype)throws SQLException; 
	/**获得最新的图书*/
	public BookInfo[] getNewBooks(int page,int pageSize) throws SQLException;
	/**获得今日新图书的数量*/
	public int getTotalNumberByNewBooksInOneDay() throws SQLException;
	/**获得热门图书*/
	public BookInfo[] getHotBooks(int page,int pageSize)throws SQLException;
	/**获得热门图书数量*/
	public int getTotalNumberByHot()throws SQLException;
	/**给图书打分*/
	public boolean scoreToBook(String bookid,int bookRank) throws SQLException;
	/**获得代表搜索结果书名的字符串*/
	public  String autoCompleteSearch(String bookname)throws SQLException;
	/**获得搜索图书*/
	public BookInfo[] autoCompleteSearch(String bookname,int page,int pageSize) throws SQLException;
	/**获得搜索图书数量*/
	public int getTotalNumberByAutoSearch(String bookname)throws SQLException;
}
