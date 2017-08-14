package notifyrequests;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class NotifyJDBCImpl implements NotifyDAOInterface{
	
	
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
	public boolean addNotifyRequest(NotifyModel notifyrequest) {

    	QueryRunner run = new QueryRunner( dataSource );

		try	{
			
			int inserts = run.update( "INSERT INTO notifyrequests (locationid, fcm_reg_id, userid, notifybefore) VALUES (?,?,?,?)", notifyrequest.getLocationid(), notifyrequest.getFcm_reg_id(), notifyrequest.getUserid(),notifyrequest.getNotifybefore());


		} catch(SQLException sqle) {

		    sqle.printStackTrace();
		    
		    return false;

		}

		return true ;    	
		
	}

}
