//package com.penguinsrising.services.workflow.engine;
//
//import java.util.List;
//
//import org.activiti.engine.IdentityService;
//import org.activiti.engine.identity.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class UserService {
//
//	protected static Logger logger = LoggerFactory.getLogger(UserService.class);
//
//	// Get User Account "Kermit:Kermit"
//	public static boolean checkID(String userId, String password, IdentityService identityService)
//	{
//		if(identityService != null){
//	   		try {
//				logger.info("Checking Password...");
//		    	List<String> userAccountNames = identityService.getUserAccountNames(userId);
//
//		    	// Call the Identity-Service-Provider (: our LDAP module :)
//				if(identityService.checkPassword(userId, password)){
//					logger.info("Login Success !! Welcome " + userId);
//					return true;
//				}
//	   		}
//	   		catch(Exception ex){ logger.warn("Check Failed:" + ex.getMessage()); 
//	   			return false;
//	   		}
//		}
//
//		// Catch All Fail --> return false
//		logger.warn("Bad UserName or Password ~~!!");
//		return false;
//	}
//
//	// Get a list of all users (note:password returns null)
//    public static List <User> getUsers(IdentityService identityService) 
//    {
//    	List <User> users = identityService.createUserQuery().list();
//    	for(User user : users)
//    		logger.info("User:" + user.getId() + "--" + user.getPassword() + "--" + user.getFirstName());
//    	return users;
//    }
//
//
//}
