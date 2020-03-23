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
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationForm frame = new RegistrationForm();
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
	public RegistrationForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(0, 0, 450, 55);
		contentPane.add(panel);
		
		JLabel lblSignUp = new JLabel("Sign up");
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSignUp.setBounds(25, 11, 179, 33);
		panel.add(lblSignUp);
		
		JLabel label_1 = new JLabel("X");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_1.setBounds(418, 11, 22, 33);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_2.setBounds(386, 11, 22, 33);
		panel.add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 102, 153));
		panel_1.setBounds(0, 52, 450, 358);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("Username");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(43, 54, 89, 33);
		panel_1.add(label);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmPassword.setBounds(9, 153, 157, 33);
		panel_1.add(lblConfirmPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(176, 68, 223, 24);
		panel_1.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(176, 162, 223, 24);
		panel_1.add(passwordField);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setForeground(new Color(240, 248, 255));
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCreate.setBackground(new Color(0, 204, 255));
		btnCreate.setBounds(285, 212, 114, 39);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!passwordField.getText().equals(passwordField_1.getText())) {
					JOptionPane.showMessageDialog(null, "Passwords do not match!!", "Error",JOptionPane.ERROR_MESSAGE);		
					passwordField.setText("");
					passwordField_1.setText("");
				}
				else if (textField.getText().equals("") || passwordField.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Blank field", "Error",JOptionPane.ERROR_MESSAGE);
				} else {
					String userName = textField.getText();
					String password = passwordField.getText();
					String serverResponse = Client.sendUserRegistrationDetails(iRequest.SIGNUP, userName, password);;
					
					if (Client.isRegistrationRequestSuccessful(serverResponse)) {
						JOptionPane.showMessageDialog(null, "User " + userName + " successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
						LoginForm lf = new LoginForm();
						lf.setVisible(true);
						lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						setVisible(false);
						dispose();

					} else if (!Client.isRegistrationRequestSuccessful(serverResponse)) {
						JOptionPane.showMessageDialog(null, "Username is already taken", "Failure", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						passwordField.setText("");
						passwordField_1.setText("");
					}
				}
				

			}			
		});
		panel_1.add(btnCreate);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setForeground(new Color(240, 248, 255));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBackground(Color.RED);
		button_1.setBounds(176, 212, 99, 39);
		panel_1.add(button_1);
		
		JLabel lblClickHereTo = new JLabel("Click here to return to login");
		lblClickHereTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginForm lf = new LoginForm();
				lf.setVisible(true);
				//lf.pack();
				lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		lblClickHereTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickHereTo.setForeground(Color.WHITE);
		lblClickHereTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClickHereTo.setBounds(176, 273, 223, 24);
		panel_1.add(lblClickHereTo);
		
		JLabel label_5 = new JLabel("Password");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(43, 109, 89, 33);
		panel_1.add(label_5);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField_1.setBounds(175, 112, 223, 24);
		panel_1.add(passwordField_1);
		setLocationRelativeTo(null);
	}

}
