package bookingmanager;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import location.LocationDAOInterface;

public class BookingListController {

	private BookingListDAOInterface booklistInterface;
	
	 public BookingListController(BookingListDAOInterface dao) {

		 super();
		 this.booklistInterface = dao;
	 }

	 
	 public String fetchBookingList(String locationid){
		 
		 
		 List<BookinglistObject> list = booklistInterface.getBookingList(locationid);
		 
		 if(list != null){
			 
			 JSONArray jsonarray = new JSONArray();
			 
			 for(int i = 0; i<list.size(); i++){
				 
				 BookinglistObject obj = list.get(i);
				 				 
				 JSONObject jsonobject = new JSONObject();
					jsonobject.put("bookingtoken", obj.getBookingtoken());
					jsonobject.put("userid", obj.getUserid());
					jsonobject.put("locationid", obj.getLocationid());
					jsonobject.put("status", obj.getStatus());
					jsonobject.put("usermobilenumber", obj.getUsermobilenumber());
					
				 jsonarray.put(jsonobject);
								 
				 
			 }
			 
			 return jsonarray.toString();
			 
			 
		 }
		 
		return "NO_CURRENT_BOOKINGS";
		 
	 }
	 
	 
	 public String answerValidateRequest(String bookingtoken){
		 
		 if(booklistInterface.validateBooking(bookingtoken)){
			 
				return "VALIDATE_SUCCESS"; 
			 
		 }
		 else {

				return "VALIDATE_FAIL";

		}
		 
		 
	 }
	 
	 
		
}
