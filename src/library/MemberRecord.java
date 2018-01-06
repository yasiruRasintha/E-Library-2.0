package library;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.Color;

class MemberRecord extends JFrame {

	ArrayList columnNames = new ArrayList();
	ArrayList data = new ArrayList();
	JTable table;
	int memberId;
	String type;
	Date dateOfMemberShip;
	int noOfBooksIssue;
	int maxBookLimit;
	String name;
	String address;
	String part1;
	String part2;
	int count;
	String emmy = "";
	String em = "";
	int bill;
	int noOfWeeks;

	public void retrieveMember() {

	}

	public void showBooks(String note) throws SQLException {
		Connection connector = ConnectDB.getConnection();
		Statement st = connector.createStatement();
		String query = note;
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			columnNames.add(md.getColumnName(i));
		}
		while (rs.next()) {
			ArrayList row = new ArrayList(columns);
			for (int i = 1; i <= columns; i++) {
				row.add(rs.getObject(i));
			}

			data.add(row);
		}
		Vector columnNamesVector = new Vector();
		Vector dataVector = new Vector();

		for (int i = 0; i < data.size(); i++) {
			ArrayList subArray = (ArrayList) data.get(i);
			Vector subVector = new Vector();
			for (int j = 0; j < subArray.size(); j++) {
				subVector.add(subArray.get(j));
			}
			dataVector.add(subVector);
		}

		for (int i = 0; i < columnNames.size(); i++)
			columnNamesVector.add(columnNames.get(i));

		// Create table with database data
		table = new JTable(dataVector, columnNamesVector) {
			public Class getColumnClass(int column) {
				for (int row = 0; row < getRowCount(); row++) {
					Object o = getValueAt(row, column);

					if (o != null) {
						return o.getClass();
					}
				}

				return Object.class;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		// increaseBookIssued();
	}

	

	public void query1(String book) throws SQLException {
		Connection connector = ConnectDB.getConnection();
		query();
		String query = "SELECT book1 FROM librarian WHERE name = '" + emmy + "'";
		Statement st = connector.createStatement();
		ResultSet q = st.executeQuery(query);
		if (q.next()) {
			em = q.getString(1);
			System.out.println(em);
			if (em != null) {
				System.out.println("You got past the first IF");
				String query1 = "SELECT book2 FROM librarian WHERE name = '" + emmy + "'";
				Statement st1 = connector.createStatement();
				ResultSet q1 = st1.executeQuery(query1);
				String em1 = "";
				if (q1.next()) {
					em1 = q1.getString(1);
					if (em1 != null) {
						System.out.println("You got past the second IF");
						String query2 = "SELECT book3 FROM librarian WHERE name = '" + emmy + "'";
						Statement st2 = connector.createStatement();
						ResultSet q2 = st2.executeQuery(query2);
						String em2 = "";
						if (q2.next()) {
							em2 = q2.getString(1);
							if (em2 != null) {
								System.out.println("You got past the third IF");
								System.out.println("You have reached the limit for borrowing books!");
							} else {
								count++;
								System.out.println("Wow 3");
								String sql = "UPDATE librarian SET book3 = '" + book + "' WHERE name = '" + emmy + "'";
								Statement st3 = connector.createStatement();
								int eee = st3.executeUpdate(sql);
							}
						}

					} else {
						count++;
						System.out.println("Wow 2");
						String sql2 = "UPDATE librarian SET book2 = '" + book + "' WHERE name = '" + emmy + "'";
						Statement state2 = connector.createStatement();
						int ee = state2.executeUpdate(sql2);
					}
				}
			} else {
				count++;
				System.out.println("Wow 1");
				String sql1 = "UPDATE librarian SET book1 = '" + book + "' WHERE name = '" + emmy + "'";
				Statement state1 = connector.createStatement();
				int e = state1.executeUpdate(sql1);
			}
		}
	}

	public void increaseBookIssued() {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting()) {
					// do some actions here, for example
					String bookID = table.getValueAt(table.getSelectedRow(), 0).toString();
					System.out.println("Nice!!");
					try {
						query1(bookID);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void decreaseBookIssued() throws SQLException {
		String username = query();
		String note = "SELECT book1,book2,book3 FROM librarian WHERE name = '" + username + "'";
		showBooks(note);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting()) {
					int bookID = table.getSelectedColumn();
					String row = "";
					if (bookID == 1) {
						row = "book1";
					} else if (bookID == 2) {
						row = "book2";
					} else {
						row = "book3";
					}
					System.out.println("You have selected a column:" + row);
					Connection connector = ConnectDB.getConnection();
					String query = "UPDATE librarian SET " + "row" + "= null WHERE name  = '" + username + "'";
					try {
						PreparedStatement st = connector.prepareStatement(
								"UPDATE librarian SET " + row + "= null WHERE name  = '" + username + "'");
						int q = st.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public int payBill() {
		int fine;
		fine = bill * noOfWeeks;
		return fine;
	}

	public void billGui() {
		int x;
		JFrame frame = new JFrame();
		frame.setTitle("Bill");
		frame.getContentPane().setLayout(null);
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3" }));
		bill = comboBox.getSelectedIndex();
		comboBox.setEditable(true);
		comboBox.setToolTipText("1");
		comboBox.setBounds(151, 25, 47, 39);
		frame.getContentPane().add(comboBox);

		JLabel lblNoOfBooks = new JLabel("No of books");
		lblNoOfBooks.setFont(new Font("Stencil", Font.BOLD, 14));
		lblNoOfBooks.setBounds(33, 31, 106, 26);
		frame.getContentPane().add(lblNoOfBooks);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "1 week", "2 weeks", "3 weeks" }));
		noOfWeeks = comboBox_1.getSelectedIndex();
		comboBox_1.setBounds(151, 84, 64, 40);
		frame.getContentPane().add(comboBox_1);

		JLabel lblNewLabel = new JLabel("No of weeks");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 15));
		lblNewLabel.setBounds(35, 84, 93, 40);
		frame.getContentPane().add(lblNewLabel);

		x = payBill();
		JLabel lblNewLabel_1 = new JLabel("Bill:" + x);
		lblNewLabel_1.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(168, 156, 103, 45);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\yasir_000\\workspace\\Library Managment System\\bill.jpg"));
		lblNewLabel_2.setBounds(37, 143, 113, 90);
		frame.getContentPane().add(lblNewLabel_2);
		frame.setVisible(true);
	}
}
