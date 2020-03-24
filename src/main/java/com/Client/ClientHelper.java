package com.Client;

import java.net.*;
import javax.swing.JOptionPane;
import java.io.*;;

public class ClientHelper {

	   private static final String MESSAGE_TO_END_CONNECTION = "Exit";
       public ClientStreamSocket mySocket;
	   public InetAddress serverHost;
	   public int serverPort;

	   ClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException, IOException {
	  	   this.serverHost = InetAddress.getByName(hostName);
	  	   this.serverPort = Integer.parseInt(portNum);
	  	   try {
	  		 this.mySocket = new ClientStreamSocket(this.serverHost, this.serverPort);
	  	   }
	  	   catch(SocketException s) {
	  		   
	  		 JOptionPane.showMessageDialog(null, s.getStackTrace(), "Error", JOptionPane.INFORMATION_MESSAGE);
	  		   
	  	   } catch (UnknownHostException u) {
	  		   
	  		 JOptionPane.showMessageDialog(null, u.getStackTrace(), "Error", JOptionPane.INFORMATION_MESSAGE);
	  		   
	  	   } catch (IOException i) {
	  		   
	  		 JOptionPane.showMessageDialog(null, i.getStackTrace(), "Error", JOptionPane.INFORMATION_MESSAGE);
	  		   
	  	   }
	   } 

	   
	   
	   public String getEcho(String request) throws SocketException, IOException {     
	      String echo = "";
	      mySocket.sendRequest(request);
		   // now receive the echo
	      echo = mySocket.receiveRequest();
	      return echo;
	   }

	   public void terminateSession() throws SocketException, IOException {
	      //mySocket.sendRequest(MESSAGE_TO_END_CONNECTION);
	      mySocket.close();
	   } 
	} 