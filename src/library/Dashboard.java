package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DatabaseOperations.UserOperations;
import main.Main;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class Dashboard extends JFrame{
	optionInterfaces optionInstance = new optionInterfaces();
	UserOperations userOperations = new UserOperations();
	private JTextField textField;
	ArrayList columnNames = new ArrayList();
	ArrayList data = new ArrayList();
	JTable table = new JTable();
	JFrame tableContainer = new JFrame();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel dashboardGui() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 2 / 3;
		JPanel dashboard = new JPanel();
		dashboard.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dashboard.setBackground(Color.LIGHT_GRAY);
		JSearchTextField searchBar = new JSearchTextField();
		dashboard.setPreferredSize(new Dimension(width, height));
		dashboard.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		dashboard.setName("Options");
		dashboard.setLayout(null);
		
		JButton btnPayBill = new JButton("Pay Bill");
		btnPayBill.setForeground(Color.BLACK);
		btnPayBill.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 14));
		btnPayBill.setBounds(10, 96, 131, 37);
		dashboard.add(btnPayBill);
		btnPayBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//jar.billGui();
			}
		});
		
		JButton btnChangeProfile = new JButton(" Profile");
		btnChangeProfile.setForeground(Color.BLACK);
		btnChangeProfile.setFont(new Font("Rockwell Extra Bold", Font.BOLD | Font.ITALIC, 14));
		btnChangeProfile.setBounds(10, 131, 131, 37);
		dashboard.add(btnChangeProfile);
		
		JPanel panel = new JPanel();
		panel.setBounds(184, 0, width,height);
		dashboard.add(panel);
		panel.setLayout(null);
		//panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(136, 44, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(232, 43, 65, 23);
		panel.add(searchBtn);
		dashboard.setVisible(true);
		
		btnChangeProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.showPanels("4");
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = textField.getText();
				try {
					ResultSet tableData = userOperations.searchBooks(search);
					showBooks(tableData);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		return dashboard;
	}
	
	public void showBooks(ResultSet tableData) throws SQLException {
		DefaultTableModel dm = new DefaultTableModel();
		
		ResultSetMetaData md = tableData.getMetaData();
		int columns = md.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			columnNames.add(md.getColumnName(i));
			
		}
		columnNames.add("Button");
				
		Object[][] resultSet2 = new Object[4][columns];
        int row1 = 0;
        if (tableData.next()) {
        	do {
	            for (int i = 0; i < columns; i++) {
	                resultSet2[row1][i] = tableData.getObject(i+1);
	              
	            }
	            row1++;
        	} while(tableData.next());
        }
		
	    dm.setDataVector( resultSet2, columnNames.toArray());
	    //table.repaint();
	    JTable table = new JTable(dm);
	    table.getColumn("Button").setCellRenderer(new ButtonRenderer());
	    table.getColumn("Button").setCellEditor(
	        new ButtonEditor(new JCheckBox(),12));
	    JScrollPane scroll = new JScrollPane(table);
	    getContentPane().add(scroll);
	    setSize(400, 100);
	    setVisible(true);
	    tableContainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
			
		

	}
	
	public void failure() {
		JFrame frmIncorrect = new JFrame();
		frmIncorrect.setSize(600,450);
		frmIncorrect.setBackground(Color.WHITE);
		frmIncorrect.setTitle("Incorrect");
		frmIncorrect.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\yasir_000\\workspace\\Library Managment System\\cross-512.png"));
		lblNewLabel.setBounds(54, 66, 97, 110);
		frmIncorrect.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Password Incorrect");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		lblNewLabel_2.setBounds(190, 66, 230, 110);
		frmIncorrect.getContentPane().add(lblNewLabel_2);
		frmIncorrect.setVisible(true);
	}
}
