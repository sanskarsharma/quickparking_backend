package bookingmanager;

import java.util.List;

public interface BookingListDAOInterface {
	
	public List<BookinglistObject> getBookingList(String locationid);
	
	public boolean validateBooking(String bookingtoken);
	

}
