package com.Server;

import java.util.ArrayList;
import java.util.List;
import com.Users.User;



public class ServerThread implements Runnable {
	
	public ServerStreamSocket myDataSocket;
	public static boolean sessionStarted = true;
	public static List<User> loggedInUsers = new ArrayList<User>();
	ServerThread(ServerStreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}
	
	
	public void run() {
		
		String request;
		ServerStreamSocket.sessionId ++;
		
		try {
			while (sessionStarted) {				
				request = myDataSocket.receiveRequest();
				
					myDataSocket.sendRequest(request);
			} 
		} 
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} 
	} 
	
} 