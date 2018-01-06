package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DatabaseOperations.UserOperations;
import main.Main;

public class optionInterfaces {
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JTextField textField;
	JPasswordField passwordField;
	JLabel errorText = new JLabel("");
	String username;
	String password;
	Login login = new Login();
	String currentUser;
	
	
	
	public JPanel changeProfile() {
		panel.setForeground(Color.YELLOW);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		//panel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\yasir_000\\Downloads\\pixel.png"));
		panel.setName("Edit Profile");
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Password :");
		lblUsername.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblUsername.setOpaque(true);
		lblUsername.setBackground(Color.GRAY);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(34, 91, 72, 41);
		panel.add(lblUsername);
		
		JLabel label = new JLabel("");
		label.setBounds(34, 77, 46, 14);
		panel.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 101, 174, 20);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yasir_000\\workspace\\Library Managment System\\images.jpg"));
		lblNewLabel.setBounds(116, 11, 199, 58);
		panel.add(lblNewLabel);
		
		JButton btnSubmit = new JButton("Change!");
		btnSubmit.setBounds(336, 137, 89, 23);
		panel.add(btnSubmit);
		btnSubmit.setActionCommand("OK");
		btnSubmit.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				      checkTextField();
				      try {
				    		UserOperations op = new UserOperations();
				    		currentUser = login.currentUser;
							op.changePassword(currentUser,password);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      
				   }
			   });

		errorText.setBounds(76, 241, 333, 14);
		panel.add(errorText);
		
		panel.setVisible(true);
		panel.setSize(600,394);
		return panel;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel addBooks() {
		panel2.setName("Search Books");
		panel2.setForeground(Color.YELLOW);
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Search For Books");
		lblNewLabel_1.setBounds(87, 48, 46, 14);
		panel2.add(lblNewLabel_1);
		panel2.setName("Search Books");
		panel2.setVisible(true);
		return panel2;
		}
	public void setErrorText(String errorMessage) {
		errorText.setText(errorMessage);
		errorText.setForeground(Color.red);
	}
	public void checkTextField() {
		if(passwordField.getPassword().length == 0 ){
			System.out.println("Enter something");
			setErrorText("The new Password is mandatory");
		}
		else{
			password = passwordField.getText();
			setErrorText("");
		}
	}
}
