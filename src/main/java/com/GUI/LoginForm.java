package com.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.Client.Client;
import com.Protocol.iRequest;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import java.awt.Cursor;
import javax.swing.JButton;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(0, 0, 450, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(25, 11, 179, 33);
		panel.add(lblNewLabel);
		
		JLabel jLabelClose = new JLabel("X");
		jLabelClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jLabelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		jLabelClose.setForeground(Color.WHITE);
		jLabelClose.setFont(new Font("Tahoma", Font.BOLD, 24));
		jLabelClose.setBounds(418, 11, 22, 33);
		panel.add(jLabelClose);
		
		JLabel jLabelMinimise = new JLabel("-");
		jLabelMinimise.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jLabelMinimise.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		jLabelMinimise.setForeground(Color.WHITE);
		jLabelMinimise.setFont(new Font("Tahoma", Font.BOLD, 24));
		jLabelMinimise.setBounds(386, 11, 22, 33);
		panel.add(jLabelMinimise);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 153));
		panel_1.setBounds(0, 55, 450, 245);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(43, 54, 89, 33);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(43, 98, 89, 33);
		panel_1.add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(142, 63, 223, 24);
		panel_1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(142, 107, 223, 24);
		panel_1.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(0, 204, 255));
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(251, 142, 114, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textField.getText().equals("") || passwordField.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Blank field", "Error",JOptionPane.ERROR_MESSAGE);
				} else {
					String userName = textField.getText();
					String password = passwordField.getText();
					String serverResponse = Client.sendUserLogInDetails(iRequest.LOGIN , userName, password);
					List<String> receivedMessageSplit = Arrays.asList(serverResponse.split(" "));
					Client.sessionId = receivedMessageSplit.get(3);
					 if (!Client.checkIfUserIsLoggedIn(serverResponse)) {
							JOptionPane.showMessageDialog(null, "User is already logged in", "Failure", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
							passwordField.setText("");
						} else if (Client.isLoginRequestSuccessful(serverResponse)) {
						TwitterUploadPage frame = new TwitterUploadPage();
						frame.displayUserDetails(userName, Client.sessionId);	
						frame.setVisible(true);
						setVisible(false);
						dispose();
					} else if (!Client.isLoginRequestSuccessful(serverResponse)) {
						JOptionPane.showMessageDialog(null, "Username or password is incorrect, please try again", "Failure", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						passwordField.setText("");
					} 
				}

				
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(240, 248, 255));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.setBounds(142, 142, 99, 39);
		panel_1.add(btnCancel);
		
		JLabel lblClickToRegister = new JLabel("Click here to create a new account");
		lblClickToRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationForm rf = new RegistrationForm();
				rf.setVisible(true);
				//rf.pack();
				rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		lblClickToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickToRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClickToRegister.setForeground(Color.WHITE);
		lblClickToRegister.setBounds(142, 203, 223, 14);
		panel_1.add(lblClickToRegister);
		setLocationRelativeTo(null);
	}
}
