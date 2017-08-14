package notifyrequests;

public class NotifyController {
	
	private NotifyDAOInterface notifydaointerface;
	
	public NotifyController(NotifyDAOInterface dao){
		
		super();
		this.notifydaointerface = dao;
		
	}
	
	public String answerNotifyrequest(NotifyModel notifreq){
		
		if(notifydaointerface.addNotifyRequest(notifreq)){
			
			return "{\"response\":\"NOTIFY_SUCCESS\"}"; 

			
		}
		else{
			
			return "{\"response\":\"NOTIFY_FAIL\"}"; 

		}
				
	}

}
