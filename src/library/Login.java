/**
 * 
 */
package library;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import DatabaseOperations.UserOperations;
import main.Main;

import java.awt.FlowLayout;

/**
 * @author yasir_000
 *
 */
public class Login {
	JPanel formLogin;
	JTextField textField;
	JPasswordField passwordField;
	String 	username;
	String 	password;
	JLabel errorText = new JLabel("");
	UserOperations userOperations = new UserOperations();
	static String currentUser;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel getLoginPanel(){
		//Login Form designing
		formLogin = new JPanel();
		formLogin.setForeground(Color.YELLOW);
		formLogin.setBackground(Color.LIGHT_GRAY);
		formLogin.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		formLogin.setName("Login");
		formLogin.setLayout(null);
	
		//Username 
		JLabel labelUsername = new JLabel("Username :");
		labelUsername.setBounds(73, 167, 71, 17);
		labelUsername.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		labelUsername.setOpaque(true);
		labelUsername.setBackground(Color.LIGHT_GRAY);
		labelUsername.setForeground(Color.BLACK);
		formLogin.add(labelUsername);
		
		//Textfield for entering Username
		textField = new JTextField();
		textField.setBounds(182, 167, 86, 20);
		textField.setColumns(10);
		formLogin.add(textField);
		
		//Password
		JLabel labelPassword = new JLabel("Password :");
		labelPassword.setBounds(75, 231, 69, 17);
		labelPassword.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		labelPassword.setOpaque(true);
		labelPassword.setForeground(Color.BLACK);
		labelPassword.setBackground(Color.LIGHT_GRAY);
		formLogin.add(labelPassword);
		
		//textfield for entering Password
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 231, 86, 20);
		formLogin.add(passwordField);
		
			//image for interface
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(182, 11, 207, 120);
			lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yasir_000\\workspace\\Library Managment System\\images.jpg"));
			formLogin.add(lblNewLabel);
		
		//submit button 
		JButton btnSubmit = new JButton("Login");
		btnSubmit.setBounds(309, 260, 97, 23);
		formLogin.add(btnSubmit);
		btnSubmit.setActionCommand("OK");
		
		btnSubmit.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				   checkTextField();
				   
			   } });
				
		formLogin.setSize(600,450);
		
		JLabel lblNotAMember = new JLabel("Not A Member Yet?");
		lblNotAMember.setFont(new Font("Candara", Font.ITALIC, 12));
		lblNotAMember.setBounds(191, 306, 97, 14);
		formLogin.add(lblNotAMember);
		errorText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		//Display error text
		
		errorText.setBounds(73, 345, 333, 14);
		formLogin.add(errorText);
		
		formLogin.setVisible(true);
		return formLogin;
		
	}
	
	public void checkTextField() {
		if(textField.getText().isEmpty() || passwordField.getPassword().length == 0 ){
			System.out.println("Enter something");
			setErrorText("The Username and Password is mandatory");
		}
		
		else{
			username = textField.getText();
			password = passwordField.getText();
			setErrorText("");
			authentication(username, password);
		}
	}
	
	public void setErrorText(String errorMessage) {
		errorText.setText(errorMessage);
		errorText.setForeground(Color.red);
	}
	
	public void authentication(String username, String password) {
		boolean isAuthenticated = userOperations.login(username,password);
		if(isAuthenticated){
			setErrorText("It is authenticated!");
			Main.showPanels("3");
			currentUser = username;
			
		}else{
			setErrorText("Incorrect username or password!");
			
		}
		
	}
}
