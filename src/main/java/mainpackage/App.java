package mainpackage;

import static spark.Spark.*;

import java.io.FileInputStream;
import java.util.Timer;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

import bookingmanager.BookingListController;
import bookingmanager.BookingListDAOInterface;
import bookingmanager.BookinglistJDBCImpl;
import firebase.*;

import firebase.FCM;
import location.RequestController;
import location.RequestModel;
import location.SensorModel;
import notifyrequests.Checker;
import notifyrequests.NotifyController;
import notifyrequests.NotifyDAOInterface;
import notifyrequests.NotifyJDBCImpl;
import notifyrequests.NotifyModel;
import location.LocationDAOInterface;
import location.LocationJDBCImpl;
import sensor.Controller;
import sensor.DAO;
import sensor.JdbcDAO;
import sensor.SensorData;
import user.UserController;
import user.UserDAOInterface;
import user.UserJDBCImpl;
import user.UserModel;

public class App {
	
	// DAO for sensor
	private final static DAO dao = new JdbcDAO();
	private final static Controller controller = new Controller(dao);
	
	// DAO for user
	private final static UserDAOInterface userdao = new UserJDBCImpl();
	private final static UserController userController = new UserController(userdao);
	
	//DAO for location requests
	private final static LocationDAOInterface locationdao = new LocationJDBCImpl();
	private final static RequestController requestController = new RequestController(locationdao);
	
	//DAO for bookings list 
	private final static BookingListDAOInterface bookinglistdao = new BookinglistJDBCImpl();
	private final static BookingListController bookinglistController = new BookingListController(bookinglistdao);
	
	//DAO for notify requests
	private final static NotifyDAOInterface notifydao = new NotifyJDBCImpl();
	private final static NotifyController notifycontroller = new NotifyController(notifydao);


	

	

	
/*	static{
		
		try{
			
			FileInputStream serviceAccount =
					  new FileInputStream("E:\\Android\\SparkingParking\\iiitsparkpark-f3a09-firebase-adminsdk-mfxsu-921f53078f.json");

					FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
					  .setDatabaseUrl("https://iiitsparkpark-f3a09.firebaseio.com")
					  .build();

					FirebaseApp.initializeApp(options);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}		*/
	

    public static void main( String[] args ){
    	
    	
    	Timer timer = new Timer();
    	timer.schedule(new Checker(), 0,60000);

    	//...

        post("/pingu", ( req, res) -> { 
        	
         	
        	SensorModel receivedData = new SensorModel(req.headers("locationid"),req.headers("sensortype"));
        	
        	return requestController.receiveSensorPing(receivedData);
        	      	

        	//return "hello";
        	
        
        	
        } ); 
        
        
        get("/fcm", (req, res) -> {
        	
        	//Just I am passed dummy information
        	
        	

        	String tokenId = req.headers("fcmtoken");
        			
        			//"f-PXtSN-G8I:APA91bFTp_K0BkIoswdeH-qPt8C_Phg4a_o0yFtKfVl07CjovXk4255kV--MDREu6xUEhDKPGiH4PL7X1vNQIxHi-HyXMEJvoJSBqwnnuq3AcPCKQ9zx6uiRrkYKNORsara43G9urBhq";

        	String server_key = "AIzaSyBMUD-fK0j9NtUg6u_MsV-UdvWVDLZC9Ws" ;

        	String message = req.headers("message").toString();



        	//Method to send Push Notification

        	FCM.send_FCM_Notification( tokenId,message);

        	return " i am dones";
        	
        		
        });
        
        

        post("/registeryourid", ( req, res) -> { 
        	      	
        	UserModel userdata = new UserModel(req.headers("name"),req.headers("mob"),req.headers("uid"),req.headers("fcmid"));
        	
        	return userController.register(userdata);

        	//return "hello";
        	
        
        	
        } ); 
        
        

        post("/enquiry", ( req, res) -> { 
        	      	
        	RequestModel enquiry = new RequestModel(req.headers("locationid"),req.headers("uid"));
        	
        	return requestController.answerEnquiry(enquiry);

        	//return "hello";
        	
        
        	
        } ); 
        
        
        post("/bookslot", ( req, res) -> { 
	      	
        	RequestModel bookrequest = new RequestModel(req.headers("locationid"),req.headers("uid"));
        	
        	return requestController.answerBookingRequest(bookrequest);
        	
        	//return "hello";
    	     
        	
        } ); 
        
        
        post("/bookinglistforadmin", ( req, res) -> { 
        	
        	String locationid = req.headers("locationid");
	      	        	
        	return bookinglistController.fetchBookingList(locationid);
        	
        	//return "hello";
    	     
        	
        } ); 
        
        post("/validatebookingforadmin", ( req, res) -> { 
        	
        	String bookingtoken = req.headers("bookingtoken");
	      	        	
        	return bookinglistController.answerValidateRequest(bookingtoken);
        	
        	//return "hello";
    	     
        	
        } ); 
        
        post("/notifyrequest", ( req, res) -> { 
        	
        	NotifyModel notifyreq = new NotifyModel(req.headers("locationid"),req.headers("fcmid"),req.headers("uid"),req.headers("notifybefore"));
        	
        	return notifycontroller.answerNotifyrequest(notifyreq);
        	
        	//return "hello";
    	     
        	
        } ); 
        
        
        

    }

}
