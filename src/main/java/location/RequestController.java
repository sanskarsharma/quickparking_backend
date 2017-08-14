package location;

import org.json.JSONObject;

import user.UserDAOInterface;

public class RequestController {

private LocationDAOInterface locationDaoInterface;
	
	 public RequestController(LocationDAOInterface dao) {

		 super();
		 this.locationDaoInterface = dao;
	 
	 }	
	 
	 
	 
	 public String answerEnquiry(RequestModel enquiry){
		 
		 EnquiryResponse response = locationDaoInterface.slotEnquiry(enquiry);
		 
		if(response.isStatus()){
			//true

			JSONObject jsonresponse = new JSONObject();
			jsonresponse.put("locationid", response.getLocationID());
			jsonresponse.put("totalcapacity", response.getTotalcapacity());
			jsonresponse.put("vacant", response.getVacant());
			jsonresponse.put("on_hold", response.getOn_hold());
			jsonresponse.put("occupied", response.getOccupied());
			jsonresponse.put("status", response.isStatus());
			
			return jsonresponse.toString();
			
			
		}
		else{
			//false
			

			JSONObject jsonresponse = new JSONObject();
			jsonresponse.put("locationid", "Could not fetch");
			jsonresponse.put("totalcapacity", "Could not fetch");
			jsonresponse.put("vacant", "Could not fetch");
			jsonresponse.put("on_hold", "Could not fetch");
			jsonresponse.put("occupied", "Could not fetch");

			jsonresponse.put("status", response.isStatus());
			
			return jsonresponse.toString();
			
			
			
		}
	
		
	 }
	 
	 
	 public String answerBookingRequest(RequestModel bookrequest){
		 
		 BookingResponse response = locationDaoInterface.slotBooking(bookrequest);
		 		 
		 if(response.getBookingstatus()){
			//true

				JSONObject jsonresponse = new JSONObject();
				jsonresponse.put("bookingstatus", response.getBookingstatus());
				jsonresponse.put("bookingtoken", response.getBookingToken());
					
				return jsonresponse.toString();
				
				
			 
		 }
		 else{
			 //false
			 
			 	JSONObject jsonresponse = new JSONObject();
				jsonresponse.put("bookingstatus", response.getBookingstatus());
				jsonresponse.put("bookingtoken", response.getBookingToken());
				
				return jsonresponse.toString();
				
				
			 
		 }
		
		 
	 }
	 
	 
	 public String receiveSensorPing(SensorModel sensorping){
		 
		 
		 if(locationDaoInterface.sensorPing(sensorping)){
			 
			 return "{\"message\":\"PING_SUCCESS\"}"; 

		 }
		 else{
			
			 return "{\"message\":\"PING_FAIL\"}";

		 }

	 }

	 
	
}
