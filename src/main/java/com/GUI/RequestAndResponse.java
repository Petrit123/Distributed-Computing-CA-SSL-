package com.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Client.Client;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;

public class RequestAndResponse extends JFrame {

	private JPanel contentPane;
	private static JTextArea ClientRequestArea;
	private static JTextArea ClientReceivedResponseArea;
	private static JTextArea ServerReceivedArea;
	private static JTextArea ServerResponseArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestAndResponse frame = new RequestAndResponse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RequestAndResponse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1149, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ServerReceivedArea = new JTextArea();
		ServerReceivedArea.setBounds(39, 35, 457, 178);
		contentPane.add(ServerReceivedArea);
		
		ClientRequestArea = new JTextArea();
		ClientRequestArea.setBounds(610, 35, 457, 178);
		contentPane.add(ClientRequestArea);
		
		ServerResponseArea = new JTextArea();
		ServerResponseArea.setBounds(39, 260, 457, 178);
		contentPane.add(ServerResponseArea);
		
		ClientReceivedResponseArea = new JTextArea();
		ClientReceivedResponseArea.setBounds(610, 260, 457, 178);
		contentPane.add(ClientReceivedResponseArea);
	}
	
	public static void receiveRequestAndResponse(String clientRequest, String clientReceivedResponse) {
		ClientRequestArea.append("The client sent the following request to the server: " + clientRequest + "\n");
		ClientReceivedResponseArea.append("The client received the following response from the server " + clientReceivedResponse + "\n");
		ServerReceivedArea.append("Server received the following request from the client " + clientRequest + "\n");
		ServerResponseArea.append("The server sent the following response to the client " + clientReceivedResponse + "\n");
	}
}
