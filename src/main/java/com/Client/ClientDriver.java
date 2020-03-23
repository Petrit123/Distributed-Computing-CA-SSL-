package com.Client;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.GUI.ConnectToServerForm;


public class ClientDriver {
   public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
	   
	   Client c = new Client();
	   c.displayHomeScreen();
	   
	   

   } 
} 
