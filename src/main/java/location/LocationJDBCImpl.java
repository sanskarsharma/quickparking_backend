package location;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import user.UserModel;

public class LocationJDBCImpl implements LocationDAOInterface{

	private static MysqlDataSource dataSource;

    static {

        try {

        	dataSource = new MysqlDataSource();

        	dataSource.setUser("root");

        	dataSource.setPassword("mysql.2996");

        	dataSource.setServerName("localhost");

        	dataSource.setDatabaseName("sparkdb");

        } catch (Exception e) {

            throw new ExceptionInInitializerError(e);

        }

    }


	
	
	@Override
	public EnquiryResponse slotEnquiry(RequestModel enquiry) {

    	QueryRunner run = new QueryRunner( dataSource );
    	
    	ResultSetHandler<EnquiryResponse> result = new BeanHandler<EnquiryResponse>(EnquiryResponse.class);
    	
    	EnquiryResponse response = null;
    	
    	try {
			 response = run.query("SELECT locationid, totalcapacity, vacant, on_hold, occupied FROM locationtable WHERE locationid = ?", result, enquiry.getLocationid());
			 response.setStatus(true);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(false);
		}

		return response ;    	
		
	}




	@Override
	public BookingResponse slotBooking(RequestModel bookrequest) {
		
		QueryRunner run = new QueryRunner( dataSource );
		
		ResultSetHandler<UserModel> rshUser = new BeanHandler<UserModel>(UserModel.class){

			@Override
			public UserModel handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				if(rs.next()){
					UserModel userkamodelbro = new UserModel(rs.getString("name"), rs.getString("mobile_num"), rs.getString("user_id"), rs.getString("fcm_reg_id"));
					return userkamodelbro;

				}
				return null;
				
			}
			
		};
		
		UserModel userknumberklie= null;
		
		BookingResponse response  = new BookingResponse();
    	    	    	
    	try {
    		
			 String token = BookingTokenGenerator.generateToken(bookrequest);
			 
			 userknumberklie = run.query("SELECT name, mobile_num, user_id, fcm_reg_id FROM useridnotifid WHERE user_id = ? ", rshUser,bookrequest.getUserid());
			 
			 String usermobilenumber = userknumberklie.getMobileNumber();
			 
			// Logger logger = LoggerFactory.getLogger(LocationJDBCImpl.class);
			   // logger.trace("Hello World");
			    //logger.debug("Hello World");
			   // logger.info(userknumberklie.getMobileNumber()+"   "+userknumberklie.getUserID()+ "    "+ userknumberklie.getFcmID());
			    //logger.warn("Hello World");
			    //logger.error("Hello World");
			 
			 int insertBookingTable = run.update( "INSERT INTO bookingstable (bookingtoken, user_id, locationid, bookingstatus, usermobilenumber) VALUES (?,?,?,?,?)", token, bookrequest.getUserid(), bookrequest.getLocationid(), true, usermobilenumber);
		 
			 if(insertBookingTable==1){
				 
				 int updatequery = run.update("UPDATE locationtable SET on_hold = on_hold + 1, vacant = vacant - 1 WHERE locationid = ?", bookrequest.getLocationid());

				
				 if(updatequery==1){
					 					 
					 String eventname = token;
					 int createevent = run.update("CREATE EVENT IF NOT EXISTS "+token+" ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE DO BEGIN UPDATE locationtable SET on_hold = on_hold - 1, vacant = vacant + 1 WHERE locationid = ? ;  DELETE FROM bookingstable WHERE bookingtoken = ? ; END", bookrequest.getLocationid(),token);
					 
					 response.setBookingstatus(true);
					 response.setBookingToken(token);
				 
				 }
				 
			 }
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
			response.setBookingstatus(false);
			response.setBookingToken("BOOKING_FAILED");
		}

		return response ;  
		
		
		
		
	}




	@Override
	public boolean sensorPing(SensorModel data) {
		// TODO Auto-generated method stub
		
		QueryRunner run = new QueryRunner( dataSource );

		try	{

			if(data.getSensortype().equals("entry")){
				
			    int entry = run.update("UPDATE locationtable SET occupied = occupied + 1, on_hold = on_hold - 1 WHERE locationid = ?", data.getLocationid());

			}
			else if(data.getSensortype().equals("exit")){
				
			    int exit = run.update("UPDATE locationtable SET occupied = occupied - 1, vacant = vacant + 1 WHERE locationid = ?", data.getLocationid());
				
			}
			
		} catch(SQLException sqle) {

		    throw new RuntimeException("Problem updating SENSORPING", sqle);
		    
		   // return false;

		}

		return true ;    	
		
	}

}
