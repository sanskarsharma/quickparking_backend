package location;

public class RequestModel {
	
	private String locationid;
	private String userid;
	
		
	public RequestModel(String locationid, String userid) {
		super();
		this.locationid = locationid;
		this.userid = userid;
	}
	
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
