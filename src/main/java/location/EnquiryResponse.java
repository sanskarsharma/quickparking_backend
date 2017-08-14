package location;

public class EnquiryResponse {
	

	private String locationid;
	private int totalcapacity;
	private int vacant;
	private int on_hold;
	private int occupied;
	
	private boolean status;

	
	public int getOccupied() {
		return occupied;
	}
	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}
	
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
		
	}
	public String getLocationID() {
		return locationid;
	}
	public void setLocationID(String locationID) {
		this.locationid = locationID;
	}
	public int getTotalcapacity() {
		return totalcapacity;
	}
	public void setTotalcapacity(int totalcapacity) {
		this.totalcapacity = totalcapacity;
	}
	public int getVacant() {
		return vacant;
	}
	public void setVacant(int vacant) {
		this.vacant = vacant;
	}
	public int getOn_hold() {
		return on_hold;
	}
	public void setOn_hold(int on_hold) {
		this.on_hold = on_hold;
	}
	
}
