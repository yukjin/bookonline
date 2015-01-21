package store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bookOnline.BookInfo;
import bookOnline.Rank;

public class DBServiceImpl implements DBService
{
	private ConnectionPool pool;//连接池
	public static final String SELECT_ALL_BY_CATEGORY="select*from bookinfo where booktype=?";
	public static final String SELECT_BY_LIMIT="select*from bookinfo where booktype=? limit ?,?";
	public static final String SELECT_BY_RELATED="select bookname,bookintro,bookpic from bookinfo where booktype=? and bookname!=? limit 0,6";
	public static final String SELECT_ALL_NEW_IN_ONE_DAY="select*from bookinfo WHERE DATEDIFF(addtime,NOW())=0";
	public static final String SELECT_ALL_HOTBOOKS="select*from bookrank,bookinfo where bookrank.bookinfoid=bookinfo.bookid and bookrank.bookrank>3.5 order by bookrank desc";
	public static final String AUTO_COMPLETE="select bookname,bookintro,bookpic from bookinfo,bookrank where bookinfo.bookid=bookrank.bookinfoid and bookname like ? order by bookrank desc";
	public DBServiceImpl()throws Exception
	{
		pool=new ConnectionPoolImpl();
	}
	//创建并返回一个Statement
	public Statement getStatement() throws SQLException {

		
		return pool.getConnection().createStatement();
	}

