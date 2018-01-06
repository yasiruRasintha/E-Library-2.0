package DatabaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.api.mysqla.result.Resultset;

public class UserOperations {
	 Connection connector;
	public UserOperations() {
		  connector =  ConnectDB.getConnection();
		  System.out.println("Conneted");
	}
	
	public boolean login(String username,String password) {
		 try {
				Statement st = connector.createStatement();
				String query = "SELECT password FROM user WHERE username = '" + username + "'";
				ResultSet q = st.executeQuery(query);
				String realPassword = "";
				
			if(q.next()){	
				realPassword = q.getString(1);
				if(realPassword.equals(password)){
					System.out.println(realPassword);
					System.out.println("success");
					return true;
				}
				else {
					System.out.println("Sorry your password did not work!");
					return false;
				}
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
		
	}
	
	public boolean register(String user,String pass) throws Exception {
		Statement st = connector.createStatement();
		String query = String.format("INSERT INTO user (username, password) VALUES ('%s', '%s')", user,pass);
		System.out.println(query);
		if(st.executeUpdate(query) == 1){
			return true;
		} 
		return false;
	}
	

	
	public boolean changePassword(String user,String pass) throws Exception {
		Statement st = connector.createStatement();
		String query = "UPDATE user SET password = '"+pass+"' WHERE name = '"+user+"'";
		System.out.println(query);
		if(st.executeUpdate(query) == 1){
			return true;
		} 
		return false;
	}
	
	public ResultSet searchBooks(String name) throws SQLException{
		System.out.println("user op");
		String sql = "SELECT  idbooks,name, availability FROM books WHERE name LIKE ?";
		PreparedStatement preparedStatement = connector.prepareStatement(sql);
		preparedStatement.setString(1, "%" + name + "%");
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet;
		}
	
	public boolean addBooks(int bookid,int userid) throws SQLException{
		Statement updateBorrowings = connector.createStatement();
		String insertBookId = String.format("INSERT INTO borrowings (bookid,userid,date) VALUES ('%s', '%s', NOW())" ,bookid,userid);
		System.out.println(insertBookId);
		if(updateBorrowings.executeUpdate(insertBookId) == 1){
			return true;
		}
		return false; 
		}
	
}
	

