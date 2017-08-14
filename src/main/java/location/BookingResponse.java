package location;

public class BookingResponse {
	
	private boolean bookingstatus;
	private String bookingToken;
	
	public BookingResponse(boolean bookingstatus, String bookingToken) {
		super();
		this.bookingstatus = bookingstatus;
		this.bookingToken = bookingToken;
	}
	
	
	public BookingResponse() {
		// TODO Auto-generated constructor stub
	}

	

	public boolean getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(boolean bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	public String getBookingToken() {
		return bookingToken;
	}
	public void setBookingToken(String bookingToken) {
		this.bookingToken = bookingToken;
	}
	
	
	
}
