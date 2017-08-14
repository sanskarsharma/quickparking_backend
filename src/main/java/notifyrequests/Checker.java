package notifyrequests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import bookingmanager.BookinglistObject;
import firebase.FCM;
import user.UserModel;

public class Checker extends TimerTask{
	
	
	private static MysqlDataSource dataSource;
	
	private QueryRunner run = new QueryRunner( dataSource );
	
	public static List<String> locations ;
	
	
	
	
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
	public void run() {

    
    	try {
    		
			Checker.locations = run.query("SELECT locationid FROM locationtable WHERE vacant !=0 ",
			        new ColumnListHandler<String>(1));
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
    	
    	
		List<NotifyModel> thelist = new ArrayList<NotifyModel>();

    	ResultSetHandler<List<NotifyModel>> rsh = new BeanListHandler<NotifyModel>(NotifyModel.class){

			@Override
			public List<NotifyModel> handle(ResultSet rs) throws SQLException {

				if(rs.next()){
					NotifyModel notifymodelbro = new NotifyModel(rs.getString("locationid"), rs.getString("fcm_reg_id"), rs.getString("userid"), rs.getString("notifybefore"));
					thelist.add(notifymodelbro);

				}
				return thelist;
				
			}
			
		};
    	
		
    	
    	for(String loc : locations){
    		
    		List<NotifyModel> reallist = null;

			Logger logger = LoggerFactory.getLogger(Checker.class);
    		
    		try {
    			
				reallist = run.query("Select * from notifyrequests WHERE locationid = ? ", rsh, loc);
				logger.info(loc + "1");

				
				for(NotifyModel obj : reallist){
		    		logger.info(obj.getLocationid()+ "2");
		    		String message = obj.getLocationid().toUpperCase()+" just got an empty slot. Tap to book  ";
		    		
		    		FCM.send_FCM_Notification(obj.getFcm_reg_id(), message);
		    		
		    	}
		    		
				
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
    		
    		
    		
    		
    	}
    	
		
		
		
	}
	
	
	
}
