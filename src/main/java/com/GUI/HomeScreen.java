package com.GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;

public class HomeScreen {

	public JFrame frame;
	public JLabel lblNewLabel = new JLabel("100");
	public JProgressBar progressBar = new JProgressBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
					window.frame.setVisible(true);
					for (int i = 0; i < 100; i++) {
						Thread.sleep(100);
						window.lblNewLabel.setText(Integer.toString(i));
						window.progressBar.setValue(i);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		progressBar.setForeground(new Color(255, 255, 255));
		progressBar.setBackground(new Color(0, 0, 0));
		progressBar.setBounds(0, 0, 569, 28);
		frame.getContentPane().add(progressBar);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 27, 569, 292);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 549, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to my Client-Server application");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 61, 549, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Designed By: Petrit Krasniqi 2020");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(368, 247, 191, 34);
		panel.add(lblNewLabel_2);
		
		JLabel label = new JLabel("New label");
		label.setBounds(241, 2, 46, 14);
		frame.getContentPane().add(label);
		frame.setLocationRelativeTo(null);
	}
}