	//关闭Statement对象以及与之关联的Connection对象
	public void closeStatement(Statement stmt) 
	{
		try{
			if(stmt!=null)
			{
				Connection con=stmt.getConnection();
				stmt.close();
				con.close();//调用代理对象，将con对象放回连接池中
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}


	}
	public BookInfo queryByBookName(String bookname) throws SQLException
	{
		String sql="select*from bookrank,bookinfo where bookrank.bookinfoid=bookinfo.bookid and bookname='"+bookname+"'";
		Statement stmt=getStatement();
		ResultSet rs=stmt.executeQuery(sql);
		BookInfo bookinfo=new BookInfo();
		while(rs.next())
		{
			bookinfo.setBookid(rs.getString("bookid"));
			bookinfo.setBookname(rs.getString("bookname"));
			bookinfo.setBookconcern(rs.getString("bookconcern"));
			bookinfo.setPublishtime(rs.getDate("publishtime"));
			bookinfo.setBookauthor(rs.getString("bookauthor"));
			bookinfo.setAuthorintro(rs.getString("authorintro")==null?"暂无":rs.getString("authorintro"));
			bookinfo.setBookprice(rs.getDouble("bookprice"));
			bookinfo.setBookpic(rs.getString("bookpic"));
			bookinfo.setBookintro(rs.getString("bookintro"));
			bookinfo.setBooktype(rs.getString("booktype"));
			bookinfo.setBooknum(rs.getInt("booknum"));
			bookinfo.setRank(new Rank(rs.getInt("bookrank"),rs.getInt("peopleNumber")));//该书的评价分数
		}
		rs.close();
		return bookinfo;
	}
	public BookInfo[] queryBookInfoByPageAndType(int page, String type,int pageSize)
			throws SQLException {
		
		PreparedStatement pstmt=pool.getConnection().prepareStatement(SELECT_BY_LIMIT);
		pstmt.setString(1,type);
		int offset=(page-1)*pageSize;//计算每页的起始位置
		pstmt.setInt(2, offset);//mysql limit 0开始
		pstmt.setInt(3, pageSize);
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		int booknum=rs.getRow();
		rs.beforeFirst();
		BookInfo[]bookinfos=new BookInfo[booknum];
		int i=0;
		while(rs.next())
		{
			bookinfos[i]=new BookInfo();
			bookinfos[i].setBookname(rs.getString("bookname"));
			bookinfos[i].setBookintro(rs.getString("bookintro"));
			bookinfos[i].setBookpic(rs.getString("bookpic"));	
			bookinfos[i].setBooktype(rs.getString("booktype"));
			bookinfos[i].setBooktype(rs.getString("bookid"));
			i++;
		}
		return bookinfos;
	}
	public int getTotalNumberByCategory(String type) throws SQLException {
		

		PreparedStatement pstmt=pool.getConnection().prepareStatement(SELECT_ALL_BY_CATEGORY);
		pstmt.setString(1,type);
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		return rs.getRow();
	}
	public BookInfo[] getRelatedBook(String bookname,String booktype) throws SQLException {
		
		PreparedStatement pstmt=pool.getConnection().prepareStatement(SELECT_BY_RELATED);
		pstmt.setString(1,booktype);
		pstmt.setString(2,bookname);
		ResultSet rs=pstmt.executeQuery();
		if(!rs.next())
		{
			return null;
		}
		rs.last();
		int booknum=rs.getRow();
		rs.beforeFirst();
		BookInfo[]bookinfos=new BookInfo[booknum];
		int i=0;
		while(rs.next())
		{
			bookinfos[i]=new BookInfo();
			bookinfos[i].setBookname(rs.getString("bookname"));
			bookinfos[i].setBookintro(rs.getString("bookintro"));
			bookinfos[i].setBookpic(rs.getString("bookpic"));	
			i++;
		}
		return bookinfos;
	}
	public BookInfo[] getNewBooks(int page,int pageSize) throws SQLException {
		String sql=SELECT_ALL_NEW_IN_ONE_DAY+" limit ?,?";
		PreparedStatement pstmt=pool.getConnection().prepareStatement(sql);
		int offset=(page-1)*pageSize;//计算每页的起始位置
		pstmt.setInt(1, offset);//mysql limit 0开始
		pstmt.setInt(2, pageSize);
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		int booknum=rs.getRow();
		rs.beforeFirst();
		BookInfo[]bookinfos=new BookInfo[booknum];
		int i=0;
		while(rs.next())
		{
			bookinfos[i]=new BookInfo();
			bookinfos[i].setBookname(rs.getString("bookname"));
			bookinfos[i].setBookintro(rs.getString("bookintro"));
			bookinfos[i].setBookpic(rs.getString("bookpic"));	
			i++;
		}
		return bookinfos;
	}
	public int getTotalNumberByNewBooksInOneDay() throws SQLException {
		PreparedStatement pstmt=pool.getConnection().prepareStatement(SELECT_ALL_NEW_IN_ONE_DAY);
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		return rs.getRow();
	}
	public BookInfo[] getHotBooks(int page, int pageSize) throws SQLException {
		PreparedStatement pstmt=pool.getConnection().prepareStatement(SELECT_ALL_HOTBOOKS+" limit ?,?");
		int offset=(page-1)*pageSize;//计算每页的起始位置
		pstmt.setInt(1, offset);//mysql limit 0开始
		pstmt.setInt(2, pageSize);
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		int booknum=rs.getRow();
		rs.beforeFirst();
		BookInfo[]bookinfos=new BookInfo[booknum];
		int i=0;
		while(rs.next())
		{
			bookinfos[i]=new BookInfo();
			bookinfos[i].setBookname(rs.getString("bookname"));
			bookinfos[i].setBookintro(rs.getString("bookintro"));
			bookinfos[i].setBookpic(rs.getString("bookpic"));	
			i++;
		}
		return bookinfos;
	}
	public int getTotalNumberByHot() throws SQLException {
		PreparedStatement pstmt=pool.getConnection().prepareStatement(SELECT_ALL_HOTBOOKS);//超过3.5分为隆重推荐
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		return rs.getRow();
	}
	public boolean scoreToBook(String bookId,int bookRank) throws SQLException {
		String sql="select bookrankid,bookrank,peopleNumber from bookrank,bookinfo where bookrank.bookinfoid=bookinfo.bookid and bookid='"+bookId+"'";
		Statement stmt=pool.getConnection().createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			int people=rs.getInt("peopleNumber")+1;
			int bookrankid=rs.getInt("bookrankid");
			double bookrank=(bookRank+rs.getDouble("bookrank")*rs.getInt("peopleNumber"))/people;
			bookrank=(double)Math.round(bookrank*10)/10;


			rs.close();
			stmt.close();
			String updateSql="UPDATE bookstoreonline.bookrank SET bookrank="+bookrank+", peopleNumber="+people+" WHERE bookrankid="+bookrankid;
			Statement st=pool.getConnection().createStatement();
			int result=st.executeUpdate(updateSql);
			if(result==1)
			{
				return true;
			}
		}
		return false;
	}
	public String autoCompleteSearch(String bookname) throws SQLException {
		
		
		PreparedStatement pstmt=pool.getConnection().prepareStatement(AUTO_COMPLETE);
		pstmt.setString(1,"%"+bookname+"%");
		ResultSet rs=pstmt.executeQuery();
		String result="[";
		while(rs.next())
		{
				result+="'"+rs.getString("bookname")+"',";
		}
		result=result.substring(0,result.length()-1);
		result+="]";
		return result;
	}
	public BookInfo[] autoCompleteSearch(String bookname, int page, int pageSize)
			throws SQLException {
		PreparedStatement pstmt=pool.getConnection().prepareStatement(AUTO_COMPLETE+" limit ?,?");
		pstmt.setString(1,"%"+bookname+"%");
		int offset=(page-1)*pageSize;//计算每页的起始位置
		pstmt.setInt(2, offset);//mysql limit 0开始
		pstmt.setInt(3, pageSize);
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		int booknum=rs.getRow();
		rs.beforeFirst();
		BookInfo[]bookinfos=new BookInfo[booknum];
		int i=0;
		while(rs.next())
		{
			bookinfos[i]=new BookInfo();
			bookinfos[i].setBookname(rs.getString("bookname"));
			bookinfos[i].setBookintro(rs.getString("bookintro"));
			bookinfos[i].setBookpic(rs.getString("bookpic"));	
			i++;
		}
		return bookinfos;
	}
	public int getTotalNumberByAutoSearch(String bookname) throws SQLException {
		
		PreparedStatement pstmt=pool.getConnection().prepareStatement(AUTO_COMPLETE);
		pstmt.setString(1,"%"+bookname+"%");
		ResultSet rs=pstmt.executeQuery();
		rs.last();
		return rs.getRow();
	}
	
}

