package com.Server;

import java.io.*;
import java.net.*;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;

public class Server {
	
	private static final int SERVER_PORT = 5094;
	private boolean isServerListening = false;

	public void startServer(){
		
		try {
			System.setProperty("javax.net.ssl.keyStore", "za.store");
			System.setProperty("javax.net.ssl.keyStorePassword", "Hello123");
			//ServerSocket myConnectionSocket = new ServerSocket(SERVER_PORT);
			ServerSocket myConnectionSocket = ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(SERVER_PORT);
			System.out.println("Server is ready for connections");
			
			listenForConnection();
			waitForConnection(myConnectionSocket);
			
			
		} // end try
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void listenForConnection() {
		if (!isServerListening) {
			isServerListening = true;
		}
	}
	
	public void waitForConnection(ServerSocket serverSocket) throws IOException {
		while (isServerListening) {
			
			System.out.println("Waiting for a connection");
			
			ServerStreamSocket myDataSocket = new ServerStreamSocket(serverSocket.accept());
			System.out.println("Connection accepted");
			
			// Start a thread to handle this client's session
			Thread theThread = new Thread(new ServerThread(myDataSocket));
			theThread.start();
		}
	}
	
}

