package bookingmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import user.UserModel;

public class BookinglistJDBCImpl implements BookingListDAOInterface {
	

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
	public List<BookinglistObject> getBookingList(String locationid) {

		QueryRunner queryRunner = new QueryRunner(dataSource);
		
		List<BookinglistObject> thelist = new ArrayList<BookinglistObject>();

		
		ResultSetHandler<List<BookinglistObject>> resultSetHandler = new BeanListHandler<BookinglistObject>(BookinglistObject.class){


			@Override
			public List<BookinglistObject> handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				
				if(rs.next()){
					BookinglistObject obj = new BookinglistObject(rs.getString("bookingtoken"), rs.getString("user_id"), rs.getString("locationid"),rs.getBoolean("bookingstatus"),rs.getString("usermobilenumber"));
					thelist.add(obj);
				}
				
				return thelist;
				
			}
			
		};
		
		List<BookinglistObject> bookinglist = null;
		
		try {
			
			 bookinglist = queryRunner.query("SELECT bookingtoken, user_id, locationid, bookingstatus, usermobilenumber FROM bookingstable WHERE locationid = ?", resultSetHandler,locationid);
		
		} catch (SQLException e) {

			e.printStackTrace();
			bookinglist=null;
		}
		
		return bookinglist;
	}



	@Override
	public boolean validateBooking(String bookingtoken) {

		QueryRunner run = new QueryRunner( dataSource );

		try	{

			int deleteevent = run.update(" DROP EVENT IF EXISTS "+ bookingtoken);
			
				
			int deletebooking = run.update( " DELETE FROM bookingstable WHERE bookingtoken = ? ", bookingtoken);

			

		} catch(SQLException sqle) {

		    throw new RuntimeException("Problem deleting entry from BOOKINGSTABLE", sqle);
		    
		    //return false;

		}

		return true ;    	
		
		
	}
    
    
    
    
    
    
    

}
