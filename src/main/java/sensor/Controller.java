package sensor;

import java.util.HashMap;
import java.util.Map;

public class Controller {

	private DAO dao;

	public Controller(DAO dao) {

		super();

		this.dao = dao;

	}

	public String add(SensorData data){

		//Map<String, Object> data = new HashMap<String, Object>();

		if (dao.addSensor(data)){

			return "{\"message\":\"Added a note!\"}"; 

		} else {

			return "{\"message\":\"Failed to add a note\"}";

		}

	}

}


