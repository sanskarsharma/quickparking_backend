package notifyrequests;

public class NotifyModel {

	private String locationid;
	private String fcm_reg_id;
	private String userid;
	private String notifybefore;
	
	
	public NotifyModel(String locationid, String fcm_reg_id, String userid, String notifybefore) {
		super();
		this.locationid = locationid;
		this.fcm_reg_id = fcm_reg_id;
		this.userid = userid;
		this.notifybefore = notifybefore;
	}
	
	
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getFcm_reg_id() {
		return fcm_reg_id;
	}
	public void setFcm_reg_id(String fcm_reg_id) {
		this.fcm_reg_id = fcm_reg_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNotifybefore() {
		return notifybefore;
	}
	public void setNotifybefore(String notifybefore) {
		this.notifybefore = notifybefore;
	}
	
	
	
}
