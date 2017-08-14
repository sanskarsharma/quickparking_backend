package firebase;

public class TestFCM {



public static void main(String[] args) {



//Just I am passed dummy information

String tokenId = "f-PXtSN-G8I:APA91bFTp_K0BkIoswdeH-qPt8C_Phg4a_o0yFtKfVl07CjovXk4255kV--MDREu6xUEhDKPGiH4PL7X1vNQIxHi-HyXMEJvoJSBqwnnuq3AcPCKQ9zx6uiRrkYKNORsara43G9urBhq";

String server_key = "AIzaSyBMUD-fK0j9NtUg6u_MsV-UdvWVDLZC9Ws" ;

String message = "Welcome to FCM Server push notification!.";



//Method to send Push Notification

FCM.send_FCM_Notification( tokenId,message);

}

}

