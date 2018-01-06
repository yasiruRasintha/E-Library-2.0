package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DatabaseOperations.UserOperations;

public class Register {
	JPanel formRegister = new JPanel();
	JTextField textField;
	JPasswordField passwordField;
	public String 	username;
	public String 	password;
	UserOperations userOperations = new UserOperations();
	JLabel errorText = new JLabel("");
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel Register() {
		//
		formRegister.setForeground(Color.YELLOW);
		formRegister.setBackground(Color.LIGHT_GRAY);
		formRegister.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		formRegister.setName("Register");
		formRegister.setLayout(null);
		
		JLabel labelUsername = new JLabel("Username :");
		labelUsername.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		labelUsername.setOpaque(true);
		labelUsername.setBackground(Color.GRAY);
		labelUsername.setForeground(Color.WHITE);
		labelUsername.setBounds(34, 91, 72, 41);
		formRegister.add(labelUsername);
		
		JLabel label = new JLabel("");
		label.setBounds(34, 77, 46, 14);
		formRegister.add(label);
		
		textField = new JTextField();
		textField.setBounds(126, 101, 174, 20);
		formRegister.add(textField);
		textField.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		labelPassword.setOpaque(true);
		labelPassword.setForeground(Color.WHITE);
		labelPassword.setBackground(Color.GRAY);
		labelPassword.setBounds(34, 143, 72, 41);
		formRegister.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 153, 174, 20);
		formRegister.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yasir_000\\workspace\\Library Managment System\\images.jpg"));
		lblNewLabel.setBounds(116, 11, 199, 58);
		formRegister.add(lblNewLabel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(336, 229, 89, 23);
		formRegister.add(btnSubmit);
		btnSubmit.setActionCommand("OK");
		
		btnSubmit.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				   btnSubmit.setEnabled(false);
				   if(action() == 1){
				      UserOperations reg = new UserOperations();
				      try {
				    	if(reg.register(username, password)){
				    		setErrorText("Successfully Registered! You may Login",Color.green);
				    	}  
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      
				   }}
			   });
		
		errorText.setBounds(73, 345, 333, 14);
		formRegister.add(errorText);
		formRegister.setVisible(true);
		formRegister.setSize(600,450);
		return formRegister;
	}
	
	public int action() {
		int isTrue = 0;
		if(textField.getText().isEmpty() || passwordField.getPassword().length == 0 ){
			System.out.println("Enter something");
			setErrorText("The password and username fields are mandatory",Color.RED);
		} 
		
		
		else{
			isTrue = 1;
			username = textField.getText();
			password = passwordField.getText();
			setErrorText("",Color.BLACK);
		}
		return isTrue;
	}
	public void setErrorText(String errorMessage,Color color) {
		errorText.setText(errorMessage);
		errorText.setForeground(color);
	}
}
