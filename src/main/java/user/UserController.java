package user;

public class UserController {

	private UserDAOInterface userDaoInterface;
	
	public UserController(UserDAOInterface dao){
		super();
		this.userDaoInterface = dao;
	}
	
	public String register(UserModel user){
		
		if(userDaoInterface.registerIDs(user)){
			return "{\"message\":\"REGISTER_SUCCESS!\"}"; 

		}
		else{
			return "{\"message\":\"REGISTER_FAIL\"}";

		}
		
	}
	
}
