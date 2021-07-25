import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class Admin {

	DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"1234");
	
	public abstract void admin();
	
	 public int getRowNum(ResultSet rs) {
 		int num = 0;

 		try {
 			rs.last();
 			num = rs.getRow();
 			rs.beforeFirst();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return num;

 	}
	
	
}
