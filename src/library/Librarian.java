package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Librarian {
	public String 	username;
	public String 	password;
	JFrame frmRegister = new JFrame();
	JTextField textField;
	JPasswordField passwordField;
	
	
public void searchedBook() {
		
	}
public void verifiedMember() {
	
}
public void IssuedBook() {
	
}
public void calculateFine() {
	
}	
public void createdBill() {
	
}
public void returnedBook() {
	
}


public void edit(String user,String pass) throws Exception {
	MemberRecord rec = new MemberRecord();
	String q = rec.query();
	//String pass1 = new String(computeHash(pass));
	Connection connector = ConnectDB.getConnection();
	Statement st = connector.createStatement();
	Statement st2 = connector.createStatement();
	//String query = String.format("INSERT INTO librarian (name, password) VALUES ('%s', '%s')", user,pass1);
	String query = "UPDATE librarian SET name = '"+user+"' WHERE name = '"+q+"'";
	String query2 = "UPDATE librarian SET password = '"+pass+"' WHERE name = '"+q+"'";
	System.out.println(query);
	System.out.println(query2);
	st.executeUpdate(query);
	st2.executeUpdate(query2);
}

public static byte[] computeHash(String x)
		  throws Exception
		  {
		     java.security.MessageDigest d =null;
		     d = java.security.MessageDigest.getInstance("SHA-1");
		     d.reset();
		     d.update(x.getBytes());
		     return  d.digest();
		  }
public void changeProfile() {
	frmRegister.getContentPane().setForeground(Color.YELLOW);
	frmRegister.getContentPane().setBackground(Color.LIGHT_GRAY);
	frmRegister.getContentPane().setFont(new Font("Century Gothic", Font.PLAIN, 14));
	frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\yasir_000\\Downloads\\pixel.png"));
	frmRegister.setTitle("Edit Profile");
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
	
	JButton btnSubmit = new JButton("Change!");
	btnSubmit.setBounds(335, 227, 89, 23);
	frmRegister.getContentPane().add(btnSubmit);
	btnSubmit.setActionCommand("OK");
	btnSubmit.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent ae){
			      action();
			      try {
					edit(username, password);
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

}
