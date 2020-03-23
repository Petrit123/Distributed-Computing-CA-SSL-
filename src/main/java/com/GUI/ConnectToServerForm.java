package com.GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.Client.Client;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

public class ConnectToServerForm extends JFrame {

	private Image serverIcon = new ImageIcon(ConnectToServerForm.class.getResource("/com/resources/server.png")).getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
	private JPanel contentPane;
	private JTextField txtHostName;
	private JTextField txtPortNumber;
	public  String hostName;
	public  String portNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectToServerForm frame = new ConnectToServerForm();
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
	public ConnectToServerForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(170, 157, 250, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtHostName = new JTextField();
		txtHostName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if (txtHostName.getText().equals("Hostname")) {
					txtHostName.setText("");
				} else {
					txtHostName.selectAll();
				}
			}
		});
		txtHostName.setBorder(null);
		txtHostName.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHostName.setText("Hostname");
		txtHostName.setBounds(10, 11, 170, 20);
		panel.add(txtHostName);
		txtHostName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(170, 208, 250, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtPortNumber = new JTextField();
		txtPortNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if (txtPortNumber.getText().equals("Port number")) {
					txtPortNumber.setText("");
				} else {
					txtPortNumber.selectAll();
				}
			}
		});
		txtPortNumber.setBorder(null);
		txtPortNumber.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPortNumber.setText("Port number");
		txtPortNumber.setColumns(10);
		txtPortNumber.setBounds(10, 11, 170, 20);
		panel_1.add(txtPortNumber);
		
		JPanel panel_2 = new JPanel();
		panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBounds(170, 288, 250, 50);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connect to server");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hostName = txtHostName.getText().toString();
				portNumber = txtPortNumber.getText().toString();
				Client c = new Client();
				LoginForm lf = new LoginForm();
				lf.setVisible(true);
				lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(false);
				dispose();
				c.startClient(hostName, portNumber);

			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(69, 11, 144, 28);
		panel_2.add(lblNewLabel);
		
		final JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?","Confirmation",JOptionPane.YES_NO_OPTION) == 0) {
					ConnectToServerForm.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.WHITE);
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(580, 0, 20, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(246, 45, 90, 101);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(serverIcon));
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
