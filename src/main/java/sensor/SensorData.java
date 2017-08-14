package sensor;

public class SensorData {
		private String sensorID;
		private String sensorType;
		private String locationID;
		
		
		
		public SensorData(String sensorID, String sensorType, String locationID) {
			super();
			this.sensorID = sensorID;
			this.sensorType = sensorType;
			this.locationID = locationID;
		}
		
		// getter/setter
		
		public String getSensorID() {
			return sensorID;
		}
		public void setSensorID(String sensorID) {
			this.sensorID = sensorID;
		}
		public String getSensorType() {
			return sensorType;
		}
		public void setSensorType(String sensorType) {
			this.sensorType = sensorType;
		}
		public String getLocationID() {
			return locationID;
		}
		public void setLocationID(String locationID) {
			this.locationID = locationID;
		}
		
		
	
}
