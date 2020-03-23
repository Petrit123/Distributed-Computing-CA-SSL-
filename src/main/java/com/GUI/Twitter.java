package com.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.Client.Client;
import com.Protocol.iProtocolResponse;
import com.Protocol.iRequest;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Twitter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblUser;
	private JLabel lblSessionId;
	public String userName = "";
	private JButton downloadBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Twitter frame = new Twitter();
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
	public Twitter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1329, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setCaretPosition(0);
		textPane.setBounds(212, 21, 1091, 370);
		contentPane.add(textPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(212, 402, 967, 30);
		contentPane.add(textField);
		
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(1189, 402, 114, 30);
		contentPane.add(btnNewButton);
	    appendToPane(textPane, "Welcome!  Enter a line to receive an echo from the server, or type exit to quit.", Color.BLUE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String serverResponse = Client.sendUserTMPMessage(iRequest.UPLOAD, userName, textField.getText());
				List<String> receivedMessageSplit = Arrays.asList(serverResponse.split(","));
				String message = receivedMessageSplit.get(1);
				String response = receivedMessageSplit.get(2);
				String timeStamp = receivedMessageSplit.get(3);
				appendToPane(textPane, "\n\n############################################################################################### \n", Color.GRAY);
				appendToPane(textPane, message, Color.BLACK);
				if (response.trim().equals("Sent")) {
					appendToPane(textPane,"\t\t\t\t\t\t " + response, Color.GREEN);
				} else if (response.trim().equals("Failed")) {
					appendToPane(textPane,"\t\t\t\t\t\t " + response, Color.RED);
				}
				appendToPane(textPane, "\n\t\t\t\t\t\t " + timeStamp, Color.BLUE);
				appendToPane(textPane, "\n###############################################################################################", Color.GRAY);
			}
		});
		
		JButton button_1 = new JButton("Log Off");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Goodbye " + userName, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					
					setVisible(false);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();

				//	JOptionPane.showMessageDialog(null, "Sorry " + userName + " there was an error is logging you out", "ERROR", JOptionPane.ERROR_MESSAGE);

			}
		});
		button_1.setBounds(20, 402, 173, 30);
		contentPane.add(button_1);
		
		lblSessionId = new JLabel("SessionId:");
		lblSessionId.setForeground(new Color(255, 255, 255));
		lblSessionId.setBounds(20, 53, 81, 14);
		contentPane.add(lblSessionId);
		
		lblUser = new JLabel("User: 1");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setBounds(20, 21, 134, 21);
		contentPane.add(lblUser);
		
		downloadBtn = new JButton("Download");
		downloadBtn.addMouseListener(new MouseAdapter()  {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String serverResponse = Client.sendDownloadRequest(iRequest.DOWNLOAD, userName);
				
				if(Client.isTMPMessageDownloaded(serverResponse)) {
					JOptionPane.showMessageDialog(null,"Successfully downloaded messages in the following location: " + userName + "/" + userName + "Messages.html", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		downloadBtn.setBounds(20, 349, 173, 30);
		contentPane.add(downloadBtn);
		
	}
	
    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
    
	public void displayUserDetails(String userName, String sessionId) {
		lblUser.setText("User: " + userName);
		lblSessionId.setText("SessionId: " + sessionId);
		this.userName = userName;
	}

}
