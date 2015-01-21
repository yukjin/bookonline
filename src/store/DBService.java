package store;

import java.sql.SQLException;
import java.sql.Statement;

import bookOnline.BookInfo;

public interface DBService 
{
	/**���Statement����*/
	public Statement getStatement()throws SQLException;
	/**�ر�Statement�����Լ���֮������Connection����*/
	public void closeStatement(Statement stmt);
	/**�����������Ҳ����ش���һ�����bean*/
	public BookInfo queryByBookName(String bookname)throws SQLException;
	/**����ָ��������ָ��ҳ��ͼ����Ϣ*/
	public BookInfo[] queryBookInfoByPageAndType(int page, String type,int pageSize)throws SQLException;
	/**���ָ������ͼ�������*/
	public int getTotalNumberByCategory(String category)throws SQLException;
	/**������ͼ��*/
	public BookInfo[] getRelatedBook(String bookname,String booktype)throws SQLException; 
	/**������µ�ͼ��*/
	public BookInfo[] getNewBooks(int page,int pageSize) throws SQLException;
	/**��ý�����ͼ�������*/
	public int getTotalNumberByNewBooksInOneDay() throws SQLException;
	/**�������ͼ��*/
	public BookInfo[] getHotBooks(int page,int pageSize)throws SQLException;
	/**�������ͼ������*/
	public int getTotalNumberByHot()throws SQLException;
	/**��ͼ����*/
	public boolean scoreToBook(String bookid,int bookRank) throws SQLException;
	/**��ô�����������������ַ���*/
	public  String autoCompleteSearch(String bookname)throws SQLException;
	/**�������ͼ��*/
	public BookInfo[] autoCompleteSearch(String bookname,int page,int pageSize) throws SQLException;
	/**�������ͼ������*/
	public int getTotalNumberByAutoSearch(String bookname)throws SQLException;
}
