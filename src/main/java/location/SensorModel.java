package location;

public class SensorModel {
	
	private String locationid;
	private String sensortype;
	
	
	
	
	public SensorModel(String locationid, String sensortype) {
		super();
		this.locationid = locationid;
		this.sensortype = sensortype;
	}
	
	
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getSensortype() {
		return sensortype;
	}
	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}
	
	
	

}
