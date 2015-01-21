package bookOnline;

import java.util.Date;

public class Order 
{
	private String tradeNumber;//订单号
	private int tradeNum;//交易数量
	private int userId;
	private String bookId;
	private String tradeAddress;
	private double tradeMoney;
	private Date tradeTime;
	private int tradeState;
	public Order(){}
	public Order(String tradeAddress,String tradeNumber,int tradeNum,int userId,String bookId,double tradeMoney,Date tradeTime,int tradeState)
	{
		this.tradeNumber=tradeNumber;
		this.userId=userId;
		this.bookId=bookId;
		this.tradeMoney=tradeMoney;
		this.tradeTime=tradeTime;
		this.tradeState=tradeState;
		this.tradeAddress=tradeAddress;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	public int getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public int getTradeState() {
		return tradeState;
	}
	public void setTradeState(int tradeState) {
		this.tradeState = tradeState;
	}
	public String getTradeAddress() {
		return tradeAddress;
	}
	public void setTradeAddress(String tradeAddress) {
		this.tradeAddress = tradeAddress;
	}
	
}
