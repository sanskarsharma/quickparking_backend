package user;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class UserJDBCImpl implements UserDAOInterface  {
	
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
	public boolean registerIDs(UserModel user) {
		// TODO Auto-generated method stub
		
		

    	QueryRunner run = new QueryRunner( dataSource );

		try	{
			
			int newtry = run.update("UPDATE useridnotifid SET fcm_reg_id = ? WHERE user_id = ? ",user.getFcmID(),user.getUserID());
			
			if(newtry == 0){
				
				  int inserts = run.update( "INSERT INTO useridnotifid (name, mobile_num, user_id, fcm_reg_id) VALUES (?,?,?,?)", user.getName(), user.getMobileNumber(), user.getUserID(),user.getFcmID());

				
			}
			
			//int newtry = run.update("UPDATE useridnotifid SET fcm_reg_id = ? WHERE user_id = ? IF @@ROWCOUNT = 0 INSERT INTO useridnotifid (name, mobile_num, user_id, fcm_reg_id) VALUES (?,?,?,?) ",user.getFcmID(),user.getUserID(), user.getName(), user.getMobileNumber(), user.getUserID(),user.getFcmID());

			//int updateorinsert = run.update("IF EXISTS (SELECT * FROM useridnotifid WHERE user_id = ? ) UPDATE useridnotifid SET name = ?, fcm_reg_id = ? ELSE INSERT INTO useridnotifid(name, mobile_num, user_id, fcm_reg_id) VALUES (?,?,?,?)",user.getUserID(),user.getName(),user.getFcmID(),user.getName(),user.getMobileNumber(),user.getUserID(),user.getFcmID());

		   // int inserts = run.update( "INSERT INTO useridnotifid (name, mobile_num, user_id, fcm_reg_id) VALUES (?,?,?,?)", user.getName(), user.getMobileNumber(), user.getUserID(),user.getFcmID());

		} catch(SQLException sqle) {

		    throw new RuntimeException("Problem updating", sqle);
		    
		    //return false;

		}

		return true ;    	
		
	}

}
