package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import library.Dashboard;
import library.Login;
import library.Login;
import library.Register;
import library.optionInterfaces;

public class Main {
	Dashboard dashboard = new Dashboard();
	optionInterfaces face = new optionInterfaces();
	JFrame frame = new JFrame("CardLayout demo");
	static JPanel panelCont = new JPanel();
	JPanel panelFirst;
	JPanel panelSecond;
	JPanel dashboardPanel;
	JPanel changePassword;
	JButton registerBtn = new JButton("Register");
	JButton loginBtn = new JButton("Login");
	JButton logOutBtn = new JButton("Log out");
	JButton dashboardBtn = new JButton("Dashboard");
	static CardLayout cl = new CardLayout();
	
	public Main() {
		// get the screen size as a java dimension
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// get 2/3 of the height, and 2/3 of the width
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;

		// set the jframe height and width
		frame.setPreferredSize(new Dimension(width, height));
		dashboardPanel = dashboard.dashboardGui();
		changePassword = face.changeProfile();
		Login loginPanel = new Login(); 
		Register registerPanel = new Register();
		panelFirst = loginPanel.getLoginPanel();
		panelSecond = registerPanel.Register();
		registerBtn.setBounds(309,303,123,23);
		loginBtn.setBounds(336,263,89,23);
		dashboardBtn.setBounds(336,171,101,23);
		panelCont.setLayout(cl);
		panelFirst.add(registerBtn);
		panelSecond.add(loginBtn);
		dashboardPanel.add(logOutBtn);
		changePassword.add(dashboardBtn);
		panelCont.add(panelFirst, "1");
		panelCont.add(panelSecond, "2");
		panelCont.add(dashboardPanel, "3");
		panelCont.add(changePassword, "4");
		cl.show(panelCont,"1");
		
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "2");
				}
			});
		
		loginBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cl.show(panelCont, "1");
			}
		});
	
		logOutBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cl.show(panelCont, "1");
			}
		});
		
		dashboardBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCont, "3");
				}
			});
		
		
		
		frame.getContentPane().add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	public static void showPanels(String panelNum){
			cl.show(panelCont, panelNum);
		}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}
	
}
