package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import com.Users.User;
import com.GUI.*;
import com.Protocol.iProtocolResponse;
import com.Protocol.iRequest;

public class Client {
      public static final String MESSAGE_TO_END_CONNECTION = "Exit";
      public InputStreamReader is = new InputStreamReader(System.in);
      public BufferedReader br = new BufferedReader(is);
      public static String sessionId = "";
      public User user;
      public static ClientHelper helper;
      public static String clientRequest = "";
      
      
      public void startClient(String hostName, String portNumber) {
         try {
			helper = new ClientHelper(getHostName(hostName), getPortNumber(portNumber));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
     }

      private String getHostName(String hostName) {
    	  
    		  if (hostName.length() == 0) {
    			  hostName = "localhost";
    		  }
    	  
    	  return hostName;
      }
      
      private String getPortNumber(String portNumber) {

              if (portNumber.length() == 0) {
                  portNumber = "5094";          // default port number  
              }

          
          return portNumber;
      }
      
      
	public void displayHomeScreen() {
		HomeScreen window = new HomeScreen();
		window.frame.setVisible(true);
	     ConnectToServerForm frame = new ConnectToServerForm();
		
		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(30);
				window.lblNewLabel.setText(Integer.toString(i));
				window.progressBar.setValue(i);
				if (i == 100) {
					window.frame.setVisible(false);
					frame.setVisible(true);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static String sendUserLogInDetails(String request, String userName, String password) {
		
		String userLogInRequest = request + "," +   userName + "," + password;	
		
		String serverResponse = "";
		try {
			serverResponse = helper.getEcho(userLogInRequest);
	
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return serverResponse;
	}
	
	public static boolean isLoginRequestSuccessful(String serverResponse) {
		List<String> receivedMessageSplit = Arrays.asList(serverResponse.split(" "));
		String responseCode = receivedMessageSplit.get(0);
		String responseMessage = receivedMessageSplit.get(1);
		String fullResponse = responseCode + " " + responseMessage;
		boolean isLoginRequestSuccessful = true;
		if (fullResponse.equals(iProtocolResponse.successfulLoginRequest)) {
			isLoginRequestSuccessful = true;
		} else if (fullResponse.equals(iProtocolResponse.invalidUserLoginDetailsRequest)) {
			isLoginRequestSuccessful = false;
		}
		
		return isLoginRequestSuccessful;
	}
	
	public static boolean checkIfUserIsLoggedIn(String serverResponse) {
		List<String> receivedMessageSplit = Arrays.asList(serverResponse.split(" "));
		String responseCode = receivedMessageSplit.get(0);
		String responseMessage = receivedMessageSplit.get(1);
		String fullResponse = responseCode + " " + responseMessage;
		boolean isLoginRequestSuccessful = true;
		if (fullResponse.equals(iProtocolResponse.loginRequestUserAlreadyLoggedIn)) {
			isLoginRequestSuccessful = false;
		}
		
		return isLoginRequestSuccessful;
	}
	
	
	public static String sendUserRegistrationDetails(String request, String userName, String password) {
		
		String createUserRequest = request + "," + userName + "," + password;
		
		String serverResponse = "";
		
		try {
			serverResponse = helper.getEcho(createUserRequest);
		}  catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return serverResponse;
	}
	
	public static boolean isRegistrationRequestSuccessful(String serverResponse) {
		boolean isLoginRequestSuccessful = false;
		if (serverResponse.equals(iProtocolResponse.successfulSignUpRequest)) {
			isLoginRequestSuccessful = true;
		} else if (serverResponse.equals(iProtocolResponse.invalidUserNameSignUpRequest)) {
			isLoginRequestSuccessful = false;
		}
		
		return isLoginRequestSuccessful;
	}
	
	
	public static String sendUserTMPMessage(String request, String userName, String message) {
		
		String sendUserTMPMessageRequest = request + "," + userName + "," + message;
		
		String serverResponse = "";
		
		try {
			serverResponse = helper.getEcho(sendUserTMPMessageRequest);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverResponse;
		
	}
	
	public static String sendDownloadRequest(String request, String userName) {
		
		String sendUserDownloadRequest = request + "," + userName;
		
		String serverResponse = "";
		
		try {
			serverResponse = helper.getEcho(sendUserDownloadRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverResponse;
	}
	
	public static boolean isTMPMessageSuccessfulySent(String serverResponse) {
		List<String> receivedMessageSplit = Arrays.asList(serverResponse.split(" "));
		String responseCode = receivedMessageSplit.get(0);
		String responseMessage = receivedMessageSplit.get(1);
		String fullResponse = responseCode + " " + responseMessage;
		boolean isTMPMessageSuccessfulySent = false;
		if (fullResponse.equals(iProtocolResponse.successFulUpload)) {
			isTMPMessageSuccessfulySent = true;
		} else if (fullResponse.equals(iProtocolResponse.failedUpload)) {
			isTMPMessageSuccessfulySent = false;
		}
		
		return isTMPMessageSuccessfulySent;
	}
	
	public static boolean isTMPMessageDownloaded(String serverResponse) {
		List<String> receivedMessageSplit = Arrays.asList(serverResponse.split(" "));
		String responseCode = receivedMessageSplit.get(0);
		String responseMessage = receivedMessageSplit.get(1);
		String fullResponse = responseCode + " " + responseMessage;
		boolean isMessageDownloaded = false;
		
		if (fullResponse.equals(iProtocolResponse.succesFulDownload)) {
			isMessageDownloaded = true;
		} else if (fullResponse.equals(iProtocolResponse.failedDownload)) {
			isMessageDownloaded = false;
		}
		
		return isMessageDownloaded;
	}
	
	public static String sendLogOffRequest(String request, String userName) {
		String loggOfRequest = request + "," + userName;
		
		String serverResponse = "";
		
		try {
			serverResponse = helper.getEcho(loggOfRequest);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverResponse;
	}
	

      
} 
