package location;

import java.util.Random;

public class BookingTokenGenerator {
	
	public static String generateToken(RequestModel request){
		
		String token = request.getLocationid().substring(0, 2) + request.getUserid().substring(10, 14) + new Random().nextInt(100);
		
		return token;
			
	}	

}
