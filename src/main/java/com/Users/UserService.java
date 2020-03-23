//package com.Users;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import com.Protocol.Response;
//
//public class UserService {
//	
//	private File file = new File("Users.txt");
//	private User user = new User();
//	private User userWitSessionId;
//	private List<User> users = new ArrayList<User>();
//	private List<User> loggedInUsers = new ArrayList<User>();
//	private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
//	private int sessionId = 0;
//	
//	public String createUser(String userName, String password) {
//		String serverResponse = "";
//		try {
//		if (!isValidUserName(userName)) {
//			serverResponse = "301 " + Response.FAILED;
//		} else {
//			user.setUserName(userName);
//	        user.setPassWord(encryptPassword(password, 2));
//	        saveUserInfo(userName, password);
//			users.add(user);
//			System.out.println("Successfully registered user " + userName);
//			serverResponse = "302 " + Response.SUCCESS;
//		}
//		} catch (IOException ex) {
//			System.out.println("Error in creating user");
//			ex.printStackTrace();
//
//		}
//		
//		return serverResponse;
//	}
//	
//	public void addUserToListOfUsers(String userName, String password) {
//		user.setUserName(userName);
//		user.setPassWord(password);
//		users.add(user);
//	}
//	
//	public void saveUserInfo(String userName, String password) throws IOException {
//		
//		try {
//			
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.txt", true), StandardCharsets.UTF_8));
//			 writer.append("\n" + userName + ": ").append(encryptPassword(password, 2));
//			 writer.newLine();
//			 //writer.append(encryptPassword(password, 2));
//			 writer.close();
//			 System.out.println("Successfully saved to file");
//		}  catch (FileNotFoundException e) {
//			System.out.println("Error writing to file ");
//			e.printStackTrace();
//	      }
//		
// }
//	
//	public boolean isValidUserName(String userName) {
//		
//		boolean isValidName = false;
//		
//		try {
//		BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
//		String existingUserName = br.readLine();
//		while (existingUserName != null) {
//			if (userName.equalsIgnoreCase(existingUserName.substring(0, existingUserName.indexOf(":")))) {
//				System.out.println("Username already exists... \nPlease enter another username");
//				isValidName = false;
//				break;
//			} else {
//				isValidName = true;
//				break;
//			}
//
//		}
//		br.close();
//		} catch (IOException e) {
//			System.out.println("Error in validating user");
//			e.printStackTrace();
//		}
//		
//		return isValidName;
//	}
//	
//	public String logIn(String userName, String password) {
//		String logInResponse = "404 DENIED";
//		try {
//			password = encryptPassword(password, 2);
//			BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
//			//System.out.println(userNameInFile.substring(userNameInFile.indexOf(':') + 2));
//			String userNameInFile;
//			while ((userNameInFile = br.readLine()) != null) {
//                if (userName.equals(userNameInFile.substring(0, userNameInFile.indexOf(':'))) && password.equals(userNameInFile.substring(userNameInFile.indexOf(':') + 2))) {
//                	System.out.println("Credentials are valid");
//                	sessionId ++;
//                	//loggedInUsers.add(new User(userName, password, true,sessionId));
//                	logInResponse = "200 SUCCESS";
//                	//break;
//
//                } 
//		}
//			br.close();
//	} catch (IOException e) {
//		System.out.println("Error in validating user log in details");
//		e.printStackTrace();
//	}
//		
//		return logInResponse;
//		
//	}
//	
//	public void logOut(String userName) {
//		
//		for (User loggedInUser: loggedInUsers) {
//			
//			if (userName.equals(loggedInUser.getUserName())) {
//				
//				System.out.print(loggedInUser.getUserName() + " has successfully logged out.");
//				loggedInUsers.remove(loggedInUser);
//			}
//		}
//	}
//	
//	public int getNumRows(String passwordInPlainText, int numColumns) {
//		passwordInPlainText = passwordInPlainText.replaceAll("\\s", "");
//		int numRows;
//		if (passwordInPlainText.length() % numColumns == 0) {
//			
//			numRows = (passwordInPlainText.length() / numColumns);
//		} else {
//			numRows = ((passwordInPlainText.length() / numColumns) + 1);
//		}
//		
//		return numRows;
//	}
//	
//	public String encryptPassword(String passwordInPlainText, int numColumns) {
//		passwordInPlainText = passwordInPlainText.replaceAll("\\s","");
//		
//		int numRows = getNumRows(passwordInPlainText, numColumns);
//		String encryptedText = "";
//		
//		for (int col = 0; col < numColumns; col++) {
//			int index = col;
//			
//			for (int row = 0; row < numRows; row ++) {
//				
//				encryptedText += passwordInPlainText.charAt(index);
//				index += numColumns;
//			}
//		}
//		
//		return encryptedText;
//	}
//	
//	public String decryptPassword(String encryptedPassword, int numColumns) {
//		encryptedPassword = encryptedPassword.replaceAll("\\s+", "");
//		int numRows = getNumRows(encryptedPassword,numColumns);
//		return encryptPassword(encryptedPassword, numRows);
//	}
//	
//	public void listLoggedInUsers() {
//		
//		for(User loggedInUser: loggedInUsers) {
//			System.out.print(loggedInUser.getUserName() + " is logged in");
//		}
//	}
//
//	public User getLoggedInUserBySessionId(int sessionId) {
//		
//		for (User loggedInUser: loggedInUsers) {
//			if (loggedInUser.getSessionId() == sessionId) {
//				userWitSessionId = loggedInUser;
//				System.out.println(userWitSessionId.getUserName());
//				System.out.println(userWitSessionId.getSessionId());
//			}
//		}
//		
//		return userWitSessionId;
//	}
//	
//
//	
//	public int getSessionId() {
//		return sessionId;
//	}
//}
