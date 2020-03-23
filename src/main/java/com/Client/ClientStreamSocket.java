package com.Client;

import java.net.*;
import java.io.*;

public class ClientStreamSocket {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public ClientStreamSocket(InetAddress acceptorHost, int acceptorPort) throws SocketException, IOException {
		socket = new Socket(acceptorHost, acceptorPort);
		setStreams();
	}
	
	private void setStreams() throws IOException {
		
		// get an input stream for reading from the data socket
		InputStream inStream = socket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream = socket.getOutputStream();
		// create a PrinterWriter object character-mode output
		output = new PrintWriter(new OutputStreamWriter(outStream));
	}
	
	public void sendRequest(String request) throws IOException {
		
		System.out.print("Client sent request " + request + "\n");
		output.print(request + "\n");
		/* The ensuing flush method call is necessary for the data to
		 * be written to the socket data stream before the
		 * socket is closed
		 */
		
		output.flush();
		// end message
	}
	
	
	public String receiveRequest() throws IOException {
		// read a line from the data stream
		String request = input.readLine();
		System.out.print("Client received " + request);
		return request;
	} // end message
	
	public void close() throws IOException {
		socket.close();
	}
	
}
