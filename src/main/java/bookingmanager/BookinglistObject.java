package bookingmanager;

public class BookinglistObject {
	
	private String bookingtoken;
	private String user_id;
	private String locationid;
	private boolean bookingstatus;
	private String usermobilenumber;
	
	
	
	
	
	public BookinglistObject(String bookingtoken, String user_id, String locationid, boolean bookingstatus,
			String usermobilenumber) {
		super();
		this.bookingtoken = bookingtoken;
		this.user_id = user_id;
		this.locationid = locationid;
		this.bookingstatus = bookingstatus;
		this.usermobilenumber = usermobilenumber;
	}
	public String getUsermobilenumber() {
		return usermobilenumber;
	}
	public void setUsermobilenumber(String usermobilenumber) {
		this.usermobilenumber = usermobilenumber;
	}
	public String getBookingtoken() {
		return bookingtoken;
	}
	public void setBookingtoken(String bookingtoken) {
		this.bookingtoken = bookingtoken;
	}
	public String getUserid() {
		return user_id;
	}
	public void setUserid(String userid) {
		this.user_id = userid;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public boolean getStatus() {
		return bookingstatus;
	}
	public void setStatus(boolean status) {
		this.bookingstatus = status;
	}
	
	

}
