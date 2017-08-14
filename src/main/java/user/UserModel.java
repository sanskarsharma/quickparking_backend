package user;

public class UserModel {
	
	public String name;
	public String mobile_num;
	public String user_id;
	public String fcm_reg_id;
	
		
	public UserModel() {

	}

	public UserModel(String name, String mobileNumber, String userID, String fcmID) {
		super();
		this.name = name;
		this.mobile_num = mobileNumber;
		this.user_id = userID;
		this.fcm_reg_id = fcmID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobile_num;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobile_num = mobileNumber;
	}
	public String getUserID() {
		return user_id;
	}
	public void setUserID(String userID) {
		this.user_id = userID;
	}
	public String getFcmID() {
		return fcm_reg_id;
	}
	public void setFcmID(String fcmID) {
		this.fcm_reg_id = fcmID;
	}
	
	

}
