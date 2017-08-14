package sensor;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JdbcDAO implements DAO {

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

    public boolean addSensor(SensorData data) {

    	QueryRunner run = new QueryRunner( dataSource );

		try	{

		    int inserts = run.update( "INSERT INTO sensorTable (SENSORID, SENSORTYPE, LOCATIONID) VALUES (?,?,?)", data.getSensorID(), data.getSensorType(), data.getLocationID());

		} catch(SQLException sqle) {

		    throw new RuntimeException("Problem updating", sqle);
		    
		    //return false;

		}

		return true ;    	

    }

}
