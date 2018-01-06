package library;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Font;

public class Interface {
	JFrame frmRegister = new JFrame();
	JTextField textField;
	JPasswordField passwordField;
	public String 	username;
	public String 	password;
	Login log = new Login();
	/**
	 * @wbp.parser.entryPoint
	 */
	public void Register() {
		frmRegister.getContentPane().setForeground(Color.YELLOW);
		frmRegister.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmRegister.getContentPane().setFont(new Font("Century Gothic", Font.PLAIN, 14));
		frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\yasir_000\\Downloads\\pixel.png"));
		frmRegister.setTitle("Register");
		frmRegister.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblUsername.setOpaque(true);
		lblUsername.setBackground(Color.GRAY);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(34, 91, 72, 41);
		frmRegister.getContentPane().add(lblUsername);
		
		JLabel label = new JLabel("");
		label.setBounds(34, 77, 46, 14);
		frmRegister.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(126, 101, 174, 20);
		frmRegister.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblPassword.setOpaque(true);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBackground(Color.GRAY);
		lblPassword.setBounds(34, 143, 72, 41);
		frmRegister.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 153, 174, 20);
		frmRegister.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yasir_000\\workspace\\Library Managment System\\images.jpg"));
		lblNewLabel.setBounds(116, 11, 199, 58);
		frmRegister.getContentPane().add(lblNewLabel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(336, 229, 89, 23);
		frmRegister.getContentPane().add(btnSubmit);
		btnSubmit.setActionCommand("OK");
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setActionCommand("OK");
		btnLogIn.setBounds(236, 229, 89, 23);
		frmRegister.getContentPane().add(btnLogIn);
		btnSubmit.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				      action();
				      Librarian lib = new Librarian();
				      try {
						lib.register(username, password);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      
				   }
			   });
		btnLogIn.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				      try {
						log();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      
				   }
			   });

		frmRegister.setVisible(true);
		frmRegister.setSize(600,450);
	}
	
	public void action() {
		if(textField.getText().isEmpty()){
			System.out.println("Enter something");
		}
		
		
		else{
			username = textField.getText();
			password = passwordField.getText();
		}
	}
	public void log() throws IOException {
		log.form();
	}
}
