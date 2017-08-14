package location;

public interface LocationDAOInterface {

	public EnquiryResponse slotEnquiry(RequestModel enquiry);
	
	public BookingResponse slotBooking(RequestModel bookrequest);
	
	public boolean sensorPing(SensorModel sensor);
	
}
